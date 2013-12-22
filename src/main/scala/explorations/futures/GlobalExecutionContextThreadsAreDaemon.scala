package explorations.futures

import scala.concurrent._
import ExecutionContext.Implicits.global

object Example1 {
	def main(args: Array[String]): Unit = {
	  
	  val f = future {
	    val v = (1 to 20000).reduce(_+_)
	    
	    println(v)
	  }
	  
	  /* 
	   * The threads of global execution context are daemon threads,
	   * so the execution of future f does not prevent the program from terminating
	   * 
	   * https://github.com/scala/scala/blob/master/src/library/scala/concurrent/impl/ExecutionContextImpl.scala#L35
	   */
	}
}