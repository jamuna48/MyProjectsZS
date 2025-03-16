package EmojiCentiment;
   import java.util.Scanner;
   
public class CentimentFinder {
	
	Scanner scan=new Scanner(System.in);
	String result;
	public String getInput() {
		System.out.println("enter your content");
		  String out="";
		  String input=scan.nextLine();
	      
		 while (!(input.equalsIgnoreCase("exit"))) {
			 result+=(input+"\n");
			 
			   input=scan.nextLine();
			 
		 }
		 
		   return result;  
		}

	public void emojicounter() {
	    String whole="";
		int temp=0;
		boolean flag=false;
		for(int i=0;i<result.length();i++) {
			int ans=result.codePointAt(i);
		    char output=result.charAt(i);
		    int last=temp;
		    for(int j=0;j<result.length();j++){
		    	 char output2=result.charAt(i);
              if(!(output>=65 && output<=122)||(output == ' ')) {
            	 flag=true; 
              }
              else if(flag) {
            	  if(surrogatePair(output ,output2))
            		last=last+1;
            	   System.out.println(output+"appears"+last+"times");
            	   flag=false;
            	  
              }
              else {
            	  whole=whole+output;
            	  
              }
              
		    }
		 System.out.println(whole);
			 
			
		}

	}
	
	
	
	
	
  private boolean surrogatePair(char output, char output2) {
		// TODO Auto-generated method stub
		return false;
	}

public static void main(String [] args) {
	  CentimentFinder  find=new  CentimentFinder ();
	  find.getInput();
	  find.emojicounter();
	  }
}
