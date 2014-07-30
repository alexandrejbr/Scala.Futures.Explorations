package explorations.futures

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{Success, Failure}
import scala.io.StdIn

object CallbacksOnComplete {
  def main(args: Array[String]): Unit = {
    val f = Future {
      // 3/0 // To provoke an exception
      (1 to 20000).reduce(_ + _)
    }
    
    f onComplete {
      case Success(v) => println(v)
      case Failure(e) => println("An error has occured:" + e.getMessage) // executed in case of exception
    }
    
    StdIn.readLine
  }

}