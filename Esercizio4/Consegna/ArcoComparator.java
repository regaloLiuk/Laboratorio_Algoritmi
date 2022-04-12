import java.util.Comparator;

public class ArcoComparator implements Comparator<Float>{
  @Override
  public int compare(Float t,Float l){
    if(t-l==0)
      return 0;
    else if(t-l>0)
      return 1;
    else
      return -1;
  }

}
