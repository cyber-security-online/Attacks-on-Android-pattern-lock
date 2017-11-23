import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author André Dalwigk
 *
 */
public class RainbowTable {

	/**
	 * Character space for hex numbers.
	 */
	private final static char[] CHARACTER_SPACE = "0123456789ABCDEF".toCharArray();

	/**
	 * List with cell objects.
	 */
	private final List<Cell> cells;

	/**
	 * List with patterns encoded as Strings.
	 */
	private final List<String> patterns;

	/**
	 * File reader.
	 */
	private final BufferedReader reader;

	/**
	 * File writer for String.
	 */
	private final PrintWriter writer;

	/**
	 * Ctor for the RainbowTable object.
	 * 
	 * @param path_to_valid_patterns
	 *            Path to the file containing all valid patterns.
	 * @param path_to_hash_file
	 *            Location of the hash file.
	 * @throws IOException
	 */
	public RainbowTable(final String path_to_valid_patterns, final String path_to_hash_file) throws IOException {
		cells = new ArrayList<>();
		patterns = new ArrayList<>();
		reader = new BufferedReader(new FileReader(path_to_valid_patterns));
		writer = new PrintWriter(path_to_hash_file);
		// Create list with cell objects.
		for (int xPos = 0; xPos < 3; xPos++) {
			for (int yPos = 0; yPos < 3; yPos++) {
				cells.add(new Cell(xPos, yPos));
			}
		}
		// Load all valid pattern Strings from the pattern file.
		String line;
		while ((line = reader.readLine()) != null) {
			patterns.add(line);
		}
		reader.close();
	}

	/**
	 * Create and save the rainbow table.
	 */
	public void save() {
		String data = "";
		for (final String pattern : patterns) {
			data = pattern + ";" + pattern_to_hash_string(create_pattern_from_string(pattern));
			writer.println(data);
		}
		writer.close();
	}

	/**
	 * Method to create a sample gesture.key file.
	 * 
	 * @param path
	 *            Path where the file should be saved.
	 * @param pattern
	 *            Pattern to create the gesture.key file from.
	 * @throws IOException
	 */
	public void create_sample_gesture_file(final String path, final List<Cell> pattern) throws IOException {
		final FileOutputStream fos = new FileOutputStream(path);
		fos.write(PatternUtility.patternToHash(pattern));
		fos.close();
	}

	/**
	 * Create a pattern (List<Cell>) from a String.
	 * 
	 * @param string_pattern
	 *            String representation of a pattern (to convert).
	 * @return Converted String.
	 */
	private List<Cell> create_pattern_from_string(final String string_pattern) {
		final List<Cell> pattern = new ArrayList<>();
		for (int pos = 0; pos < string_pattern.length(); pos++) {
			pattern.add(cells.get(Integer.parseInt(string_pattern.charAt(pos) + "") - 1));
		}
		return pattern;
	}

	/**
	 * Convert a pattern to a sha-1 hash String.
	 * 
	 * @param pattern
	 *            Pattern to convert.
	 * @return Hex String
	 */
	private String pattern_to_hash_string(final List<Cell> pattern) {
		return bytes_to_hex_string(PatternUtility.patternToHash(pattern));
	}

	/**
	 * Convert a byte array to a hex String.
	 * 
	 * @param bytes
	 *            Byte array to convert.
	 * @return Conveted byte array.
	 */
	private static String bytes_to_hex_string(final byte[] bytes) {
		char[] hexChars = new char[2 * bytes.length];
		for (int counter = 0; counter < bytes.length; counter++) {
			int v = bytes[counter] & 0xFF;
			hexChars[2 * counter] = CHARACTER_SPACE[v >>> 4];
			hexChars[2 * counter + 1] = CHARACTER_SPACE[v & 0x0F];
		}
		return new String(hexChars);
	}
}