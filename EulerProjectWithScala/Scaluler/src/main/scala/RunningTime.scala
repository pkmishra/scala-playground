/**
 * Created by IntelliJ IDEA.
 * User: pmishra
 * Date: 25/8/11
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */

  
  object RunningTime {
  def apply[T](name: String)(block: => T) {
    val start = System.currentTimeMillis
    try {
        block
    } finally {
        val diff = System.currentTimeMillis - start
        println("# Block \"" + name +"\" completed, time taken: " + diff + " ms (" + diff / 1000.0 + " s)")
    }
  }
}

