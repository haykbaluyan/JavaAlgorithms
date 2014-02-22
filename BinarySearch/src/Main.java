//binary and linear searches
//binary search implemented iteratively performs the best
//binary search implemented recursively performs as second
//linear search takes the most amount of execution time in the worst case
//binary search worst case O(logn)
//binary search average case will be  O(1*1+2*2+4*3+...+)/n==O((2^0)*1+(2^1)*2+(2^2)*3+...+(2^k)*(k+1))/n,
//where k=log2(n+1)-1,,,,, the result of this sum is shown in http://en.wikipedia.org/wiki/Summation
//so the average case of will be O(nlogn)/n==O(logn)
//linear search worst case O(n) , average case O((1+2+...+n)/n)==O(n), best case O(1)

public class Main {

	//binary search by recursion approach
	public static int binarySearchRec(int[] sortedArray, int start, int end, int key){
	
		if(start==end) return -1;
		int mid=start+(end-start)/2;
		if(key==sortedArray[mid]) return mid; 
		if(key>sortedArray[mid]){
			return binarySearchRec(sortedArray, mid+1,end,key);
		}
	
		return	binarySearchRec(sortedArray, start,mid,key);
		
	
		
	}
	//binary search by iterative approach
	public static int binarySearchIter(int[] sortedArray, int start, int end, int key){
		
		
		while(start<end){
			int mid=start+(end-start)/2;
			if(key==sortedArray[mid]){
				return mid;
			}
			if(key>sortedArray[mid]){
				start=mid+1;
			}
			else{
				end=mid;
			}
			
		}
		return -1;
	}
	
	//linear search
		public static int linearSearch(int[] sortedArray, int key){
			
			
			for(int i=0;i<sortedArray.length;i++){
				if(sortedArray[i]==key) {
					
					return i;
				}
			}
			return -1;
		}
		
	//interpolation search
		// complexity is O(log(logn)) and in pracitce this showssame results as iterative binary search
		public static long interpolSearch(int[] sortedArray, int key){
			
			long start=0;
			long end=sortedArray.length;
			for(int i=0;i<5;i++){
				
					long mid=start+(end-1-start)*(key-sortedArray[(int) start])/(sortedArray[(int) (end-1)]-sortedArray[(int) start]);
				if(sortedArray[(int) mid]==key){
					return mid;
				}
				if(sortedArray[(int) mid]>key){
					end=mid;
				}
				else{
					start=mid+1;
				}
				
			}
			return -1;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] sortedArray=new int[10000000];
		for(int i=0;i<sortedArray.length;i++){
			sortedArray[i]=i;
			
		}
		long startTime=System.nanoTime();
		System.out.println(binarySearchRec(sortedArray,0,sortedArray.length,10000000-1));
		long endTime1=System.nanoTime();
		System.out.println(binarySearchIter(sortedArray,0,sortedArray.length,10000000-1));
		long endTime2=System.nanoTime();
		
		System.out.println(linearSearch(sortedArray,10000000-1));
		long endTime3=System.nanoTime();
		System.out.println(endTime1-startTime);
		System.out.println(endTime2-endTime1);
		System.out.println(endTime3-endTime2);
		
		
		long startTimeInterPol=System.nanoTime();
		System.out.println(interpolSearch(sortedArray,16-1));
		long endTimeInterPol=System.nanoTime();
		System.out.println(endTimeInterPol-startTimeInterPol);
		
	}

}
