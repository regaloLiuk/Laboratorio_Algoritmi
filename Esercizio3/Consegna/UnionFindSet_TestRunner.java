import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class UnionFindSet_TestRunner {
    
    /**
     * @param args: the command line arguments
     */
    public static void main(String [] args){
        Result result = JUnitCore.runClasses(UnionFindSet_UnitTest.class);
        for (Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }  // for
        
        System.out.println(result.wasSuccessful());
    }  // main
} // class

