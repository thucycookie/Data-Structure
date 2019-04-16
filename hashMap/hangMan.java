import java.util.Scanner;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Arrays;

public class hangMan{

    public static void playHangman(Map<Integer, List<String>> dict, int len, int wrong_times){
	//a dictionary of word families,
	//start with key of blank spaces * len with value
	//of all the words in the passed in dict with the same len

	//the initialWordfamily will contain the hidden word as key
	//and a list of word families as value. This word familiy
	//is the biggest out of the other ones. 
	Map<String, List<String>> initialWordfamilies = new HashMap<>();

	Map<String, List<String>> wordFamilies = new HashMap<>();
	
	String key = "";
	String padded_key = "";
	Scanner kb = new Scanner(System.in);

	//to stored the guessed letters already
	List<Character> guessed = new ArrayList<>();
	char guessingLetter;
	
	//store the indices of the correctly guessed letter
	//this is for filling in the hidden letters
	List<Integer> index = new ArrayList<>();

	//for storing the biggest found word family 
	List<String> biggestWordFamily = new ArrayList<>();
	List<String> formerFamilyList = new ArrayList<>();

	//keep track of the biggest word family
	int biggest = 0;
	
	//String padding
	//this is for printing out the blank spaces at the beginning of a game
	for(int i = 0; i < len; i++){
	    key += "_";
	}
	
	//intialize the word families dict with "___" (with len * "_" ) as key
	//and the value is a list of all the words with the same length
	initialWordfamilies.put(key,dict.get(len));

	//for strategy initialization (i.e choose word family
	//that reveals the least letters
	int smallest = 100;
	int occurences = 0;
	
	//while loop stops once a user ran out of guesses or guess a word
	//correctly
	while(wrong_times > 0){

	    System.out.println("The current biggest word family is: " + initialWordfamilies);
	    
	    System.out.println("The word in guessing is " + key);

	    for(int characterIndex = 0; characterIndex < key.length(); characterIndex++){
		//break when the user get it right                                             //when the loop finishes at the final character and found that it is not _                                                                       
		if(key.charAt(characterIndex) != '_' && characterIndex == key.length() - 1){                                                                
		    System.out.println("You won! The word is " + key);

		    return;

		}

		if(key.charAt(characterIndex) == '_'){

		    break;

		}

	    }
	    
	    System.out.println("Enter a character to guess");

	    guessingLetter = kb.nextLine().charAt(0);


	    while(guessed.contains(guessingLetter)){

		System.out.println("You have guessed this letter already! Type in a different one.");

		guessingLetter = kb.nextLine().charAt(0);

	    }

	    //add the guessing letter if it has not been guessed already
	    guessed.add(guessingLetter);

	    //step 5c, separating list of hidden words into word families
	    
	    //1st we got to find all the words with the guessing letter appear
	    //in them and then find all the possible word families
	    //and put them in the wordFamilies dictionary
	    
	    for(String string : initialWordfamilies.keySet()){
	
                key = string;

		for(int b = 0; b < initialWordfamilies.get(string).size(); b++){  	
			String word = initialWordfamilies.get(string).get(b);
			index.clear();	    

			//count occurences of the guessing letter
		    	for(int i = 0; i < word.length(); i++){

				if(word.charAt(i) == guessingLetter){ 
			    	index.add(i);

				}

			}

			//filter out all the words in which 
			//pad the key, so it appears something like a__le
			//prev_padded_key = padded_key;

			char[] charArr = key.toCharArray(); 

			for(int a = 0; a < index.size(); a++){

			    charArr[index.get(a)] = guessingLetter;

			}

			padded_key = String.valueOf(charArr);

			//if the wordFamilies dict does not contain the padded_key -> create one
			//
	   		if(wordFamilies.containsKey(padded_key)){

			    formerFamilyList = wordFamilies.get(padded_key);
			    biggestWordFamily = formerFamilyList;
			    biggestWordFamily.add(word);
			    wordFamilies.replace(padded_key,formerFamilyList,biggestWordFamily);
			    
			}else{
			    
			    biggestWordFamily = new ArrayList<>();
			    biggestWordFamily.add(word);
			    wordFamilies.put(padded_key,biggestWordFamily);

			}
			
		}

		//check whether a user get it right, i.e fill in all blanks
		//if not -> subtract from occurences

		for(int characterIndex = 0; characterIndex < key.length(); characterIndex++){

		    if(string.charAt(characterIndex) == '_'){

			wrong_times--;
			break;
		    }
		    
		}
	    }

	    //after finishing looping through all of the word family
	    //we look for the largest one
	    String new_key = "";

	    for(String string:wordFamilies.keySet()){
		if(wordFamilies.get(string).size() > biggest){
		    if(wordFamilies.get(string).size() != 2){

			biggest = wordFamilies.get(string).size();
			new_key = string;
			biggestWordFamily = wordFamilies.get(string);

		    }else{

			for(String word:wordFamilies.get(string)){
			    if(!word.contains(String.valueOf(guessingLetter))){
				new_key = string;
				biggestWordFamily = new ArrayList<>();
				biggestWordFamily.add(word);
			    }
			}
		    }
		}

	    }

	    initialWordfamilies = new HashMap<>();
	    initialWordfamilies.put(new_key, biggestWordFamily);
	    
	    //this is for printing out purpose (print "The guessing word is: "
	    key = new_key;	
	    
	    //re-initialize wordFamilies for every time we 
	    //loop through initialWordFamilies again
	    wordFamilies = new HashMap<>();	
	    biggest = 0;
	    System.out.println("You have: " + wrong_times + " guesses left.");

	    if(wrong_times == 0){

		System.out.println("The word is: " + initialWordfamilies.get(new_key).get(0));

	    }

	}

    }
    
