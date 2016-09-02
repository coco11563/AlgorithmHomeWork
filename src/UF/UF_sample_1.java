package UF;

import edu.princeton.cs.introcs.StdOut;

/**
 * QUICK FIND
 * 
 * Initialize N
 * 
 * Union N (too expansive)
 * 
 * find 1
 * 
 * @author coco11563
 *
 */
public class UF_sample_1 {
	public int[] id ;
	public int count ;
	public UF_sample_1(int n){
		id = new int[n];
		count = n ;
		for(int i = 0 ; i < n ; i++){
			id[i] = i ;
		}
	}
	
	public void union(int num1 , int num2){
		int changeNum = id[num1];
		if(num1 > count || num2 > count){
			StdOut.println("wrong input!");
		}else if(id[num1] == id[num2]){
			StdOut.println("same type!");
		}else{
			for(int i = 0 ; i < count ;i ++){
				if(id[i] == changeNum){//取中间值就不会出错啦
					id[i] = id[num2];
				}
			}
			StdOut.println("finish one connection");
		}
	}
	public void connected(int num1 , int num2){
		if(id[num1] == id[num2]){
			StdOut.println(num1 + " , "+ num2 + " they are connected");
		}
		else 
			StdOut.println(num1 + " , "+ num2 + " they are not connected");
	}
	public void println(){
		StringBuilder str = new StringBuilder();
		for(int i = 0 ; i < count ; i++){
			str.append(id[i] +",");
		}
		StdOut.println(str.toString());
	}
	public static void main(String args[]){
		UF_sample_1 uf = new UF_sample_1(10);
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
	}
}
