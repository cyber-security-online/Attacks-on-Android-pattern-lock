import java.io.IOException;
/**
 * Main program.
 * 
 * @author André Dalwigk
 *
 */
public class Main {
	public static void main(final String... args) throws IOException {
		final String valid_pattern_path = "ValidPatterns.txt";
		final String rainbow_table_path = "AndroidPatternSHA-1.txt";
		Combinations.save_patterns(valid_pattern_path);
		final RainbowTable rainbow_table = new RainbowTable(valid_pattern_path, rainbow_table_path);
		rainbow_table.save();
	}
}