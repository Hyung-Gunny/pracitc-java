 










 
Homework 7.1 동적프로그래밍 기반 배낭문제, 파일 입출력
(1) Solution (java 프로그램 소스코드, 반드시 주석문 포함할 것)
package chap07_Homework1_22112204_박형건;
/*
 * 파일명: chap07_Homework1_22112204_박형건 
 * 프로그램의 목적 및 기능 : 동적프로그래밍 기반 배낭 문제, 파일 입출력 
 * - main 부분이다.
 * 프로그램 작성자 : 박형건 (2023/10/21)
 * 학번 : 22112204
 * =======================
 * 수정일: 2023.10.21
 * 최종 수정일: 2023.10.26
 * =======================
 */

import java.util.Scanner; 
import java.io.*;


public class App_knapsackProblem01 
{
	public static void main(String[] args) throws IOException {
		KSP_Solution sol;
		int knapsack_capacity = 20;
		final String f_items = "/Users/baghyeong-geon/eclipse-workspace/KSP_items.txt";
		final String fout_name = "/Users/baghyeong-geon/eclipse-workspace/KSP_solution.txt";
		Scanner fin = new Scanner(new File(f_items));
		FileWriter fout = new FileWriter(fout_name);
		Knapsack01 kp = new Knapsack01(knapsack_capacity, fin); 
		System.out.printf("Knapsack01 (capacity = %d) is initialized for %d items\n",
		knapsack_capacity, kp.getNumItems());
		fin.close();
		fout.write(" Processing to obtain best solution by bruteforce() :\n ");
		sol = kp.BruteForce_KP01();
		System.out.printf("Solution obtained by bruteforce() :\n "); 
		kp.printSolution(sol);
		fout.write("Solution obtained by bruteforce() :\n ");
		kp.fprintSolution(fout, sol);
		fout.write(" Processing to obtain best solution by DynamicProgramming() :\n "); 
		sol = kp.DynProg_KP01();
		System.out.printf("Solution obtained by DynamicProgramming() :\n "); 
		kp.printSolution(sol);
		fout.write("Solution obtained by DynamicProgramming() :\n "); kp.fprintSolution(fout, sol);
		System.out.printf("Knapsack01 (capacity = %d) solution is stored in file %s.\n",
		knapsack_capacity, fout_name); fout.close();
		}

}

package chap07_Homework1_22112204_박형건;
/*
 * 파일명: chap07_Homework1_22112204_박형건 
 * 프로그램의 목적 및 기능 : 동적프로그래밍 기반 배낭 문제, 파일 입출력 
 * - class item 
 * 프로그램 작성자 : 박형건 (2023/10/21)
 * 학번 : 22112204
 * =======================
 * 수정일: 2023.10.21
 * 최종 수정일: 2023.10.26
 * =======================
 */

public class Item {
	String name;
	int value;
	int weight;
	public Item(String nm, int val, int wt)//배낭에 담길 아이템들 
	{
		this.name=nm;
		this.value=val;
		this.weight=wt;
	}
	
	public String toString()
	{
		String str;
		str=String.format("(%s, %d, %d)", this.name,this.value,this.weight);
		return str;
	}

}

package chap07_Homework1_22112204_박형건;
/*
 * 파일명: chap07_Homework1_22112204_박형건 
 * 프로그램의 목적 및 기능 : 동적프로그래밍 기반 배낭 문제, 파일 입출력 
 * - class knapsack01 
 * 프로그램 작성자 : 박형건 (2023/10/21)
 * 학번 : 22112204
 * =======================
 * 수정일: 2023.10.21
 * 최종 수정일: 2023.10.26
 * =======================
 */


