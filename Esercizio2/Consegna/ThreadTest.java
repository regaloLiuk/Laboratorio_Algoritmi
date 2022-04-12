import java.util.*;
import java.io.*;
import java.util.concurrent.CyclicBarrier;
import java.lang.Exception;

public class ThreadTest{

  public static void main(String [] args) throws IOException{

    CyclicBarrier barrier=new CyclicBarrier(2);

    SimpleRunner r=new SimpleRunner(0,barrier);
    Thread t = new Thread(r);

    SimpleRunner r1=new SimpleRunner(1,barrier);
    Thread t1 = new Thread(r1);



    t.start();
    t1.start();



  }



}
