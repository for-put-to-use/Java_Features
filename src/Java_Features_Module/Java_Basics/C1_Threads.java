package Java_Features_Module.Java_Basics;

public class C1_Threads {

	public static void main(String[] args) throws InterruptedException {
		threadLifeCycleDemo();
	}
	
	/*
	 * Thread Life Cycle
	 * 
	 * States:
	 * 		New --> ( Runnable <--> Running ) --> Terminated
	 * 							|
	 * 							\> Blocked
	 * 							|
	 * 							\> Waiting
	 * 							|
	 * 							\> Timed Waiting
	 */
	public static void threadLifeCycleDemo() throws InterruptedException {
		
		Thread thread = new Thread(new ThreadStates());
		System.out.println("State: New");
		
		thread.start();
		System.out.println("State: "+thread.getState()+" (Runnable)");
		
		System.out.println("Thread name: "+Thread.currentThread().getName());
		
		Thread.sleep(10000); //pausing main to observe the above thread
		
		synchronized(thread) {
//			thread.sleep(5000);
//			System.out.println("State: Timed Waiting");
			System.out.println(">State: "+ thread.getState());
		}
		System.out.println("State: "+ thread.getState()+" (Terminated)");
		
		
	}

}

class ThreadStates implements Runnable {

	@Override
	public void run() {
		System.out.println("State: Running");
		
		synchronized(this) {
			try {
				Thread.sleep(5000);
				System.out.println("Thread name: "+Thread.currentThread().getName());
				System.out.println("State: "+Thread.currentThread().getState()+" (Timed Waiting)");
				
				this.wait(3000);
				System.out.println("Thread name: "+Thread.currentThread().getName());
				System.out.println("State: "+Thread.currentThread().getState()+" (Waiting)");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
}