    public static void main(String[] args){

	// reading in the text file
	String fileName = "words.txt";
    
	//get word counts in words.txt
	Map<Integer, List<String>> dictionary = new HashMap<Integer, List<String>>();

	try {

	    Scanner scanner = new Scanner(new File(fileName));

	    while(scanner.hasNextLine()){
		String line = scanner.nextLine();

		//if dictionary does not contain word already, add word and the length
		//the key is the length and the value is a list of words with
		//corresponding length
		if(!dictionary.containsKey(line.length())){

		    List<String> words = new ArrayList<>();
		    words.add(line);
		    dictionary.put(line.length(),words);

		}else{

		    List<String> words = dictionary.get(line.length());
		    words.add(line);
		    dictionary.put(line.length(),words);
		}
			
	    }

	    scanner.close();

	}catch (FileNotFoundException e){

	    e.printStackTrace();

	}

	boolean continuePlaying = true;

	String contPlaying = "";

    	Scanner kb = new Scanner(System.in);

    	while(continuePlaying){

		//step 2: asking the user for the size of the hidden word
		int len = 0;

		//while loop stops asking if the user enters the length of a hidden
		//word that exists in the dictionary
		while(!dictionary.containsKey(len)){

		    System.out.println("What is the length of the word that you want to guess?");

		    len = kb.nextInt();

		}

		//step 3: ask the user how many times do they want to guess before losing
		int wrong_times = 0;
		System.out.println("How many times do you want to guess before losing?");
		wrong_times = kb.nextInt();

		//step 4 and 5 are performed in this function call
		playHangman(dictionary, len, wrong_times);

		//step 6 asking the user if they want to play again 
		System.out.println("Enter Yes if you want to continue playing. If not, enter No.");

		while(contPlaying.equals("")){

		    contPlaying = kb.nextLine();

		}
		
		if(contPlaying.equals("Yes")){

		    continuePlaying = true;

		}else if(contPlaying.equals("No")){

		    continuePlaying = false;

		}
    	}
    }
}
