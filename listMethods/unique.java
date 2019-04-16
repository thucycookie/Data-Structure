import java.util.*;

public class unique{

    //check if the list is unique (i.e no repeating items)
    public static <E> boolean unique(List<E> list){

	//initialize a new ArrayList
	List<E> uniqueList = new ArrayList<>();

	//add the first item to uniqueList
	//with the passed in list's first item
	uniqueList.add(0,list.get(0));


	//put in an exception and change i to start at 1
	//
	//starts at index 1 because we already add the first item
	//to the uniqueList already
	for(int i = 1; i < list.size(); i++){

	    //loop through unique list 
	    for(int j = 0; j < uniqueList.size(); j++){
		
		//if an item is seen again in list
		//then list is not unique
		if((list.get(i)).equals(uniqueList.get(j))){

		    return false;

		    //traverse to the end of the list
		    //i.e the item is not seens twice
		    //then the item is unique and is added to unique list
		}else if(j == uniqueList.size() - 1){

		    uniqueList.add(i,list.get(i));

		    //if there is no break, uniqueList will iterate one more time
		    //this time, the last element of uniqueList will equal to 
		    //the current element in list, which is double counting 
		    //a unique variable 
		    //
		    break;

		}

	    }

	}
		return true;
    }

    

    //find all the unique values of a list
    public static <E> List<E> unique_vals(List <E> list){

	List uniqueList = new ArrayList();

	//keep adding items from list
	//remove only if uniqueList is not unique
	for(E item: list){

	    uniqueList.add(item);

	    if(!unique(uniqueList)){

		uniqueList.remove(item);

	    }

	}

	return uniqueList;

    }

    //return a new list that only contains mulitples of the passed in integer
    public static List<Integer> allMultiples(List<Integer> list, int multiple){

	List<Integer> multiples = new ArrayList<Integer>();

	for(int item: list){

	    if(item % multiple == 0){

		multiples.add(item);

	    }

	}

	return multiples;

    }
	

    //return a new list that contains string that matches a specific length
    public static List<String> allStringsOfSize(List<String> list, int length_long){

	List<String> words = new ArrayList<String>();

	for(String item: list){

	    if(item.length() == length_long){

		words.add(item);

	    }

	}

	return words;

    }

    

    public static <E> boolean isPermutation(List <E> list_1, List <E> list_2){

	//find all unique values of list 1 and 2
	List<E> unique_1 = unique_vals(list_1);

	List<E> unique_2 = unique_vals(list_2);

	//keep track of integers' frequencies in list 1 and 2
	List<Integer> freq_1 = new ArrayList<Integer>();

	List<Integer> freq_2 = new ArrayList<Integer>() ;

	//for counting purpose
	int dups_1 = 0;

	int dups_2 = 0;

	int count = 0;

	//not same size -> false
	if(list_1.size() != list_2.size()){

	    return false;

	}else{

	    for(E item_1: unique_1){
				

		// it is one for dups_1 because we are comparing the unique object
		// in list 1, so if there is no same item in list 2,
		// then we would add one to the freq_1 for list_1 and 
		// zero for list 2
		//
		dups_1 = 1;
                dups_2 = 0; 
                count = 0;


		for(E item_2: unique_2){

		    //this counts if item1 ever equal to item2
		    //this prevents the case in which item2 does
		    //not have the same object in item1
		    //
		    if(item_1.equals(item_2)){

			//get counts for the item in list_1
			for(E stuff_1: list_1){

			    if(item_1.equals(stuff_1)){

				dups_1++;
			    }
			}
			//double counts 
			dups_1--;

			//get counts for the item in list_2
			for(E stuff_2: list_2){

			    if(item_2.equals(stuff_2)){

				dups_2++;

			    }

			}

			//everytime an item appears in both list
			//we increment count once
			//and add the frequency of that item
			//in the corresponding list that contains
			//the frequencies
			count++;
			freq_1.add(dups_1);
			freq_2.add(dups_2);
					

		    }

		}

		//if there list1 and list2 do not share any item
		//-> false
		if(count == 0){

		    return false;

		}

	    }

	    //loop through the frequency list
	    for(int i = 0; i < freq_1.size(); i++){

		//if a frequency count is off like list_1 has 4 numbers of 8
		//and list_2 has 2 numbers of 8 -> false because they are
		//not permutation of each other
		if(freq_1.get(i) != freq_2.get(i)){

		    return false;

		}

	    }

	    return true;

	}

    }
    
    //convert a string to a list of words
    public static List<String> stringToListOfWords(String str){

	List<String> listOfWords = new ArrayList<String>();

	//split by spaces
	String[] arrOfStr = str.split("\\s+");

	for(String a: arrOfStr){

	    listOfWords.add(a);

	}

	return listOfWords;

    }

    //remove all items in the list that matches with the requested item
    public static <E> void  removeAllInstances(List <E> list, E item){
	boolean remove = false;

	for(int i = 0; i < list.size(); i++){

	    if(remove){

		remove = false;

		i--;

	    }

	    if(list.get(i).equals(item)){

		list.remove(item);

		remove = true;

	    }

	}
	   
    }
    

    public static void main(String[] args){

	List<String> a_list = Arrays.asList("hi","hello","meow","good morning", "bonjour","hi");

	List<Integer> b_list = Arrays.asList(1,2,3,3,4);

	System.out.println(unique(a_list));

	List<Integer> multiples = Arrays.asList(1, 25, 2, 5, 30, 19, 57, 2, 25);

	System.out.println(allMultiples(multiples,5));

	List<String> words = Arrays.asList("I", "like", "to", "eat", "eat", "eat","apples", "and", "bananas");

	System.out.println(allStringsOfSize(words,3));

	List<Integer> list_1 = Arrays.asList(1,2,3,3,4);

	List<Integer> list_2 = Arrays.asList(3,3,4,2,1);

	System.out.println(isPermutation(list_1,list_2));

	String str = "Hello, Worlds!";

	System.out.println(stringToListOfWords(str));

	List<Integer> numbs = new LinkedList<>(Arrays.asList(1, 4, 5, 6, 5, 5, 5, 5, 2));

	removeAllInstances(numbs,5);

	System.out.println(numbs);

    }

}
