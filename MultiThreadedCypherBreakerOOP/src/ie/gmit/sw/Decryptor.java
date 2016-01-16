package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;

public class Decryptor implements Runnable {
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private int key;
	
	public Decryptor(BlockingQueue<Resultable> queue, String cypherText, int key) {
		super();
		this.queue = queue;
		this.cypherText = cypherText;
		this.key = key;
	}

	public void run(){
		RailFence rf = new RailFence();
		String plainText = rf.decrypt(cypherText, key);
		//get the score 
		
		Resultable r = null;//Create a result
		try {
			queue.put(r);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}
