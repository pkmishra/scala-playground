
/**
 * Created by IntelliJ IDEA.
 * User: pmishra
 * Date: 24/8/11
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
/** Problem # 1 Text
If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
The sum of these multiples is 23.
Find the sum of all the multiples of 3 or 5 below 1000.
**/

import org.specs2.mutable._
import org.specs2.ScalaCheck

class Problem1Spec extends Specification with ScalaCheck {

    "Sum of all numbers below 10 that are multiple of 3 or 5" should {
      "be 23" in {
        new Problem1().Solve(10) must_== 23
        new Problem1().SolveFromInternet(10) must_== 23
      }
    }

  "Sum of all numbers below 1000 that are multiple of 3 or 5" should {
    "be 233168" in {
      new Problem1().Solve(1000) must_== 233168
      new Problem1().SolveFromInternet(1000) must_== 233168
    }
  }
}