//origin work by Andrew Rosen
//Andrew Rosen mostly provided the skeleton for
//this class
//The content was by Thuc Duong
//
import java.util.Iterator;
import java.util.*;

public class CircularLinkedList<E> implements Iterable<E> {
	
    // Your variables
    Node<E> head;
    Node<E> tail;
    int size;  // BE SURE TO KEEP TRACK OF THE SIZE
    
	
    // implement this constructor	
    public CircularLinkedList() {
	head = null;
	tail = null;
	size = 0;
    }

    // I highly recommend using this helper method
    // Return Node<E> found at the specified index
    // be sure to handle out of bounds cases
    private Node<E> getNode(int index) {
	
	//handles out of bounds situation
	if(index < 0 || index >= size){
	    
	    throw new IndexOutOfBoundsException();
	    
	}
	//initialize current node as the head
	Node <E> current = head;
	
	//traverse through the linkedlist until
	//we get to the node with matched index
	for(int i = 0; i < index; i++){

	    current = current.next;

	}

	return current;
	
    }


    // attach a node to the end of the list
    public boolean add(E item) {
	
	this.add(size,item);

	return true;

    }

	
    // Cases to handle
    // out of bounds
    // adding to empty list
    // adding to front
    // adding to "end"
    // adding anywhere else
    // REMEMBER TO INCREMENT THE SIZE
    public void add(int index, E item){
		

	//put mem address of item being added
	//inside of variable beingAdded
	Node<E> beingAdded = new Node<E>(item);
	    
	//out of bounds
	//
	if(index < 0 || index > size){

	    throw new IndexOutOfBoundsException();

	}

	//empty list
	//so the head and tail is the same
	//
	if(size == 0){

	    tail = beingAdded;

	    head = beingAdded;

	}//adding to the front
	else if(index == 0){

	    
	    //attaches to head first
	    //so we do not lose reference
	    beingAdded.next = head;

	    //then makes things being added
	    //the head
	    //
	    head = beingAdded;
	    
	}//adding to the end
	else if(index == size){
	    
	    //attaches to tail first
	    //
	    
	    tail.next = beingAdded;
	    tail = beingAdded;
	    
	}//everything else, usually somewhere in the middle
	else{

	    //think about i-1,i and i+1
	    //get the node before the pos being added
	    //
	    Node<E> before = getNode(index - 1);
	    
	    //points the node being added to the node at
	    //pos you want to insert in
	    beingAdded.next = before.next;

	    //points before to beingAdded as next node
	    //
	    before.next = beingAdded;

	}
		
	//tail's next is head since it is circular
	//
	//increments size whenever adding
	size++;
    }	

    // remove must handle the following cases
    // out of bounds
    // removing the only thing in the list
    // removing the first thing in the list (need to adjust the last thing in the list to point to the beginning)
    // removing the last thing 
    // removing any other node
    // REMEMBER TO DECREMENT THE SIZE
    public E remove(int index) {

	//get the node being removed
	E removing = null;

	//out of bounds
	if(index < 0 || index >= size){
	    throw new IndexOutOfBoundsException();
	}//only thing in the list
	else if(size == 0){
	    head = null;
	    tail = null;
	}//removing the first thing and point last to first
	else if(index == 0){
	    removing = this.head.item;
	    //remove head
	    //
	    head = head.next;
	}//everything else but mostly removing things from middle of a list
	else{
	    //get the mem location of the node before
	    Node<E> before = getNode(index - 1);
			
	    //get item from item being removed
	    removing = before.next.item;

	    //points node before to node after
	    //before.next = getNode(index + 1);
	    before.next = before.next.next;
	}
	size--;
	return removing;
    }
		
    // Turns your list into a string
    // Useful for debugging
    public String toString(){
	Node<E> current = head;
	StringBuilder result = new StringBuilder();
	if(size == 0){
	    return "";
	}
	if(size == 1) {
	    return head.item.toString();
	    
	}
	else{
	    do{
		result.append(current.item);
		result.append(" ==> ");
		current = current.next;
	    } while(current != head);
	}
	return result.toString();
    }
		
