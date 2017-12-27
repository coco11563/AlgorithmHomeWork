package UF;

import edu.princeton.cs.introcs.StdOut;

public class UF_sample_2 {
	/**
	 * Initialize N
	 * 
	 * Union N ↑
	 * 
	 * Find N
	 * 
	 * Tree could be too tall - so the connected method maybe too slow
	 * 
	 * Find is too expensive - could be N array accesses(worst situation)
	 * 
	 * @param args
	 */
	
	public int[] id ;
	public int count ;
	public UF_sample_2(int n){
		id = new int[n];
		count = n ;
		for(int i = 0 ; i < n ; i++){
			id[i] = i ;
		}
	}
	public void union(int num1 , int num2){
		id[getRoot(num1)] = getRoot(num2) ; //单个节点的根连接到单个点的根处，而不是单纯的连到单个点
	}
	public int getRoot(int num){
		int m = num ;
		while(id[m]!=m){
			m = id[m];
		}
		return m;
	}
	public boolean connected(int num1 , int num2){
		if(getRoot(num1) == getRoot(num2)){
			StdOut.println(num1 + " , "+ num2 + " they are connected");
			return true;
		}
		
		else {
			StdOut.println(num1 + " , "+ num2 + " they are not connected");
			return false;
		}
	}
	public void println(){
		StringBuilder str = new StringBuilder();
		for(int i = 0 ; i < count ; i++){
			str.append(id[i] +",");
		}
		StdOut.println(str.toString());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UF_sample_2 uf = new UF_sample_2(10);
		uf.union(4, 3);
		uf.println();
		uf.union(3, 8);
		uf.println();
		uf.union(6, 5);
		uf.println();
		uf.union(9, 4);
		uf.println();
		uf.union(2, 1);
		uf.println();
		uf.union(8, 9);
		uf.println();
		uf.connected(5, 0);
		uf.union(5, 0);
		uf.println();
		uf.union(7, 2);
		uf.println();
		uf.union(6, 1);
		uf.println();
		uf.union(7, 3);
		uf.println();
	}

}
