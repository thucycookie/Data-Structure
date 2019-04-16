import java.util.List;
import java.util.ArrayList;

public class IndexNode  {

    // The word for this entry
    String word;

    // The number of occurrences for this word
    int occurences;

    // A list of line numbers for this word.
    List<Integer> list = new ArrayList<Integer>(); 

    IndexNode left;
    IndexNode right;

    // Constructor should take in a word and a line number
    // it should initialize the list and set occurrences to 1
    public IndexNode(String word, int lineNumber){

	//initialize an IndexNode object
	this.word = word;
	this.list.add(lineNumber);
	this.occurences = 1;

    }
	
    // return the word, the number of occurrences, and the lines it appears on.
    // string must be one line
    public String toStringIndexTree(){
	String result = this.word;
	result += " count:" + Integer.toString(occurences);
	result += " line numbers: ";
	for(Integer lineNumber: this.list){
	    result += Integer.toString(lineNumber) + " ";
	}
	return result;
    }

}
