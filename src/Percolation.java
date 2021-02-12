//Rachel McMullan
//George O'Malley 

import java.util.Random;

public class Percolation {
	
	private boolean[][] sites;
	private int mySize, myTop, myBottom;
	private WeightedQuickUnionUF myPerc;
	private int numSites, numOpen, numFull;
	
	public Percolation(int size) { // create size by size grid, with all sites blocked
		if (size <= 0) throw new IllegalArgumentException();
		sites = new boolean[size][size];
		mySize = size;
		myTop = size * size;
		myBottom = myTop + 1;
		myPerc = new WeightedQuickUnionUF(size * size + 2);
		numSites = size * size;
		numOpen = 0;
		numFull = 0;
		
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
		if(i < 0 || i >= mySize)
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
		if (!sites[i][j]) {
			sites[i][j] = true; // sites[i][j] is now open
			numOpen++;
		}
		int index = getIndex(i, j);
                
                //see if there are any adjacent open sites and connect
                if(i>0 && sites[i-1][j])
                    myPerc.union(index, getIndex(i-1,j));
                //check down: see if (i+1, j) is open
                if(i<mySize-1 && sites[i+1][j])
                    myPerc.union(index, getIndex(i+1, j));
                //check left: see if  (i, j-1) is open 
                if(j>0 && sites[1][j-1])
                    myPerc.union(index, getIndex(i, j-1));
                //check right: see if (i, j+1) is open 
                if(j<mySize-1 && sites[i][j+1])
                    myPerc.union(index, getIndex(i, j+1));
                //(if valid indices) 
                
		// if opening top row, connect to virtual top
		if (i == 0) {
			myPerc.union(myTop, index);
		}
		// if opening bottom row, connect to virtual bottom
		if (i==mySize-1) 
			myPerc.union(myBottom, index); 
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
		boolean myBool = myPerc.connected(myTop, getIndex(i, j));
		if (myBool) numFull++;
		return myBool;
	}
	
	/**
	 * does the system percolate?
	 * @return
	 */
	public boolean percolates() {
		// the system percolates if the bottom row has a full site, i.e. a site that connects to the first row. 
                
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
		for (int i = 0; i < mySize; i++) {
			for (int j = 0; j < mySize; j++) {
				if (sites[i][j]) System.out.print("O");
				else System.out.print("B");	
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * opens random sites
	 */
	public void randomize() {
		Random r = new Random();
		while (!percolates()) {
			int randRow = r.nextInt(mySize);
			int randCol = r.nextInt(mySize);
			open(randRow, randCol);
		}
	}
}
