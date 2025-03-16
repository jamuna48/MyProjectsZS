package ArrayCreator;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class ArrayGenrator {
	Scanner sc = new Scanner(System.in);
	Random ran = new Random();

	public int[] linearArray() {

		System.out.println("enter your start value: ");
		int start = sc.nextInt();

		System.out.println("enter your Step value:");
		int step = sc.nextInt();

		System.out.println("enter your length");
		int length = sc.nextInt();

		int linearArr[] = new int[length];
		linearArr[0] = start;

		for (int i = 1; i < length; i++) {
			linearArr[i] = linearArr[i - 1] + step;

		}

		return linearArr;
	}

	public int[] random() {
		System.out.println("enter your start value");
		int range1 = sc.nextInt();
		System.out.println("enter your range value");
		int range2 = sc.nextInt();
		System.out.println("enter your size");
		int arrLength = sc.nextInt();
		int arr[] = new int[arrLength];
		for (int i = 0; i < arrLength; i++) {
			arr[i] = ran.nextInt(range1, range2);
		}
		return arr;
	}

	public void ShowMenu() {
		System.out.println("enter your choice 😃");
		System.out.println("if you choose 1 it will return LINEAR ARRAY");
		System.out.println("if you choose 2 it will return RANDOM ARRAY");
		int choice = sc.nextInt();
		System.out.println("Thanks for your selection your is............");

		if (choice < 3) {
			switch (choice) {
			case 1:
				System.out.println("enter your seperator....");
				String seperator = sc.next();
				System.out.println("enter  your left array opening symbol:");

				String leftSymbol = sc.next();
				System.out.println("enter your right array closing symbol:");
				String rightSymbol = sc.next();
				int[] linear = linearArray();

				System.out.print(leftSymbol);
				for (int i = 0; i < linear.length; i++) {
					System.out.print(linear[i]);

					if (i < linear.length - 1) {

						System.out.print(seperator);
					}
				}
				System.out.println(rightSymbol + "\n");
				break;

			case 2:
				System.out.println("enter your seperator....");
				String seperator1 = sc.next();
				System.out.println("enter  your left array opening symbol:");

				String leftSymbol1 = sc.next();
				System.out.println("enter your right array closing symbol:");
				String rightSymbol2 = sc.next();
				int[] linear1 = random();

				System.out.print(leftSymbol1);
				for (int i = 0; i < linear1.length; i++) {
					System.out.print(linear1[i]);

					if (i < linear1.length - 1) {

						System.out.print(seperator1);
					}
				}
				System.out.println(rightSymbol2 + "\n");
               break;

			}
			

		}

		else {
			System.out.println("OMG something wrong ,, it is invalid input");
		}

	}

	public static void main(String[] args) {
		ArrayGenrator result = new ArrayGenrator();
		result.ShowMenu();
	}

}
