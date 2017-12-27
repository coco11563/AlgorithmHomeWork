package UF;
/*
 * Union-find with specific canonical(权威的) element. 
 * Add a method find() to the union-find data type so that 
 * find(i) returns the largest element in the connected component containing i. 
 * The operations, union(), connected(), and find() should all take logarithmic(对数的) time or better.
 * For example, if one of the connected components is {1,2,6,9}, 
 * then the find() method should return 9 for each of the four 
 * elements in the connected components.
 * 
 */
public class UnionFind_Question_2 extends UF_sample_3{

	public UnionFind_Question_2(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getRoot(int num){//路径压缩
		while(num!=id[num]){
			id[num] = id[id[num]];
			num = id[num];
		}
		return num ;
	}
	public int find(int i){
		int root = getRoot(i);
		int temp = i ;
		for(int j = i ; j < count ; j ++){
			if(getRoot(id[j]) == root){
				temp = max( i , j);
			}
		}
		return temp;
	}
	public int max(int i , int j ){
		int ret = i > j ? i : j;
		return ret ;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UnionFind_Question_2 uf = new UnionFind_Question_2(11);
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
//		uf.union(7, 3);
		uf.println();
		int i = uf.find(10);
		System.out.println(i);
	}

}
