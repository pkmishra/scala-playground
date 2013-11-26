/**
 * Created by IntelliJ IDEA.
 * User: pmishra
 * Date: 24/8/11
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
/*
The prime factors of 13195 are 5, 7, 13 and 29.
What is the largest prime factor of the number 600851475143 ?
*/

import org.specs2.mutable._
import org.specs2.ScalaCheck

class Problem4Spec extends Specification with ScalaCheck {

  "Largest prime factor of number 13195" should {
    "be 29" in {
      val p = new Problem3
      p.Solve(13195) must_== 29
      p.SolveFromInternet(13195) must_== 29
    }
  }

  "Largest prime factor of number 600851475143" should {
    "be 6857" in {
      val p = new Problem3
      p.Solve(600851475143L) must_== 6857
      p.SolveFromInternet(600851475143L) must_== 6857
    }
  }
}