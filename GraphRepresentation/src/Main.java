import java.util.*;
class Node<E>{
	public enum Status {VISITED, NONVISITED};
	private E id;
	private ArrayList<Node<E>> adjNodes;
	private Status status;
	private Node<E> parent;
	private int rank;
	
	
	
	
	public Node(E id){
		this.id=id;
		this.status=Status.NONVISITED;
		adjNodes=new ArrayList<Node<E>>();
	}
	public void setId(E id){
		this.id=id;
	}
	public E getId(){
		return id;
	}
	public void setStatus(Status status){
		this.status=status;
	}
	public Status getStatus(){
		return this.status;
	}
	public void setParent(Node<E> parent){
		this.parent=parent;
	}
	public Node<E> getParent(){
		return this.parent;
	}
	public int getRank(){
		return rank;
	}
	public void setRank(int rank){
		this.rank=rank;
	}
	public void addAdjNodes(Node<E> ... nodes){
		assert adjNodes!=null: "Adjacent list is NULL";
		for(Node<E> n : nodes){
			adjNodes.add(n);
		}
	
	}
	public ArrayList<Node<E>> getAdjNodes(){
		return adjNodes;
	}
	public void printNode(){
		ListIterator<Node<E>> iterator=adjNodes.listIterator();
		while(iterator.hasNext()){
			System.out.print("("+id+","+((Node<E>)iterator.next()).getId()+")");
		}
		System.out.println();
	}
	
}
class Edge<E>{
	Node<E> start;
	Node<E> end;
	public Edge(Node<E> start,Node<E> end ){
		this.start=start;
		this.end=end;
	}
	public Node<E> getStart(){
		return this.start;
	}
	public Node<E> getEnd(){
		return this.end;
	}
}
class Graph<E>{
	LinkedList<Node<E>> nodes;
	LinkedList<Edge<E>> edges;
	public Graph(){
		nodes=new LinkedList<Node<E>>();
		edges=new LinkedList<Edge<E>>();
		
	}
	public LinkedList<Node<E>> getNodes(){
		return nodes;
	}
	public LinkedList<Edge<E>> getEdges(){
		return edges;
	}
	public void addEdge(Edge<E> e){
	
		for(Edge<E> edge:edges){
			if(edge.start==e.end && edge.end==e.start){
				return;
			}
		}
		edges.add(e);
	}
	public void  addNodes(Node<E> ... nodesToAdd){
		assert nodes!=null: "Adjacent list is NULL";
		
		for(Node<E> node:nodesToAdd)
		{
			nodes.add(node);
			if(node.getAdjNodes()!=null){
				for(Node<E> adjNode: node.getAdjNodes()){
					System.out.println(adjNode.getId());
					this.addEdge(new Edge<E>(node,adjNode));
				}
			}
		}
		
	}
	public void removeEdge(Node<E> start, Node<E> end){
		start.getAdjNodes().remove(end);
		end.getAdjNodes().remove(start);
	}
	public void printGraph(){
		ListIterator<Node<E>> iterator=nodes.listIterator();
		while(iterator.hasNext()){
			iterator.next().printNode();
		}
	}
	//this uses BFS to check if there is a cycle
	//complexity O(E+V)
	public boolean isCycle(){
		if(nodes.size()==0){
			return true;
		}
		boolean result=false;
		Node<E> start=nodes.get(0);
		start.setStatus(Node.Status.VISITED);
		start.setParent(null);
		LinkedList<Node<E>> queue=new LinkedList<Node<E>>();
		queue.add(start);
		while(queue.size()!=0){
			Node<E> current=queue.removeFirst();
			
				for(Node<E> n : current.getAdjNodes()){
					if(n.getStatus()==Node.Status.NONVISITED){
						n.setStatus(Node.Status.VISITED);
						n.setParent(current);
						queue.add(n);
					}
					else{
						if(current.getParent()!=n){
							result=true;
							break;
						}
					}
				}
			
		}
		return result;
	}
	//this uses DFS to check if there is a cycle
	//complexity O(E+V)
	public boolean isCycleDFS(){
		if(nodes.size()==0){
			return true;
		}
	
		Node<E> start=nodes.get(0);
		start.setStatus(Node.Status.VISITED);
		start.setParent(null);
		
		return visitDFS(start);
	
		
	}
	public boolean visitDFS(Node<E> nodeToVisit){
		boolean result=false;
		for(Node<E> n : nodeToVisit.getAdjNodes()){
			if(n.getStatus()==Node.Status.NONVISITED){
				n.setStatus(Node.Status.VISITED);
				n.setParent(nodeToVisit);
				if(visitDFS(n)){
					result= true;
					break;
				}
				
			}
			else{
				if(nodeToVisit.getParent()!=n){
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public Node<E> findParent(Node<E> n){
		if(n.getParent()==null){
			return n;
		}
		return findParent(n.getParent());
		
	}
	public void union(Node<E> n1, Node<E> n2){
		Node<E> pn1=findParent(n1);
		Node<E> pn2=findParent(n2);
		n1.setParent(n2);
	}
	
	public void unionWithRank(Node<E> n1, Node<E> n2){
		Node<E> pn1=findParent(n1);
		Node<E> pn2=findParent(n2);
		if(pn1.getRank()<pn2.getRank()){
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
	//this uses union subsets to find whether there is a cycle
	//comlexity O(E*V)
	public boolean isCycleWithUnion(){
		for(Edge<E> e : edges){
			
				Node<E> pstN=findParent(e.getStart());
				Node<E> pendN=findParent(e.getEnd());
				if(pstN==pendN){
					return true;
				}
				union(pstN,pendN);
		}
		
		return false;
	}
	//this uses union subsets with rank to find whether there is a cycle
	//complexit O(E*logV)
	public boolean isCycleWithUnionRank(){
		for(Edge<E> e : edges){
			
				Node<E> pstN=findParent(e.getStart());
				Node<E> pendN=findParent(e.getEnd());
				if(pstN==pendN){
					return true;
				}
				union(pstN,pendN);
		}
		
		return false;
	}
	
}
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node<Integer> n1=new Node<Integer>(1);
		Node<Integer> n2=new Node<Integer>(2);
		Node<Integer> n3=new Node<Integer>(3);
		Node<Integer> n4=new Node<Integer>(4);
		Node<Integer> n5=new Node<Integer>(5);
		n1.addAdjNodes(n2);
		n2.addAdjNodes(n1,n5,n4);
		n3.addAdjNodes(n5);
		n4.addAdjNodes(n2);
		n5.addAdjNodes(n2,n3);
		Graph G=new Graph();
		G.addNodes(n1,n2,n3,n4,n5);
		
		G.printGraph();
		System.out.println(G.getEdges().size());
		//System.out.println("Remove edge between "+n1.getId()+" and "+n2.getId());
		//G.removeEdge(n1, n2);
		//G.printGraph();
		System.out.println(G.isCycleWithUnionRank());
		
		
		
	}

}
