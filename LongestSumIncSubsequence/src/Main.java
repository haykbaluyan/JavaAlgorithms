import java.util.LinkedList;


public class Main {

	static int allMax=0;
	static int indexMax=0;
	static LinkedList<LinkedList<Integer>> keepSubseq=new LinkedList<LinkedList<Integer>>();
	public static int MaxSumIncSubseq(int []elems, int n){

		if(n==0){

			if(allMax<elems[n]){
				allMax=elems[n];
				indexMax=0;
			}
	

			LinkedList<Integer> temp=new LinkedList<Integer>();
			temp.add(elems[n]);
			keepSubseq.set(0,temp);
			
			return elems[n];
		}

		int maxForN=elems[n];
		
		LinkedList<Integer> temp=new LinkedList<Integer>();
		temp.add(elems[n]);
		keepSubseq.set(n, temp);
		for(int j=0;j<n;j++){
			int resForJ=MaxSumIncSubseq(elems,j);
			if(elems[j]<elems[n] && maxForN<resForJ+elems[n]){
					temp=new LinkedList<Integer>();
					temp.addAll(keepSubseq.get(j));
					temp.add(elems[n]);
					keepSubseq.set(n, temp);
					maxForN=resForJ+elems[n];	
			}
		}
		if(allMax<maxForN){
			allMax=maxForN;
			indexMax=n;
		}
		return maxForN;
	}
	public static void MaxSumIncSubseqDP(int []elems){
		int totalMax=0;
		int L[]=new int[elems.length];
		int track[]=new int[elems.length];
	
		for(int i=0;i<elems.length;i++){
			L[i]=elems[i];
			track[i]=i;
		}
		
		for(int i=1;i<elems.length;i++){
			
			
			for(int j=0;j<i;j++){
				if(elems[j]<elems[i] && L[i]<elems[i]+L[j]){
					L[i]=elems[i]+L[j];
					track[i]=j;
				}
			}
			

			if(totalMax<L[i]){
				totalMax=L[i];
				
				
			}
		
		}
	
		System.out.println(totalMax);
		printTrack(indexMax,elems,track);
		System.out.println('\n');
		
		
	}
	public static void printTrack(int index,int []elems,int []track){
		
		if(index==track[index]){
			System.out.print(elems[index]+" ");
			return;
		}
		printTrack(track[index],elems,track);
		System.out.print(elems[index]+" ");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
		
		for(int i=0;i<arr.length;i++){
			keepSubseq.add(new LinkedList<Integer>());
		}
		MaxSumIncSubseq(arr,arr.length-1);
		System.out.println(allMax);
		System.out.println(keepSubseq.get(indexMax));
		
		MaxSumIncSubseqDP(arr);
		
		
		
	}

}
