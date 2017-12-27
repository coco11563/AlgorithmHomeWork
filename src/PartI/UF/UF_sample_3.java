package UF;

import edu.princeton.cs.introcs.StdOut;

public class UF_sample_3 {
	/**
	 * now we try to make the Quick-Union tree more short
	 * 
	 * Modify quick-union to avoid tall trees
	 * 
	 * Keep track of size of each tree(number of object)
	 * 
	 * Balance by ** linking root of smaller tree to roof of large trss. **
	 * 
	 * WEIGHT UNION
	 * 
	 * Initialized N
	 * 
	 * Union lgN ↑
	 * 
	 * Connected lgN
	 */
	public int[] sz ; //size tree to store the number of son that every roots have 
	public int[] id ;
	public int count ;
	public UF_sample_3(int n){
		id = new int[n];
		sz = new int[n];
		count = n ;
		for(int i = 0 ; i < n ; i++){
			id[i] = i ;
			sz[i] = 1 ;
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
			StdOut.println(num1 + ","+ num2 + " they are connected");
			return true;
		}
		
		else {
			StdOut.println(num1 + ","+ num2 + " they are not connected");
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
		UF_sample_3 uf = new UF_sample_3(10);
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
		uf.connected(5, 0);
		uf.println();
		uf.union(7, 2);
		uf.println();
		uf.union(6, 1);
		uf.println();
		uf.union(7, 3);
		uf.println();
	}
}
