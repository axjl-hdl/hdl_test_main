package cn.ms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ArrayTest {
	
	private static int[] A = {10,15,9,21,40,101,11};
	private static int[] B = {11,40,22,6,7,55,89};
	
	public static void main(String[] args){
//		union(A, B);
//		
		intersection(A, B);
		
		difference(A, B);
	}
	
	//并集
	public static void union(int[] a, int[] b){
		/*
		 * Set 
		 * 1. 无序的    2. 不重复的
		 */
		Set<Integer> set = new HashSet<Integer>();
		
		for (Integer i : a) {
			set.add(i);
		}
		
		for (Integer i : b) {
			set.add(i);
		}		
		
		System.out.println(set.toString());
	}
	
	//交集
	public static void intersection(int[] a, int[] b){
		List<Integer> list = new ArrayList<Integer>();
		
		Set<Integer> set = new HashSet<Integer>();
		
		for (Integer i : a) {
			if (!list.contains(i)) {
				list.add(i);
			}
		}
		
		for (Integer i : b) {
			if (list.contains(i)) {
				set.add(i);
			}
		}
		System.out.println(set.toString());
	}
	
	//差集
	public static void difference(int[] a, int[] b){
		List<Integer> list = new LinkedList<Integer>();
		for (int i : a) {
			if (!list.contains(i)) {
				list.add(i);
			}
		}
		
		for (Integer i : b) {
			if (list.contains(i)) {
				list.remove(i);
			}else{
				list.add(i);
			}
		}
		
		System.out.println(list.toString());
	}
	
}
