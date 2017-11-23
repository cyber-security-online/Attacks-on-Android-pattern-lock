import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author André Dalwigk
 * @version 1.0
 */
public class Combinations {
	/**
	 * Lower bound for the length of an Android lockscreen pattern.
	 */
	private static final int LOWER_BOUND = 4;

	/**
	 * Upper bound for the length of an Android lockscreen pattern.
	 */
	private static final int UPPER_BOUND = 9;

	/**
	 * Function to create a list containing all (N choose k)*k! subsets of
	 * M={1,2,...,9}, where N=9 and k is variable.
	 *
	 * @param k
	 *            Size of the subsets.
	 * @return List containing all subsets.
	 */
	private static List<String> all_patterns(final int k) {
		// ArrayList containing all possible patterns.
		final List<String> patterns = new ArrayList<>();
		String tmp = "";
		// Calculate value to start with.
		for (int counter = 1; counter <= k; counter++) {
			tmp += counter;
		}
		try {
			int number = Integer.parseInt(tmp);
			// As long as the number is not larger than (length+1) ...
			while (number < Math.pow(10, k)) {
				final String string_number = Integer.toString(number);
				// ... check if there're duplications or zeros.
				if (!has_duplicate(string_number) && !string_number.contains("0")) {
					patterns.add(string_number);
				}
				number++;
			}
		} catch (final NumberFormatException nfe) {
			System.err.println("Error while parsing in function all_patterns(final int length)!");
		}
		return patterns;
	}

	/**
	 * Check if there are any duplicates in to_check.
	 *
	 * @param to_check
	 *            String to check.
	 * @return true, if there is a duplicate in to_check (false otherwise).
	 */
	private static boolean has_duplicate(final String to_check) {
		// HashSet for checking duplicates.
		final Set<Character> container = new HashSet<>();
		for (int index = 0; index < to_check.length(); index++) {
			if (!container.add(to_check.charAt(index))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Function checking, if the String to_check follows all pattern creation
	 * rules.
	 *
	 * @param to_check
	 *            String to check.
	 * @return true, if the String breaks (at least) one rule (false otherwise).
	 */
	private static boolean is_invalid(final String to_check) {
		return to_check.contains("13")
				&& (to_check.indexOf("2") == -1 || (to_check.indexOf("2") > to_check.indexOf("13")))
				|| to_check.contains("31")
						&& (to_check.indexOf("2") == -1 || (to_check.indexOf("2") > to_check.indexOf("31")))
				|| to_check.contains("39")
						&& (to_check.indexOf("6") == -1 || (to_check.indexOf("6") > to_check.indexOf("39")))
				|| to_check.contains("28")
						&& (to_check.indexOf("5") == -1 || (to_check.indexOf("5") > to_check.indexOf("28")))
				|| to_check.contains("82")
						&& (to_check.indexOf("5") == -1 || (to_check.indexOf("5") > to_check.indexOf("82")))
				|| to_check.contains("46")
						&& (to_check.indexOf("5") == -1 || (to_check.indexOf("5") > to_check.indexOf("46")))
				|| to_check.contains("64")
						&& (to_check.indexOf("5") == -1 || (to_check.indexOf("5") > to_check.indexOf("64")))
				|| to_check.contains("93")
						&& (to_check.indexOf("6") == -1 || (to_check.indexOf("6") > to_check.indexOf("93")))
				|| to_check.contains("79")
						&& (to_check.indexOf("8") == -1 || (to_check.indexOf("8") > to_check.indexOf("79")))
				|| to_check.contains("97")
						&& (to_check.indexOf("8") == -1 || (to_check.indexOf("8") > to_check.indexOf("97")))
				|| to_check.contains("71")
						&& (to_check.indexOf("4") == -1 || (to_check.indexOf("4") > to_check.indexOf("71")))
				|| to_check.contains("17")
						&& (to_check.indexOf("4") == -1 || (to_check.indexOf("4") > to_check.indexOf("17")))
				|| to_check.contains("37")
						&& (to_check.indexOf("5") == -1 || (to_check.indexOf("5") > to_check.indexOf("37")))
				|| to_check.contains("73")
						&& (to_check.indexOf("5") == -1 || (to_check.indexOf("5") > to_check.indexOf("73")))
				|| to_check.contains("19")
						&& (to_check.indexOf("5") == -1 || (to_check.indexOf("5") > to_check.indexOf("19")))
				|| to_check.contains("91")
						&& (to_check.indexOf("5") == -1 || (to_check.indexOf("5") > to_check.indexOf("91")));
	}

	/**
	 * Function to calculate all valid patterns.
	 *
	 * @param combinations
	 *            Patterns to modify (= delete all invalid patterns).
	 * @return List containing all valid patterns.
	 */
	private static List<String> combinations(final List<String> combinations) {
		final List<String> patterns = new ArrayList<>();
		for (String combination : combinations) {
			if (!is_invalid(combination)) {
				patterns.add(combination);
			}
		}
		return patterns;
	}

	/**
	 * Function to save all valid patterns to a file.
	 * 
	 * @param path
	 *            Path to the file.
	 * @throws FileNotFoundException
	 *             Exception thrown if file can't be found.
	 */
	public static void save_patterns(final String path) throws FileNotFoundException {
		final PrintWriter out = new PrintWriter(path);
		List<String> tmp_result = new ArrayList<>();
		for (int length = LOWER_BOUND; length <= UPPER_BOUND; length++) {
			tmp_result = combinations(all_patterns(length));
			for (final String pattern : tmp_result) {
				out.println(pattern);
			}
		}
		out.close();
	}
}