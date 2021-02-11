//Rachel McMullan
//George O'Malley 

import java.util.Random;
import java.lang.Math;

public class PercolationStats {
    
    private int size, trials;   //grid dimensions and number of experiments
    private double[] thresholds;//percolation thresholds for each trial
    private Random random;
    private Percolation grid;   //current system
    
    //perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T){
        
        //validate arguments
        if(N<=0){
            throw new IllegalArgumentException(Integer.toString(N));}
        if(T<=0){
            throw new IllegalArgumentException(Integer.toString(T));}
        
        //initialize global fields 
        size=N; 
        trials=T;
        thresholds=new double[T];
        
        int count;  //number of sites opened before percolation 
        
        //loop repeats the experiment T times 
        for(int t=0; t<T; t++){
            
            //reset upon each iteration 
            count=0; 
            grid=new Percolation(size);
            
            //loop repeats until system percolates
            while(!grid.percolates()){
                
                System.out.println(count);
                
                //open a site at a random index
                getOpen(random.nextInt(size), random.nextInt(size));
                    
                count++; //update number of open sites
            }
            
            //calculate/store percolation threshold of the current experiment 
            thresholds[t]= count/(double)(size*size);
        }
    }
    
    //open a blocked site at a random index
    private boolean getOpen(int i, int j){
        
        //base case: site is blocked
        if(!grid.isOpen(i, j)){
            grid.open(i, j);
            System.out.println(i+" "+j);
            return true;
        }
        //recursive case: site at index is open, generate another index
        else{
            return getOpen(random.nextInt(size), random.nextInt(size));   
        }
    }
    
    //sample mean of percolation threshold
    public double mean(){ 
        
        int total=0; 
        for(int t=0; t<trials; t++){
            total+=thresholds[t];
        }
        return total/trials;
    }
    
    //sample standard deviation of percolation threshold
    public double stddev(){
        
        double sum=0; 
        for(int t=0; t<trials; t++){
            sum += Math.pow(thresholds[t]-mean(), 2);
        }
        return Math.sqrt(sum/(trials-1));
    }
    
    //low  endpoint of 95% confidence interval
    public double confidenceLow(){
        
        return mean()-(1.96 * stddev()/Math.sqrt(trials));
    }
    
    //high endpoint of 95% confidence interval
    public double confidenceHigh(){
        return mean()+(1.96 * stddev()/Math.sqrt(trials));
    }
}
