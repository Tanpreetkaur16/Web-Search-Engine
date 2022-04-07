import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Linktotext {
	
	public static void htmlToTextParser(HashMap<String,String> UT) throws IOException {
		// differentiating keys and value from filenames and urls from given hashmaps storing it in array of string
		Object[] key = UT.keySet().toArray();
		String[] URL = Arrays.stream(key).toArray(String[]::new);

		Object[] fname = UT.values().toArray();
		String[] fileName = Arrays.stream(fname).toArray(String[]::new);

		//parsing through the loop and storing the filenames as text files
		for(int s = 0; s<URL.length ; s++) {
	            // jsoup always connect to the url and returns to the html file
		    String document = Jsoup.connect((String) URL[s]).get().html();
		    Document doc = Jsoup.parse(document);
		    String HTMLCode = doc.toString();
		    
		    // This code will write HTML code to .htm file
		    BufferedWriter writerTxtToFile = new BufferedWriter(new FileWriter("/Users/manpreetsingh/Downloads/searchENGranking/Files/"+fileName[s]+".htm"));
		    writerTxtToFile.write(HTMLCode);                                                                                 // Write the text to a file
		    writerTxtToFile.close();
		   
		    HTMLCode = "";
		    
		    for(Element element: doc.getAllElements()) {
	            // Get text from each dom element 
		    	for (Node n : element.childNodes()) {
		            if (n instanceof TextNode && !((TextNode) n).isBlank()) {
		            	HTMLCode += ((TextNode) n).text();
		            } 
		        }
	        } 
			//writing to the text file
		    writerTxtToFile = new BufferedWriter(new FileWriter("/Users/manpreetsingh/Downloads/searchENGranking/Files/Text/"+fileName[s]+".txt"));
		    writerTxtToFile.write(HTMLCode);                                                                                 // Write the text to a file
		    writerTxtToFile.close();

		}
		
	}

	
}
