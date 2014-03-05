import java.util.ArrayList;
import java.util.LinkedList;

class Node<E>{
	E id;
	int weight;
	int key;
	Node<E> parent;
	public int indexInHeap;
	LinkedList<Node<E>> adjNodes;
	LinkedList<Integer> adjWeights;
	public Node(E id){
		adjNodes=new LinkedList<Node<E>>();
		adjWeights=new LinkedList<Integer>();
		this.id=id;
		this.key=Integer.MAX_VALUE;
		
		
	}
	
	public void setId(E id){
		this.id=id;
	}
	public E getId(){
		return id;
	}
	public void setKey(int key){
		this.key=key;
	}
	public int getKey(){
		return key;
	}
	public void setWeight(int weight){
		this.weight=weight;
	}
	public int getWeight(){
		return weight;
	}
	public void setParent(Node<E> parent){
		this.parent=parent;
	}
	public Node<E> getParent(){
		return parent;
	}
	public LinkedList<Node<E>> getAdjNodes(){
		return adjNodes;
	}
	public LinkedList<Integer> getAdjWeights(){
		return adjWeights;
	}
	public void addAdjNodes(Node<E> ...  nodes){
		assert nodes!=null : "There is no node to add";
		assert adjNodes!=null : "Adjacent list is null";
		for(Node<E> n : nodes){
			adjNodes.add(n);
		}
	}
	public void addAdjWeights(Integer ...  weights){
		assert weights!=null : "There is no node to add";
		assert adjWeights!=null : "Adjacent list is null";
		for(Integer w : weights){
			adjWeights.add(w);
		}
	}
	public void printNode(){
		if(adjNodes!=null){
			for(int i=0; i < adjNodes.size();i++){
				System.out.println("("+id+","+adjNodes.get(i).getId() + ") "+adjWeights.get(i));
			}
		
		}
		else{
			System.out.println("Node " + id+" has no adjacent nodes");
		}
	}
	
}
class Graph<E>{
	LinkedList<Node<E>> nodes;
	public Graph(){
		nodes=new LinkedList<Node<E>>();
	}
	public void addNodes(Node<E> ... nodes){
		assert nodes!=null : "There is no node to add";
		assert this.nodes!=null : "Nodes' list is null";
		for(Node<E> n : nodes){
			this.nodes.add(n);
		}
	}
	public void printGraph(){
		if(nodes!=null){
			for(Node<E> n : nodes){
				n.printNode();
			}
		}
		else{
			System.out.println("The graph is empty");
		}
	}
	//complexity O(V+VlogV+ElogV)==O(ElogV)
	public void DijkstraSP(){
		Node<E> start=nodes.get(0);
		start.setKey(0);
		start.setParent(null);
		MinHeap<E> minHeap=new MinHeap<E>();
		for(int i=0; i< nodes.size();i++){
			nodes.get(i).indexInHeap=i;
			minHeap.addNode(nodes.get(i));
		}
		//this take O(V) time
		minHeap.buildMinHeap();
		
		//this take O(V*logV+E*logV) where VlogV is comming from V time extractMin and
		//ElogV comes from for loop and updateHeap
		while(minHeap.getSize()!=0){
			Node<E> minNode=minHeap.extractMin();

			for(int i=0;i<minNode.getAdjNodes().size();i++){
				if(minNode.getKey()+minNode.getAdjWeights().get(i)<minNode.getAdjNodes().get(i).getKey()){
					minNode.getAdjNodes().get(i).setKey(minNode.getKey()+minNode.getAdjWeights().get(i));
					minNode.getAdjNodes().get(i).setParent(minNode);
					minHeap.updateHeap(minNode.getAdjNodes().get(i).indexInHeap);
				}
			}
		}
		
		for(Node<E> n: nodes){
			if(n.getParent()!=null){
				System.out.println("("+n.getParent().getId()+","+n.getId() + ") "+(n.getKey()-n.getParent().getKey()));
				
			}
		}
	}
}

class MinHeap<E>{
	int size;
	ArrayList<Node<E>> array;
	
	public int getSize(){
		return size;
	}
	public ArrayList<Node<E>> getArray(){
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
		array=new ArrayList<Node<E>>();
	}
	public void addNode(Node<E> n){
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
		if(left<size && array.get(left).getKey()<array.get(smallest).getKey()){
			smallest=left;
			
		}
		if(right<size && array.get(right).getKey()<array.get(smallest).getKey()){
			smallest=right;
		}
		if(smallest!=i){
			Node<E> tmp=array.get(smallest);
			array.get(i).indexInHeap=smallest;
			array.set(smallest, array.get(i));
			tmp.indexInHeap=i;
			array.set(i, tmp);
			minHeapify(smallest);
	
		}
		
		
	}
	public void updateHeap(int i){
		while(i>0 && array.get(getParent(i)).getKey()>array.get(i).getKey()){
			int indParent=getParent(i);
		
				Node<E> tmp=array.get(indParent);
				array.get(i).indexInHeap=indParent;
				array.set(indParent, array.get(i));
				tmp.indexInHeap=i;
				array.set(i, tmp);
				
			i=indParent;
			
		}
	}
	public Node<E> extractMin(){
		Node<E> result=array.get(0);
		
		
		array.set(0, array.get(array.size()-1));
		minHeapify(0);
		array.remove(array.size()-1);
		size--;
		return result;
	}
	
	
}
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node<Integer> n0=new Node<Integer>(0);
		Node<Integer> n1=new Node<Integer>(1);
		Node<Integer> n2=new Node<Integer>(2);
		Node<Integer> n3=new Node<Integer>(3);
		Node<Integer> n4=new Node<Integer>(4);
		Node<Integer> n5=new Node<Integer>(5);
		Node<Integer> n6=new Node<Integer>(6);
		Node<Integer> n7=new Node<Integer>(7);
		Node<Integer> n8=new Node<Integer>(8);
		n0.addAdjNodes(n1,n7);
		n0.addAdjWeights(4,8);
		n1.addAdjNodes(n0,n2,n7);
		n1.addAdjWeights(4,8,11);
		n2.addAdjNodes(n1,n3,n5,n8);
		n2.addAdjWeights(8,7,4,2);
		n3.addAdjNodes(n2,n4,n5);
		n3.addAdjWeights(7,9,14);
		n4.addAdjNodes(n3,n5);
		n4.addAdjWeights(9,10);
		n5.addAdjNodes(n2,n3,n4,n6);
		n5.addAdjWeights(4,14,10,2);
		n6.addAdjNodes(n5,n7,n8);
		n6.addAdjWeights(2,1,6);
		n7.addAdjNodes(n0,n1,n6,n8);
		n7.addAdjWeights(8,11,1,7);
		n8.addAdjNodes(n2,n6,n7);
		n8.addAdjWeights(2,6,7);
		Graph<Integer> G=new Graph<Integer>();
		G.addNodes(n0,n1,n2,n3,n4,n5,n6,n7,n8);
		G.printGraph();
		System.out.println("Dijkstra");
		G.DijkstraSP();
	}

}
