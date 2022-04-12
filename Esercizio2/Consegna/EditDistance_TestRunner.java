//package Esercizio2;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class EditDistance_TestRunner {
    
    /**
     * @param args: the command line arguments
     */
    public static void main(String [] args){
        Result result = JUnitCore.runClasses(EditDistance_UnitTest.class);
        for (Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }  // for
        
        System.out.println(result.wasSuccessful());
    }  // main
} // class


