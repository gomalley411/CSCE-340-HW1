public class Percolation{
    
    private boolean sites[][];
    private int mySize;
    private WeightedQuickUnionUF myPerc;
    private WeightedQuickUnionUF myFull;
    private int myTop, myBottom; 
    
    public Percolation(int size){ // create size by size grid, with all sites blocked
        
        if(size<=0) throw new IllegalArgumentException();
        
        sites=new boolean[size][size];
        mySize=size; 
        myPerc=new WeightedQuickUnionUF(mySize * mySize + 2);
        myFull=myPerc; 
        myTop=mySize*mySize;
        myBottom=myTop+mySize;
        
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                sites[i][j]=false;
            }
        }
    }
    
    private int getIndex(int i, int j){
        
        if(i<0 || i>=mySize || j<0 ||j >=mySize) 
            throw new IndexOutOfBoundsException();
        
        return i*mySize+j;
    }
    
    public void open(int i, int j){
        validate(i, j);
        if(!sites[i][j])
            sites[i][j]=true; 
        
        int index=getIndex(i,j);
        
        if(i==0){
            myPerc.union(myTop, index);
            myFull.union(myTop, index);
        }
        if(i==mySize-1) 
            myPerc.union(myBottom, index);
        
    }
    
    //returns truth value of the given site, where true is open
    public boolean isOpen(int i, int j){
        if(i<0 || j<0 || i>=mySize || j>=mySize)
            return false; //I still think it should be IndexOutOfBoundsException
        return sites[i][j];
    }
    
    public boolean isFull(int i, int j){
        validate(i, j);
        return myFull.connected(myTop, getIndex(i, j));
    }
    
    public void validate(int i, int j) {
	if (i < 0 || i >= mySize) {
            throw new IllegalArgumentException("open(): index " + i + " is not between 0 and " + (mySize-1));
	}
	else if (j < 0 || j >= mySize) {
            throw new IllegalArgumentException("open(): index " + j + " is not between 0 and " + (mySize-1));
	}
    }
    
    public void print() {
	for (int i = 0; i < mySize; i++) {
            for (int j = 0; j < mySize; j++) {
		if (sites[i][j]) 
                    System.out.print("O");
		else 
                    System.out.print("X");	
		}
            System.out.println();
        }
        System.out.println();
    }  
    
    public boolean percolates(){
        return myPerc.connected(myTop, myBottom);
    }
}
