import java.util.*;
import java.io.*;

public class Kruskal{


  public static void main(String[] args) throws IOException {

    ArcoComparator comp=new ArcoComparator();
 	  Graph<String,Float> g = new Graph<String,Float>(false,comp);

    FileReader d = new FileReader(args[0]);
    BufferedReader buff = new BufferedReader(d);

    StringTokenizer st = new StringTokenizer(buff.readLine(),",");
    Vector<Arc<String,Float>> ris=new Vector<Arc<String,Float>>();
    Set<String> node=new HashSet<String>();
    String part;
    String arr;
    String dist;
    String tmp;
    int i=0;

    while(buff!=null){
        part=st.nextElement().toString();
        arr=st.nextElement().toString();
        dist=st.nextElement().toString();

        if(!g.thereIsNode(part))
          g.addNode(part);

        if(!g.thereIsNode(arr))
          g.addNode(arr);

        g.addArch(part,arr,Float.parseFloat(dist));
        tmp=buff.readLine();
        if(tmp==null)
          break;
        st = new StringTokenizer(tmp,",");
    }
    ris=g.Kruskal();

    float weight=0;

    for(int j=0;j<ris.size();j++){
      weight=weight+((Float)ris.elementAt(j).getEtic());
      node.add(ris.elementAt(j).getNode());
      node.add(ris.elementAt(j).getFinish());
    }
    
    System.out.println("Grafo --> Numero archi : "+g.sizeArch()+" Numero nodi : "+g.sizeNode());
    System.out.println("Copertura--> Numero nodi : "+node.size()+" Numero di archi: "+ris.size()+" Peso: "+weight/1000+" km");

  }
}
