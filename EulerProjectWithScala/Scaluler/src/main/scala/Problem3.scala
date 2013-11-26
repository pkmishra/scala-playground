/**
 * Created by IntelliJ IDEA.
 * User: pmishra
 * Date: 25/8/11
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */

/*
The prime factors of 13195 are 5, 7, 13 and 29.
What is the largest prime factor of the number 600851475143 ?
*/

class Problem3 {

  def SolveFromInternet(i: Double): Int ={
    lazy val ps: Stream[Int] = 2 #:: Stream.from(3).filter(i =>
      ps.takeWhile(j => j * j <= i).forall(i % _ > 0))
    val limit = math.sqrt(i)
    return ps.view.takeWhile(_ < limit).filter(i % _ == 0).last
  }


  def Solve(i: Double): Int = {
    var maxfactor = 0
    var num = i
    if( num % 2 == 0){
      maxfactor = 2
      while(num % 2 == 0)
       num = num /2
    }
    else
      maxfactor = 1
    var factor = 3
    //Theorem:every number n can at most have one prime factor greater than  sqrt(n)
    var limit = math.sqrt(num)
    while(num > 1 && factor <= limit){
      if(num % factor == 0){
        num = num / factor
        maxfactor = factor
        while(num % factor == 0)
          num = num / factor
        limit = math.sqrt(num)
      }
      factor += 2
    }
    if (num == 1)
      return maxfactor
    else
      return num.toInt

    /* require(i > 2)
    var n = 2
    while (n <= num && num > 1) {
      if (num % n == 0) {
        if (n > max)
          max = n
        num = (num / n)
        n = n - 1
      }

      n = n + 1
    }
    return max */
  }
}