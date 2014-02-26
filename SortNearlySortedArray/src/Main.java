
public class Main {

	//sort k nearly sorted array (each element is at most k position away from its target position)	
	//insertion sort, for each elemnt it does maximum O(k) comparison and swaps, hence complexity is O(nk)
	public static void insertionSortOnNearlySorted(int [] arrayToSort){
		for(int i=1;i<arrayToSort.length;i++){
			for(int j=i;j>=1;j--){
				if(arrayToSort[j]<arrayToSort[j-1]){
					int tmp=arrayToSort[j];
					arrayToSort[j]=arrayToSort[j-1];
					arrayToSort[j-1]=tmp;
				}
			}
		}
		
	}
	
	static public class MaxHeap<E extends Comparable>{
		private E[] heap;
		private int size;
		private int n;
		private int startIndex;
		public MaxHeap(E[] h, int num, int max){
			heap=h; n=num; size=max;startIndex=0; buildHeap();
		}
		public void setN(int n){
			this.n=n;
		}
		public int getStartIndex(){
			return startIndex;
		}
		public void setStartIndex(int startIndex){
			this.startIndex=startIndex;
		}
		public int heapSize(){
			return n;
		}
		public boolean isLeaf(int pos){
			return pos>=n/2 && pos<n;
		}
		public int leftChild(int pos){
			
			assert (pos<(n+startIndex)/2) : "Position has no left child";
			return startIndex+2*(pos-startIndex)+1;
		}
		public int rightChild(int pos){
			assert pos<(n+startIndex-1)/2: "Position has no right child";
			return startIndex+2*(pos-startIndex)+2;
		}
		public int parent(int pos){
			assert pos>0 : "Position has no parent";
			return (startIndex+pos-1)/2;
		}
		public void insert(E val){
			assert n<size : "Heap is full";
			int curr=n++;
			heap[curr]=val;
			while((curr!=0) && heap[curr].compareTo(heap[parent(curr)])>0){
				E tmp=heap[curr];
				heap[curr]=heap[parent(curr)];
				heap[parent(curr)]=tmp;
				
			}
		}
		public void replaceMinNew(int startIndex, int n){
			E tmp=heap[startIndex];
			heap[startIndex]=heap[n-1];
			heap[n-1]=tmp;
			this.setStartIndex(startIndex);
			this.setN(n);
			if(n-startIndex!=0){
				
				siftDown(startIndex);
			}
		}
		public void buildHeap(){
			for(int i=n/2-1;i>=0;i--){
				siftDown(i);
			}
		}
		public void siftDown(int pos){
			assert (pos>=startIndex && pos<n): "Illegal heap position";
		
			if(pos>=(n+startIndex)/2)
				return;
			int j=leftChild(pos);
		
			int largest=pos;
			if(heap[largest].compareTo(heap[j])<0){
				largest=j;
			}
			if(j<n-1 && heap[largest].compareTo(heap[j+1])<0){
				largest=j+1;
			}
		
			if(largest!=pos){
				E tmp=heap[largest];
				heap[largest]=heap[pos];
				heap[pos]=tmp;
				siftDown(largest);
			}
		}
		public E removeMax(){
			assert n>0 : "Removing empty heap";
	
			E tmp=heap[0];
			heap[0]=heap[n-1];
			heap[n-1]=tmp;
			n--;
			if(n!=0){
				siftDown(0);
			}
			return heap[n];
		}
		
		
	} 
	//this is in place heapsort for k nearly sorted array
	//first build max heap of first k elements (O(k) time)
	//second n-k times replace min and heapify mean (O(n-k)*logk time)
	//overall complexity O(k+(n-k)*logk)==O(nlogk), which is O(nlogn) when k==n
	public static void heapSort(Integer[] arrayToSort,int k){
		MaxHeap<Integer> heap=new MaxHeap<Integer>(arrayToSort,k,arrayToSort.length);
		int startIndex=1;
		for(int i=k;i<arrayToSort.length;i++){
		
			heap.replaceMinNew(startIndex++,i+1);
		}
	}
	public static Integer[] initArray(int n){
	
		Integer[] arrayToSort=new Integer [n];
		for(int i=0;i<arrayToSort.length;i++){
			arrayToSort[i]=n-i;
		}
		return arrayToSort;
	}
	public static void printArray(Integer []arrayToSort){
		for(int i=0;i<arrayToSort.length;i++){
			System.out.print(arrayToSort[i]+" ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Integer []arrayToSort={7,9,8,3,5,2,6,4,1};
		printArray(arrayToSort);
		//insertionSortOnNearlySorted(arrayToSort);
		//printArray(arrayToSort);
		heapSort(arrayToSort,3);
		printArray(arrayToSort);
	}

}
