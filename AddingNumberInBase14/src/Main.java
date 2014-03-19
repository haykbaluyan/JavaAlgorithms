
public class Main {

	public static void addBase14(String s1, String s2){
		String result=new String();
		int carry=0;
		if(s1.length()<s2.length()){
			for(int j=0;j<s2.length()-s1.length();j++){
				s1="0"+s1;
			}
		}
		else{
		
			int diff=s1.length()-s2.length();
			for(int j=0;j<diff;j++){
				s2="0"+s2;
	
			}
		}
		int i=s1.length()-1;
	
		while(i>=0){
			int sum=getNumeralValue(s1.charAt(i))+getNumeralValue(s2.charAt(i))+carry;
		
			carry=sum/14;
			result=getNumeral(sum%14)+result;
			i--;
		}
		
		if(carry!=0){
			result=getNumeral(carry)+result;
		}
		
		System.out.println("The sum is: "+result);
		
		
		
	}
	static int getNumeralValue(char num)
	{
	  if( num >= '0' && num <= '9')
	    return (num - '0');
	  if( num >= 'A' && num <= 'D')  
	    return (num - 'A' + 10);
	  return 0;
	}
	/* Function to get numeral for a value.   
	  For example it returns 'A' for input 10 
	  '1' for 1, etc */
	static char getNumeral(int val)
	{
	  if( val >= 0 && val <= 9)
	    return (char)(val + (int)'0');
	  if( val >= 10 && val <= 14)  
	    return (char)(val + (int)'A' - 10);
	  return ' ';
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String num1="DDDD";
		String num2="DDD";
		System.out.println(num1+" "+num2);
		addBase14(num1,num2);
	
		
	}

}
