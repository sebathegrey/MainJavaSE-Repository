import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestHtml {

	public static void main(String[] args) throws IOException {
		
		StringBuilder html = new StringBuilder();
		BufferedReader input = null;
		try {
		URL url = new URL("http://www.google.pl/");
		input = new BufferedReader(new InputStreamReader(url.openStream()));

	    String htmlLine;
	    while ((htmlLine=input.readLine())!=null) {
	    	html.append(htmlLine);
	    }
	}
	catch (Exception e){}
	    input.close();
	

	Pattern exp = Pattern.compile("<meta content=\"video.other\" property=\"og:type\">");
	Matcher matcher = exp.matcher(html.toString());
	if(matcher.find())
	{
	    System.out.println("Generator: "+matcher.group(0));
	    
	}
	System.out.println(html.toString());
	}

}
