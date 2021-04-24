package combinations;

import java.util.Scanner;

public class Driver {
	
	public static void main(String args[]) {
		Integer[] items = {1, 2, 3, 4, 5};
		int boxes = 2;
		Distinguishable<Integer> test = new Distinguishable<Integer>(items, boxes);
		//test.setItems(items);
		//test.setBoxes(boxes);
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()) {
			String input = scan.nextLine();
			if(input.equals("stop")) {
				System.out.println("Exiting loop and closing down.");
				break;
			}
			if(input.equals("get combinations")) {
				test.printCombinations();
				continue;
			}
			System.out.println("No valid input detected. Try again!");
		}
		System.out.println(test.getBoxes());
		scan.close();
	}
}
