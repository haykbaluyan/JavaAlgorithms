
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

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int []arrayToSort=new int[100];
		for(int i=0;i<arrayToSort.length;i++){
			arrayToSort[i]=arrayToSort.length-i;
		}
		long startTime=System.nanoTime();
		selectionSort(arrayToSort);
		long endTime=System.nanoTime();
		System.out.println(endTime-startTime);
	}

}
