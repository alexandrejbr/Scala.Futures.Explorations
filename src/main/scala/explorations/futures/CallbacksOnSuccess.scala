package explorations.futures

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.io.StdIn

object CallbacksOnSuccess {

  def main(args: Array[String]): Unit = {
    val f = Future {
      3/0 // To provoke an exception
    }
    
    f.onSuccess {
      /* 
       * This will not be executed because the execution of future f throws
       * an exception. The interesting thing to notice is that there isn't
       * anything that indicates that an exception was throws inside the future
       */
      case v => println(v)  
    }
    
    
    /* 
     * "Since partial functions have the isDefinedAt method, 
     * the onFailure method only triggers the callback if it is defined for a particular Throwable."
     */
    f.onFailure {
      case e => println(e.getMessage)
    }
    
    StdIn.readLine
  }

}