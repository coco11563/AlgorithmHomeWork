package Week_One_Home_Work;

public class Percolation {
	private int[] metallic ;
	private int[] sz ;
	int id[] ;
	int count ;
	int n ;
	public Percolation(int n) {// create n-by-n grid, with all sites blocked
		// TODO Auto-generated constructor stub
		this.n = new Integer(n) ;
		n = n * n;
		metallic = new int[n + 2] ;
		sz = new int [n + 2] ;
		id = new int [n + 2] ;
		count = n + 2 ;
		for(int i = 0 ; i < n + 2 ; i++){//n+1和0点
			metallic[i] = 0 ;
			id[i] = i ;
			sz[i] = 1 ;
		}
	}

	public boolean isFull(int row, int col) {// is site (row i, column j) full?
		// TODO Auto-generated method stub
		if(connected(toNum(row,col) , 0)){
			return true ;
		}
		return false;
	}

	public boolean percolates() { // does the system percolate?
		// TODO Auto-generated method stub
		if(connected(0 , count - 1)){
			return true ;
		}else{
		return false;
		}
	}

	public boolean isOpen(int row, int col) {// is site (row i, column j) open?
		// TODO Auto-generated method stub
		if(metallic[toNum(row , col)] == 1){
			return true ;
		}
		return false ;
	}
	public int toNum(int row , int col){
		return ( row - 1 ) * n + col ;
	}
	public void open(int i, int j) {// open site (row i, column j) if it is not open already
		// TODO Auto-generated method stub
		if(!isOpen(i , j)){ //这个点没开
			metallic[toNum(i , j)] = 1 ;
			if(i == 1){
				checkNearby(i , j);
				union(0 , toNum(i , j));
				System.out.println(connected(count-1, toNum(i , j)));
				System.out.println(connected(0, toNum(i , j)));
			}else if(i == n){
				checkNearby(i , j);
				union(count - 1 , toNum(i , j));
				System.out.println(connected(count-1, toNum(i , j)));
				System.out.println(connected(0, toNum(i , j)));
			}else{
				checkNearby(i , j);
			System.out.println(connected(count-1, toNum(i , j)));
			System.out.println(connected(0, toNum(i , j)));
			}
			
			
		}
		
	}
	public void checkNearby(int i , int j){
		if((i -1) > 0&&isOpen(i-1 , j)){//上面的是开的
			union(toNum(i-1 , j) , toNum(i , j)); //就把上面的根与这个点相连
		}if((i+1) <= n&&isOpen(i+1 , j)){//下面的是开的
			union(toNum(i+1 , j) , toNum(i , j)); //就把下面的根与这个点相连
		}if((j - 1) > 0&& isOpen(i , j-1) ){//左面的是开的
			union(toNum(i , j-1) , toNum(i , j)); //就把左边的根与这个点相连
		}if((j + 1) <= n&&isOpen(i , j+1) ){//右面的是开的
			union(toNum(i , j+1) , toNum(i , j)); //就把右边的根与这个点相连
		}
	}

	public int getRoot(int num){
		while(num!=id[num]){
//			id[num] = id[id[num]]; //如果每次都是根连根或者二级连二级那么路径压缩就没有意义了
			num = id[num];
		}
		return num ;
	}
	public void union(int num1 , int num2){
		int pid = getRoot(num1) ; 
		int qid = getRoot(num2) ;
		if(sz[pid] < sz[qid]){id[pid] = qid ; sz[qid] = sz[qid] + sz[pid];}//pid树小则把小树连到大树上
		else{id[qid] = pid ; sz[pid] = sz[qid] + sz[pid];}
	}
	public boolean connected(int num1 , int num2){
		if(getRoot(num1) == getRoot(num2)){
			return true;
		}
		else {
			return false;
		}
	}
	public static void main(String args[]){// test client (optional)
		Percolation per = new Percolation(10);
		PercolationVisualizer.draw(per,10);
	}
}