    public Iterator<E> iterator() {

	return new ListIterator<E>();

    }

    
    // provided code for different assignment
    // you should not have to change this
    // change at your own risk!
    // this class is not static because it needs the class it's inside of to survive!
    private class ListIterator<E> implements Iterator<E>{
		
	Node<E> nextItem;
	Node<E> prev;
	int index;
		
	@SuppressWarnings("unchecked")
	//Creates a new iterator that starts at the head of the list
	public ListIterator(){
	    nextItem = (Node<E>) head;
	    index = 0;
	}

	// returns true if there is a next node
	// this is always should return true if the list has something in it
	public boolean hasNext() {
	    // TODO Auto-generated method stub
	    return size != 0;
	}
		
	// advances the iterator to the next item
	// handles wrapping around back to the head automatically for you
	public E next() {
	    // TODO Auto-generated method stub
	    prev =  nextItem;
	    nextItem = nextItem.next;
	    index =  (index + 1) % size;
	    return prev.item;
	    
	}
		
	// removed the last node was visted by the .next() call 
	// for example if we had just created a iterator
	// the following calls would remove the item at index 1 (the second person in the ring)
	// next() next() remove()
	public void remove() {
	    int target;
	    if(nextItem == head) {
		target = size - 1;
	    } else{ 
		target = index - 1;
		index--;
	    }
	    CircularLinkedList.this.remove(target); //calls the above class
	}
	
    }
	
    // It's easiest if you keep it a singly linked list
    // SO DON'T CHANGE IT UNLESS YOU WANT TO MAKE IT HARDER
    private static class Node<E>{
	E item;
	Node<E> next;
	
	public Node(E item) {
	    this.item = item;
	}
		
    }

    //solitaire algorithm
    public static String generate_key_stream(String final_key_stream, CircularLinkedList<Integer> key_stream, int string_size){
		
	if(string_size >= 0){
		
	    Node<Integer> current = key_stream.head;
	    
	    int Joker_27_index = 0;
		
	    while(current.item != 27){
		Joker_27_index++;
		current = current.next;
	    }
	    Node<Integer> Joker_27 = key_stream.getNode(Joker_27_index);
	    key_stream.remove(Joker_27_index);
		
	    //moves Joker B down one index
	    //
	    Joker_27_index++;
	    key_stream.add(Joker_27_index,Joker_27.item);

	    //look for Joker A aka. 28 in the deck
	    //
	    current = key_stream.head;
		
	    int Joker_28_index = 0;
	    
	    while(current.item != 28){
		    Joker_28_index++;
		    current = current.next;
	    }

	    Node<Integer> Joker_28 = key_stream.getNode(Joker_28_index);
	    key_stream.remove(Joker_28_index);

	    //moves Joker A down 2 index
	    //
	    Joker_28_index += 2;
	    key_stream.add(Joker_28_index,Joker_28.item);
		
	    //triple cut
	    //
	    Joker_28_index--;
	    Joker_27_index++;
	    CircularLinkedList<Integer> top = new CircularLinkedList<>();
	    CircularLinkedList<Integer> mid = new CircularLinkedList<>();
	
	    int i = 0;
	
	    for(i = 0; i < key_stream.size; i++){
		top.add(key_stream.remove(0));
		if(key_stream.head.item == 28 || key_stream.head.item == 27){
		    break;
		}
		
	    };

	    mid.add(key_stream.remove(0));

	    for(i = 0; i < key_stream.size; i++){
		mid.add(key_stream.remove(0));
		if(key_stream.head.item == 28 || key_stream.head.item == 27){
		    mid.add(key_stream.remove(0));
		    break;
		}
	    }
		
	    //moves the bottom of the deck to top
	    for(int n = 0; n < mid.size; n++){

	 	key_stream.add(mid.getNode(n).item);

	    }
		
	    //moves the middle aka. the former head of the deck
	    //to the bottom
	    //
	    for(int a = 0; a < top.size; a++){
		key_stream.add(top.getNode(a).item);
	    }
		
	    //step 4
	    //
	    int last_index = key_stream.size - 1;
	    int bottom_val = key_stream.getNode(last_index).item;
	     CircularLinkedList<Integer> remove = new CircularLinkedList<>();
	
	     //remove the first item bottom_val times
	     //
	     for(int b = 0; b < bottom_val; b++){
		 remove.add(key_stream.remove(0));
	     }
	
	     //add remove pile before the last card in the deck
	     //
	     int second_to_last = 0;

	     for(int e = 0; e < remove.size; e++){
		 second_to_last = key_stream.size - 1;
		 key_stream.add(second_to_last, remove.getNode(e).item);
	     }
		
	     //step 5
	     //
	     int order_of_key_stream = key_stream.head.item;
	     int return_key_stream = key_stream.getNode(order_of_key_stream).item;		

	     //recursively generating key_stream values
	     if(return_key_stream == 27 || return_key_stream == 28){
		 
		 final_key_stream += "" + generate_key_stream(final_key_stream,key_stream,string_size-1);
		
	     }else{
		 
		 final_key_stream += Integer.toString(return_key_stream) + "," + generate_key_stream(final_key_stream,key_stream,string_size-1);

	     }

	}
	return final_key_stream;
    }

