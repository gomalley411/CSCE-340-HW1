
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Stopwatch s = new Stopwatch();
		Percolation p = new Percolation(5);
		p.print();
		p.open(1, 3);
		System.out.println("Open" + p.isOpen(1, 3));
		System.out.println("Full: " + p.isFull(1, 3));
		p.open(0, 3);
		p.open(2, 3);
		p.open(3, 3);
		p.print();
		System.out.println("Full: "+ p.isFull(3, 3)); // why is this false, should be true
		System.out.println("Time:\t"+ s.elapsedTime() +" seconds");
	}

}
