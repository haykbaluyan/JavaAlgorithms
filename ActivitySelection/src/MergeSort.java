
public class MergeSort {
	//merge sort -> divide array into two parts, sort them using again merge sort and then merge them using O(n) time algorithm
		//merge sort has time complexity O(nlogn) for worst,average and best cases
		//merge sort is better than selection, bubble and insertion sort even for small number of inputs  
		//merge sort is stable (for implementation given below), however it is not "in place"
		//for merging step merge sort requires O(n) extra memory
		public static void mergeSort(int[] arrayToSort,int start, int end){
			
			if(start<end-1){
				int mid=start+(end-start)/2;
				
				mergeSort(arrayToSort,start,mid);
				mergeSort(arrayToSort,mid,end);
				merge(arrayToSort,start,mid,end);
			}
			
		}
		public static void merge(int[] arrayToSort,int start, int mid, int end){

			int indFirst=start;
			int indSecond=mid;
			int []mergeArray=new int[end-start];
			int indMerge=0;
			while(indFirst<mid && indSecond<end){
				if(arrayToSort[indFirst]<arrayToSort[indSecond]){
					mergeArray[indMerge++]=arrayToSort[indFirst++];
				}
				else{
					mergeArray[indMerge++]=arrayToSort[indSecond++];
				}
			}
			while(indFirst<mid){
				mergeArray[indMerge++]=arrayToSort[indFirst++];
			}
			while(indSecond<end){
				mergeArray[indMerge++]=arrayToSort[indSecond++];
			}
			
			for(int i=start;i<end;i++){
				arrayToSort[i]=mergeArray[i-start];
			}
			
		
		}
		
}
