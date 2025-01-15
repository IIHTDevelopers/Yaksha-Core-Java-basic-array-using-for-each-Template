package com.yaksha.assignment;

public class ArrayUsingForEachAssignment {

	public static void main(String[] args) {

		// Task 1: Print Elements of an Array
		System.out.println("Task 1: Print Elements of an Array");
		int[] arr1 = { 1, 2, 3, 4, 5 };
		for (int num : arr1) { // For-each loop
			System.out.println(num);
		}

		// Task 2: Find the Length of an Array
		System.out.println("\nTask 2: Find the Length of an Array");
		int[] arr2 = { 1, 2, 3, 4, 5 };
		System.out.println("Length of the array: " + arr2.length);

		// Task 3: Find the Maximum Value in an Array
		System.out.println("\nTask 3: Find the Maximum Value in an Array");
		int[] arr3 = { 1, 2, 3, 4, 5, 9 };
		int max = arr3[0];
		for (int num : arr3) { // For-each loop
			if (num > max) {
				max = num;
			}
		}
		System.out.println("Maximum Value: " + max);

		// Task 4: Skip a Specific Value in an Array
		System.out.println("\nTask 4: Skip a Specific Value in an Array");
		int[] arr4 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (int num : arr4) { // For-each loop
			if (num == 7) {
				continue;
			}
			System.out.println(num);
		}

		// Task 5: Sum of Elements in an Array
		System.out.println("\nTask 5: Sum of Elements in an Array");
		int[] arr5 = { 1, 2, 3, 4, 5 };
		int sum = 0;
		for (int num : arr5) { // For-each loop
			sum += num;
		}
		System.out.println("Sum of the array elements: " + sum);
	}
}
