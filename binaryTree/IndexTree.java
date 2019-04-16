import java.util.Scanner;
import java.io.*;

// Your class. Notice how it has no generics.
// This is because we use generics when we have no idea what kind of data we are getting
// Here we know we are getting two pieces of data:  a string and a line number
public class IndexTree {

    // This is your root 
    // again, your root does not use generics because you know your nodes
    // hold strings, an int, and a list of integers

    private IndexNode root;
	
    // Make your constructor
    // It doesn't need to do anything
    public IndexTree (){

	this.root = null;

    }
    
    // complete the methods below
    // this is your wrapper method
    // it takes in two pieces of data rather than one
    // call your recursive add method
    public void add(String word, int lineNumber){

	this.root = add(this.root, word, lineNumber);	

    }

    // your recursive method for add
    // Think about how this is slightly different the the regular add method
    // When you add the word to the index, if it already exists, 
    // you want to  add it to the IndexNode that already exists
    // otherwise make a new indexNode
    private IndexNode add(IndexNode root, String word, int lineNumber){

	// base case is when the root of a sub tree is zero
	// this mean that the word does not exist
	if(root == null){

	    return new IndexNode(word, lineNumber);

	}
	// to compare whether a word is before or after alphabetically
	// in the tree
	// returns 0, -1 or 1 for compareTo method
	int comparisonResult = word.compareTo(root.word);

	if(comparisonResult == 0){
	    //if word already exists, add to the IndexNode that it is currently at?
	    root.occurences += 1;
	    root.list.add(lineNumber);

	    //tell the parents "hey I am still here"
	    return root;

	}else if(comparisonResult < 0){

	    //if alphabetically before, recursively go to left tree
	    root.left = add(root.left, word, lineNumber);

	    return root;

	}else{

	    //if alphabetically after, recursively go to the right tree
	    root.right = add(root.right, word, lineNumber);

	    return root;

	}

    }

    // returns true if the word is in the index
    // public wrapper method 

    public boolean contains(String word){

	return contains(this.root, word);

    }

    private boolean contains(IndexNode root, String word){

	//base case when the root does not exist

	if(root == null){

	    return false;

	}

	int comparisonResult = word.compareTo(root.word);

	if(comparisonResult == 0){

	    return true;

	}else if(comparisonResult < 0){

	    return contains(root.left, word);

	}else{

	    return contains(root.right, word);

	}

    }

    // call your recursive method
    public void delete(String word){

	this.root = delete(this.root, word);

    }
	
    // your recursive case
    // remove the word and all the entries for the word
    // This should be no different than the regular technique.
    private IndexNode delete(IndexNode root, String word){

	// base case
	if(root == null){
	    return null;
	}

	int comparisonResult = word.compareTo(root.word);

	if(comparisonResult < 0){
	    root.left = delete(root.left, word);
	    return root; 
	}else if(comparisonResult > 0){
	    root.right = delete(root.right, word);
	    return root;

	}else{

	    //found the word that we want to delete
	    //case 1, if sub-root is leaf node, i.e no children
	    if(root.left == null && root.right == null){

		//tell the parent that I am not there anymore 

		return null;

	    }else if(root.left != null && root.right == null){

		//have one child to left but no to right
		return root.left;

	    }else if(root.right != null && root.left == null){

		//have one child to right but no to left

		return root.right;

	    }else{

		//have 2 children
		//go left first and then traverses all the way to right
		IndexNode rootOfLeftSubtree = root.left;
		IndexNode parentOfPredecessor = null;
		IndexNode predecessor = null;
		    
		//if cannot traverse all the way to right
		//of the immediate node of the left subtree
		if(rootOfLeftSubtree.right == null){
		    //moves the root of left subtree up
		    root.word = rootOfLeftSubtree.word;
		    root.left = rootOfLeftSubtree.left;
		    return root; 
		}else{//this means that we can traverses all to right

		    parentOfPredecessor = rootOfLeftSubtree;
		    IndexNode current = rootOfLeftSubtree.right;

		    //like the linkedlist

		    while(current.right != null){

			parentOfPredecessor = current;

			current = current.right;

		    }

		    predecessor = current;

		    root.word = predecessor.word;

		    parentOfPredecessor.right = predecessor.left;

		    return root;

		}

	    }

	}
    }
       	
    // prints all the words in the index in inorder order
    // To successfully print it out
    // this should print out each word followed by the number of occurrences and the list of all occurrences
    // each word and its data gets its own line
    public void printIndex(){

	System.out.println(toString(this.root));

    }

    private String toString(IndexNode root){

	//base case
	if(root == null){
	    return "";
	}

	String output = "";

	//in order traversal
	//traverses left

	output += toString(root.left);

	output += root.toStringIndexTree() + "\n";

	output += toString(root.right);
	return output; 

    }

    public static void main(String[] args){

	IndexTree index = new IndexTree();

	// reading in the text file
	String fileName = "pg100.txt";
	int lineNumber = 1;

	
	try {
	    Scanner scanner = new Scanner(new File(fileName));
	    while(scanner.hasNextLine()){
		String line = scanner.nextLine();

		//split the words by space
		String[] words = line.split("\\s+");
		for(String word: words){
		    word = word.replaceAll(":", "");
		    word = word.replaceAll(",", "");
		    word = word.replaceAll("'", "");

		    try{

			int number = Integer.parseInt(word);

		    }catch (NumberFormatException e){

			//add all the words to the tree 
			index.add(word.toLowerCase(), lineNumber);

		    }

		}

		lineNumber++;

	    }

	    scanner.close();

	}catch (FileNotFoundException e){

	    e.printStackTrace();
	};

	// print out the index
	index.printIndex();
	
	// test removing a word from the index
	index.delete("thy");
	index.printIndex();
	
	
    }
}
