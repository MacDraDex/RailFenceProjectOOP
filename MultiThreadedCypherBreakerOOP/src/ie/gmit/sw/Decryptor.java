package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;

public class Decryptor implements Runnable {
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private int key;
	private TextScorer textScorer;
	
	public Decryptor(BlockingQueue<Resultable> queue, String cypherText, int key, TextScorer textScorer) {
		super();
		this.queue = queue;
		this.cypherText = cypherText;
		this.key = key;
		this.textScorer = textScorer;
	}

	public void run(){
		RailFence rf = new RailFence();
		String plainText = rf.decrypt(cypherText, key);
		
		//get the score 
		double doubleResult = textScorer.getScore(plainText);
		
		//Create a result
		Resultable r = new Result(plainText, key, doubleResult);
		
		try {
			queue.put(r);
			System.out.println(doubleResult);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}