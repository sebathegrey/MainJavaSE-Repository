
public class Sollerstask2 {

	public static void main(String[] args) {
		System.out.println("Liczba Wolnych miejsc : "+solution(10,"2B 3A 2C 3F 5A 1G 5C 6 1 6D 6I 4A 4H 5E"));
		
	}
	
	 public static int solution(int N, String S) {
		    int [][] plan=new int[N][10];
		    
		    for(String str : S.split(" ")){ 
		      try{	
					plan[Integer.parseInt((String) str.subSequence(0, str.length()-1))-1][str.substring(str.length()-1).charAt(0)-65]=1;
				}
		      	catch (NumberFormatException e){
					//System.err.println("B³edny format");	
				}
		        catch (StringIndexOutOfBoundsException e){
					//System.err.println("B³êdny format");	
				}
		      catch (ArrayIndexOutOfBoundsException e){
              }
		    }
		    /*for(int i=0;i<plan.length;i++){
		    	for(int j=0;j<10;j++){
		    		System.out.print(plan[i][j]+" ");
		    	}
		    	System.out.println();st
		    }
		    System.out.println();*/
		   // System.out.println("Liczba Wolnych miejsc : "+countFamillyPl(N, plan));
		 return countFamillyPl(N, plan);
	    }
	 	public static int countFamillyPl(int N,int [][]plan){
		    
		    int freePlace=0;
		    for(int j=0;j<N;j++){	
		    	
		    	int temp=0;
		    	
		    	for(int i=3;i<7;i++){
		    		if(plan[j][i]==0){
		    			temp++;
		    			if(temp>=3){
		    				freePlace++;
		    				break;}
		    		}
		    		else temp=0;
		    	}
		    	
		    	for(int i=0;i<3;i++){
		    		if(plan[j][i]==1)
		    			break;
		    		if(i==2)
		    			freePlace++;
		    		}
		    	
		    	for(int i=7;i<10;i++){
		    		if(plan[j][i]==1)
		    			break;
		    		if(i==9)
		    			freePlace++;
	    		}
		    }
		    
	 		return freePlace;
	 	}
}