import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Knapsack01 {
    private Item[] items;
    private int capacity;

    public Knapsack01(Item[] items, int capacity) {
        this.items = items;
        this.capacity = capacity;
    }

    public Knapsack01(int knapsack_capacity, Scanner fin) {
        this.capacity = knapsack_capacity;
        this.items = readItemsFromFile(fin);
    }

    private Item[] readItemsFromFile(Scanner fin) {
        int numItems = fin.nextInt();
        Item[] items = new Item[numItems];
        for (int i = 0; i < numItems; i++) {
            String name = fin.next();
            int value = fin.nextInt();
            int weight = fin.nextInt();
            items[i] = new Item(name, value, weight);
        }
        return items;
    }

    public KSP_Solution BruteForce_KP01() {
    	int numItems = items.length;
        int bestValue = 0;
        int bestWeight = 0;
        int currentWeight, currentValue;
        ArrayList<Item> bestSubset = new ArrayList<>();
        ArrayList<Item> currentSubset = new ArrayList<>();

        // 반복문을 사용하여 가능한 모든 부분집합 생성
        for (int mask = 0; mask < (1 << numItems); mask++) {
            currentWeight = 0;
            currentValue = 0;
            currentSubset.clear();

            // 아이템을 선택할지 여부를 확인
            for (int i = 0; i < numItems; i++) {
                if ((mask & (1 << i)) != 0) {
                    currentWeight += items[i].weight;
                    currentValue += items[i].value;
                    currentSubset.add(items[i]);
                }
            }

            // 현재 부분집합이 배낭 용량 내에 있고 더 나은 결과를 얻었다면 갱신
            if (currentWeight <= capacity && currentValue > bestValue) {
                bestValue = currentValue;
                bestWeight = currentWeight;
                bestSubset.clear();
                bestSubset.addAll(currentSubset);
            }
        }

        KSP_Solution solution = new KSP_Solution();
        solution.selectedItems = bestSubset;
        solution.totalValue = bestValue;
        solution.totalWeight = bestWeight;

        return solution;
    }

    public int getNumItems() {
        return items.length;
    }

    public void printSolution(KSP_Solution sol) {
        System.out.println("Selected Items: " + sol.selectedItems);
        System.out.println("Total Value: " + sol.totalValue);
        System.out.println("Total Weight: " + sol.totalWeight);
    }

    public void fprintSolution(FileWriter fout, KSP_Solution sol) {
        try {
            fout.write("Selected Items: " + sol.selectedItems + "\n");
            fout.write("Total Value: " + sol.totalValue + "\n");
            fout.write("Total Weight: " + sol.totalWeight + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public KSP_Solution DynProg_KP01() {
    	int numItems = items.length;
        int[][] dp = new int[numItems + 1][capacity + 1];
        
        // dp 배열 초기화
        for (int i = 0; i <= numItems; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (items[i - 1].weight <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - items[i - 1].weight] + items[i - 1].value);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        // 최적 해 추적
        int maxValue = dp[numItems][capacity];
        int remainingCapacity = capacity;
        ArrayList<Item> selectedItems = new ArrayList<>();

        for (int i = numItems; i > 0 && maxValue > 0; i--) {
            if (maxValue != dp[i - 1][remainingCapacity]) {
                selectedItems.add(items[i - 1]);
                maxValue -= items[i - 1].value;
                remainingCapacity -= items[i - 1].weight;
            }
        }

        KSP_Solution solution = new KSP_Solution();
        solution.selectedItems = selectedItems;
        solution.totalValue = dp[numItems][capacity];
        solution.totalWeight = capacity - remainingCapacity;

        return solution;
    }
}

package chap07_Homework1_22112204_박형건;

/*
 * 파일명: chap07_Homework1_22112204_박형건 
 * 프로그램의 목적 및 기능 : 동적프로그래밍 기반 배낭 문제, 파일 입출력 
 * - class ksp_solution 
 * 프로그램 작성자 : 박형건 (2023/10/21)
 * 학번 : 22112204
 * =======================
 * 수정일: 2023.10.21
 * 최종 수정일: 2023.10.26
 * =======================
 */

import java.util.ArrayList;

public class KSP_Solution {
	ArrayList<Item> selectedItems;
	int totalValue;
	int totalWeight;

}


(2)실행결과 (화면 캡쳐)
 




Homework 7.2 재균형 기능을 가지는 이진탐색트리 구현 
(1) Solution (java 프로그램 소스코드, 반드시 주석문 포함할 것)
package chap07_Homework2_22112204_박형건;
/*
 * 파일명: chap07_Homework2_22112204_박형건 
 * 프로그램의 목적 및 기능 : 재균형기을 가지는 이진탐색트리 구현 
 * - main 부분이다.
 * 프로그램 작성자 : 박형건 (2023/10/21)
 * 학번 : 22112204
 * =======================
 * 수정일: 2023.10.21
 * 최종 수정일: 2023.10.26
 * =======================
 */


import java.util.Scanner; 
import java.io.*;

public class App_BalancedBinarySearchTree 
{
	public static void main(String[] args) throws IOException {
		final String fout_name = "/Users/baghyeong-geon/eclipse-workspace/BST_IntegerEntry.txt"; Scanner cin = new Scanner(System.in);
		FileWriter fout = new FileWriter(fout_name);
		int[] intArray = {0, 1, 2, 3, 4, 5};
		String BST_name = "BST_IntegerEntry";
		BalancedBinarySearchTree<Integer> bstIntEntry = new BalancedBinarySearchTree<Integer>(BST_name); for (int int_entry : intArray) {
		bstIntEntry.insert_WithRebalancing(int_entry);
		System.out.println(bstIntEntry); }
		bstIntEntry.fprintBST_withDepth(fout);
		BinarySearchTreeNode<Integer> bstn_int; int int_searchKey = 1, searchResult; while(true) {
		System.out.printf("Input an int_searchKey (-1 to quit) : "); int_searchKey = cin.nextInt();
		if (int_searchKey == -1)
		break;
		bstn_int = bstIntEntry.search(int_searchKey);
		if ((bstn_int != null) && (int_searchKey == bstn_int.getEntry())){
		System.out.printf("int_searchKey(%d) is found in the %s\n", int_searchKey, BST_name); } else {
		System.out.printf("int_searchKey(%d) is not found in the %s\n", int_searchKey, BST_name); }
		}
		for (int i=0; i<intArray.length; i++) {
		BinarySearchTreeNode<Integer> bstn, BST_root = bstIntEntry.getRoot(); bstn = bstIntEntry._deleteBSTN(BST_root);
		bstIntEntry.setRoot(bstn);
		bstIntEntry.fprintBST_withDepth(fout);
		}
		String[] strArray =
			{ "A", "B", "C", "D", "E", "F", "G"};
			BST_name = "BST_StringEntry";
			BalancedBinarySearchTree<String> bstStringEntry = new BalancedBinarySearchTree<String>(BST_name); for (String str_entry : strArray) {
			bstStringEntry.insert_WithRebalancing(str_entry);
			System.out.println(bstStringEntry);
			}
			bstStringEntry.fprintBST_withDepth(fout);
			BinarySearchTreeNode<String> bstn_str;
			String str_searchKey, str_searchResult;
			while(true) {
			System.out.printf("Input a strt_searchKey (. to quit) : "); str_searchKey = cin.next();
			if (str_searchKey.equals("."))
			break;
			bstn_str = bstStringEntry.search(str_searchKey);
			if ((bstn_str != null) && str_searchKey.equals(bstn_str.getEntry())){ System.out.printf("str_searchKey(%s) is found in the %s\n", str_searchKey, BST_name);
			} else {
			System.out.printf("str_searchKey(%s) is not found in the %s\n", str_searchKey, BST_name); }
			}
			BinarySearchTreeNode<String> bstn;
			BinarySearchTreeNode<String> BST_root;
			for (int i=0; i<strArray.length; i++) {
			BST_root = bstStringEntry.getRoot();
			bstn = bstStringEntry._deleteBSTN(BST_root);
			bstStringEntry.setRoot(bstn);
			bstStringEntry.fprintBST_withDepth(fout);
			}
			cin.close();
			fout.close();
	}

}

package chap07_Homework2_22112204_박형건;

/*
 * 파일명: chap07_Homework2_22112204_박형건 
 * 프로그램의 목적 및 기능 : 재균형기을 가지는 이진탐색트리 구현 
 * - class balancedbinarysearchtree
 * 프로그램 작성자 : 박형건 (2023/10/21)
 * 학번 : 22112204
 * =======================
 * 수정일: 2023.10.21
 * 최종 수정일: 2023.10.26
 * =======================
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BalancedBinarySearchTree<E> {

	private String _name;
	private BinarySearchTreeNode<E> _root;
	private int _numEntry;
	public BalancedBinarySearchTree (String bst_nm)
	{
		this._name = bst_nm;
		this._root = null;
		this._numEntry = 0;
	}
	public int size()
	{
		return this._numEntry;
	}
	public boolean isEmpty()
	{
		return (this._numEntry == 0);
	}
	public BinarySearchTreeNode<E> getRoot()
	{
		return this._root;
	}
	private BinarySearchTreeNode<E> _insertInOrder(BinarySearchTreeNode<E> subRoot_bstn, BinarySearchTreeNode<E> pr_bstn, E newEntry) 
	{
		BinarySearchTreeNode<E> new_bstn, bstn = null, leftChild, rightChild;
		if (subRoot_bstn == null) 
		{
			new_bstn = new BinarySearchTreeNode<E>(newEntry);
			if (this._numEntry == 0)
				this._root = new_bstn; // this BSTN becomes the _root
			new_bstn.setParent(null);
			new_bstn.setLeftChild(null);
			new_bstn.setRightChild(null);
			this._numEntry++;
			return new_bstn;
		} 
		else
		{
			if (subRoot_bstn.compareKey(newEntry) > 0)
			{
				leftChild = subRoot_bstn.getLeftChild();
				bstn = _insertInOrder(leftChild, subRoot_bstn, newEntry);
				if (bstn != null)
				{
					subRoot_bstn.setLeftChild(bstn);
					bstn.setParent(subRoot_bstn);
				}
				return null;
			} // connected to else if

			else if (subRoot_bstn.compareKey(newEntry) < 0) 
			{
				rightChild = subRoot_bstn.getRightChild();
				bstn =_insertInOrder(rightChild, subRoot_bstn, newEntry);
				if (bstn != null)
				{
					subRoot_bstn.setRightChild(bstn);
					bstn.setParent(subRoot_bstn);
				}
				return null;
			}
			else
			{
				subRoot_bstn.setEntry(newEntry);
				bstn = subRoot_bstn;
				return null;
			}
		}
	}
	public BinarySearchTreeNode<E> insert(E newEntry) 
	{
		BinarySearchTreeNode<E> bstn;
		bstn = _insertInOrder(this._root, null, newEntry);
		return bstn;
	}
	public BinarySearchTreeNode<E> _search(BinarySearchTreeNode<E> subRoot, E searchKey)
	{
		BinarySearchTreeNode<E> bstn_result = null;
		if (subRoot == null)
			return null;
		if (subRoot.compareKey(searchKey) == 0)
			return subRoot;
		else if (subRoot.compareKey(searchKey) > 0) 
		{ // search key is less/smaller than the key of subRoot
			bstn_result = _search(subRoot.getLeftChild(), searchKey);
			return bstn_result;
		}
		else
		{// search key is greater/bigger than the key of subRoot
			bstn_result = _search(subRoot.getRightChild(), searchKey);
			return bstn_result;
		}
	}
	public BinarySearchTreeNode<E> search(E searchKey) 
	{
		BinarySearchTreeNode<E> bstn_result = null;
		bstn_result = _search(this._root, searchKey);
		return bstn_result;
	}
	public BinarySearchTreeNode<E> _findMin(BinarySearchTreeNode<E> bstn)
	{
		if (bstn.getLeftChild() != null) 
		{
			bstn = bstn.getLeftChild();
			while (bstn.getLeftChild() != null) 
			{
				bstn = bstn.getLeftChild();
			}
		}
		return bstn;
	}
	public BinarySearchTreeNode<E> _findMax(BinarySearchTreeNode<E> bstn) 
	{
		if (bstn.getRightChild() != null)
		{
			bstn = bstn.getRightChild();
			while (bstn.getRightChild() != null)
			{
				bstn = bstn.getRightChild();
			}
		}
		return bstn;
	}
	public void _inOrderTraversal(BinarySearchTreeNode<E>
	curBSTN, ArrayList<E> array_values) 
	{
		if (curBSTN.getLeftChild() != null) 
		{ 
			_inOrderTraversal(curBSTN.getLeftChild(), array_values);
		}
		array_values.add(curBSTN.getEntry());
		if (curBSTN.getRightChild() != null) 
		{
			_inOrderTraversal(curBSTN.getRightChild(), array_values);
		}
	}
	public String toString() 
	{
		ArrayList<E> array_values = new ArrayList<E>();
		String str = String.format("%s : ", this._name); _inOrderTraversal(this._root, array_values);
		for (E entry : array_values) 
		{
			str += entry + " ";
		}
		return str;
	}
	public void _printBST_withDepth(BinarySearchTreeNode<E> 
	curBSTN, int level) 
	{
		if (curBSTN == null)
			return;
		if (curBSTN.getRightChild() != null) 
		{ 
			_printBST_withDepth(curBSTN.getRightChild(), level + 1);
		}
		String str = "";
		for (int i=0; i<level; i++)
		{
			str += " ";
		}
		str += curBSTN.getEntry();
		System.out.println(str);
		if (curBSTN.getLeftChild() != null)
		{
			_printBST_withDepth(curBSTN.getLeftChild(), level + 1);
		}
	}
	public void printBST_withDepth() 
	{
		BinarySearchTreeNode<E> bstn;
		if (this._numEntry == 0)
		{
			System.out.printf("BinarySearchTree(%s) is Empty !!\n",this._name);
			return;
		}
		String str = String.format("BinarySearchTree(%s, num_entry= %d) :\n", this._name, this._numEntry);
		System.out.println(str); _printBST_withDepth(this._root, 0);
	}
	public int _getHeight(BinarySearchTreeNode<E> bstn)
	{
		int height = 0;
		int height_Lc, height_Rc;
		if (bstn != null) 
		{
			height_Lc = _getHeight(bstn.getLeftChild());
			height_Rc = _getHeight(bstn.getRightChild());
			if (height_Lc > height_Rc)
				height = 1 + height_Lc;
			else
				height = 1 + height_Rc;
		}
		return height;
	}
	public int _getHeightDiff(BinarySearchTreeNode<E> bstn) 
	{
		int heightDiff = 0;
		if (bstn != null)
		{
			heightDiff = _getHeight(bstn.getLeftChild()) - _getHeight(bstn.getRightChild());
		}
		return heightDiff;
	}
	public BinarySearchTreeNode<E> _deleteBSTN(BinarySearchTreeNode<E> toBeDeleted)
	{
		BinarySearchTreeNode<E> newSubRoot = null, temp, w, wLc;
		if (toBeDeleted == null)
			return null;
		if (toBeDeleted.getLeftChild() == null && toBeDeleted.getRightChild() == null) 
		{
			newSubRoot = null;
		} 
		else if (toBeDeleted.getLeftChild() != null && toBeDeleted.getRightChild() == null) 
		{
			newSubRoot = toBeDeleted.getLeftChild();
			newSubRoot.setParent(toBeDeleted.getParent());
		} 
		else if (toBeDeleted.getLeftChild() == null && toBeDeleted.getRightChild() != null)
		{
			newSubRoot = toBeDeleted.getRightChild();
			newSubRoot.setParent(toBeDeleted.getParent());
		} 
		else 
		{
			int heightDiff = _getHeightDiff(toBeDeleted);
			BinarySearchTreeNode<E> lChild = toBeDeleted.getLeftChild();
			BinarySearchTreeNode<E> rChild = toBeDeleted.getRightChild();
			BinarySearchTreeNode<E> parToBeDeleted = toBeDeleted.getParent();
			BinarySearchTreeNode<E> ioPd = null, lcIoPd, parIoPd;
			BinarySearchTreeNode<E> ioSs = null, rcIoSs, parIoSs;
			if (heightDiff > 0)
			{ // left subtree is higher, so put the in-order predecessor 
				// in the place of the toBeDeleted BSTN
				ioPd = _findMax(toBeDeleted.getLeftChild());
				lcIoPd = ioPd.getLeftChild();
				parIoPd = ioPd.getParent();
				if (ioPd.getParent() != toBeDeleted)
				{
					ioPd.setLeftChild(lChild);
					parIoPd.setRightChild(lcIoPd);
					if (lcIoPd != null)
					{
						lcIoPd.setParent(parIoPd);
					}
				}
				ioPd.setRightChild(rChild);
				ioPd.setParent(parToBeDeleted);
				newSubRoot = ioPd;
			}
			else 
			{ // right subtree is higher, so put the in-order successor 
				// in the place of the toBeDeleted BSTN
				ioSs = _findMin(toBeDeleted.getRightChild());
				rcIoSs = ioSs.getRightChild();
				parIoSs = ioSs.getParent();
				if (parIoSs != toBeDeleted)
				{
					ioSs.setRightChild(rChild);
					parIoSs.setLeftChild(rcIoSs);
					if (rcIoSs != null)
					{
						rcIoSs.setParent(parIoSs);
					}
				}
				ioSs.setLeftChild(lChild);
				ioSs.setParent(parIoSs);
				newSubRoot = ioSs;
			}
			if (lChild != ioPd)
				lChild.setParent(newSubRoot);
			if (rChild != ioSs)
				rChild.setParent(newSubRoot);
		} // end if - else
		if (toBeDeleted == this._root)
		{
			this._root = newSubRoot;
		}
		this._numEntry--;
		return newSubRoot;
	}
	public BinarySearchTreeNode<E> _rotateLL(BinarySearchTreeNode<E> curSubRoot) 
	{
		BinarySearchTreeNode<E> newSubRoot, BRC, curParent;
		curParent = curSubRoot.getParent();
		newSubRoot = curSubRoot.getLeftChild();
		BRC = newSubRoot.getRightChild();
		curSubRoot.setLeftChild(BRC);
		if (BRC != null)
			BRC.setParent(curSubRoot);
		newSubRoot.setRightChild(curSubRoot);
		newSubRoot.setParent(curParent);
		curSubRoot.setParent(newSubRoot);
		return newSubRoot;
	}
	public BinarySearchTreeNode<E> _rotateRR(BinarySearchTreeNode<E> curSubRoot) 
	{
		BinarySearchTreeNode<E> newSubRoot, BLC, curParent;
		curParent = curSubRoot.getParent();
		newSubRoot = curSubRoot.getRightChild();
		BLC = newSubRoot.getLeftChild();
		curSubRoot.setRightChild(BLC);
		if (BLC != null)
		{
			BLC.setParent(curSubRoot);
		}
		newSubRoot.setLeftChild(curSubRoot);
		newSubRoot.setParent(curParent);
		curSubRoot.setParent(newSubRoot);
		return newSubRoot;
	}
	public BinarySearchTreeNode<E> _rotateLR(BinarySearchTreeNode<E> curSubRoot) 
	{
		BinarySearchTreeNode<E> subRoot, newSubRoot, A, B, C, BL, BR, curParent;
		C = curSubRoot;
		curParent = curSubRoot.getParent();
		A = C.getLeftChild();
		B = A.getRightChild();
		BL = B.getLeftChild();
		BR = B.getRightChild();
		subRoot = _rotateRR(A);
		newSubRoot = _rotateLL(C);
		newSubRoot.setParent(curParent);
		A.setParent(newSubRoot);
		C.setParent(newSubRoot);
		if (BL != null)
			BL.setParent(A);
		if (BR != null)
			BR.setParent(C);
		return newSubRoot;
	}
	public BinarySearchTreeNode<E> _rotateRL(BinarySearchTreeNode<E> curSubRoot)
	{
		BinarySearchTreeNode<E> subRoot, newSubRoot, A, B, C, BL, BR, curParent;
		A = curSubRoot;
		curParent = curSubRoot.getParent();
		C = A.getRightChild();
		B = A.getLeftChild();
		BL = B.getLeftChild();
		BR = B.getRightChild();
		subRoot = _rotateLL(C);
		newSubRoot = _rotateRR(A);
		newSubRoot.setParent(curParent);
		A.setParent(newSubRoot);
		C.setParent(newSubRoot);
		if (BL != null)
			BL.setParent(A);
		if (BR != null)
			BR.setParent(C);
		return newSubRoot;
	}
	public BinarySearchTreeNode<E> _reBalance(BinarySearchTreeNode<E> curSubRoot) {
		BinarySearchTreeNode<E> newSubRoot = null;
		int heightDiff = 0;
		heightDiff = _getHeightDiff(curSubRoot);
		if (heightDiff > 1) 
		{ // left subtree is higher
			if (_getHeightDiff(curSubRoot.getLeftChild()) > 0) 
			{
				newSubRoot = _rotateLL(curSubRoot);
			} 
			else 
			{
				newSubRoot = _rotateLR(curSubRoot);
			}
		} 
		else if (heightDiff < -1) 
		{
			if (_getHeightDiff(curSubRoot.getRightChild()) < 0)
			{
				newSubRoot = _rotateRR(curSubRoot);
			} 
			else
			{
				newSubRoot = _rotateRL(curSubRoot);
			}
		}
		return newSubRoot;
	}
	public BinarySearchTreeNode<E> _insert_withRebalancing(BinarySearchTreeNode<E> curSubRoot, BinarySearchTreeNode<E> curParent, E newEntry) 
	{
		BinarySearchTreeNode<E> newBSTN, bstn, LC, RC;
		if (curSubRoot == null) 
		{
			newBSTN = new BinarySearchTreeNode<E> (newEntry);
			curSubRoot = newBSTN;
			if (curParent != null)
			{
				newBSTN.setParent(curParent);
			}
			newBSTN.setLeftChild(null);
			newBSTN.setRightChild(null);
			this._numEntry++;
			return curSubRoot;
		}
		E bstn_entry = curSubRoot.getEntry();
		if (curSubRoot.compareKey(newEntry) > 0) 
		{
			LC = curSubRoot.getLeftChild();
			bstn = _insert_withRebalancing(LC, curSubRoot, newEntry);
			if (bstn != null) 
			{
				curSubRoot.setLeftChild(bstn);
				curSubRoot = _reBalance(curSubRoot);
			}
		} 
		else 
		{
			RC = curSubRoot.getRightChild();
			bstn = _insert_withRebalancing(RC, curSubRoot, newEntry);
			if (bstn != null) 
			{
				curSubRoot.setRightChild(bstn);
				curSubRoot = _reBalance(curSubRoot);
			}
		}
		return curSubRoot;
	}
	public void insert_WithRebalancing(E newEntry)
	{
		BinarySearchTreeNode<E> newSubRoot;
		newSubRoot = _insert_withRebalancing(this._root, null, newEntry);
		if (newSubRoot != null)
			this._root = newSubRoot;
	}
	public void _fprintBST_withDepth(BinarySearchTreeNode<E> curBSTN, int level,FileWriter fout) throws IOException
	{
		if (curBSTN == null)
			return;
		if (curBSTN.getRightChild() != null) 
		{ 
			_fprintBST_withDepth(curBSTN.getRightChild(), level + 1,fout);
		}
		String str = "";
		for (int i=0; i<level; i++)
		{
			str += "  ";
		}
		str += curBSTN.getEntry();
		str+="\n";
		fout.write(str);
		if (curBSTN.getLeftChild() != null)
		{
			_fprintBST_withDepth(curBSTN.getLeftChild(), level + 1,fout);
		}
	}
	public void fprintBST_withDepth(FileWriter fout) throws IOException
	{
		BinarySearchTreeNode<E> bstn;
		if (this._numEntry == 0)
		{
			String str=String.format("BinarySearchTree(%s) is Empty !!\n",this._name);
			fout.write(str);
			return;
		}
		String str_2 = String.format("BinarySearchTree(%s, num_entry= %d) :\n", this._name, this._numEntry);
		fout.write(str_2);
		_fprintBST_withDepth(this._root, 0,fout);
	}
	public void setRoot(BinarySearchTreeNode<E> bt)
	{
	this._root=bt;	
	}
}

package chap07_Homework2_22112204_박형건;
/*
 * 파일명: chap07_Homework2_22112204_박형건 
 * 프로그램의 목적 및 기능 : 재균형기을 가지는 이진탐색트리 구현 
 * - class binarysearchtree
 * 프로그램 작성자 : 박형건 (2023/10/21)
 * 학번 : 22112204
 * =======================
 * 수정일: 2023.10.21
 * 최종 수정일: 2023.10.26
 * =======================
 */


import java.util.ArrayList;

public class BinarySearchTree<E> 
{
	private String _name;
	private BinarySearchTreeNode<E> _root;
	private int _numEntry;
	public BinarySearchTree (String bst_nm)
	{
		this._name = bst_nm;
		this._root = null;
		this._numEntry = 0;
	}
	public int size()
	{
		return this._numEntry;
	}
	public boolean isEmpty()
	{
		return (this._numEntry == 0);
	}
	public BinarySearchTreeNode<E> getRoot()
	{
		return this._root;
	}
	private BinarySearchTreeNode<E> _insertInOrder(BinarySearchTreeNode<E> subRoot_bstn, BinarySearchTreeNode<E> pr_bstn, E newEntry) 
	{
		BinarySearchTreeNode<E> new_bstn, bstn = null, leftChild, rightChild;
		if (subRoot_bstn == null) 
		{
			new_bstn = new BinarySearchTreeNode<E>(newEntry);
			if (this._numEntry == 0)
				this._root = new_bstn; // this BSTN becomes the _root
			new_bstn.setParent(null);
			new_bstn.setLeftChild(null);
			new_bstn.setRightChild(null);
			this._numEntry++;
			return new_bstn;
		} 
		else
		{
			if (subRoot_bstn.compareKey(newEntry) > 0)
			{
				leftChild = subRoot_bstn.getLeftChild();
				bstn = _insertInOrder(leftChild, subRoot_bstn, newEntry);
				if (bstn != null)
				{
					subRoot_bstn.setLeftChild(bstn);
					bstn.setParent(subRoot_bstn);
				}
				return null;
			} // connected to else if

			else if (subRoot_bstn.compareKey(newEntry) < 0) 
			{
				rightChild = subRoot_bstn.getRightChild();
				bstn =_insertInOrder(rightChild, subRoot_bstn, newEntry);
				if (bstn != null)
				{
					subRoot_bstn.setRightChild(bstn);
					bstn.setParent(subRoot_bstn);
				}
				return null;
			}
			else
			{
				subRoot_bstn.setEntry(newEntry);
				bstn = subRoot_bstn;
				return null;
			}
		}
	}
	public BinarySearchTreeNode<E> insert(E newEntry) 
	{
		BinarySearchTreeNode<E> bstn;
		bstn = _insertInOrder(this._root, null, newEntry);
		return bstn;
	}
	public BinarySearchTreeNode<E> _search(BinarySearchTreeNode<E> subRoot, E searchKey)
	{
		BinarySearchTreeNode<E> bstn_result = null;
		if (subRoot == null)
			return null;
		if (subRoot.compareKey(searchKey) == 0)
			return subRoot;
		else if (subRoot.compareKey(searchKey) > 0) 
		{ // search key is less/smaller than the key of subRoot
			bstn_result = _search(subRoot.getLeftChild(), searchKey);
			return bstn_result;
		}
		else
		{// search key is greater/bigger than the key of subRoot
			bstn_result = _search(subRoot.getRightChild(), searchKey);
			return bstn_result;
		}
	}
	public BinarySearchTreeNode<E> search(E searchKey) 
	{
		BinarySearchTreeNode<E> bstn_result = null;
		bstn_result = _search(this._root, searchKey);
		return bstn_result;
	}
	public BinarySearchTreeNode<E> _findMin(BinarySearchTreeNode<E> bstn)
	{
		if (bstn.getLeftChild() != null) 
		{
			bstn = bstn.getLeftChild();
			while (bstn.getLeftChild() != null) 
			{
				bstn = bstn.getLeftChild();
			}
		}
		return bstn;
	}
	public BinarySearchTreeNode<E> _findMax(BinarySearchTreeNode<E> bstn) 
	{
		if (bstn.getRightChild() != null)
		{
			bstn = bstn.getRightChild();
			while (bstn.getRightChild() != null)
			{
				bstn = bstn.getRightChild();
			}
		}
		return bstn;
	}
	public void _inOrderTraversal(BinarySearchTreeNode<E>
	curBSTN, ArrayList<E> array_values) 
	{
		if (curBSTN.getLeftChild() != null) 
		{ 
			_inOrderTraversal(curBSTN.getLeftChild(), array_values);
		}
		array_values.add(curBSTN.getEntry());
		if (curBSTN.getRightChild() != null) 
		{
			_inOrderTraversal(curBSTN.getRightChild(), array_values);
		}
	}
	public String toString() 
	{
		ArrayList<E> array_values = new ArrayList<E>();
		String str = String.format("%s : ", this._name); _inOrderTraversal(this._root, array_values);
		for (E entry : array_values) 
		{
			str += entry + " ";
		}
		return str;
	}
	public void _printBST_withDepth(BinarySearchTreeNode<E> 
	curBSTN, int level) 
	{
		if (curBSTN == null)
			return;
		if (curBSTN.getRightChild() != null) 
		{ 
			_printBST_withDepth(curBSTN.getRightChild(), level + 1);
		}
		String str = "";
		for (int i=0; i<level; i++)
		{
			str += " ";
		}
		str += curBSTN.getEntry();
		System.out.println(str);
		if (curBSTN.getLeftChild() != null)
		{
			_printBST_withDepth(curBSTN.getLeftChild(), level + 1);
		}
	}
	public void printBST_withDepth() 
	{
		BinarySearchTreeNode<E> bstn;
		if (this._numEntry == 0)
		{
			System.out.printf("BinarySearchTree(%s) is Empty !!\n",this._name);
			return;
		}
		String str = String.format("BinarySearchTree(%s, num_entry= %d) :\n", this._name, this._numEntry);
		System.out.println(str); _printBST_withDepth(this._root, 0);
	}
	public int _getHeight(BinarySearchTreeNode<E> bstn)
	{
		int height = 0;
		int height_Lc, height_Rc;
		if (bstn != null) 
		{
			height_Lc = _getHeight(bstn.getLeftChild());
			height_Rc = _getHeight(bstn.getRightChild());
			if (height_Lc > height_Rc)
				height = 1 + height_Lc;
			else
				height = 1 + height_Rc;
		}
		return height;
	}
	public int _getHeightDiff(BinarySearchTreeNode<E> bstn) 
	{
		int heightDiff = 0;
		if (bstn != null)
		{
			heightDiff = _getHeight(bstn.getLeftChild()) - _getHeight(bstn.getRightChild());
		}
		return heightDiff;
	}
	public BinarySearchTreeNode<E> _deleteBSTN(BinarySearchTreeNode<E> toBeDeleted)
	{
		BinarySearchTreeNode<E> newSubRoot = null, temp, w, wLc;
		if (toBeDeleted == null)
			return null;
		if (toBeDeleted.getLeftChild() == null && toBeDeleted.getRightChild() == null) 
		{
			newSubRoot = null;
		} 
		else if (toBeDeleted.getLeftChild() != null && toBeDeleted.getRightChild() == null) 
		{
			newSubRoot = toBeDeleted.getLeftChild();
			newSubRoot.setParent(toBeDeleted.getParent());
		} 
		else if (toBeDeleted.getLeftChild() == null && toBeDeleted.getRightChild() != null)
		{
			newSubRoot = toBeDeleted.getRightChild();
			newSubRoot.setParent(toBeDeleted.getParent());
		} 
		else 
		{
			int heightDiff = _getHeightDiff(toBeDeleted);
			BinarySearchTreeNode<E> lChild = toBeDeleted.getLeftChild();
			BinarySearchTreeNode<E> rChild = toBeDeleted.getRightChild();
			BinarySearchTreeNode<E> parToBeDeleted = toBeDeleted.getParent();
			BinarySearchTreeNode<E> ioPd = null, lcIoPd, parIoPd;
			BinarySearchTreeNode<E> ioSs = null, rcIoSs, parIoSs;
			if (heightDiff > 0)
			{ // left subtree is higher, so put the in-order predecessor 
				// in the place of the toBeDeleted BSTN
				ioPd = _findMax(toBeDeleted.getLeftChild());
				lcIoPd = ioPd.getLeftChild();
				parIoPd = ioPd.getParent();
				if (ioPd.getParent() != toBeDeleted)
				{
					ioPd.setLeftChild(lChild);
					parIoPd.setRightChild(lcIoPd);
					if (lcIoPd != null)
					{
						lcIoPd.setParent(parIoPd);
					}
				}
				ioPd.setRightChild(rChild);
				ioPd.setParent(parToBeDeleted);
				newSubRoot = ioPd;
			}
			else 
			{ // right subtree is higher, so put the in-order successor 
				// in the place of the toBeDeleted BSTN
				ioSs = _findMin(toBeDeleted.getRightChild());
				rcIoSs = ioSs.getRightChild();
				parIoSs = ioSs.getParent();
				if (parIoSs != toBeDeleted)
				{
					ioSs.setRightChild(rChild);
					parIoSs.setLeftChild(rcIoSs);
					if (rcIoSs != null)
					{
						rcIoSs.setParent(parIoSs);
					}
				}
				ioSs.setLeftChild(lChild);
				ioSs.setParent(parIoSs);
				newSubRoot = ioSs;
			}
			if (lChild != ioPd)
				lChild.setParent(newSubRoot);
			if (rChild != ioSs)
				rChild.setParent(newSubRoot);
		} // end if - else
		if (toBeDeleted == this._root)
		{
			this._root = newSubRoot;
		}
		this._numEntry--;
		return newSubRoot;
	}
	public BinarySearchTreeNode<E> _rotateLL(BinarySearchTreeNode<E> curSubRoot) 
	{
		BinarySearchTreeNode<E> newSubRoot, BRC, curParent;
		curParent = curSubRoot.getParent();
		newSubRoot = curSubRoot.getLeftChild();
		BRC = newSubRoot.getRightChild();
		curSubRoot.setLeftChild(BRC);
		if (BRC != null)
			BRC.setParent(curSubRoot);
		newSubRoot.setRightChild(curSubRoot);
		newSubRoot.setParent(curParent);
		curSubRoot.setParent(newSubRoot);
		return newSubRoot;
	}
	public BinarySearchTreeNode<E> _rotateRR(BinarySearchTreeNode<E> curSubRoot) 
	{
		BinarySearchTreeNode<E> newSubRoot, BLC, curParent;
		curParent = curSubRoot.getParent();
		newSubRoot = curSubRoot.getRightChild();
		BLC = newSubRoot.getLeftChild();
		curSubRoot.setRightChild(BLC);
		if (BLC != null)
		{
			BLC.setParent(curSubRoot);
		}
		newSubRoot.setLeftChild(curSubRoot);
		newSubRoot.setParent(curParent);
		curSubRoot.setParent(newSubRoot);
		return newSubRoot;
	}
	public BinarySearchTreeNode<E> _rotateLR(BinarySearchTreeNode<E> curSubRoot) 
	{
		BinarySearchTreeNode<E> subRoot, newSubRoot, A, B, C, BL, BR, curParent;
		C = curSubRoot;
		curParent = curSubRoot.getParent();
		A = C.getLeftChild();
		B = A.getRightChild();
		BL = B.getLeftChild();
		BR = B.getRightChild();
		subRoot = _rotateRR(A);
		newSubRoot = _rotateLL(C);
		newSubRoot.setParent(curParent);
		A.setParent(newSubRoot);
		C.setParent(newSubRoot);
		if (BL != null)
			BL.setParent(A);
		if (BR != null)
			BR.setParent(C);
		return newSubRoot;
	}
	public BinarySearchTreeNode<E> _rotateRL(BinarySearchTreeNode<E> curSubRoot)
	{
		BinarySearchTreeNode<E> subRoot, newSubRoot, A, B, C, BL, BR, curParent;
		A = curSubRoot;
		curParent = curSubRoot.getParent();
		C = A.getRightChild();
		B = A.getLeftChild();
		BL = B.getLeftChild();
		BR = B.getRightChild();
		subRoot = _rotateLL(C);
		newSubRoot = _rotateRR(A);
		newSubRoot.setParent(curParent);
		A.setParent(newSubRoot);
		C.setParent(newSubRoot);
		if (BL != null)
			BL.setParent(A);
		if (BR != null)
			BR.setParent(C);
		return newSubRoot;
	}
	public BinarySearchTreeNode<E> _reBalance(BinarySearchTreeNode<E> curSubRoot) {
		BinarySearchTreeNode<E> newSubRoot = null;
		int heightDiff = 0;
		heightDiff = _getHeightDiff(curSubRoot);
		if (heightDiff > 1) 
		{ // left subtree is higher
			if (_getHeightDiff(curSubRoot.getLeftChild()) > 0) 
			{
				newSubRoot = _rotateLL(curSubRoot);
			} 
			else 
			{
				newSubRoot = _rotateLR(curSubRoot);
			}
		} 
		else if (heightDiff < -1) 
		{
			if (_getHeightDiff(curSubRoot.getRightChild()) < 0)
			{
				newSubRoot = _rotateRR(curSubRoot);
			} 
			else
			{
				newSubRoot = _rotateRL(curSubRoot);
			}
		}
		return newSubRoot;
	}
	public BinarySearchTreeNode<E> _insert_withRebalancing(BinarySearchTreeNode<E> curSubRoot, BinarySearchTreeNode<E> curParent, E newEntry) 
	{
		BinarySearchTreeNode<E> newBSTN, bstn, LC, RC;
		if (curSubRoot == null) 
		{
			newBSTN = new BinarySearchTreeNode<E> (newEntry);
			curSubRoot = newBSTN;
			if (curParent != null)
			{
				newBSTN.setParent(curParent);
			}
			newBSTN.setLeftChild(null);
			newBSTN.setRightChild(null);
			this._numEntry++;
			return curSubRoot;
		}
		E bstn_entry = curSubRoot.getEntry();
		if (curSubRoot.compareKey(newEntry) > 0) 
		{
			LC = curSubRoot.getLeftChild();
			bstn = _insert_withRebalancing(LC, curSubRoot, newEntry);
			if (bstn != null) 
			{
				curSubRoot.setLeftChild(bstn);
				curSubRoot = _reBalance(curSubRoot);
			}
		} 
		else 
		{
			RC = curSubRoot.getRightChild();
			bstn = _insert_withRebalancing(RC, curSubRoot, newEntry);
			if (bstn != null) 
			{
				curSubRoot.setRightChild(bstn);
				curSubRoot = _reBalance(curSubRoot);
			}
		}
		return curSubRoot;
	}
	public void insert_WithRebalancing(E newEntry)
	{
		BinarySearchTreeNode<E> newSubRoot;
		newSubRoot = _insert_withRebalancing(this._root, null, newEntry);
		if (newSubRoot != null)
			this._root = newSubRoot;
	}


}

package chap07_Homework2_22112204_박형건;
/*
 * 파일명: chap07_Homework2_22112204_박형건 
 * 프로그램의 목적 및 기능 : 재균형기을 가지는 이진탐색트리 구현 
 * - class binarysearchtreenode
 * 프로그램 작성자 : 박형건 (2023/10/21)
 * 학번 : 22112204
 * =======================
 * 수정일: 2023.10.21
 * 최종 수정일: 2023.10.26
 * =======================
 */


public class BinarySearchTreeNode<E>
{
	private E _entry;
	private BinarySearchTreeNode<E> _parent; 
	private BinarySearchTreeNode<E> _leftChild;
	private BinarySearchTreeNode<E> _rightChild;
	public BinarySearchTreeNode() 
	{ 
		this._parent = null; this._leftChild = null; this._rightChild = null;
	}
	public BinarySearchTreeNode(E entry) {
	
		this._entry = entry; this._parent = null; this._leftChild = null; this._rightChild = null;
	}
	public E getEntry() 
	{
	
		return this._entry; 
		
	}
	public void setEntry(E newEntry) 
	{ 
		this._entry = newEntry;
	}
	public BinarySearchTreeNode<E> getParent() 
	{
		return this._parent;
	}
	public BinarySearchTreeNode<E> getLeftChild()
	{
	
		return this._leftChild; 
		
	}
	public BinarySearchTreeNode<E> getRightChild()
	{ 
		return this._rightChild;
	}
	public void setParent(BinarySearchTreeNode<E> prBSTN)
	{
	
		this._parent = prBSTN; 
		
	}
	public void setLeftChild(BinarySearchTreeNode<E> leftChildBSTN) 
	{
		this._leftChild = leftChildBSTN;
	}
	public void setRightChild(BinarySearchTreeNode<E> rightChildBSTN)
	{
	
		this._rightChild = rightChildBSTN; 
		
	}
	public int compareKey(E otherEntry) 
	{ 
		int cmpResult = 0;
	
		if (this._entry instanceof Integer) 
		{
	
			cmpResult = (int) (this._entry) - (int) otherEntry;
	
		} 
		else if (this._entry instanceof Double) 
		{
	
			if ((double) (this._entry) > (double)otherEntry)
				cmpResult = 1;
	
			else if ((double) (this._entry) < (double)otherEntry)
				cmpResult = -1; 
			else
				cmpResult = 0;
			
		} 
		else if (this._entry instanceof String)
		{
			cmpResult = ((String)(this._entry)).compareTo((String)(otherEntry));
			
		}
		else
		{
				System.out.println("compareKey() is not defined for entry_type E !!"); 
				
		}
	
		return cmpResult; 
	}
	

}


(2)실행결과 (화면 캡쳐)
 
 

Homework 7.3 완전이진트리 구조의 HeapPriorityQueue_Task 구현 
(1) Solution (java 프로그램 소스코드, 반드시 주석문 포함할 것)
package chap07_Homework3_22112204_박형건;
/*
 * 파일명: chap07_Homework3_22112204_박형건 
 * 프로그램의 목적 및 기능 : 완전이진트리 구조의 HeapPriorityQueue_Task 구현 
 * - main부분이다.
 * 프로그램 작성자 : 박형건 (2023/10/21)
 * 학번 : 22112204
 * =======================
 * 수정일: 2023.10.21
 * 최종 수정일: 2023.10.26
 * =======================
 */


public class App_HeapPriQ_Task {

	public static void main(String[] args) {
		Task[] events = {
		new Task(9, "Task_9"), new Task(8, "Task_8"), new Task(7, "Task_7"), new Task(6, "Task_6"), new Task(5, "Task_5"), new Task(4, "Task_4"), new Task(3, "Task_3"), new Task(2, "Task_2"), new Task(1, "Task_1"), new Task(0, "Task_0")
		};
		HeapPriQ_Task heapPriQ_Ev_Task = new HeapPriQ_Task("HeapPriQ_Task", 20); System.out.printf("Inserting events to heapPriQ_Ev_Task :\n");
		for (Task ev : events) {
		System.out.printf("%s ", ev);
		heapPriQ_Ev_Task.insert(ev); }
		System.out.printf("\nSequence of events by RemoveHeapMin() :\n"); for (int i=0; i<events.length; i++) {
		Task ev = heapPriQ_Ev_Task.removeHeapMin();
		System.out.printf("%s ", ev); }
		}
}

package chap07_Homework3_22112204_박형건;
/*
 * 파일명: chap07_Homework3_22112204_박형건 
 * 프로그램의 목적 및 기능 : 완전이진트리 구조의 HeapPriorityQueue_Task 구현 
 * - class completebinarytree
 * 프로그램 작성자 : 박형건 (2023/10/21)
 * 학번 : 22112204
 * =======================
 * 수정일: 2023.10.21
 * 최종 수정일: 2023.10.26
 * =======================
 */

public class CompleteBinaryTree<E> extends GenericArray<E>{

	public CompleteBinaryTree(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}
	

}

package chap07_Homework3_22112204_박형건;
/*
 * 파일명: chap07_Homework3_22112204_박형건 
 * 프로그램의 목적 및 기능 : 완전이진트리 구조의 HeapPriorityQueue_Task 구현 
 * - class genericarray
 * 프로그램 작성자 : 박형건 (2023/10/21)
 * 학번 : 22112204
 * =======================
 * 수정일: 2023.10.21
 * 최종 수정일: 2023.10.26
 * =======================
 */


public class GenericArray<E> {
	protected Object[] genArray;
    protected int capacity;
    protected int size;

    public GenericArray(int capacity) {
        this.capacity = capacity;
        genArray = new Object[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}

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

package chap07_Homework3_22112204_박형건;
/*
 * 파일명: chap07_Homework3_22112204_박형건 
 * 프로그램의 목적 및 기능 : 완전이진트리 구조의 HeapPriorityQueue_Task 구현 
 * - class task
 * 프로그램 작성자 : 박형건 (2023/10/21)
 * 학번 : 22112204
 * =======================
 * 수정일: 2023.10.21
 * 최종 수정일: 2023.10.26
 * =======================
 */


public class Task implements Comparable<Task> {
    private int _priority;
    private String _title;

    public Task(int priority, String title) {
        _priority = priority;
        _title = title;
    }

    public int getPriority() {
        return _priority;
    }

    @Override
    public String toString() {
        return "Task(" + _priority + ", " + _title + ")";
    }

    @Override
    public int compareTo(Task other) {
        // Compare tasks by priority
        return Integer.compare(this._priority, other._priority);
    }
}


(2)실행결과 (화면 캡쳐)
 

