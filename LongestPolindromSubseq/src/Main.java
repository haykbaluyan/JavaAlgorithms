
public class Main {

	public static int LongestPolSubseq(String s,StringBuffer pol){
		
		if(s.length()==1) {pol.append(s.charAt(0)); return 1;}
		if(s.charAt(0)==s.charAt(s.length()-1)){
			StringBuffer middlePol=new StringBuffer();
			int res=LongestPolSubseq(s.substring(1,s.length()-1),middlePol);
			pol.append(s.charAt(0));
			pol.append(middlePol);
			pol.append(s.charAt(s.length()-1));
			return 2+res;
		}
		StringBuffer firstHalfPol=new StringBuffer();
		StringBuffer secondHalfPol=new StringBuffer();
		String firstHalf=s.substring(1,s.length());
		String secondHalf=s.substring(0,s.length()-1);
	
		int firstHalfPolLength=LongestPolSubseq(firstHalf,firstHalfPol);
		int secondHalfPolLength=LongestPolSubseq(secondHalf,secondHalfPol);
		if(firstHalfPolLength>secondHalfPolLength){
			pol.append(firstHalfPol);
			return firstHalfPolLength;
		}
		pol.append(secondHalfPol);
		return secondHalfPolLength;	
	}
	public static void LongestPolSubseqDP(String s){
		int L[][]=new int[s.length()][s.length()];
		char [][]track=new char[s.length()][s.length()];
		for(int i=0;i<s.length();i++){
			L[i][i]=1;
			track[i][i]='S';
		}
		//this takes n^2 time
		for(int z=1;z<s.length();z++)
			for(int i=0;i<s.length()-z;i++){
			{
				int j=i+z;
				if(s.charAt(i)==s.charAt(j)){
					L[i][j]=L[i+1][j-1]+2;
					track[i][j]='M';
				}
				else{
					if(L[i][j-1]>=L[i+1][j]){
						L[i][j]=L[i][j-1];
						track[i][j]='L';
					}
					else{
						L[i][j]=L[i+1][j];
						track[i][j]='R';
					}
				}
			}
		}
		
		int row=0;
		int col=s.length()-1;
		//this takes linear time,since every time we move left or right
		printTrack(s,track,row,col);
		
		System.out.println();
	
		
	}
	public static void printTrack(String s, char [][]track, int row, int col){
		
		if(col<row){
			return;
		}
		if(row==col){
			System.out.print(s.charAt(row));
			return;
		}
		if(track[row][col]=='M'){
			System.out.print(s.charAt(row));
			printTrack(s,track,row+1,col-1);
			System.out.print(s.charAt(col));
			return;
		}
		if(track[row][col]=='R'){
			printTrack(s,track,row+1,col);
			return;
		}
		if(track[row][col]=='L'){
			printTrack(s,track,row,col-1);
			return;
		}
	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="BBABCBCAB";
		StringBuffer ss=new StringBuffer();
		System.out.println(LongestPolSubseq(s,ss));
		System.out.println(ss);
		
		LongestPolSubseqDP(s);
	
	}

}
