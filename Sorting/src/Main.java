
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
		
	}

}
