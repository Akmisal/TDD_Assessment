
import java.util.ArrayList;

public class StringCalculator {
	public static int add(String numbers) {
		if (numbers.isEmpty()) {
			return 0;
		}
		String delimiter = ",|\n"; // Default delimiters: comma and newline
		String numPart = numbers; // Check for custom delimiter at the beginning
		if (numbers.startsWith("//")) {
			int delimiterEnd = numbers.indexOf("\n");
			delimiter = numbers.substring(2, delimiterEnd);
			numPart = numbers.substring(delimiterEnd + 1);
		} // Split the numbers based on the delimiters
		String[] nums = numPart.split(delimiter); // Parse and sum up numbers
		int sum = 0;
		ArrayList<Integer> negatives = new ArrayList<>();
		for (String num : nums) {
			if (!num.trim().isEmpty()) { // Ensure it's not an empty value
				int number = Integer.parseInt(num.trim());
				if (number < 0) {
					negatives.add(number);
				} else {
					sum += number;
				}
			}
		} // Throw exception for negative numbers
		if (!negatives.isEmpty()) {
			throw new IllegalArgumentException("negative numbers not allowed " + negatives);
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(add("")); // Output: 0
		System.out.println(add("1")); // Output: 1
		System.out.println(add("1,5")); // Output: 6
		System.out.println(add("1\n2,3")); // Output: 6
		System.out.println(add("//;\n1;2")); // Output: 3
		try {
			System.out.println(add("1,-2,3")); // Throws exception
		} catch (Exception e) {
			System.out.println(e.getMessage()); // Output: negative numbers not allowed [-2]
		}
	}
}
