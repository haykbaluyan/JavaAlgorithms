
public class Main {
	
	//selection sort -> each time find min/max element and exchange with the first element and do the same with the remaining array 
	//the algorithm has O(n^2) complexity for all worst,average and best cases
	//it is "in place" (O(1) extra memory is used)
	//it is stable, since the order of same elements is the same in resulting array
	//number of swaps is only O(n)
	public static void selectionSort(int []arrayToSort){
		
		
		for(int i=0;i<arrayToSort.length-1;i++){
			int min_index=i;
			for(int j=i+1;j<arrayToSort.length;j++){
				if(arrayToSort[j]<arrayToSort[min_index]){
					min_index=j;
				}
			}
			
			//exchange with min_index
			int b=arrayToSort[i];
			arrayToSort[i]=arrayToSort[min_index];
			arrayToSort[min_index]=b;
		}
		
		for(int i=0;i<arrayToSort.length;i++){
			System.out.print(arrayToSort[i]+" ");
		}
		System.out.println();
	}
	
	//bubble sort -> for each iteration check neighbors and exchange if they are not in right order
	//the algorithm has O(n^2) complexity for all worst,average and best cases
	//it is "in place" (O(1) extra memory is used)
	//it is stable (consider equal elements in right order), since the order of same elements is the same in resulting array
	//number of swaps is worst case O(n^2), average case O(n^2), best case O(1)
	//for small inputs  (n<=30) bubble sort may be better than selection sort, however in general they are almost same
	public static void bubbleSort(int[] arrayToSort){
		
		for(int i=arrayToSort.length-1;i>=1;i--){
			for(int j=0;j<i;j++){
				if(arrayToSort[j]>arrayToSort[j+1]){
					int tmp=arrayToSort[j];
					arrayToSort[j]=arrayToSort[j+1];
					arrayToSort[j+1]=tmp;
				}
			}
		}
		for(int i=0;i<arrayToSort.length;i++){
			System.out.print(arrayToSort[i]+" ");
		}
		System.out.println();
		
		
	}
	
	//insertion sort -> for each iteration check current element with the previous one and exchange if not in order
	//worst complexity is O(n^2), average is O(n^2), best case O(n)
	//insertion sort also is "in place" and stable
	//for small inputs seems insertion sort is better than selection and bubble, however for big inputs they are almost saem
	//number of performed swaps can be O(n^2) which is bad compared with O(n) swaps in case of selection sort
	public static void insertionSort(int[] arrayToSort){
		for(int i=1;i<arrayToSort.length;i++){
			for(int j=i-1;j>=0;j--){
				if(arrayToSort[j+1]<arrayToSort[j]){
					int tmp=arrayToSort[j+1];
					arrayToSort[j+1]=arrayToSort[j];
					arrayToSort[j]=tmp;
				}
				
			}
		}
		for(int i=0;i<arrayToSort.length;i++){
			System.out.print(arrayToSort[i]+" ");
		}
		System.out.println();
	
	}
	
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
	
	//the max heap is binary complete tree, where any node is greater than all its children nodes
	//the min heap is bianry complete tree, where any node is smaller than all its children nodes
	//to heapify the array we need to go from bottom up and heapify each of them recursively
	//the complexity is a bit tricky, for each of n elements you need to recursively call heapify at most logn time
	//hence the complexity of heapify seems to be O(nlogn), however it is actually O(n), 
	//because you don't call heapify exactly logn time (you do less)
	//for 2^h nodes you call heapify 0 times, for 2^(h-1) you call it at most 1 times, for 2^(h-2) nodes you call it at most 2 times and so on
	//hence you can prove that complexity is O(n)
	//for more details see http://stackoverflow.com/questions/9755721/build-heap-complexity
	
	public static void maxHeapify(int []arrayToHeapify,int heapSize,int i){
		
			int left=2*i+1;
			int right=2*i+2;
			int index=i;
			if(left<heapSize && arrayToHeapify[i]<arrayToHeapify[left]){
				index=left;
			}
			if(right<heapSize && arrayToHeapify[index]<arrayToHeapify[right]){
				index=right;
			}
			
			if(index!=i){
				int tmp=arrayToHeapify[i];
				arrayToHeapify[i]=arrayToHeapify[index];
				arrayToHeapify[index]=tmp;
				maxHeapify(arrayToHeapify,heapSize,index);
			}
			
		
	}
	
