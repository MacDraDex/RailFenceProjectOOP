package ie.gmit.sw;

import java.io.*;
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
					//temp.put(stuff[0],stuff[1]);
			}
			return temp;
	}
}