    //encrypt the message
    public static String encrypt(String message){
	//convert to upper case
	//
	message.toUpperCase();
			
	String encrypt_msg = ""; 

	//message should be some factor of 5 so we pad it with X
	//if that is not the case
	if(message.length() % 5 != 0){
	    int to_pad = 5 - (message.length()%5);
	    for(int a = 0; a < to_pad; a++){
		message += "X";
	    }
	}

	//empty string list to store msg as intergers
	//
	List<Integer> msg_num = new ArrayList<>();
	for(int i = 0; i < message.length(); i++){
	    msg_num.add((int)message.charAt(i) - 64);
	    encrypt_msg += msg_num.get(i).toString() + ",";
	}
	return encrypt_msg;
		
    }

    public static void main(String[] args){

	String msg = "HOWAREYOU";
	System.out.println(encrypt(msg));
		
	//construct a circular linked list
	//                
	CircularLinkedList<Integer> key_stream = new CircularLinkedList<>();
	List<Integer> normal = Arrays.asList(1,4,7,10,13,16,19,22,25,28,3,6,9,12,15,18,21,24,27,2,5,8,11,14,17,20,23,26);

	for(int a = 0; a < normal.size(); a++){
	    key_stream.add(a,normal.get(a));
	}

	String key_stream_string = generate_key_stream("",key_stream,msg.length());
	List<String> message = Arrays.asList(encrypt(msg).split(","));
	List<String> key_stream_split = Arrays.asList(key_stream_string.split(","));
	List<Character> encrypt_msg = new ArrayList<>();
	int sum = 0;

	//encrypt with method that generate key stream
	for(int i = 0; i < key_stream_split.size(); i++){
	    sum = Integer.parseInt(message.get(i)) + Integer.parseInt(key_stream_split.get(i));

	    if(sum > 26)	
		sum -= 26;
	    encrypt_msg.add((char)(sum + 64));		
	}

	System.out.println("The encrypt message is " + encrypt_msg);

	List<Character> decrypt_msg = new ArrayList<>();

	//decrypting the message 
	for(int b = 0; b < encrypt_msg.size(); b++){
	    sum = (int)(encrypt_msg.get(b)) - 64 - Integer.parseInt(key_stream_split.get(b));
	    if(sum <= 0){
		sum += 26;
	    }
	    decrypt_msg.add((char)(sum + 64));
	}

	System.out.println("The decrypt message is " + decrypt_msg);

    }
	
}
