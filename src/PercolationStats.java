import java.util.Arrays;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
//	private List<Percolation> per;
	private double trial[];
	public PercolationStats(int n, int trials)   // perform trials independent experiments on an n-by-n grid
	{
		if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Arguments out of bound");
		trial = new double[trials];
		Percolation per ;
		for (int j = 0; j < trials; j++) {
			per = new Percolation(n);
			int i = 0;
			while (!per.percolates()) {
				int row = StdRandom.uniform(n)+1;
				int col = StdRandom.uniform(n)+1;
				if (!per.isOpen(row, col)) {
					per.open(row, col);
					i++;
				} 
			} 
			trial[j] = ((double) i)/((double)(n * n));
		}

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
		int arg1 = Integer.parseInt(args[0]); // element num
		int arg2 = Integer.parseInt(args[1]); // trial
		PercolationStats pc = new PercolationStats(arg1, arg2);
		Percolation per ;
		for (int j = 0; j < arg2; j++) {
			per = new Percolation(arg1) ;
			int i = 0;
			while (!per.percolates()) {
				int row = StdRandom.uniform(arg1)+1;
				int col = StdRandom.uniform(arg1)+1;
				if (!per.isOpen(row, col)) {
					per.open(row, col);
					i++;
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
