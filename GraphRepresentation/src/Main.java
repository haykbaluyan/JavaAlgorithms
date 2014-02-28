import java.util.*;
class Node<E>{
	public enum Status {VISITED, NONVISITED};
	private E id;
	private ArrayList<Node<E>> adjNodes;
	private Status status;
	private Node<E> parent;
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
class Graph<E>{
	LinkedList<Node<E>> nodes;
	
	public Graph(){
		nodes=new LinkedList<Node<E>>();
		
	}
	public LinkedList<Node<E>> getNodes(){
		return nodes;
	}
	public void  addNodes(Node<E> ... nodesToAdd){
		assert nodes!=null: "Adjacent list is NULL";
		for(Node<E> node:nodesToAdd)
			nodes.add(node);
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
		n2.addAdjNodes(n1,n5);
		n3.addAdjNodes(n5);
		n4.addAdjNodes(n2,n5);
		n5.addAdjNodes(n1,n2,n3,n4);
		Graph G=new Graph();
		G.addNodes(n1,n2,n3,n4,n5);
		G.printGraph();
		//System.out.println("Remove edge between "+n1.getId()+" and "+n2.getId());
		//G.removeEdge(n1, n2);
		//G.printGraph();
		System.out.println(G.isCycle());
		
		
	}

}
