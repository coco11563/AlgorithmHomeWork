public class Percolation {
	private int[] metallic;
	private int[] sz;
	private int id[];
	private int count;
	private int n;
	private int[] percolation;
	public Percolation(int n) {// create n-by-n grid, with all sites blocked
		// TODO Auto-generated constructor stub
		int num = Integer.valueOf(n);
		if (n < 1) throw new IllegalArgumentException("Illeagal Argument");
		this.n = num;
		int size = num * num;
		metallic = new int[size + 2];
		sz = new int [size + 2];
		id = new int [size + 2];
		percolation = new int [num];
		count = size + 2;
		for (int i = 0; i < size + 2; i++) {
			metallic[i] = 0;
			id[i] = i;
			sz[i] = 1;
		}
		for (int i = 0 ; i < num ; i++) {
			percolation[i] = 0;
		}
	}


	public boolean isFull(int row, int col) {// is site (row i, column j) full?
		// TODO Auto-generated method stub
		if (row == n) {
			if (percolation[col] != 1) {
				if (checkPer(row , col)) {
					return true;
				}
			}
		}
		else if (connected(toNum(row, col), 0)) {
			return true;
		}
		return false;
	} 
	
	private boolean checkPer(int i , int j) {
		if ((i - 1) > 0 && connected(toNum(i - 1, j) , 0)) {
			return true; 
		}if ((j - 1) > 0 && connected(toNum(i, j - 1) , 0)) {
			return true;
		} if ((j + 1) > 0 && connected(toNum(i, j + 1) , 0)) {
			return true;
		}
		return false;
	}
	public boolean percolates() { // does the system percolate?
		// TODO Auto-generated method stub
		if (connected(0, count - 1)) {
			return true;
		} else {
		return false;
		}
	}

	public boolean isOpen(int row, int col) {// is site (row i, column j) open?
		// TODO Auto-generated method stub
		if (metallic[toNum(row, col)] == 1) {
			return true;
		}
			return false;
		
	}
	private int toNum(int row, int col) {
		 if (row <= 0 || row > n) 
			 throw new IndexOutOfBoundsException("row i out of bound");
	     if (col <= 0 || col > n) 
	    	 throw new IndexOutOfBoundsException("column j out of bound");
		return (row - 1) * n + col;
	}
	public void open(int i, int j) {// open site (row i, column j) if  it is not open already
		// TODO Auto-generated method stub
		if (!isOpen(i, j)) { 
			metallic[toNum(i, j)] = 1;
		if (i == 1) {
			checkNearby(i, j);
			union(0, toNum(i, j));
		}
		if (i == n) {
			checkNearby(i, j);
			union(count - 1, toNum(i, j));
			percolation[j] = 1;
		} else {
			checkNearby(i, j);
		}
		}
	}
		
	private void checkNearby(int i, int j) {
		if ((i -1) > 0 && isOpen(i-1, j)) {
			union(toNum(i-1, j), toNum(i, j)); 
		} if ((i+1) <= n && isOpen(i+1, j)) {
			union(toNum(i+1, j), toNum(i, j)); 
		} if ((j - 1) > 0 && isOpen(i, j-1)) {
			union(toNum(i, j-1), toNum(i, j)); 
		} if ((j + 1) <= n && isOpen(i, j+1)) {
			union(toNum(i, j+1), toNum(i, j)); 
		}
	}

	private int find(int num) {
		while(num != id[num]) {
		id[num] = id[id[num]]; 
			num = id[num];
		}
		return num;
	}
	private void union(int num1, int num2) {
		int pid = find(num1); 
		int qid = find(num2);
		if (sz[pid] < sz[qid])
			{
				id[pid] = qid; 
				sz[qid] = sz[qid] + sz[pid];
			}
		else {
			id[qid] = pid; 
			sz[pid] = sz[qid] + sz[pid];
		}
	}
	private boolean connected(int num1, int num2) {
		if (find(num1) == find(num2)) {
			return true;
		}
		else {
			return false;
		}
	}
	public static void main(String args[]) {// test client (optional)
	}
}
