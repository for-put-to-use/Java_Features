package Java_Features_Module.Java_Basics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class C3_Thread_Safe_Collections {
	/*
	 * Collections in java.util.collections
	 * 		ConcurrentHashMap
	 * 		CopyOnWriteArrayList
	 * 			ThreadSafe, no ConcurrentModificationException
	 * 		CopyOnWriteArraySet
	 * 			ThreadSafe, no ConcurrentModificationException
	 * 		BlockingQueue (interface)
	 * 			ArrayBlockingQueue
	 * 			LinkedBlockingQueue
	 * 			PriorityBlockingQueue (binary heap DS CBT)
	 * 			DelayQueue
	 * 		ConcurrentLinkedQueue
	 * 		ConcurrentSkipListMap
	 * 		ConcurrentSkipListSet
	 * Synchronized Collections in java.util
	 * 		Collections.synchronizedList(List<T> list)
	 * 		Collections.synchronizedMap(Map<K,V> map)
	 * 		Collections.synchronizedSet(Set<T> set)
	 * 		Collections.synchronizedCollection(Collection<T> c)
	 * Other Thread Safe Collections
	 * 		Vector
	 * 		Stack
	 */

	public static void main(String[] args) throws InterruptedException {
		C3_Thread_Safe_Collections tsc = new C3_Thread_Safe_Collections();
//		tsc.demoConcurrentHashMapVsHashMap();
		tsc.demoCopyOnWriteArrayListNSet();
	}
	
	void blockingQueuesDemo() throws InterruptedException {
	/* 		BlockingQueue (interface)
	 * 			ArrayBlockingQueue - fixed size capacity
	 * 			LinkedBlockingQueue - 
	 * 			PriorityBlockingQueue
	 * 			DelayQueue
	 */
		
		BlockingQueue<Integer> bqa = new ArrayBlockingQueue<>(3);
		bqa.add(2);
		System.out.println(bqa.take());
		bqa.add(3);
		bqa.add(4);
		bqa.add(5);
		bqa.add(6); //thread blocked as queue capacity is max:3
		
		BlockingQueue<Integer> bql_noCap = new LinkedBlockingQueue<>();
		BlockingQueue<Integer> bql_cap = new LinkedBlockingQueue<>(4);
		
		BlockingQueue<Integer> bqp = new PriorityBlockingQueue<>();
		
		BlockingQueue<Delayed> bqdq = new DelayQueue<>();
		
		
		class DelayedElement implements Delayed {

			@Override
			public int compareTo(Delayed o) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public long getDelay(TimeUnit unit) {
				// TODO Auto-generated method stub
				return 0;
			}
			
		}
		
	}
	
	void demoCopyOnWriteArrayListNSet() throws InterruptedException {
		CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
		CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();
		Random rand = new Random();
		
		for(int i = 0; i < 10; ++i) {
			list.add(rand.nextInt());
			set.add(rand.nextInt());
		}
		
		System.out.println("list size "+list.size());
		System.out.println("set size "+ set.size());
		
		Thread[] threads = new Thread[2];
		
		threads[0] = new Thread(()->{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Iterator<Integer> it = list.iterator();
			while(it.hasNext()) {
				System.out.println(Thread.currentThread().getName()+" list "+it.next());
			}
			Iterator<Integer> it2 = set.iterator();
			while(it2.hasNext()) {
				System.out.println(Thread.currentThread().getName()+" set "+it2.next());
			}
		});
		
		threads[0].start();
		
		/*
		 * If this second thread threads[1] starts before first thread then we see 20 elements
		 *  if not then only 10 elements parsed in iterator of first thread threads[0]
		 */
		threads[1] = new Thread(()->{
			try {
				Thread.sleep(3000); // 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i = 0; i < 10; ++i) {
				list.add(rand.nextInt());
				set.add(rand.nextInt());
			}
			System.out.println("list AFTER size "+list.size());
			System.out.println("set AFTER size "+ set.size());
		});
		
		
		threads[1].start();
		
		Thread.sleep(2000);
		
		threads[0].join();
		threads[1].join();
	}
	
	void demoConcurrentHashMapVsHashMap() throws InterruptedException {
		demoConcurrentHashMap();
		demoHashMap();
	}
	
	void demoConcurrentHashMap() throws InterruptedException {
		Map<String, Integer> map = new ConcurrentHashMap<>();
		
		Thread[] threads = new Thread[10];
		
		for(int i=0; i < threads.length; ++i) {
			threads[i] = new Thread(()->{
				for(int j=0;j<100;++j)
					map.put(Thread.currentThread().getName()+"--"+j, j);
			});
		}
		
		for(Thread eachThread: threads) {
			eachThread.start();
		}
		
		for(Thread eachThread: threads) {
			eachThread.join();
		}
		
		System.out.println("concurrentHashMap size: "+map.size());
	}
	
	void demoHashMap() throws InterruptedException {
		Map<String, Integer> map = new HashMap<>();
		
		Thread[] threads = new Thread[10];
		
		for(int i=0; i < threads.length; ++i) {
			threads[i] = new Thread(()->{
				for(int j=0;j<100;++j)
					map.put(Thread.currentThread().getName()+"--"+j, j);
			});
		}
		
		for(Thread eachThread: threads) {
			eachThread.start();
		}
		
		for(Thread eachThread: threads) {
			eachThread.join();
		}
		
		System.out.println("hashMap size: "+map.size());
	}
}


