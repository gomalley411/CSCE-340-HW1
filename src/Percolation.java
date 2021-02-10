public class Percolation {
<<<<<<< HEAD
	// refer to textbook or to Prof. Wu 
	// if help is needed, but do NOT copy
=======
>>>>>>> 404eb1b8efc8421fd09e86affe653aacb9b32152
	
	private boolean[][] sites;
	private int mySize;
	private WeightedQuickUnionUF myPerc;
	private WeightedQuickUnionUF myFull;
	private int myTop, myBottom;
	
	public Percolation(int size) { // create size by size grid, with all sites blocked
		if (size <= 0) throw new IllegalArgumentException();
		sites = new boolean[size][size];
		mySize = size;
		myPerc = new WeightedQuickUnionUF(mySize * mySize + 2);
		myFull = myPerc;
		myTop = mySize * mySize;
		myBottom = myTop + mySize; //Rnote: I'm having a hard time understanding this logic
		
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
		if (i < 0 || i >= mySize || j < 0 || j >= mySize) {
			throw new IndexOutOfBoundsException();
		}
		return i * mySize + j; //Rnote: this is really clever 
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
		if (i == 0) {
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
										//Rnote: why not an IndexOutOfBoundsException?
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
	
	/** 
	 * validates a pair of coordinates. I'm aware a function like this in QuickFindUF, but since I might decide
	 * to change this function later I wanted to make a spare one here, just in case
	 * @param i
	 * @param j
	 */
	public void validate(int i, int j) {
		if (i < 0 || i >= mySize) {
			throw new IllegalArgumentException("open(): index " + i + " is not between 0 and " + (mySize-1));
		}
		else if (j < 0 || j >= mySize) {
			throw new IllegalArgumentException("open(): index " + j + " is not between 0 and " + (mySize-1));
		}
	}
}

	
