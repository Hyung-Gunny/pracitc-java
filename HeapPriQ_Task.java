package chap07_Homework3_22112204_박형건;
/*
 * 파일명: chap07_Homework3_22112204_박형건 
 * 프로그램의 목적 및 기능 : 완전이진트리 구조의 HeapPriorityQueue_Task 구현 
 * - class Heappriq_task
 * 프로그램 작성자 : 박형건 (2023/10/21)
 * 학번 : 22112204
 * =======================
 * 수정일: 2023.10.21
 * 최종 수정일: 2023.10.26
 * =======================
 */


public class HeapPriQ_Task extends CompleteBinaryTree<Task>{

	public HeapPriQ_Task(String string, int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}
	public int insert(Task task) {
        if (size == capacity) {
            return -1; // Queue is full
        }

        int currentIndex = size;
        genArray[currentIndex] = task;
        size++;

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            Task parent = (Task) genArray[parentIndex];

            if (task.getPriority() < parent.getPriority()) {
                // Swap task and parent
                genArray[currentIndex] = parent;
                genArray[parentIndex] = task;
                currentIndex = parentIndex;
            } else {
                break;
            }
        }

        return 0; // Successful insertion
    }

	public Task removeHeapMin() {
	    if (isEmpty()) {
	        return null; // Queue is empty
	    }

	    Task minTask = (Task) genArray[0];
	    genArray[0] = genArray[size - 1]; // Move the last element to the root
	    size--;

	    int currentIndex = 0;

	    while (true) {
	        int leftChildIndex = 2 * currentIndex + 1;
	        int rightChildIndex = 2 * currentIndex + 2;
	        int smallestChildIndex = -1;

	        if (leftChildIndex < size) {
	            smallestChildIndex = leftChildIndex;

	            if (rightChildIndex < size) {
	                Task leftChild = (Task) genArray[leftChildIndex];
	                Task rightChild = (Task) genArray[rightChildIndex];

	                if (leftChild.compareTo(rightChild) > 0) {
	                    smallestChildIndex = rightChildIndex;
	                }
	            }

	            Task smallestChild = (Task) genArray[smallestChildIndex];

	            if (minTask.compareTo(smallestChild) > 0) {
	                // Swap smallestChild and current
	                genArray[currentIndex] = smallestChild;
	                genArray[smallestChildIndex] = minTask;
	                currentIndex = smallestChildIndex;
	            } else {
	                break;
	            }
	        } else {
	            break;
	        }
	    }

	    return minTask;
	}



}

