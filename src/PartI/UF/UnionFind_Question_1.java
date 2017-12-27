package UF;

import edu.princeton.cs.introcs.StdOut;

/*
 * Given a social network containing n members and a log file 
 * containing m timestamps at which times pairs of members formed 
 * friendships, design an algorithm to determine the earliest 
 * time at which all members are connected 
 * (i.e., every member is a friend of a friend of a friend ... of a friend). 
 * Assume that the log file is sorted by timestamp and that friendship is an equivalence(等价的) relation. 
 * The running time of your algorithm should be mlogn or better 
 * and use extra space proportional to n.
 */
public class UnionFind_Question_1 {
	
	private int[] member ;
	private int[] sz ;
	int count ;
	int[][] step ; //用来存做朋友的步骤
	String[] timestamp ; //用来存时间轴
	public UnionFind_Question_1(int n){
		member = new int[n];
		sz = new int[n];
		count = n ;
		for(int i = 0 ; i < n ; i++){
			member[i] = i ;
			sz[i] = 1 ;
		}
	}
	public int getRoot(int num){
		while(num!=member[num]){
			member[num] = member[member[num]];//路径压缩
			num = member[num];
		}
		return num ;
	}
	public void union(int num1 , int num2){
		int pid = getRoot(num1) ; 
		int qid = getRoot(num2) ;
		if(pid != qid){//成立则是一个有效的链接，总群体数量-1
			count -- ;
		}
		if(sz[pid] < sz[qid]){member[pid] = qid ; sz[qid] = sz[qid] + sz[pid];}
		else{member[qid] = pid ; sz[pid] = sz[qid] + sz[pid];}
	}
	public void println(){
		StringBuilder str = new StringBuilder();
		for(int i = 0 ; i < member.length ; i++){
			str.append(member[i] +",");
		}
		StdOut.println(str.toString());
	}
	public void read( String path){
		//这里返回读取的值
	}
	public static void main(String args[]){
		UnionFind_Question_1 uq= new UnionFind_Question_1(100);//100个人
		int i = 0 ;
		int fri_1 ;
		int fri_2 ;
		while(uq.count != 1){
			fri_1 = uq.step[i][1];
			fri_2 = uq.step[i][0];
			uq.union(fri_1, fri_2);
			i++ ;
		}
		System.out.println(uq.timestamp[i]); //输出全链接的时间
	}
}
