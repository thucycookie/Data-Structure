import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JPanel;

public class MazeGridPanel extends JPanel{
    private int rows;
    private int cols;
    private Cell[][] maze;

    public void genDFSMaze() {
	Stack<Cell> stack  = new Stack<Cell>();
	Cell start = maze[0][0];

	//makes the initial cell the current cell and 
	//marks it as visited
	//blue is how we are going to mark the visited cell
	//
	Cell current = start;
	current.setBackground(Color.BLUE);

	//calculates the number of cells
	int numberOfCells = rows * cols;
	int count = 0;
	
	//while there are unvisited cells
	//
	while(count < numberOfCells){
	    //current = stack.peek();
	    //if cell does not have visisted neighbors
	    //south -> east -> west -> north
	    //
	    System.out.println(current.row + 1);
		
	    //this is to prevent Index out of Bounds when calling
	    //visited function
	    /*if(current.row == maze[0].length){
	      current = current[current.row - 1][]
	      }else if(current.col = maze.length){
	      
	      }*/

	    if(current.row + 1 < maze[0].length && !visited(current.row + 1,current.col)){

		//pushes current cell to stack
		stack.push(current);

		//remove the wall
		current.southWall = false;

		//make chosen the current cell
		current = maze[current.row + 1][current.col];

		//remove the wall
		current.northWall = false;

		//marks it as visited
		current.setBackground(Color.BLUE);
		count++;
		current.repaint();

	    }else if(current.col + 1 < maze.length && !visited(current.row,current.col + 1)){
		stack.push(current);
		current.eastWall = false;
		current = maze[current.row][current.col + 1];
		current.westWall = false;
		current.setBackground(Color.BLUE);
		count++;
		current.repaint();

	    }else if(current.col - 1 < maze.length && !visited(current.row,current.col - 1)){
		stack.push(current);
		current.westWall = false;
		current = maze[current.row][current.col - 1];
		current.eastWall = false;
		current.setBackground(Color.BLUE);
		count++;
		current.repaint();

	    }else if(current.row - 1 < maze[0].length && !visited(current.row - 1,current.col)){

		stack.push(current);
		current.northWall = false;
		current = maze[current.row - 1][current.col];
		current.southWall = false;
		current.setBackground(Color.BLUE);
		count++;
		current.repaint();

	    }
	    //else if stack is not empty this is where backtracks happen
	    else if(!stack.isEmpty()){
		current = stack.pop();
		count--;
	    }
	}
	System.out.println(count);
	this.repaint();
    }

    public void solveMaze() {

	//create a stack to keep track of all the cells
	//that have been visited and backtrack if we
	//hit a dead end
	Stack<Cell> stack  = new Stack<Cell>();

	Cell start = maze[0][0];

	start.setBackground(Color.GREEN);

	Cell finish = maze[rows-1][cols-1];    

	finish.setBackground(Color.RED);

	stack.push(start);

	//while if the most currently visited cell is not the finished cell
	//and if the stack is not empty 
	while(stack.peek() != finish && !stack.isEmpty()){

	    Cell current = stack.peek();

	    //if we can go south and have not visited there yet
	    //
	    if(!current.southWall && (!visited(current.row + 1,current.col))){

		stack.push(maze[current.row + 1][current.col]);

		//colors green to mark the path
		maze[current.row + 1][current.col].setBackground(Color.GREEN);

		System.out.println("South");

	    }else if(!current.eastWall && (!visited(current.row,current.col +1))){
		
		stack.push(maze[current.row][current.col + 1]);

		maze[current.row][current.col +1].setBackground(Color.GREEN);

		System.out.println("East");

	    }else if(!current.westWall && (!visited(current.row,current.col -1))){

		stack.push(maze[current.row][current.col - 1]);

		maze[current.row][current.col - 1].setBackground(Color.GREEN);

		System.out.println("West");

	    }else if(!current.northWall && (!visited(current.row -1,current.col))){

		stack.push(maze[current.row - 1][current.col]);

		maze[current.row - 1][current.col].setBackground(Color.GREEN);

		System.out.println("North");

	    }else{
		//dead end
		//set the current cell as grey to mark as visited
		//pop the stack to backtracks
		current.setBackground(Color.GRAY);

		//this is how we backtracked to the last position
		stack.pop();

		System.out.println("Popped");
	    }
	}
    }

    //this is how to know whether we have visited a cell or not
    public boolean visited(int row, int col) {
	
	Cell c = maze[row][col];

	Color status = c.getBackground();

	//added blue because in DFSGenMaze, visited cell when 
	//creating is marked as BLUE
	//
	
	if(status.equals(Color.WHITE)  || status.equals(Color.RED) || status.equals(Color.BLUE)) {
	    return false;
	}

	return true;

    }


    public void genNWMaze() {

	for(int row = 0; row  < rows; row++) {

	    for(int col = 0; col < cols; col++) {

		if(row == 0 && col ==0) {
		    continue;
		}
		else if(row ==0) {
		    maze[row][col].westWall = false;
		    maze[row][col-1].eastWall = false;
		} else if(col == 0) {
		    maze[row][col].northWall = false;
		    maze[row-1][col].southWall = false;
		}else {
		    boolean north = Math.random()  < 0.5;
		    if(north ) {
			maze[row][col].northWall = false;
			maze[row-1][col].southWall = false;
		    } else {  // remove west
			maze[row][col].westWall = false;
			maze[row][col-1].eastWall = false;
		    }
		    maze[row][col].repaint();
		}
	    }
	}
	this.repaint();
    }
    
    public MazeGridPanel(int rows, int cols) {
	this.setPreferredSize(new Dimension(800,800));
	this.rows = rows;
	this.cols = cols;
	this.setLayout(new GridLayout(rows,cols));
	this.maze =  new Cell[rows][cols];

	for(int row = 0 ; row  < rows ; row++) {
	    for(int col = 0; col < cols; col++) {
		maze[row][col] = new Cell(row,col);
		this.add(maze[row][col]);
	    }
	}
	//this.genNWMaze();
	this.genDFSMaze();
	//this.solveMaze();
    }
}
