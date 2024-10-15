package Java_Features_Module.Java_5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class C1_ThreadPoolExecutorFramework {
	/*
	 * ThreadPool, Executor Interface, ExecutorService, ThreadPoolExecutor, 
	 * 		ScheduledThreadPoolExecutor
	 */
	
	public static void main(String[] args) {
		C1_ThreadPoolExecutorFramework tpef = new C1_ThreadPoolExecutorFramework();
//		tpef.fixedThreadPool();
//		tpef.singleThreadPool();
//		tpef.cachedThreadPool();
		tpef.scheduledThreadPool();
	}
	
	void threadPoolExecutorDemo() {
		this.fixedThreadPool();
	}
	
	void fixedThreadPool() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		executor.submit(new demoThread());
		executor.submit(new demoThread());
		executor.execute(new demoThread());
		executor.execute(new demoThread());
		
		executor.shutdown();
		
		//waiting 5 seconds and forcing shutdown
		try {
			if(!executor.awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS)) {
				//5 seconds is not enough so forcing shutdown
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			executor.shutdownNow();
//			e.printStackTrace();
		}
	}
	
	void cachedThreadPool() {
		/*
		 * No upper limit but reuses threads as much as possible
		 */
		ExecutorService executor = Executors.newCachedThreadPool();
		
		executor.submit(new demoThread());
		executor.submit(new demoThread());
		executor.execute(new demoThread());
		executor.execute(new demoThread());
		
		executor.shutdown();
	}
	
	void singleThreadPool() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		

		executor.submit(new demoThread());
		executor.submit(new demoThread());
		executor.execute(new demoThread());
		executor.execute(new demoThread());
		
		executor.shutdown();
	}
	
	void scheduledThreadPool() {
		/*
		 * Scheduling Tasks
		 * 
		 */
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		
		executor.schedule(()->{
			System.out.println("Executed after 2 seconds");
		}, 2, java.util.concurrent.TimeUnit.SECONDS);
		
		executor.scheduleAtFixedRate(()->{
			System.out.println("Executed after 3 seconds and then every 2 seconds");
		}, 3, 2, java.util.concurrent.TimeUnit.SECONDS);
		
		
		executor.scheduleWithFixedDelay(()->{
			System.out.println("Executed after 4 seconds and then after "
					+ "end of each execution there will be a delay of 2 seconds before "
					+ "start of next execution");
		}, 4, 2, java.util.concurrent.TimeUnit.SECONDS);
		
		executor.schedule(()->{
			executor.shutdown();
			System.out.println("Executor shutdown");
		}, 20, java.util.concurrent.TimeUnit.SECONDS);
		
	}
}

class demoThread implements Runnable {

	@Override
	public void run() {
		System.out.println("Starting "+Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ending "+Thread.currentThread().getName());
	}
	
}
