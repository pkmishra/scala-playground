import org.scalacheck._
/**
 * Created by IntelliJ IDEA.
 * User: pmishra
 * Date: 24/8/11
 * Time: 2:01 PM
 * To change this template use File | Settings | File Templates.
 */

/** Problem # 1 Text
If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
The sum of these multiples is 23.
Find the sum of all the multiples of 3 or 5 below 1000.
**/

class Problem1 {

  def Solve(input : Int): Int ={
    var sum = 0
    for (number <- 1 until input by 1 if (number%3 == 0 || number%5==0))
     sum +=number
    return sum
    }
  def SolveFromInternet(input : Int): Int = {
    return (1 until input).filter(n => n % 3 == 0 || n % 5 == 0).sum
  }
  }