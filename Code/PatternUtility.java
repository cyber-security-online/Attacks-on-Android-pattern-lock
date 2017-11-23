import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * PatternUtility class encapsulating the function mapping function
 * 'patternToHash'.
 * 
 * @author André Dalwigk
 *
 */
public class PatternUtility {
	/**
	 * Source:
	 * https://android.googlesource.com/platform/frameworks/base.git/+/f02b60aa4f367516f40cf3d60fffae0c6fe3e1b8/core/java/com/android/internal/widget/LockPatternUtils.java
	 * 
	 * @param pattern
	 *            Pattern that
	 * @return SHA-1 hash as a byte array.
	 */
	public static byte[] patternToHash(List<Cell /* type modified */> pattern) {
		if (pattern == null) {
			return null;
		}

		final int patternSize = pattern.size();
		byte[] res = new byte[patternSize];
		for (int i = 0; i < patternSize; i++) {
			Cell /* type modified */ cell = pattern.get(i);
			res[i] = (byte) (cell.getRow() * 3 + cell.getColumn());
		}
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] hash = md.digest(res);
			return hash;
		} catch (NoSuchAlgorithmException nsa) {
			return res;
		}
	}
}