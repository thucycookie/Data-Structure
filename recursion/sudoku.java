import java.util.Arrays;
import java.util.*;
import java.io.File;

public class sudoku {

    //check whether a sudoku move is legal
    public static boolean legal(int[][] board, int row, int col, int val){

	//check for all elements in the board's row
	for(int i = 0; i < board.length; i++){
	    if(board[row][i] == val){
		return false;
	    }
	}
		
	//check for all elements in the board's col
	for(int i = 0; i < board.length;i++){
	    if(board[i][col] == val){
		return false;
	    }
	}

	//check for surrounded squares
	//i.e the smaller sudoku boards within the bigger
	//board 
	
	//find squareroot because each board is made up of
	//n x n smaller boards 
	int smallLen = (int) Math.sqrt(board.length);
	int smallRow = row - (row % smallLen);
	int smallCol = col - (col % smallLen);

	for(int a = smallRow; a < smallRow + smallLen; a++){
	    for(int u = smallCol; u < smallCol + smallLen; u++){
		if(board[a][u] == val){
		    return false;
		}
	    }
	}
	return true;
		
    }

    
    public static boolean solveSudoku(int[][] sudoku){

	//loop through all possible choices
	//
	for(int row = 0; row < sudoku.length; row++){
	    for(int col = 0; col < sudoku[0].length; col++){
		//if there is an empty box
		if(sudoku[row][col] == 0){
		    for(int val = 1; val < 10; val++){

			//check for legality
			if(legal(sudoku,row,col,val)){

			    //marked the sudoku square
			    sudoku[row][col] = val;

			    if(solveSudoku(sudoku)){
				System.out.println(Arrays.toString(sudoku[0]));
				return true;
			    }
			}
			//deleted marked board
			//if the value does not work
			//
			sudoku[row][col] = 0;

		    }

		    //back tracks when all tried val does not work

		    return false;

		}

	    }

	}

	return true;

    }
    
    public static void main(String[] args){
	int[][] sudoku = new int[][]{{5,0,0},{2,7,4},{0,8,0}};
	solveSudoku(sudoku);
	for(int row = 0; row < sudoku.length; row++){
	    for(int col = 0; col < sudoku[0].length; col++){
		System.out.print(sudoku[row][col]);
	    }
	    System.out.println();
	}

	//extra credit
	//this is on Euler's website
	File Read = new File("./sudoku.txt");
	int row = 0;
	int col = 0; 
	int[][] board = new int[9][9];
	int count = 0;
	int sum = 0;

	//read in the file line by line
	try{
	    Scanner scanner = new Scanner(System.in);
	    scanner = new Scanner(Read);

	    while(scanner.hasNextLine()){
		String line = scanner.nextLine();

		//reset row to be zero once the next line has Grid
		//this is when we have read in the prev sudoku grid
		if(line.startsWith("Grid")){
		    row = 0;
		}


		//if does not start with grid -> it is for reading in the sudoku
		//array
		if(!line.startsWith("Grid")){

		    col = 0; 
		    for(int i = 0; i < line.length(); i++){
			board[row][col] = Character.getNumericValue(line.charAt(i));
			col++;
		    }
		    row++;	
		}
		
		//finishes one sudoku board
		if(row == board.length){
		    solveSudoku(board);
		    count++;
		    System.out.println("Board " + count);
		    for(int row_print = 0; row_print < board.length; row_print++){
			for(int col_print = 0; col_print < board.length; col_print++){
			    System.out.print(board[row_print][col_print]);
			}
			System.out.println();
		    }

		    //find the three digits number
		    String three_digits = Integer.toString(board[0][0]) + Integer.toString(board[0][1]) + Integer.toString(board[0][2]);
					
		    //finding the sum of the top left 3 digits number
		    sum += Integer.valueOf(three_digits);
		}
	    }
	} catch (Exception e){
	    System.out.println("Something wrong with reading file.");
	}
	System.out.println("The sum of all top left is " + sum);
	//the program printed out 24702, which matches with the answer on the Euler's website
    }
}
