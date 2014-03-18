import java.util.ArrayList;


public class Main {

	//exponential since it is using recursion
	static ArrayList<ArrayList<Integer>> cuts=new ArrayList<ArrayList<Integer>>();
	public static int cutRod(int size_Rod, int []price_Pieces){
	
		if(size_Rod==0){
			
			if(cuts.size()==0){
				cuts.add(new ArrayList<Integer>());
			}
			return 0;
		}
		int maxCut=0;
		int maxCutIndex=0;
		for(int i=1;i<=size_Rod;i++){
			int curCut=price_Pieces[i-1]+cutRod(size_Rod-i,price_Pieces);
			if(maxCut<curCut){
				maxCut=curCut;
				maxCutIndex=i;
			}
			
		}
	
		if(cuts.size()<=size_Rod){
			ArrayList<Integer> a=new ArrayList<Integer>();
			a.addAll((ArrayList<Integer>)cuts.get(size_Rod-maxCutIndex));
			a.add(maxCutIndex);
			cuts.add(a);
		}
		return maxCut;
	}
	public static int cutRodDP(int size_Rod,int []price_Pieces){
		int cuts[]=new int[size_Rod+1];
		int track[]=new int[size_Rod+1];
		cuts[0]=0;
		track[0]=0;
		for(int i=1;i<size_Rod+1;i++){
			int max_I=price_Pieces[i-1];
			int max_Index=i;
			for(int j=1;j<=i;j++){
				if(max_I<price_Pieces[j-1]+cuts[i-j]){
					max_I=price_Pieces[j-1]+cuts[i-j];
					max_Index=j;
				}
			}
			cuts[i]=max_I;
			track[i]=max_Index;
		}
		
		
		int n=size_Rod;
		while(n>0){
			System.out.print(track[n]+" ");
			n=n-track[n];
		}
		
		System.out.println();
		
		return cuts[size_Rod];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int price_Pieces[] = {1, 5, 8, 9, 10, 17, 17, 20};
		int size_Rod=price_Pieces.length;
		System.out.println(cutRod(size_Rod,price_Pieces));
		System.out.println(cuts.get(cuts.size()-1));
		
		
		System.out.println(cutRodDP(size_Rod,price_Pieces));
		
		
	}

}
