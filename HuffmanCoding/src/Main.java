import java.util.ArrayList;
import java.util.Stack;

class Node{
	int freq;
	char character;
	Node left;
	Node right;
	
	public Node( char character,int freq){
		this.freq=freq;
		this.character=character;
	}
	public int getFreq(){
		return freq;
	}
	public void setFreq(int freq){
		this.freq=freq;
	}
	public char getCharacter(){
		return character;
	}
	public void setCharacter(char character){
		this.character=character;
	}
	public Node getLeft(){
		return left;
	}
	public void setLeft(Node left){
		this.left=left;
	}
	public Node getRight(){
		return right;
	}
	public void setRight(Node right){
		this.right=right;
	}
	
}
class MinHeap{
	int size;
	ArrayList<Node> array;
	
	public int getSize(){
		return size;
	}
	public ArrayList<Node> getArray(){
		return array;
	}
	public int getLeft(int i){
		return 2*i+1;
	}
	public int getRight(int i){
		return 2*i+2;
	}
	public int getParent(int i){
		return (i-1)/2;
	}
	public MinHeap(){
		array=new ArrayList<Node>();
	}
	public void addNode(Node n){
		this.array.add(n);
		this.size++;
	}
	//this takes O(n) time
	public void buildMinHeap(){
		for(int i=size/2;i>=0;i--){
			minHeapify(i);
		}
	}
	public void minHeapify(int i){
		assert i<size : "Index of min heapify is out of bounds";
		int smallest=i;
		int left=getLeft(i);
		int right=getRight(i);
		if(left<size && array.get(left).getFreq()<array.get(smallest).getFreq()){
			smallest=left;
			
		}
		if(right<size && array.get(right).getFreq()<array.get(smallest).getFreq()){
			smallest=right;
		}
		if(smallest!=i){
			Node tmp=array.get(smallest);
			array.set(smallest, array.get(i));
			array.set(i, tmp);
			minHeapify(smallest);
	
		}
		
		
	}
	public Node extractMin(){
		Node result=array.get(0);
		
		
		array.set(0, array.get(array.size()-1));
		minHeapify(0);
		array.remove(array.size()-1);
		size--;
		return result;
	}
	public void insertNode(Node n){
		size++;
		array.add(n);
		
		int i=size-1;
		int parent=getParent(i);
		
		while(parent>=0 && array.get(parent).getFreq()>array.get(i).getFreq()){
			Node tmp=array.get(i);
			array.set(i, array.get(parent));
			array.set(parent, tmp);
			i=parent;
			parent=getParent(i);
		
		}
	}
	
	//complexity is (n-1)*O(logn)==O(nlogn)
	public void huffmanCoding(){
		
		//while loop runs n-1 times and each time 2 extract mins (O(logn)) and one insertNode will be done (O(logn))
		
		while(size!=1){
			Node n1=extractMin();
			Node n2=extractMin();
			
			Node nCombine=new Node('$',n1.getFreq()+n2.getFreq());
			System.out.println(n1.getFreq()+" "+n2.getFreq()+" "+nCombine.getFreq());
			nCombine.setLeft(n1);
			nCombine.setRight(n2);
			insertNode(nCombine);
		}
		printHuffmanTree(array.get(0));
	}
	public void printHuffmanTree(Node n){
		Stack<Node> s=new Stack<Node>();
		s.push(n);
	
		while(s.size()!=0){
			Node top=s.pop();
		
			System.out.print(top.getFreq()+" ");
			if(top.getRight()!=null){
				s.push(top.getRight());
			}
			if(top.getLeft()!=null){
				s.push(top.getLeft());
			}
			
		}
		System.out.println();
	}
	public static void printCodes(Node root,ArrayList<Integer> ar){
		
		if(root.getLeft()!=null){
			ar.add(0);
			printCodes(root.getLeft(),ar);
			ar.remove(ar.size()-1);
		}
		if(root.getRight()!=null){
			ar.add(1);
			printCodes(root.getRight(),ar);
			ar.remove(ar.size()-1);
		}
		if(root.getLeft()==null && root.getRight()==null){
			System.out.println(root.getCharacter()+" "+ar);
		}
				
	}
	public void printMinHeap(){
		int h=(int)(Math.ceil(Math.log(size+1)/Math.log(2)-1));
		int start=0;
		int end=0;
		for(int i=0;i<=h;i++){
			end=(int)Math.pow(2, i)+start;
			if(end>size){
				end=size;
			}
			for(int j=start; j<end;j++){
				System.out.print(array.get(j).getFreq()+" ");
			}
			start=end;
			
			System.out.println();
			
			
		}
	}
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char arr[] = {'b', 'a', 'e', 'f', 'c', 'd'};
	    int freq[] = {9, 5, 16, 45, 12, 13};
	    
	    //this takes O(n) time
	    MinHeap heap=new MinHeap();
	    for(int i=0;i<arr.length;i++){
	    	Node n=new Node(arr[i],freq[i]);
	    	heap.addNode(n);
	    	
	    }
	   
	   heap.printMinHeap();
	   //this takes O(n) time
	   heap.buildMinHeap();
	   heap.printMinHeap();
	   
	   
	   heap.huffmanCoding();
	   
	   ArrayList<Integer> ar=new ArrayList<Integer>();
	   MinHeap.printCodes(heap.getArray().get(0), ar);
	   

	}

}
