package Java_Features_Module.Java_8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import Java_Features_Module.Java_11.C0_HttpClientApi;

/*
 * Introduced in Java 8
 * A new way to create threads asynchronously (default runs on ForkJoinPool)
 * 
 * basics
 * passing ThreadPoolExecutor to run on.
 * 
 * supply -> Supplier Fn
 * accept -> Consumer Fn
 */

public class C4_CompletableFuture {
	
	static public void basicCompletableFuture() throws InterruptedException, ExecutionException {
		//Uses ForkJoinPool.commonPool by default
		CompletableFuture<String> future = CompletableFuture.supplyAsync(
				()->{
					
					return "String 1";
				});
		
		//This is a blocking method
		String result1 = future.get(); // throws InterruptedException, ExecutionException
		System.out.println(result1);
		
		// thenAccept runs on same thread 
		// non-blocking
		future.thenAccept(result2->System.out.println(result2));
		
		// thenAcceptAsync runs asynchronously on different thread
		// non-blocking
		future.thenAcceptAsync(result3->System.out.println(result3));
	}
	
	static void chainingMethodsOnCompletableFuture() {
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(
				()->{
					return 12345;
				});
		// thenApply runs on same thread
		CompletableFuture<String> future2 = future.thenApply(
				result->" Hello");
		
		// thenApplyAsync runs on different thread asynchronously
		CompletableFuture<String> future3 = future2.thenApplyAsync(
				result->result + ", How are you?");
		
		System.out.println("before future.join() 1");
		future.thenAccept(result->System.out.println(result));
		future2.thenAccept(result->System.out.println(result));
		future3.thenAccept(result->System.out.println(result));
		
		System.out.println("before future.join() 2");
		future.join(); // blocking call that waits for future to complete
		System.out.println("future completed");
	}
	
	static void realTimeApplicationsWithCompletableFuture() {
		/*
		 * 1. multiple web service calls parallely and process the results
		 * 2. processing the data through multiple stages
		 * 3. asynchronous file process (reading or writing file)
		 * 4. background tasks in UI (may be with jsps, spring mvc)
		 * 5. db queries on multiple dbs parallely
		 * 
		 */
		multipleWebServiceCalls();
	}
	
	static void multipleWebServiceCalls() {
		// https://fakestoreapi.com/
		
		CompletableFuture<String> call1 = CompletableFuture.supplyAsync(
				()->{
					C0_HttpClientApi httpCaller = new C0_HttpClientApi();
					return httpCaller.getData("data1");
				});
		CompletableFuture<String> call2 = CompletableFuture.supplyAsync(
				()->{
					C0_HttpClientApi httpCaller = new C0_HttpClientApi();
					return httpCaller.getData("data2");
				});
		
		CompletableFuture<Void> combinedCall = CompletableFuture.allOf(call1,call2);
		combinedCall.thenRun(()->{
			//this is like join
			try {
				System.out.println(call1.get());
				System.out.println(call2.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		basicCompletableFuture();
//		chainingMethodsOnCompletableFuture();
		realTimeApplicationsWithCompletableFuture();
		//exception handling
		//completion
	}
}
