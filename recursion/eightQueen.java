
public class eightQueen {
	

    //isSafe function to check
    //upper left, left and lower left
    //Since we will moving from left to right, there is no reason
    //to check anything to the right of the position in which 
    //we are placing the queen
    public static boolean isSafe(int[][] board, int row, int col){

	    //check upper left
	    for(int i = 0 ; row - i >= 0 && col - i >=0; i++){
		if(board[row-i ][col-i] == 1)
		    return false;
	    }

	    //check lower left
	    for(int i = 0; row + i < board.length && col - i > 0; i++){
		if(board[row + i][col - i] == 1)
		    return false;
	    }

	    //check left	
	    for(int a = col - 1; a >= 0; a--){
		if(board[row][a] == 1)
		    return false;
	    }
	    return true;
	} 
	
    public static boolean solveBoard(int[][] board, int col){
		
		//base case when it is out of column
		//
		if(col >= board[0].length){
			return true;
		};

		//for each possible choices aka. possible row
		for(int row = 0; row < board.length; row++){

		    //check whether the placement of a queen is in
		    //direct threat of another queen
		    if(isSafe(board, row, col)){
			
			//marked the board once it is safe
			board[row][col] = 1;

			//return true if the next placement of the
			//queen is safe
			if(solveBoard(board, col + 1) == true){
			    return true;
			}
			
		    }
		    //delete the previous marked pos
		    //if it does not work
		    board[row][col] = 0;
		}
		

		//backtrack by returning false
		//to the previous function call
		return false;
    }
    
    public static void main(String[] args){
	
	//create an 8 by 8 chessboard
	int[][] board  = new int[8][8];

	for(int row = 0; row < 8; row++){
	    for(int col = 0; col < 8; col++){
		board[row][col] = 0;
	    }
	}

	//solve the board by starting at the first
	//column
	solveBoard(board,0);

	//print chessboard after solving it
	for(int row = 0; row < 8; row++){
	    for(int col = 0; col < 8; col++){
		System.out.print(board[row][col]);
	    }
	    System.out.println();
	}
    }
}
