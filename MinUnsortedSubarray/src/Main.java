
public class Main {
	
	//minUnsortedArray -> find the min length subarray, sorting of which will result in a sorting of whole array
	//linear time algorithm, space complexity on O(1)
	public static int[] minUnsortedArray(int []sample){
		int s=0;
		//O(n)
		while(s<sample.length-1 && sample[s]<=sample[s+1]){
			s++;
		}
		if(s==sample.length-1){
			return null;
		}
		int e=sample.length-1;
		//O(n)
		while(e>=1 && sample[e]>=sample[e-1]){
			e--;
		}
		
		int min=sample[s];
		int max=sample[s];
		
		//O(n)
		for(int i=s+1;i<=e;i++){
			if(min>sample[i]){
				min=sample[i];
			}
			if(max<sample[i]){
				max=sample[i];
			}
		}
		//O(n)
		for(int i=0;i<s;i++){
			if(sample[i]>min){
				s=i;
				break;
			}
		}
		//O(n)
		for(int i=sample.length-1;i>e;i--){
			if(sample[i]<max){
				e=i;
				break;
			}
		}
		int []result={s,e};
		return result;
	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int []sample={ 50,10,5,2};
		int []res=minUnsortedArray(sample);
		if(res==null){
			System.out.println("Already Sorted Array");
			return;
		}
		for(int i=0;i<res.length;i++){
			System.out.print(res[i]+" ");
			
		}
		System.out.println();

	}

}
