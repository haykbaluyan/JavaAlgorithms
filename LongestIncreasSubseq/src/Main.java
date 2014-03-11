import java.util.ArrayList;


public class Main {

	static int max;


	public static int LCSRec(int[] elems, int end){
		
		if(end==0){
		
			
			return 1;
		}
		int maxEndHere=1;
		
	
	
		for(int i=0;i<end;i++){
			
			
			int curMax=LCSRec(elems,i);
			
			if(elems[end]>elems[i] && maxEndHere<curMax+1){
			
			
			
				maxEndHere=curMax+1;

				
			
			}
			
			
		}
		if(max<maxEndHere){
		
		
			
			max=maxEndHere;
		}
	
	
		return maxEndHere;
		
	}
	
	
	public static void LCSDynProg(int[] elems){
		
		int [] lis=new int[elems.length];
		int [] track=new int[elems.length];
		for(int i=0;i<lis.length;i++){
			lis[i]=1;
		}
		for(int i=0;i<lis.length;i++){
			track[i]=i;
		}
		
		for(int i=1;i<elems.length;i++){
			for(int j=0;j<i;j++){
				System.out.println(i+"v"+j);
				if(elems[i]>elems[j] && lis[i]<lis[j]+1){
					lis[i]=lis[j]+1;
					track[i]=j;
				}
			}
		}
		for(int i=0;i<lis.length;i++){
			System.out.print(lis[i]+" ");
		}
		System.out.println();
		for(int i=0;i<track.length;i++){
			System.out.print(i+" ");
		}
		System.out.println();
		for(int i=0;i<track.length;i++){
			System.out.print(track[i]+" ");
		}
		System.out.println();
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int []elems=  { 10, 22, 9, 33, 21, 50, 41, 60, 80 }  ;
	
		
		System.out.println(LCSRec(elems,elems.length-1));
		LCSDynProg(elems);
	
	}

}
