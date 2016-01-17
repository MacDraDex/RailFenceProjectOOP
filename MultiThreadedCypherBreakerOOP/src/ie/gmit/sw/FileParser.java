package ie.gmit.sw;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.*;

public class FileParser {
	public  Map<String, Double> parse(String file) throws Exception{
		  Map<String, Double> temp = new ConcurrentHashMap<String, Double>();
			BufferedReader br= new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String next= null;
			
			// while to see if reader didnt find a null value 
			while((next=br.readLine())!=null){
				// ADD EACH LINE TO THE PARSE
	
				String [] splitString = next.split(" "); 
				temp.put(splitString[0], Double.parseDouble(splitString[1])); 
	
			}
			return temp;
	}
}
