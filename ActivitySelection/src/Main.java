import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

class Sample<E>{
	public void printArray(E[] array){
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]);
		}
		System.out.println();
	}
}
class MyComparator implements Comparator<Entry<IntegerToObject,IntegerToObject>>{

	@Override
	public int compare(Entry<IntegerToObject,IntegerToObject> o1, Entry<IntegerToObject,IntegerToObject> o2) {
		// TODO Auto-generated method stub
		
		return o1.getValue().getInteger()-o2.getValue().getInteger();
	}
	
}

class IntegerToObject {
	private int integer;
	public int getInteger(){
		return integer;
	}
	public IntegerToObject(int integer){
		this.integer=integer;
	}
}
class ValueComparator implements Comparator<IntegerToObject> {

    HashMap<IntegerToObject, IntegerToObject> base;
    public ValueComparator(HashMap<IntegerToObject, IntegerToObject> base) {
        this.base = base;
    
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(IntegerToObject a, IntegerToObject b) {
    	if(base.get(a).getInteger()-base.get(b).getInteger()!=0)
    		return base.get(a).getInteger()-base.get(b).getInteger();
    	return 1;
    }
}
public class Main {

	static class Animal implements Comparable  {
		private String name;
		private Integer id;
		public Integer getId(){
			return id;
		}
		public Animal(String name,Integer id){
			this.name=name;
			this.id=id;
		}
		//@Override
		public int compareTo(Object arg0) {
			// TODO Auto-generated method stub
			return -name.compareTo(((Animal)arg0).name);
		}
		public static void printArrayOfAnimals(Animal[] ar){
			for(Animal ob : ar){
				System.out.print(ob.name+" ");
			}
			System.out.println();
		}
		public String toString(){
			return name+id;
		}
	
	}
	public static void printArray(Object[] array){
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer []start={1, 3, 0, 5, 8, 5};
		Integer []end={2, 4, 6, 7, 9, 9};
	
		HashMap<IntegerToObject,IntegerToObject> hmap=new HashMap<IntegerToObject,IntegerToObject>();
		for(int i=0;i<start.length;i++){
			hmap.put(new IntegerToObject(start[i]),new IntegerToObject(end[i]));
		}
		System.out.println(hmap);


		ArrayList<Entry<IntegerToObject,IntegerToObject>> listEntries=new ArrayList<Entry<IntegerToObject,IntegerToObject>>(hmap.entrySet());
		Collections.sort(listEntries, new MyComparator());
		hmap=new HashMap<IntegerToObject,IntegerToObject>();
		ArrayList<Entry<IntegerToObject,IntegerToObject>> selectedEntries=new ArrayList<Entry<IntegerToObject,IntegerToObject>>();
		Entry<IntegerToObject,IntegerToObject> lastEntry=listEntries.get(0);
		selectedEntries.add(lastEntry);
		for(int i=1;i< listEntries.size();i++ ){
			if(lastEntry.getValue().getInteger()<=listEntries.get(i).getKey().getInteger()){
				lastEntry=listEntries.get(i);
				selectedEntries.add(lastEntry);
			}
			
		}
		
		for(Entry<IntegerToObject,IntegerToObject> entry : listEntries ){
			
			System.out.println(entry.getKey().getInteger()+" "+entry.getValue().getInteger());
		}
		System.out.println("Selected intervals");
		for(Entry<IntegerToObject,IntegerToObject> entry : selectedEntries ){
			
			System.out.println(entry.getKey().getInteger()+" "+entry.getValue().getInteger());
		}
		/*ValueComparator vcomp=new ValueComparator(hmap);
		TreeMap<IntegerToObject,IntegerToObject> sorted_map = new TreeMap<IntegerToObject,IntegerToObject>(vcomp);
		System.out.println();
		sorted_map.putAll(hmap);
		for(Entry<IntegerToObject,IntegerToObject> entry : sorted_map.entrySet() ){
			hmap.put(entry.getKey(), entry.getValue());
			System.out.println(entry.getKey().getInteger()+" "+entry.getValue().getInteger());
		}*/
		
		
		
	}

}