	//time comlexity O(nlogn), however merge sort far more better (both for small and big inputs)
	//heap sort is "in place" compare to merge sort, which is not
	//heap sort is not "stable" with implementation below, however any not stable sorting algorithm can be made stable
	public static void heapSort(int []arrayToSort, int heapSize){
		
		//this part takes O(n) time
		for(int i=arrayToSort.length/2-1;i>=0;i--){
			maxHeapify(arrayToSort,arrayToSort.length,i);
		}
		
	
		//this part takes O(nlogn) since each heapify takes O(logn) time 
		//(we saw that in case of building heap maxHeapify  did not take exactly O(logn), but less)
		while(heapSize>0){
			int tmp=arrayToSort[0];
			arrayToSort[0]=arrayToSort[heapSize-1];
			arrayToSort[heapSize-1]=tmp;
			maxHeapify(arrayToSort,heapSize-1,0);
			heapSize--;
		
		}
		
		for(int i=0;i<arrayToSort.length;i++){
			System.out.print(arrayToSort[i]+" ");
		}
		System.out.println();
		
	}
	
	//quick sort -> practically very quick algorithm tos ort an array
	//this algorithm takes a pivot and partition array, than recursively sorts both parts
	//worst case complexity is O(n^2), when we have already sorted or reversed sorted array
	//avcerage and best case complexity is O(nlogn)
	//practicaly this algorithm outperforms other sorting algorithms
	//quicksort is "in place" however it is not stable
	public static void quickSort(int []arrayToSort, int start, int end){
		
		if(start<end-1){
			
			int pivotIndex=partitionQuickSort(arrayToSort,start,end);
			quickSort(arrayToSort,start,pivotIndex);
			quickSort(arrayToSort,pivotIndex+1,end);
		}
		
		
	}
	
	//partition takes O(n) time
	public static int partitionQuickSort(int []arrayToSort,int start,int end){
		int pivot=arrayToSort[end-1];
		int j=start;
		for(int i=start; i<end-1;i++){
			if(arrayToSort[i]<=pivot){
				if(j!=i){
					int tmp=arrayToSort[i];
					arrayToSort[i]=arrayToSort[j];
					arrayToSort[j]=tmp;
				}
				j++;
			}	
		}
		int tmp=pivot;
		pivot=arrayToSort[j];
		arrayToSort[j]=tmp;
		return j;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=1000;
		int []arrayToSort=new int[n];
		for(int i=0;i<arrayToSort.length;i++){
			arrayToSort[i]=arrayToSort.length-i;
		}
		long startTime=System.nanoTime();
		selectionSort(arrayToSort);
		long endTime=System.nanoTime();
		System.out.println(endTime-startTime);
		
		int []arrayToSortBubble=new int[n];
		for(int i=0;i<arrayToSortBubble.length;i++){
			arrayToSortBubble[i]=arrayToSortBubble.length-i;
		}
		long startTimeBubble=System.nanoTime();
		bubbleSort(arrayToSortBubble);
		long endTimeBubble=System.nanoTime();
		System.out.println(endTimeBubble-startTimeBubble);
		
		int []arrayToSortInsertion=new int[n];
		for(int i=0;i<arrayToSortInsertion.length;i++){
			arrayToSortInsertion[i]=arrayToSortInsertion.length-i;
		}
		long startTimeInsertion=System.nanoTime();
		insertionSort(arrayToSortInsertion);
		long endTimeInsertion=System.nanoTime();
		System.out.println(endTimeInsertion-startTimeInsertion);
		
		int []arrayToSortMerge=new int[n];
		for(int i=0;i<arrayToSortMerge.length;i++){
			arrayToSortMerge[i]=arrayToSortMerge.length-i;
		}
	
		long startTimeMerge=System.nanoTime();
		mergeSort(arrayToSortMerge,0,arrayToSortMerge.length);
		long endTimeMerge=System.nanoTime();
		for(int i=0;i<arrayToSortMerge.length;i++){
			System.out.print(arrayToSortMerge[i]+" ");
		}
		System.out.println();
		System.out.println(endTimeMerge-startTimeMerge);
		
		int []arrayToHeapSort=new int[n];
		for(int i=0;i<arrayToHeapSort.length;i++){
			arrayToHeapSort[i]=i+1;
		}
		
		long startTimeHeapSort=System.nanoTime();
		heapSort(arrayToHeapSort,arrayToHeapSort.length);
		long endTimeHeapSort=System.nanoTime();
		System.out.println(endTimeHeapSort-startTimeHeapSort);
		
		
		int []arrayToQuickSort=new int[n];
		for(int i=0;i<arrayToQuickSort.length;i++){
			arrayToQuickSort[i]=arrayToQuickSort.length-i;
		}
	
		long startTimeQuick=System.nanoTime();
		mergeSort(arrayToQuickSort,0,arrayToQuickSort.length);
		long endTimeQuick=System.nanoTime();
		for(int i=0;i<arrayToQuickSort.length;i++){
			System.out.print(arrayToQuickSort[i]+" ");
		}
		System.out.println();
		System.out.println(endTimeQuick-startTimeQuick);
		
		
		
	}

}
