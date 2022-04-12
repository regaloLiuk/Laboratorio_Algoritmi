import java.io.*;
import java.util.*;

public class Corrector{


  public static void main(String[] args) throws IOException {


  	String fileString = new String("");
  	String fileTest = new String("");

  	int edit=0;

  	FileReader d = new FileReader(args[0]);
    BufferedReader buff1 = new BufferedReader(d);

    long start=System.currentTimeMillis();

  	StringTokenizer st = new StringTokenizer(buff1.readLine(),".,;: ");

  	while (st.hasMoreElements()) {
  		String token = st.nextElement().toString();
  		System.out.println("token = " + token);

  		FileReader f = new FileReader("dictionary.txt");
  		BufferedReader buff = new BufferedReader(f);
  		fileString=buff.readLine();

  		System.out.println("suggerite:");

  		while(true){

  			fileString=buff.readLine();

  			if(fileString==null)
  				break;

  			if (Math.abs(token.length()-fileString.length())<=1){
  				if((edit=EditDistance.editDistance_dyn(token,fileString))<=1){
  					System.out.println("--"+fileString+" : "+edit);
  				}
  			}
  		}
  		System.out.println("");

  	}

  	System.out.println(" Execution time: "+(System.currentTimeMillis()-start)/1000+" sec");


  }

}
