import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

class Node<E>{
	E id;
	LinkedList<Node<E>> adjNodes;
	LinkedList<Integer> adjWeights;
	Node<E> parent;
	int rank;
	boolean isInMstSet;
	double key;
	public Node(E id){
		this.id=id;
		adjNodes=new LinkedList<Node<E>>();
		adjWeights=new LinkedList<Integer>();
		parent=null;
		rank=0;
		key=Double.POSITIVE_INFINITY;
	}
	public E getId(){
		return id;
	}
	public void setId(E id){
		this.id=id;
	}
	public LinkedList<Node<E>> getAdjNodes(){
		return adjNodes;
	}
	

	
	public LinkedList<Integer> getAdjWeights(){
		return adjWeights;
	}
	
	public void setParent(Node<E> parent){
		this.parent=parent;
	}
	public Node<E> getParent(){
		return parent;
	}
	public int getRank(){
		return rank;
	}
	public void setRank(int rank){
		this.rank=rank;
	}
	public double getKey(){
		return key;
	}
	public void setKey(double key){
		this.key=key;
	}
	public boolean getIsInMstSet(){
		return isInMstSet;
	}
	public void setIsInMstSet(boolean b){
		this.isInMstSet=b;
	}
	public void addAdjNodes(Node<E>...nodes){
		assert adjNodes!=null : "List of adjacent nodes is not initialized";
		assert nodes!=null : "List of added adjacent nodes is null";
		for(Node<E> n : nodes){
			adjNodes.add(n);
		}
		
	}
	
	public void addAdjWeights(Integer...weights){
	

		for(Integer w : weights){
			adjWeights.add(w);
		}
		
	}
	public Node<E> findParent(){
		if(this.parent==null){
			return this;
			
		}
		return this.parent.findParent();
	}
	public static <E> void union(Node<E> n1, Node<E> n2){
		Node<E> pn1=n1.findParent();
		Node<E> pn2=n2.findParent();
		pn1.setParent(pn2);
	}
	public static <E> void unionWithRank(Node<E> n1, Node<E> n2){
		Node<E> pn1=n1.findParent();
		Node<E> pn2=n2.findParent();
		if(pn1.getRank()<n2.getRank()){
			pn1.setParent(pn2);
		}
		else{
			if(pn1.getRank()>pn2.getRank()){
				pn2.setParent(pn1);
			}
			else{
				pn2.setRank(pn2.getRank()+1);
				pn1.setParent(pn2);
			}
		}
		
	}
	
	
}

class Graph<E,W>{
	LinkedList<Node<E>> nodes;

	public Graph(){
		nodes=new LinkedList<Node<E>>();
	
	}
	public LinkedList<Node<E>> getNodes(){
		return nodes;
	}
	public void addNodes(Node<E>... nodes){
		assert this.nodes!=null: "List of nodes is null";
		assert nodes!=null: "List of added nodes is null";
		for(Node<E> n :nodes)
			this.nodes.add(n);
	
	}
	
	
	public void printGraphWithNodes(){
		if(nodes==null){
			System.out.println("Graph is empty");
			return;
		}
		for(Node<E> n :nodes){
			if(n.getAdjNodes()==null){
				System.out.println(n.getId()+" no edges available");
			}
			else{
				for(Node<E> nAdj:n.getAdjNodes()){
					System.out.print("( "+n.getId()+","+nAdj.getId()+") ");
				}
				System.out.println();
			}
		}

		
	}
	
	
	public void findMinSpanTreePrim(){
		
		Node<E> start=nodes.get(0);
		

	
		start.setIsInMstSet(false);
		start.setKey(0);
		start.setParent(null);
		//this runs O(V^2) times because of findMinKey function, which is O(V)
		for(int i=0;i<nodes.size()-1;i++){
			//this runs O(V) times
			int min_index=findMinKey();
	
			Node<E> vertex=nodes.get(min_index);
			vertex.setIsInMstSet(true);
			//this overall runs O(E) times
			for(int j=0;j<vertex.getAdjNodes().size();j++){
				
				if(vertex.getAdjNodes().get(j).getIsInMstSet()==false){
					
					if(vertex.getAdjNodes().get(j).getKey()>vertex.getAdjWeights().get(j)){
						vertex.getAdjNodes().get(j).setParent(vertex);
						vertex.getAdjNodes().get(j).setKey(vertex.getAdjWeights().get(j));
						}
				
				}
			}
		}
		System.out.println("Minimum spanning tree with Prim's algorithms");
		for(int i=0;i<nodes.size();i++){
			Node<E> parent=nodes.get(i).getParent();
			if(parent!=null){
				System.out.println("("+parent.getId()+","+nodes.get(i).getId()+") "+nodes.get(i).getKey());
				
			}
			
			
		}
		
	}
	
	public int findMinKey(){
		double min=Double.POSITIVE_INFINITY;
		int min_index=-1;
		for(int i=0;i<nodes.size();i++){
	
			if(nodes.get(i).getIsInMstSet()==false && nodes.get(i).getKey()<=min){
				min=nodes.get(i).getKey();
				min_index=i;
			}
		}
		
		return min_index;
	}

}

public class Main {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph<Integer,Integer> G=new Graph<Integer,Integer>();
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
		n5.addAdjNodes(n2,n4,n6);
		n5.addAdjWeights(4,14,10,2);
		n6.addAdjNodes(n5,n7,n8);
		n6.addAdjWeights(2,1,6);
		n7.addAdjNodes(n0,n1,n6,n8);
		n7.addAdjWeights(8,11,1,7);
		n8.addAdjNodes(n2,n6,n7);
		n8.addAdjWeights(2,6,7);
		G.addNodes(n0,n1,n2,n3,n4,n5,n6,n7,n8);
	

		G.printGraphWithNodes();
		G.findMinSpanTreePrim();
		
		
	
	}

}
