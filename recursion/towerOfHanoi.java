import java.util.Stack;
import java.util.Arrays; 
//base case is when there is nothing left to move

//start,end,aux are stacks
//move(n,start,end,aux) -> aux is for storage
//if n > 0 (if there is disk to move)
//move(n - 1, start, aux, end)
//end.push(start.pop)
//print out the move
//move(n-1,aux,end,start)

public class towerOfHanoi {
	
	public static void move(int disks, Stack start, Stack end, Stack aux){
		if(disks > 0){
			move(disks - 1, start, aux, end);
			end.push(start.pop());
			System.out.println(Arrays.toString(start.toArray()));
			System.out.println(Arrays.toString(aux.toArray()));
			System.out.println(Arrays.toString(end.toArray()));
			System.out.println();
			move(disks - 1, aux, end, start);
		}
	}
	
	public static void main(String[] args){
		Stack<Integer> start = new Stack<Integer>();
		start.push(3);
		start.push(2);
		start.push(1);
		Stack<Integer> aux = new Stack<Integer>();
		Stack<Integer> end = new Stack<Integer>();
		move(3, start, end, aux);
	}

}
