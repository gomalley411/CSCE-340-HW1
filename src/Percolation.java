public class Percolation {

	
	private boolean[][] sites;
	private int mySize;
	private WeightedQuickUnionUF myPerc;
	private WeightedQuickUnionUF myFull;
	private int myTop, myBottom;
	
	public Percolation(int size) { // create size-by-size grid, with all sites blocked
		if (size <= 0) throw new IllegalArgumentException();
		sites = new boolean[size][size];
		mySize = size;
		myPerc = new WeightedQuickUnionUF(mySize * mySize + 2);
		myFull = myPerc;
		myTop = mySize * mySize;
		myBottom = myTop + size;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sites[i][j] = false;
			}
		}
		
	}
	/**
	 * returns the index of a given set of coordinates
	 * @param i
	 * @param j
	 * @return
	 */
	private int getIndex(int i, int j) {
		if (i < 1 || i > mySize || j < 1 || j > mySize) {
			throw new IndexOutOfBoundsException();
		}
		return (i-1)*mySize + (j-1);
	}
	
	/**
	 * open site (row i, column j) if it is not open already
	 * @param i = row
	 * @param j = column
	 */
	public void open (int i, int j) {
		validate(i, j);
		if (!sites[i][j]) 
			sites[i][j] = true; // sites[i][j] is now open
		
		int index = getIndex(i, j);
		
		// if opening top row, connect to virtual top
		if (i == 1) {
			myPerc.union(myTop, index);
			myFull.union(myTop, index);
		}
		if (i == mySize) myPerc.union(myBottom, index);
	}
	
	/**
	 * is site (row i, column j) open?
	 * @param i = row
	 * @param j = column
	 * @return
	 */
	public boolean isOpen(int i, int j) {
		if (i < 0 || j < 0 || i >= mySize || j >= mySize) return false; // handles if array would go out of bounds
		return sites[i][j];
	}
	
	/**
	 * is site (row i, column j) full?
	 * @param i = row
	 * @param j = column
	 * @return
	 */
	public boolean isFull(int i, int j) {
		validate(i, j);
		// a full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) sites
		return myFull.connected(myTop, getIndex(i, j));
	}
	
	/**
	 * does the system percolate?
	 * @return
	 */
	public boolean percolates() {
		// see explanation in assignment pdf file to know what percolation means
		return myPerc.connected(myTop, myBottom);
	}
	
	public void validate(int i, int j) {
		if (i < 0 || i >= mySize) {
			throw new IllegalArgumentException("open(): index " + i + " is not between 0 and " + (mySize-1));
		}
		else if (j < 0 || j >= mySize) {
			throw new IllegalArgumentException("open(): index " + j + " is not between 0 and " + (mySize-1));
		}
	}
}

	
