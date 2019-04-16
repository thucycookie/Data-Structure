import java.util.list;

public class arrayListunique {
    
    //extends Comprable interface to make sure the passed in lists
    //are sortable.
    //if the lists are strings, it will be sort based on alphabetical order.
    public static <E extends Comprable<E>> boolean isPermutation(List<E> A, List<E> B){
	
	//first case: when both lists do not have the same size
        if(A.size() != B.size()){
            return false; 
        }

	//loop through A
        for(E item : A){
            int countA = 0;
            int countB = 0;

	    //loop through A to count the item
            for (E item_A : A){
                if(item_A.equals(item)){
                    countA++;
                }
            }

	    //loop through B to count the item
            for (E item_B : B){
                if(item_B.equals(item)){
                    countB++;
                }
            }

	    //if the count for one item in A
	    //is not the same for B then A and B
	    //are not permutations of each other
	    if(countA != countB){
		return false;

	    }

	    return true;
                
        }
        
    }
}
