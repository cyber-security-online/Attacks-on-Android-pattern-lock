/**
 * Source:
 * https://android.googlesource.com/platform/frameworks/base.git/+/f02b60aa4f367516f40cf3d60fffae0c6fe3e1b8/core/java/com/android/internal/widget/LockPatternView.java
 * 
 * @author André Dalwigk
 * @version 1.0
 *
 */
public class Cell {
	int row;
	int column;
	// keep # objects limited to 9
	static Cell[][] sCells = new Cell[3][3];
	static {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				sCells[i][j] = new Cell(i, j);
			}
		}
	}

	/**
	 * @param row
	 *            The row of the cell.
	 * @param column
	 *            The column of the cell.
	 */
	Cell(int row, int column) {
		checkRange(row, column);
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	private static void checkRange(int row, int column) {
		if (row < 0 || row > 2) {
			throw new IllegalArgumentException("row must be in range 0-2");
		}
		if (column < 0 || column > 2) {
			throw new IllegalArgumentException("column must be in range 0-2");
		}
	}
}