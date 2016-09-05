package Week_One_Home_Work;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
	Percolation per ;
	double trial[] ;
	int n ;
	public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
	{
		this.n = n ;
		per = new Percolation(n);
		trial = new double[trials] ;
	}
	public void refresh(){
	per = new Percolation(n);
	}
	   public double mean()                          // sample mean of percolation threshold
	   {
		   return StdStats.mean(trial);
	   }
	   public double stddev()                        // sample standard deviation of percolation threshold
	   {
		   return StdStats.stddev(trial);
	   }
	   public double confidenceLo()                  // low  endpoint of 95% confidence interval
	   {
		   int length = trial.length;
		   double[] temp = trial.clone();
		   Arrays.sort(temp);
		   return temp[(int) (length * 0.05)];
	   }
	   public double confidenceHi()                  // high endpoint of 95% confidence interval
	   {
		   int length = trial.length;
		   double[] temp = trial.clone();
		   Arrays.sort(temp);
		   return temp[(int) (length * 0.95)];
	   }
	public static void main(String[] args)    // test client (described below)
	{
		int arg1 = Integer.parseInt(args[0]) ; // element num
		int arg2 = Integer.parseInt(args[1]) ; // trial
		PercolationStats pc = new PercolationStats(arg1,arg2);
		for(int j = 0 ; j < arg2 ; j ++){
			pc.refresh();
			int i = 0 ;
			while(!pc.per.percolates()){
				
				int row = StdRandom.uniform(arg1)+1;
				int col = StdRandom.uniform(arg1)+1;
				if(!pc.per.isOpen(row, col)){
					pc.per.open(row, col);
					i ++ ;
				}
				
			}
			pc.trial[j] = ((double) i)/((double)(arg1 * arg1));
			System.out.println(pc.trial[j]);
		}
		System.out.println("mean" + "\t" + "= " + pc.mean());
		System.out.println("stddev" + "\t" + "= " + pc.stddev());
		System.out.println("95% confidence interval" + "\t" + "= " + pc.confidenceHi() + "," + pc.confidenceLo());
	}
}
