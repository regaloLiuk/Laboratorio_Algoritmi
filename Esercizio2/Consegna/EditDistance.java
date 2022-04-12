import java.io.*;
import java.util.*;

public class EditDistance{

/*
**  Versione non dinamica
**  @param s1: stringa di cui vogliamo calcolare la distanza di edit rispetto a s2
**  @param s2: stringa di cui vogliamo calcolare la distanza di edit rispetto a s1
**  post: ritorna la distanza di edit tra s1 e s2
*/
  public static int editDistance(String s1,String s2){
    if(s1.length()==0)
      return s2.length();
    else if(s2.length()==0)
      return s1.length();
      else if(s1.charAt(0)==s2.charAt(0))
        return  Math.min(Math.min(editDistance(s1,s2.substring(1))+1,editDistance(s1.substring(1),s2.substring(1))), Math.min( editDistance(s1.substring(1),s2)+1 , editDistance(s1.substring(1),s2.substring(1))+1));
      else
        return Math.min(editDistance(s1,s2.substring(1))+1, Math.min( editDistance(s1.substring(1),s2)+1 , editDistance(s1.substring(1),s2.substring(1))+1));

  }

/*
**  Metodo wrapper per editDistance(String,String,HashMap<String,Integer>)
**  @param s1: stringa di cui vogliamo calcolare la distanza di edit rispetto a s2
**  @param s2: stringa di cui vogliamo calcolare la distanza di edit rispetto a s1
**  post: ritorna la distanza di edit tra s1 e s2
*/
  public static int editDistance_dyn(String s1,String s2){
    HashMap<String,Integer> defHashMap = new HashMap<String,Integer>(1500);
    return editDistance(s1.toLowerCase(),s2.toLowerCase(),defHashMap);
  }


/*
**  Versione dinamica
**  @param s1: stringa di cui vogliamo calcolare la distanza di edit rispetto a s2
**  @param s2: stringa di cui vogliamo calcolare la distanza di edit rispetto a s1
**  @param defHashMap: struttura di appoggio per memorizzare le distanze di edit intermedie
**  post: ritorna la distanza di edit tra s1 e s2
*/

  private static int editDistance(String s1,String s2,HashMap<String,Integer> defHashMap){
    if(s1.length()==0)
      return s2.length();

    else if(s2.length()==0)
      return s1.length();

    else if(s1.charAt(0)==s2.charAt(0)){
      if(defHashMap.containsKey(s1+" "+s2)){
        return defHashMap.get(s1+" "+s2);
      }
      else{
        int tmp=Math.min(Math.min(editDistance(s1,s2.substring(1),defHashMap)+1,editDistance(s1.substring(1),s2,defHashMap)+1),Math.min(editDistance(s1.substring(1),s2.substring(1),defHashMap)+1,editDistance(s1.substring(1),s2.substring(1),defHashMap)));
        defHashMap.put(s1+" "+s2,tmp);
        return tmp;
      }

    }else{
      if(defHashMap.containsKey(s1+" "+s2)){
        return defHashMap.get(s1+" "+s2);
      }
      else{
        int tmp=Math.min(editDistance(s1,s2.substring(1),defHashMap)+1,Math.min(editDistance(s1.substring(1),s2,defHashMap)+1,editDistance(s1.substring(1),s2.substring(1),defHashMap)+1));
        defHashMap.put(s1+" "+s2,tmp);
        return tmp;
      }
    }
  }





}
