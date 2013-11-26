/**
 * Created by IntelliJ IDEA.
 * User: pmishra
 * Date: 24/8/11
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */


  object HelloWorld {
    def main(args: Array[String]) {
      println("This is just a test")
    }
  }
  class Upper {
  def factorial(x: BigInt): BigInt =
    if (x == 0) 1 else x * factorial(x - 1)
}