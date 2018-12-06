package cn.ms;

public class SortTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int a = 1;
//		int b = 2;
//		System.out.println(a++ >= b ? "a" : "b");
//		a = 1;
//		System.out.println(++a >= b ? "a" : "b");
		
		int[] arr = {2,4,9,3,1,6,0,5,7,8}; 
//		mp_sort(arr);
		xz_sort(arr);
	}
	
	//冒泡排序
	public static void mp_sort(int[] arr){
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr.length-1; j++){
				if(arr[j+1] < arr[j]){
					int tag = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = tag;
				}
			}
		}
		for (int i : arr) {
			System.out.print(i+" ");
		}
	}
	
	
	//选择排序
	public static void xz_sort(int[] arr){
		for(int i = 0; i < arr.length; i++){
			for (int j = i+1; j < arr.length; j++) {
				if (arr[j] < arr[i]) {
					int tag = arr[i];
					arr[i] = arr[j];
					arr[j] = tag;
				}
			}
		}
		for (int i : arr) {
			System.out.print(i+" ");
		}
	}
	

}
