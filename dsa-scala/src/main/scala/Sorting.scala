package io.github.pkmishra.dsascala

class Sorting[T] {
    def insertionSort(input: Array[T], f: (T, T) => Boolean) = {
      for (i <- 0 to input.length - 1) {
        insert(input, i, input(i), f)
      }
      input.foreach(print)
      def insert(input: Array[T], itemPosition: Int, item: T, f: (T, T) => Boolean) = {
        var pos = itemPosition
        while (pos > 0 && f(item, input(pos - 1))) {
          input(pos) = input(pos - 1)
          pos = pos - 1
        }
        input(pos) = item
      }
    }

  def quickSort(input: Array[T], f: (T, T) => Boolean) = {

    quickSortInternal( 0, input.length - 1)
    input.foreach(println)
    def quickSortInternal(left: Int, right: Int): Any = {
      val pi = partition( left, right)
      if (left < pi - 1)
        quickSortInternal(left, pi - 1)
      if (pi < right)
        quickSortInternal( pi, right)

    }
    def partition(left: Int, right: Int) = {

      val pivot = input((left + right) / 2)
      var i = left
      var j = right
      while (i <= j) {
        while (f(input(i), pivot)) {
          i = i + 1
        }
        while (f(pivot, input(j))) {
          j = j - 1
        }
        if (i <= j) {
          val tmp = input(i)
          input(i) = input(j)
          input(j) = tmp
          i = i + 1
          j = j - 1
        }
      }
      i
    }
  }

  def selectionSort(input: Array[T], f: (T, T) => Boolean) = {
    for (i <- 0 to input.length - 1) {
      var min = i
      for (j <- i + 1 to input.length - 1) {
        if (f(input(j), input(min))) {
          min = j
        }
      }
      if (min != i) {
        val tmp = input(i)
        input(i) = input(min)
        input(min) = tmp
      }
    }
    input.foreach(println)
  }


  def heapSort(input: Array[T], f: (T, T) => Boolean) = {

    buildHeap()

    for (i <- input.length - 1 to 1 by -1) {
      val tmp = input(0)
      input(0) = input(i)
      input(i) = tmp
      heapify(0, i)
    }
    input.foreach(println)
    def buildHeap() = {
      for (i <- input.length / 2 - 1 to 0 by -1) {
        heapify(i, input.length)
      }

    }
    def heapify(i: Int, max: Int): Any = {
      val left = 2 * i + 1
      val right = 2 * i + 2
      var largest = 0
      if (left < max && f(input(i), input(left)))
        largest = left
      else
        largest = i
      if (right < max && f(input(largest), input(right)))
        largest = right
      //println(largest)
      if (largest != i) {
        val tmp = input(i)
        input(i) = input(largest)
        input(largest) = tmp
        heapify(largest, max)
      }
    }
  }

  def mergeSort(input: Array[T], f: (T, T) => Boolean) = {
    if (input.length <= 1)
      input
    do_mergeSort(0, input.length-1)
    input.foreach(println)

    def do_mergeSort(i: Int, j: Int): Any = {
      var p = 0
      if (i < j) {
        p = (i + j) / 2
        do_mergeSort(i, p)
        do_mergeSort(p + 1, j)
        do_merge(i, p, j)
      }
    }
    def do_merge(left: Int, mid: Int, right: Int) = {
      val sorted = input.clone().take(right-left+1)
      var pos = 0
      var lpos = left
      var rpos = mid + 1
      while (lpos <= mid && rpos <= right) {
        if (f(input(lpos), input(rpos))) {
          sorted(pos) = input(lpos)
          pos += 1
          lpos += 1
        }
        else {
          sorted(pos) = input(rpos)
          pos += 1
          rpos += 1
        }
      }
      while (lpos <= mid) {
        sorted(pos) = input(lpos)
        pos += 1
        lpos += 1
      }
      while (rpos <= right) {
        sorted(pos) = input(rpos)
        pos += 1
        rpos += 1
      }
      for(i <- 0 until pos){
        input(i+left) = sorted(i)
      }

    }



  }
  def bubbleSort(input: Array[T], f: (T, T) => Boolean) = {

    for(i <- 0 until input.length) {
      for(y <- 0 until input.length-1){
        if(f(input(y+1), input(y))){
          val temp = input(y+1)
          input(y+1) = input(y)
          input(y) = temp
        }
      }
    }
    input.foreach(println)

  }
  def shellSort(input: Array[T], f: (T, T) => Boolean) = {

    var d = input.length
    do {
      d = (d+1)/2
      for(y <- 0 until input.length-d){
        if(f(input(y+d), input(y))){
          val temp = input(y+d)
          input(y+d) = input(y)
          input(y) = temp
        }
      }
    } while( d > 1)
    input.foreach(println)

  }

  def radixSort(input: Array[Int], f: (Int, Int) => Boolean) = {


  }

}
