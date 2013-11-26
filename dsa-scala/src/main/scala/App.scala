package io.github.pkmishra.dsascala

import org.slf4j.LoggerFactory

object App {
  def main(args: Array[String]) {
    val logger = LoggerFactory.getLogger("App")
    logger.info("Hello io.github.pkmishra.dsa-scala!")
    val sorting = new Sorting[Int]
    //sorting.insertionSort(Array(5,4,10, 2,1,9, 8, 5), (i1:Int, i2:Int) => i1 < i2)
    //sorting.shellSort(Array(5,4,10, 2,1,9, 8, 5), (i1:Int, i2:Int) => i1 < i2)
    val sorting2 = new Sorting[String]
    //sorting2.mergeSort(Array("abc","xabc","xyz", "ttk","test"), (i1:String, i2:String) => i1.compare(i2) < 0)

    val sorting3 = new FunctionalSorting[Int]
    sorting3.selectionSort(List(5,4,10, 2,1,9, 8, 5)).foreach(println)
    new FunctionalSorting[Int].Heap.heapSort(List(5,4,10, 2,1,9, 8, 5)).foreach(println)
  }

}
