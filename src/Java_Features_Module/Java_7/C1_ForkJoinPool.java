package Java_Features_Module.Java_7;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/*
 * What is ForkJoinPool ?
 * 	It is the main part of Fork/Join framework
 * 
 */
public class C1_ForkJoinPool {

}

class SquareIntTask extends RecursiveAction {
	
	List<Integer> listOfInts;
	final int THRESHOLD = 3;
	
	public SquareIntTask(List<Integer> listOfInts) {
		this.listOfInts = listOfInts;
	}

	@Override
	protected void compute() {
		if(listOfInts.size() > THRESHOLD) {
			//
		}
		
	}
	
}
