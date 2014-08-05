package explorations.futures

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

object MapAndFlatMap {
  
  def factorialR(n: Int): Int = if(n < 1) 1 else n * factorialR(n-1) 
  
  def main(args: Array[String]) = {	    
    
    // MADNESS: Imagine if you keep nesting...
    val iHaveAFutureButIWantAnInt: Future[Int] = Await.result(factorialOfFactorialWithProblem(3), 2 seconds)
    val nowIHaveTheInt: Int = Await.result(iHaveAFutureButIWantAnInt, 2 seconds)
    println(nowIHaveTheInt)
    
    // This is not ok because the implementation blocks
    val theImplementationBlocks = Await.result(factorialOfFactorialBlocking(3), 2 seconds)
    println(theImplementationBlocks)
    
    // THE SOLUTION
    val theImplementationDoesntBlock = Await.result(factorialOfFactorialFlatMap(3), 2 seconds)
    println(theImplementationDoesntBlock)
  }

  def factorial(n: Int) = Future { (1 to n).reduce(_ * _) }

  // I wanted Future[Int] and I'm stuck with Future[Future[Int]]. The caller has to deal with all the madness
  def factorialOfFactorialWithProblem(n: Int): Future[Future[Int]] = {
    factorial(n).map((x) => factorial(x))
  }

  // I solved the problem but it blocks
  def factorialOfFactorialBlocking(n: Int): Future[Int] = {
    factorial(n).map((x) => Await.result(factorial(x), 1 second))
  }

  // The solution is to use flatMap
  def factorialOfFactorialFlatMap(n: Int): Future[Int] = {
    factorial(n).flatMap(factorial(_))
  }
}