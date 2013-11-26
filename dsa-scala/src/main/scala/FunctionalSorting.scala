package io.github.pkmishra.dsascala

class FunctionalSorting[T] {

  /*
   *  T can be treated as an Ordered[T] with its view bound, T <% Ordered[T].
   */
  def insertionSort[T <% Ordered[T]](list: List[T]): List[T] = {
    def sort(runningList: List[T], sortedList: List[T]): List[T] = runningList match {
      case head :: tail => sort(tail, insert(head, sortedList))
      case Nil => sortedList
    }

    def insert(item: T, sortedList: List[T]): List[T] = sortedList match {
      case head :: tail if (item > head) => head :: insert(item, tail)
      case _ => item :: sortedList
    }

    sort(list, Nil)
  }

  def quickSort[T <% Ordered[T]](input: List[T]): List[T] = {

    def sort(t: (List[T], T, List[T])): List[T] = t match {
      case (Nil, pivot, Nil) => List(pivot)
      case (l, p, r) => partitionAndSort(l) ::: (p :: partitionAndSort(r))
    }

    def partition(pList: List[T]): (List[T], T, List[T]) = {

      def partitionInternal(pivot: T, rlist: List[T], left: List[T], right: List[T]): (List[T], T, List[T]) = rlist match {

        case Nil => (left, pivot, right)
        case head :: tail => if (head < pivot)
          partitionInternal(pivot, tail, head :: left, right)
        else
          partitionInternal(pivot, tail, left, head :: right)
      }
      partitionInternal(pList.head, pList.tail, Nil, Nil)

    }

    def partitionAndSort(l: List[T]): List[T] = {
      if (l.isEmpty)
        Nil
      else
        sort(partition(l))
    }
    partitionAndSort(input)
  }

  def selectionSort[T <% Ordered[T]](input: List[T]): List[T] = {

    def sort(input: List[T], sorted: List[T]): List[T] = input match {
      case Nil => sorted
      case head :: tail => selection(head, tail, Nil, sorted)
    }

    def selection(min: T, unsorted: List[T], zs: List[T], sorted: List[T]): List[T] =
      unsorted match {
        case head :: tail => //Find Min element recursively
          if (head <= min) selection(min, tail, head :: zs, sorted)
          else selection(head, tail, min :: zs, sorted)
        case Nil => //Found the min element.. add it to sorted list
          sort(zs, min :: sorted)
      }
    sort(input, Nil)
  }

  sealed abstract class Heap[+T] {
    def rank: Int
  }

  case object EmptyHeap extends Heap[Nothing] {
    def rank = 0
  }

  case class NonEmptyHeap[T](rank: Int, element: T, left: Heap[T], right: Heap[T]) extends Heap[T]

  object Heap {
    def apply[T](x: T): Heap[T] =
      this(x, EmptyHeap, EmptyHeap)

    def apply[T](x: T, a: Heap[T], b: Heap[T]): Heap[T] =
      if (a.rank > b.rank)
        NonEmptyHeap(b.rank + 1, x, a, b)
      else
        NonEmptyHeap(a.rank + 1, x, b, a)

    def merge[T <% Ordered[T]](a: Heap[T], b: Heap[T]): Heap[T] =
      (a, b) match {
        case (x, EmptyHeap) => x
        case (EmptyHeap, x) => x
        case (x: NonEmptyHeap[T], y: NonEmptyHeap[T]) =>
          if (x.element >= y.element)
            Heap(x.element, x.left, merge(x.right, y))
          else
            Heap(y.element, y.left, merge(x, y.right))
      }

    def toList[T <% Ordered[T]](heap: Heap[T]) =
      toListWithMemory(List(), heap)

    def toListWithMemory[T <% Ordered[T]](memo: List[T], heap: Heap[T]): List[T] =
      heap match {
        case EmptyHeap => memo
        case x: NonEmptyHeap[T] =>
          toListWithMemory(x.element :: memo, merge(x.left, x.right))
      }

    def heapSort[T <% Ordered[T]](xs: Seq[T]): Seq[T] =
      toList(xs.foldLeft(EmptyHeap: Heap[T])((memo, x) => merge(Heap(x), memo)))
  }


}

