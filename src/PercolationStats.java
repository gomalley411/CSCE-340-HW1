
public class PercolationStats {
	/**
	 * perform t independent experiments on an N-by-N grid
	 * @param n -- size of grid
	 * @param t -- number of independent experiments
	 */
	public PercolationStats(int n, int t) {
		if (n <= 0 || t <= 0) throw new IllegalArgumentException();
		/* take two arguments N and T to perform experiments.
		 * calculate the mean, standard deviation, and both ends of the
		 * 95% confidence interval for perc. threshold.
		 * 
		 * USE A Random CLASS TO GENERATE RANDOM NUMBERS
		 * 
		 * DESIGN AN ALGORITHM TO COMPUTE THE SAMPLE MEAN AND STANDARD DEVIATIOn
		 */
	}
	
	/**
	 * I will design an algorithm to compute the mean
	 * @return the sample mean of percolation threshold
	 */
	public double mean() {
		// write later
	}
	
	/**
	 * I will design an algorithm to compute the standard deviation
	 * @return sample standard deviation of percolation threshold
	 */
	public double stddev() {
		// write later
		
	}
	
	/**
	 * 
	 * @return low endpoint of 95% confidence interval
	 */
	public double confidenceLow() {
		// write later
	}
	
	/**
	 * 
	 * @return high endpoint of 95% confidence interval
	 */
	public double confidenceHigh() {
		// write later
	}
}
