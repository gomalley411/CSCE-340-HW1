// George O'Malley
// Rachel McMullan

import java.util.Random;


public class Percolation {
	// refer to textbook or to Prof. Wu 
	// if help is needed
	
	private boolean[][] sites;
	private int mySize, myTop, myBottom;
	private WeightedQuickUnionUF myPerc;
	private WeightedQuickUnionUF myFull;
	
	public Percolation(int size) { // create size by size grid, with all sites blocked
		if (size <= 0) throw new IllegalArgumentException();
		sites = new boolean[size][size];
		mySize = size;

		myPerc = new WeightedQuickUnionUF(mySize * mySize + 2);
		myFull = new WeightedQuickUnionUF(mySize * mySize + 2);
		myTop = mySize * mySize;
		myBottom = mySize + mySize + 1;

		myTop = size * size;
		myBottom = myTop + size;
		myPerc = new WeightedQuickUnionUF(size * size + 2);
		myFull = new WeightedQuickUnionUF(size * size + 2);

		
		//initialize all sites as false, i.e. blocked
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sites[i][j] = false;
			}
		}
		
		randomize();
		
	}
	/**
	 * returns the corresponding 1-D index of 2-D coordinates   
	 * @param i
	 * @param j
	 * @return
	 */
	private int getIndex(int i, int j) {
		if (i < 0 || i >= mySize)
			throw new IndexOutOfBoundsException("index " + i + " is not between 0 and " + (mySize-1));
		if(j < 0 || j > mySize)
			throw new IndexOutOfBoundsException("index " + j + " is not between 0 and " + (mySize-1));
		return i*mySize + j;
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

			myPerc.union(myTop, 0);
			myFull.union(myTop, 0);


		}
		// if opening bottom row, connect to virtual bottom
		if (i==mySize-1) 
			myPerc.union(myBottom, index); // might wanna check this one too after we fix the above error
	}
	
	/**
	 * is site (row i, column j) open?
	 * @param i = row
	 * @param j = column
	 * @return
	 */
	public boolean isOpen(int i, int j) {
		if (i < 0 || i >= mySize) 
			throw new IndexOutOfBoundsException("index " + i + " is not between 0 and " + (mySize-1));
		if(j<0 || j>=mySize)
			throw new IndexOutOfBoundsException("index " + j + " is not between 0 and " + (mySize-1));				    
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
		System.out.println("Top: " + myTop + "\nBottom: " + myBottom);
		return myPerc.connected(0, mySize);
	}
	
	/** 
	 * validates a pair of coordinates. I'm aware a function like this in QuickFindUF, but since I might decide
	 * to change this function later I wanted to make a spare one here, just in case
	 * @param i
	 * @param j
	 */
	public void validate(int i, int j) {
		if (i < 0 || i >= mySize) {
			throw new IllegalArgumentException("index " + i + " is not between 0 and " + (mySize-1));
		}
		else if (j < 0 || j >= mySize) {
			throw new IllegalArgumentException("index " + j + " is not between 0 and " + (mySize-1));
		}
	}
	
	/**
	 * prints out the contents of the percolation to determine visually which sites are open
	 */
	public void print() {
		System.out.print("j:   ");
		for (int j = 0; j < mySize; j++) {
			System.out.print(j);
		}
		System.out.println();
		for (int i = 0; i < mySize; i++) {
			System.out.print("i: " + i + " ");
			for (int j = 0; j < mySize; j++) {
				if (sites[i][j]) System.out.print("O");
				else System.out.print("X");
				
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * keeps opening random sites until it percolates.
	 * before execution, assume all sites are blocked
	 */
	public void randomize() {
		// assumes all sites are blocked
		
		while (!this.percolates()) {
			Random random = new Random();
			int i = random.nextInt(mySize);
			int j = random.nextInt(mySize);
			System.out.println("(" + i + ", " + j+ ")");
			if (!isOpen(i, j)) open(i, j);
			print();
		}
	}
}

	
