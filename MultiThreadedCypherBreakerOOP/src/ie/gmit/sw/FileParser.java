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
			
			while((next=br.readLine())!=null){
				// ADD EACH LINE TO THE PARSE
				String [] stuff = next.split(" ");
				
				//Making array of string
				String arrString = Arrays.toString(stuff);
				
				//Making string 4 letters
				String a = arrString.substring(1, 5); 
				
				//Everything what is left are numbers
				String b = arrString.substring(6, arrString.length()-1);
				
				//Converting String into Double value
				double d = Double.parseDouble(b); 
				
				//Putting values into the ConcurrentHashMap
				temp.put(a,d);
			}
			return temp;
	}
}
