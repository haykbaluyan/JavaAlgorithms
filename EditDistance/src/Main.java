import java.util.ArrayList;


public class Main {

	public static int editDist(String src, String dest){
		int l1=src.length();
		int l2=dest.length();
		int L[][]=new int[l1+1][l2+1];
		char track[][]=new char[l1+1][l2+1];
		for(int i=0;i<l1+1;i++){
			L[i][0]=i;
		}
		for(int j=0;j<l2+1;j++){
			L[0][j]=j;
		}
		for(int i=1;i<l1+1;i++){
			for(int j=1;j<l2+1;j++){
				if(src.charAt(i-1)==dest.charAt(j-1)){
					L[i][j]=L[i-1][j-1];
					track[i][j]='d';
					
				}
				else{
					if(L[i-1][j]<L[i][j-1]){
						L[i][j]=L[i-1][j]+1;
						track[i][j]='u';
					}
					else{
						L[i][j]=L[i][j-1]+1;
						track[i][j]='l';
					}
				}
			}
		}
		int i=l1;
		int j=l2;
		String editSeq="";
		while(i>0 && j>0){
			if(track[i][j]=='d'){
				i--;
				j--;
			}
			else{
				if(track[i][j]=='u'){
					i--;
					editSeq='D'+editSeq;
				}
				else{
					if(track[i][j]=='l'){
						editSeq='I'+editSeq;
						j--;
					}
				}
			}
		}
		System.out.println(editSeq);
		return L[l1][l2];
	}
	
	
	public static int editDistRec(String src, String dst, int m, int n,ArrayList<Character> editSeq){
		
		if(m==0){
			for(int i=0;i<n;i++){
				editSeq.add('I');
			}
				
			return n;
		}
		if(n==0){
			for(int i=0;i<m;i++){
				editSeq.add('D');
			}
			return m;
		}
		if(src.charAt(m-1)==dst.charAt(n-1)){
			
			return editDistRec(src,dst,m-1,n-1,editSeq);
			
		}
		ArrayList<Character> decmSeq=new ArrayList<Character>();
		ArrayList<Character> decnSeq=new ArrayList<Character>();
		
		int decm=editDistRec(src,dst,m-1,n,decmSeq);
		int decn=editDistRec(src,dst,m,n-1,decnSeq);
		if(decm<decn){
			editSeq.addAll(decmSeq);
			editSeq.add('D');
			return decm+1;
		}

		editSeq.addAll(decnSeq);
		editSeq.add('I');
		return decn+1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Character> editSeq=new ArrayList<Character>();
		System.out.println(editDistRec("Sunday","Saturday","Sunday".length(),"Saturday".length(),editSeq));
		System.out.println(editSeq);
		System.out.println(editDist("Sunday","Saturday"));
		

	}

}
