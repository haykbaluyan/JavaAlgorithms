
public class Main {

	//exponential time
	public static String LCS(String str1, String str2){
		if(str1.length()==0 || str2.length()==0){
			return "";
		}
		if(str1.charAt(str1.length()-1)==str2.charAt(str2.length()-1)){
			String s=LCS(str1.substring(0, str1.length()-1),str2.substring(0, str2.length()-1));
			return s+str1.charAt(str1.length()-1);
		}
		String s1=LCS(str1.substring(0, str1.length()-1),str2.substring(0, str2.length()));
		String s2=LCS(str1.substring(0, str1.length()),str2.substring(0, str2.length()-1));
		return s1.length()>s2.length() ? s1 :s2;
	}
	//time is O(l1*l2)
	public static int LCSDynProg(String str1, String str2){
		
		int l1=str1.length();
		int l2=str2.length();
		int L[][]=new int[l1+1][l2+1];
		for(int i=0;i<l1+1;i++){
			L[i][0]=0;
		
		}
		for(int j=0;j<l2+1;j++){
			L[0][j]=0;
		}
		
		char track[][]=new char[l1+1][l2+1];
		for(int i=1;i<l1+1;i++){
			for(int j=1;j<l2+1;j++){
				if(str1.charAt(i-1)==str2.charAt(j-1)){
					L[i][j]=L[i-1][j-1]+1;
					track[i][j]='d';
				}
				else{
					if(L[i-1][j]>L[i][j-1]){
						L[i][j]=L[i-1][j];
						track[i][j]='u';
					}
					else{
						L[i][j]=L[i][j-1];
						track[i][j]='l';
					}
					
				}
			}
		}
		System.out.println(printTrack(track,str1,str2,l1,l2));
		return L[l1][l2];
	}
	//runs atr O(max(l1,l2)) time
	public static  String printTrack(char track[][], String str1, String str2, int l1, int l2){
	
		if(l1==0 || l2==0)
			return "";
		
	
			if(track[l1][l2]=='d'){
				return printTrack(track,str1,str2,l1-1,l2-1)+str1.charAt(l1-1);
			}
			if(track[l1][l2]=='u'){
				return printTrack(track,str1,str2,l1-1,l2);
			}
			return printTrack(track,str1,str2,l1,l2-1);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(LCS( "ABCDGH","AEDFHR"));
		System.out.println(LCS("AGGTAB" ,"GXTXAYB"));
		
		System.out.println(LCSDynProg( "ABCDGH","AEDFHR"));
		System.out.println(LCSDynProg("AGGTAB" ,"GXTXAYB"));
	}

}
