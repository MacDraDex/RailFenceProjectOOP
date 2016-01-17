package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.*;

//main class Runner
public class Runner {
	
	//Display Main Menu	
	private void displayMenu(Scanner input, Map<String, Double> hashMap) throws IOException {
		
		//Objects
		RailFence railFence = new RailFence();
		TextScorer textScorer = new TextScorer(hashMap);
		
		//Variables
		boolean quit = false;
		int choice = 0;
		String textToEncrypt = "";
		String encryption = "";
		
	//do-while to display user menu
		do {
			System.out.println("=======================================");
			System.out.println("=====RAILFENCE CYPHER======");
			System.out.println("=======================================");
			System.out.println("Select Option>");
			System.out.println("1. Encrypt user input");
			System.out.println("2. Exit");
			System.out.println("=======================================");
			
			System.out.print(">: ");
			
			
			choice = input.nextInt();
			
			input.nextLine();
			
			/*
			 * All text must be uppercase .toUpperCase();
			 * case1: takes input from user
			 * case2: Exit, if quit equals true then leave do-while
			 */
			switch(choice) {
				case 1:
					System.out.println("Enter text to encrypt: ");
					textToEncrypt = input.nextLine();
					textToEncrypt.toUpperCase();
					
					//Encryption + display
					encryption = encryptUserText(textToEncrypt, railFence);
					System.out.println("> " + encryption);	
					
					CypherBreaker cb = new CypherBreaker(encryption, textScorer);
					
					System.out.println("hit enter to continue...");
					input.nextLine();
					break;
				case 2:
					System.out.println("QUIT!");											
					input.nextLine();
					quit = true;
					break;
			}
		} while(!quit);
	}
	
	//Method to encrypt text
	private String encryptUserText(String encryptInput, RailFence railFence) {
		
		String encrypted = "";
		encrypted = railFence.encrypt(encryptInput, 5);
		
		return encrypted;
	}
	
	//main
	public static void main(String[] args) throws IOException {
	
		
		//String variables
		String userInput = "";
		String encryptedText = "";
		
		// Objects for user input and to read from the hashmap
		FileParser fp = new FileParser();		
		Scanner userText = new Scanner(System.in);		
		
		//Concurrent HasMap
		Map<String, Double> grams4Map = new ConcurrentHashMap<String, Double>();
		//reading data from the file to the hashmap
		try {
			grams4Map = fp.parse("4grams.txt");		
		}
		// if file not found then print this message to the screen
		catch(FileNotFoundException e) {
			System.err.println("4grams.txt not found");	
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//New Object of the class runner to run showMenu method
		Runner run = new Runner();
		run.displayMenu(userText, grams4Map);
	}// end of main
}//end of runner
