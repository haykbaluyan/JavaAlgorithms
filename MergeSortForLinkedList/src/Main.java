

class LinkedList{
	private class Node{
		private Node next;
		private Object data;
		public Node(){
			this.data=null;
			this.next=null;
		}
		public Node(Object data){
			this.data=data;
			this.next=null;
		}
		public Object getData(){
			return this.data;
		}
		public void setData(Object data){
			this.data=data;
		}
		public Node getNext(){
			return this.next;
		}
		public void setNext(Node next){
			this.next=next;
		}
	}

	private Node firstNode;
	private Node lastNode;
	private int size=0;
	public void LinkedList(){
		firstNode=null;
		lastNode=null;
		this.size=0;
	}
	
	public void setFirstNode(Node n){
		firstNode=n;
		
	}
	public Node getFirstNode(){
		return firstNode;
	}
	public Node getLastNode(){
		return lastNode;
	}

	//O(1)
	public void addToBeginning(Object data){
		Node node=new Node(data);
		if(this.firstNode==null){
			this.firstNode=node;
			this.lastNode=node;
		}
		else{
			node.setNext(this.firstNode);
			this.firstNode=node;
		}
		this.size++;
	}
	//O(1)
	public void addToEnd(Object data){
		Node node=new Node(data);
		if(this.firstNode==null){
			this.firstNode=node;
			this.lastNode=node;
		}
		else{
			this.lastNode.setNext(node);
			this.lastNode=node;
		}
		this.size++;
	}
	//O(1)
	public void removeFromBeginning(){
		if(this.size==0){
			return;
		}
		
		this.firstNode=this.firstNode.getNext();
		if(this.size==1){
			this.lastNode=null;
		}
		this.size--;
	}
	//O(n)
	public void removeFromEnd(){
		if(this.size==0){
			return;
		}
		
		Node node=this.firstNode;
		Node prev=null;
		while(node.getNext()!=null){
			prev=node;
			node=node.getNext();
		}
		if(prev==null){
			this.firstNode=null;
			this.lastNode=null;
		}
		else{
			this.lastNode=prev;
			this.lastNode.setNext(null);
		}
		this.size--;
	
	}
	//O(n)
	public void removeObject(Object data){
		Node node=this.firstNode;
		if(this.size==0){
			return;
		}
		
		if(data.equals(node.getData())){
			
			if(this.size==1){
				this.firstNode=null;
				this.lastNode=null;
			}
			else{
				this.firstNode=node.getNext();
			}
			this.size--;
			return;
		}
		
		Node prev=null;
		while(node!=null && !data.equals(node.getData()))
		{
			prev=node;
			node=node.getNext();
		}
		if(node!=null){
			prev.setNext(node.getNext());
			if(prev.getNext()==null){
				this.lastNode=null;
			}
			this.size--;
		}
		
		
	}
	public int getSize(){
		return this.size;
	}
	public String toString() {
        Node currentNode = this.firstNode;
        StringBuffer buffer = new StringBuffer();

        buffer.append("{");
        for (int i = 0; currentNode != null; i++) {
          if (i > 0) {
            buffer.append(",");
          }
          Object dataObject = currentNode.getData();

          buffer.append(dataObject == null ? "" : dataObject);
          currentNode = currentNode.getNext();
        }
        buffer.append("}");
        return buffer.toString();
      }
	
	//mergeSort on LinkedList
	public  void mergeSort(){
		
		
		if(this.firstNode==null || this.firstNode.getNext()==null)
			return;
		Node first=this.getFirstNode();
		Node second=first;
		
		Node prev=null;
		while(second!=null && second.getNext()!=null){
			
			prev=first;
			first=first.getNext();
			second=second.getNext().getNext();
		
			
		}
		
		first=this.getFirstNode();
		second=prev.getNext();
	
		prev.setNext(null);
		
		LinkedList firstList=new LinkedList();
		firstList.setFirstNode(first);
		LinkedList secondList=new LinkedList();
		secondList.setFirstNode(second);
		
	
		firstList.mergeSort();
		secondList.mergeSort();
		
		merge(firstList,secondList);
	}
	public void merge(LinkedList firstList, LinkedList secondList){
		
		LinkedList result=new LinkedList();
		Node x=firstList.getFirstNode();
		Node y=secondList.getFirstNode();
	
		while(x!=null && y!=null){
			if((int)x.getData()<(int)y.getData()){
				result.addToEnd(x.getData());
				x=x.getNext();
			}
			else{
				result.addToEnd(y.getData());
				y=y.getNext();
			}
			
		}
		
		while(x!=null){
		
				result.addToEnd(x.getData());
				x=x.getNext();
		
			
		}
		while(y!=null){
			
			result.addToEnd(y.getData());
			y=y.getNext();
	
		}
	
	
		this.setFirstNode(result.getFirstNode());
		
		
		
		
	}
}

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList list=new LinkedList();
		list.addToBeginning(5);
		list.addToBeginning(4.5);
		list.addToBeginning("20");
		list.addToEnd(30);
		System.out.println(list.toString() +" "+list.getSize());
		list.removeFromBeginning();
		list.removeFromEnd();
		list.removeFromEnd();
		list.removeFromEnd();
		list.removeFromEnd();
		list.removeFromEnd();
		System.out.println(list.toString() +" "+list.getSize());
		Object ob1=20;
		Object ob2=23;
		Object ob3=25;
		list.addToBeginning(ob1);
		list.addToBeginning(ob2);
		list.addToEnd(ob3);
		System.out.println(list.toString() +" "+list.getSize());
		
		list.removeObject(ob3);
		System.out.println(list.toString() +" "+list.getSize());
		
		
		list.addToBeginning(5);
		list.addToBeginning(15);
		list.addToBeginning(1);
		list.addToBeginning(-4);
		list.addToBeginning(52);
		System.out.println(list.toString() +" "+list.getSize());
		
		list.mergeSort();
		System.out.println(list.toString() +" "+list.getSize());
		
		
		
	}

}
