import java.util.*;
import java.io.*;
import java.util.concurrent.CyclicBarrier;
import java.lang.Exception;
import java.util.concurrent.BrokenBarrierException;

class SimpleRunner implements Runnable {
  int start;


  private final CyclicBarrier barrier;


  public SimpleRunner(int start,CyclicBarrier barrier){
      this.start=start;
      this.barrier=barrier;

    }

    public void run(){
      try{


    boolean stop=false;


    FileReader f= new FileReader("dictionary.txt");
    BufferedReader buff=new BufferedReader(f);

    FileReader f1=new FileReader("correctme.txt");
    BufferedReader buff1=new BufferedReader(f1);

    StringTokenizer st = new StringTokenizer(buff1.readLine(),".,;: ");

    String myString="";
    String fileString="";
    int edit=0;
    int counter=0;


    long startTime=System.currentTimeMillis();

    while(st.hasMoreElements()){

          myString=(String)st.nextElement();

          if(start==0)
            System.out.println(myString);


          while(!stop){
            fileString=buff.readLine();
            if(fileString==null)
              stop=true;
            else if(Math.abs(myString.length()-fileString.length())<=1 && counter%2==start )
              if((edit=EditDistance.editDistance_dyn(myString,fileString))<=1)
               System.out.println("--"+fileString+" : "+edit);

            counter++;
        }

          stop=false;
          counter=0;
          buff=new BufferedReader(new FileReader("dictionary.txt"));
          try {
               barrier.await();
             }  catch (BrokenBarrierException ex){
                  throw new RuntimeException(ex);
             }  catch (InterruptedException ex){
                  throw new RuntimeException(ex);
             }


    }
        if(start==0)
          System.out.println(" Execution time: "+(System.currentTimeMillis()-startTime)/1000+" sec");


          } catch (IOException ex) {
              throw new RuntimeException(ex);
            }

      }

}
