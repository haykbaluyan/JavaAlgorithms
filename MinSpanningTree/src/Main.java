import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

class Node<E>{
	E id;
	LinkedList<Node<E>> adjNodes;
	
	Node<E> parent;
	int rank;
	public Node(E id){
		this.id=id;
		adjNodes=new LinkedList<Node<E>>();
		parent=null;
		rank=0;
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
	
	public void setParent(Node<E> parent){
		this.parent=parent;
	}
	public int getRank(){
		return rank;
	}
	public void setRank(int rank){
		this.rank=rank;
	}
	public void addAdjNodes(Node<E>...nodes){
		assert adjNodes!=null : "List of adjacent nodes is not initialized";
		assert nodes!=null : "List of added adjacent nodes is null";
		for(Node<E> n : nodes){
			adjNodes.add(n);
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
class Edge<E,W>{
	Node<E> src;
	Node<E> dest;
	W weight;
	public Edge(Node<E> src, Node<E> dest,W weight){
		this.src=src;
		this.dest=dest;
		this.weight=weight;
	}
	public Node<E> getSrc(){
		return src;
	}
	public Node<E> getDest(){
		return dest;
	}
	public W getWeight(){
		return weight;
	}
	public void setWeight(W w){
		weight=w;
	}
	public void printEdge(){
		System.out.println("("+this.getSrc().getId()+","+this.getDest().getId()+") "+this.getWeight());
		
	}
	
}
class Graph<E,W>{
	LinkedList<Node<E>> nodes;
	LinkedList<Edge<E,W>> edges;
	public Graph(){
		nodes=new LinkedList<Node<E>>();
		edges=new LinkedList<Edge<E,W>>();
	}
	public LinkedList<Node<E>> getNodes(){
		return nodes;
	}
	public void addNodes(Node<E>... nodes){
		assert this.nodes!=null: "List of nodes is null";
		assert nodes!=null: "List of added nodes is null";
		for(Node<E> n : nodes){
			if(n.getAdjNodes()!=null){
				for(Node<E> n2 : n.getAdjNodes()){
					this.edges.add(new Edge<E,W>(n,n2,null));
				}
			}
			this.nodes.add(n);
	
		}
	}
	public void addEdge(Edge<E,W>  edge){
		assert this.edges!=null: "List of edges is null";
		assert edges!=null: "List of added edges is null";
	
		this.edges.add(edge);
	}
	
	
	public void addEdges(Edge<E,W> ... edges){
		assert this.edges!=null: "List of edges is null";
		assert edges!=null: "List of added edges is null";
		for(Edge<E,W> e : edges){
			this.edges.add(e);
		}

	}
	public void assignWeights(W ... weights ){
		
		assert weights !=null: "Weights list is empty";
		int current=0;
		for(Edge<E,W> e:edges){
			e.setWeight(weights[current++]);
		}
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
	
	public void printGraphWithEdges(){
	
		if(edges==null){
			System.out.println("Graph has no edge");
			return;
		}
		for(Edge<E,W> e :edges){
		
			e.printEdge();		
			
		}

		
	}
	class CompareEdges implements Comparator<Edge<E,W>>{

		@Override
		public int compare(Edge<E, W> arg0, Edge<E, W> arg1) {
			// TODO Auto-generated method stub
			return (Integer)(arg0.getWeight())-(Integer)(arg1.getWeight());
		}
		
	}
	public void sortEdges(){
		Collections.sort(edges,new CompareEdges());
	}
	
	//Kruskal algorithm to find min spanning tree
	//sort edges by weights (ElogE)
	//iterate over edges and include it if vertices are in diffrenet sets (union takes O(logV))
	//hence compexity of Kruskal is O(ElogE+ElogV) and since E is at most V^2, O(logV)==O(logE)
	//thus complexity of Kruskal is O(ElogE) or O(ElogV)
	public void findMinSpanTreeKruskal(){
		
		this.sortEdges();
		this.printGraphWithEdges();
		System.out.println("Min Spanning tree is");
		for(Edge<E,W> e : edges){
			Node<E> pSrc=e.getSrc().findParent();
			Node<E> destSrc=e.getDest().findParent();
			if(pSrc!=destSrc){
				e.printEdge();
				Node.unionWithRank(pSrc, destSrc);
				
			}
		}
		System.out.println();
		
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
		n1.addAdjNodes(n2,n7);
		n2.addAdjNodes(n3,n5,n8);
		n3.addAdjNodes(n4,n5);
		n4.addAdjNodes(n5);
		n5.addAdjNodes(n6);
		n6.addAdjNodes(n7,n8);
		n7.addAdjNodes(n8);
		
		G.addNodes(n0,n1,n2,n3,n4,n5,n6,n7,n8);
		Integer[] weights={4,8,8,11,7,4,2,9,14,10,2,1,6,7};
		
		G.assignWeights(weights);
	
		G.findMinSpanTreeKruskal();
		
	
	
	}

}
