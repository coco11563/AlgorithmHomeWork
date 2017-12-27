package UF;

public class MonteCarlo {
	public static boolean check(double x , double y ){
	if (x * x + y * y > 1){
		return false;
	}
	return true;
	}
	public static void main(String args[]){
		int i = 0 ;
		int t = 0 ;
		while(i < 100000000){
			if(check(Math.random() , Math.random())){
				i++ ; 
				t ++ ;
			}
			else{
				i ++ ;
				
			}
		}
		System.out.println((double)(4.0 *t)/ i );  
	}
}
