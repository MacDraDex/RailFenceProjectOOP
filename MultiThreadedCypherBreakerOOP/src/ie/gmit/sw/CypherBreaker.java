package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;

public class CypherBreaker {
	private static final int MAX_QUEUE_SIZE = 100;
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	// counter for threads
	public static volatile int count = 2;
	
	
	private TextScorer textScorer;
	private Resultable result = new Result("", 0, -300.00);
	
	
	private static Object poisonPill = new Object();
	
	public CypherBreaker(String cypherText, TextScorer textScorer){
		queue = new ArrayBlockingQueue<Resultable>(MAX_QUEUE_SIZE);
		this.cypherText = cypherText;
		this.textScorer = textScorer;
		init();
	}
	
	public void init(){
		for(int i = 2; i < cypherText.length()/2;i++){
			new Thread(new Decryptor(queue, cypherText, i, textScorer)).start();
		}
		
		new Thread(new Runnable() {
			public void run(){
				while(!queue.isEmpty()){
					try {
						Resultable r = queue.take();
						//do something here
						
						if(count == cypherText.length()/2) {
							r = new PoisonPill("", 0, -300.00);
							queue.put(r);
							System.out.println("Added poison");
						}
						
						//IF Resultable is Poisoned return an Object
						if(r instanceof PoisonPill) {
							return;
						}
						
						//Count and increment threads
						count();
						
						//Sleep thread
						Thread.sleep(500);
					} catch (InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	//Count Threads
	public static void count(){ 
		synchronized(poisonPill){
			count++;
		}	
	}
}