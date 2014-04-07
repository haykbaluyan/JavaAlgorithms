import java.util.ArrayList;


public class Main {

	public static void balance(ArrayList<String> words, int l){
		int wordsCount=words.size();
		int[][] extras=new int[wordsCount][wordsCount];
		for(int i=0;i<wordsCount;i++){
			extras[i][i]=l-words.get(i).length();
			for(int j=i+1;j<wordsCount;j++){
				extras[i][j]=extras[i][j-1]-1-words.get(j).length();
			}
		}
		
		int lineCost[][]=new int[wordsCount][wordsCount];
		for(int i=0;i<wordsCount;i++){
			for(int j=i;j<wordsCount;j++){
				if(extras[i][j]<0){
					lineCost[i][j]=Integer.MAX_VALUE;
					
				}
				else{
					if(j==wordsCount-1){
						lineCost[i][j]=0;
					}
					else{
						lineCost[i][j]=extras[i][j]*extras[i][j];
					}
				}
			}
		}
		/*for(int i=0;i<wordsCount;i++){
			for(int j=0;j<wordsCount;j++){
				System.out.print(extras[i][j]+" ");
			}
			System.out.println();
		}*/
		int[] track=new int[wordsCount+1];
		int []c=new int[wordsCount+1];
		c[0]=0;
		for(int j=1;j<=wordsCount;j++){
			c[j]=Integer.MAX_VALUE;
			for(int i=1;i<=j;i++){
				if(c[i-1]!=Integer.MAX_VALUE && lineCost[i-1][j-1]!=Integer.MAX_VALUE && c[j]>c[i-1]+lineCost[i-1][j-1]){
					c[j]=c[i-1]+lineCost[i-1][j-1];
					track[j]=i;
				}
			}
		}
		
		
		printSol(track,wordsCount);
		
		
	}
	public static int printSol(int[] p, int n){
		int k;
		if(p[n]==1){
			k=1;
		}
		else{
			k=printSol(p,p[n]-1)+1;
		}
		System.out.println(k+" "+p[n]+" "+n);
		return k;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> words=new ArrayList<String>();
		words.add("abc");
		words.add("d");
		words.add("e");
		int l=5;
		balance(words,l);
		System.out.println("End");
		
	}

}
