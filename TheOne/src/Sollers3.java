import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;


public class Sollers3 {

	public static void main(String[] args)  {
		System.out.println(solution("photo.jpg, Warsaw, 2013-09-05 14:08:15\njohn.png, London, 2015-06-20 15:13:22\nmyFriends.png, Warsaw, 2013-09-05 14:07:13\nEiffel.jpg, Paris, 2015-07-23 08:03:02\npisatower.jpg, Paris, 2015-07-22 23:59:59\nBOB.jpg, London, 2015-08-05 00:02:03\nnotredame.png, Paris, 2015-09-01 12:00:00\nme.jpg, Warsaw, 2013-09-06 15:40:22\na.png, Warsaw, 2016-02-13 13:33:50\nb.jpg, Warsaw, 2016-01-02 15:12:22\nc.jpg, Warsaw, 2016-01-02 14:34:30\nd.jpg, Warsaw, 2016-01-02 15:15:01\ne.png, Warsaw, 2016-01-02 09:49:09\nf.png, Warsaw, 2016-01-02 10:55:32\ng.jpg, Warsaw, 2016-02-29 22:13:11"));
	}
	
	    public static String solution(String S)  {

	    	LinkedHashSet<String[]> tabS= new LinkedHashSet<String[]>();
	    	StringBuilder ret= new StringBuilder();
	    	
	    	for ( String part : S.split("\n")){
	    		String [] temp=new String[3];
	    		int i=0;
	    		for ( String part2 : part.split(",")){
		    		temp[i]=part2;
		    		i++;		
	    		}
	    		tabS.add(temp);
	    	}
	    	
	    	Iterator<String[]> it = tabS.iterator();
	    	
	    	while (it.hasNext()){
	    		String[] treeStr=it.next();
	    		ret.append(treeStr[1].replaceAll("\\s+","")); 
	    		ret.append(count(tabS,treeStr[1],treeStr[2]));
	    		ret.append(treeStr[0].substring((treeStr[0].indexOf(".")),treeStr[0].length()));
	    		ret.append("\n");
	    	}
	    	return ret.toString();
	    }
	    public static String count(Set<String[]> myset, String miasto, String date){

	    	DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss", Locale.ENGLISH);
	    	int i=0;
	    	int sameCityCount=0;
	    	int thisPictureNum=0;
	    	try{
	    		Date dateIn = format.parse(date);
	    		for(String[] my: myset){
	    			if(miasto.equals(my[1])){
	    				sameCityCount++;
	    				if(dateIn.compareTo(format.parse(my[2]))<0)
	    					thisPictureNum++;
	    				}	
	    			}
	    		}
	    	catch(ParseException e){
	    		System.out.println("blond");
	    		}
	    	if(sameCityCount>8&&thisPictureNum<10)
	    		return "0"+(sameCityCount-thisPictureNum);
	    	else return ""+(sameCityCount-thisPictureNum);
	    }
	
}
