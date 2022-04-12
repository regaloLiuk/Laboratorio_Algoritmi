import java.util.*;
import java.lang.*;

public class StringComparator implements Comparator<String>{

@Override
  public int compare(String t,String l){
    return t.compareTo(l);
  }

}
