import java.util.*;

public class Graph<T,M>{

  public HashMap<T,HashMap<T,Arc<T,M>>> node;
  public int nArch;
  private boolean direct;
  private Comparator<M> comparator;


/**
**  @param direct: definisce se il grafo è diretto
**  @param comparator: comparatore per confrontare archi
**  post:Crea un nuovo Grafo vuoto
**/
  public Graph(boolean direct,Comparator<M> comparator){
    this.node = new HashMap<T,HashMap<T,Arc<T,M>>>();
    this.nArch=0;
    this.direct=direct;
    this.comparator=comparator;
  }

  public Graph(Comparator<M> comparator){
    this.node = new HashMap<T,HashMap<T,Arc<T,M>>>();
    this.nArch=0;
    this.direct=true;
    this.comparator=comparator;
  }


/**
**  @param t: definisce il valore del nodo
**  post: Aggiunge un nuovo nodo senza archi
**/
  public T addNode(T t){
    node.put(t,new HashMap<T,Arc<T,M>>());
    return t;
  }

/**
**  @param start: nodo di partenza
**  @param finish: nodo di arrivo
**  @param etic: etichetta dell'arc
**  post: Aggiunge un nuovo arc con etichetta 'etic' tra i nodi 'start' e 'finish'. Se il grafo non è diretto viene
**        creato anche l'arc da 'finish' a 'start' con etichetta 'etic'
**/
  public Arc<T,M> addArch(T start,T finish,M etic){
      if(thereIsNode(start) && thereIsNode(finish) && !thereIsArch(start,finish)){
        if((!direct && node.get(start)!=node.get(finish)) || direct){
        Arc<T,M> tmp=new Arc<T,M>(start,finish,etic);
        node.get(start).put(finish,tmp);
        this.nArch++;
        if(!direct){
          node.get(finish).put(start,new Arc<T,M>(finish,start,etic));
          this.nArch++;
        }
        return tmp;
      }
    }
      return null;
  }

/**
**  post: Ritorna true se il grafo è diretto
**/
  public boolean isDirect(){
    return this.direct;
  }

/**
**  @param t: valore nodo di cui si vuole testare la presenza
**  post: Ritorna true se il grafo contiene il nodo t
**/
  public boolean thereIsNode(T t){
    if(node.get(t)!=null)
      return true;
    else
      return false;
  }

/**
**  @param start: valore del nodo di partenza
**  @param finish: valore del nodo di arrivo
**  post: Ritorna true se il grafo contiene l'arc start->finish
**/
  public boolean thereIsArch(T start,T finish){
    if(node.containsKey(start))
      return node.get(start).containsKey(finish);
    else
      return false;
  }

/**
**  @param t: valore del nodo da rimuovere
**  post: Rimuove il nodo con valore t dal grafo
**/
  public void removeNode(T t){
    if (node.containsKey(t)){
      Iterator<T> nodeI = this.nodeSet().iterator();
      while(nodeI.hasNext())
        this.removeArch(nodeI.next(),t);
      this.nArch=this.nArch-node.get(t).size();
      node.remove(t);
      }
  }

/**
**  @param start: valore del nodo di partenza
**  @param finish: valore del nodo di arrivo
**  post: Rimuove un arc start->finish dal grafo
**/
  public void removeArch(T start,T finish){
    if(node.containsKey(start) && node.get(start).containsKey(finish)){
      node.get(start).remove(finish);
      this.nArch--;
      if(!this.direct){
        node.get(finish).remove(start);
        this.nArch--;
      }
    }
  }

/**
**  post: Ritorna il numero di nodi nel grafo
**/
  public int sizeNode(){
    return node.size();
  }

/**
**  post: Ritorna il numero di archi nel grafo
**/
  public int sizeArch(){
    return this.nArch;
  }

/**
**  post: Ritorna un set di nodi del grafo
**/
  public Set<T> nodeSet(){
    return node.keySet();
  }

/**
**  @param start: nodo di partenza
**  @param finish: nodo di arrivo
**  post: Ritorna l'etichetta dell'arc tra start e finish
**/
  public M getEtic(T start,T finish){
    return node.get(start).get(finish).getEtic();
  }

/**
**  @param start: nodo di partenza
**  @param finish: nodo di arrivo
**  post: Ritorna l'arc tra start e finish
**/
  private Arc<T,M> getArc(T t,T m){
    return node.get(t).get(m);
  }

/**
**  post: Ritorna una stringa che rappresenta lo stato del grafo
**/
  public String toString() {
    StringBuffer out = new StringBuffer();
    T nodo;
    Arc<T,M> a;
    Iterator<Arc<T,M>> arcI;
    Iterator<T> nodoI = node.keySet().iterator();
    while (nodoI.hasNext()) {
      nodo = nodoI.next();
      arcI = (this.adjacentToArch(nodo)).iterator();
      out.append("Nodo " + nodo.toString() + ": ");
      while (arcI.hasNext()) {
        a = arcI.next();
        out.append(a.toString()+", ");
      }
      out.append("\n");
    }
    return out.toString();
  }


/**
**  @param t: nodo di cui vogliamo la collezione di archi
**  post: Ritorna una collezione di archi di un nodo
**/
  public Collection<Arc<T,M>> adjacentToArch(T t){
    return node.get(t).values();
  }

/**
**  post: Ritorna un set di tutti gli archi del grafo
**/
  public Set<Arc<T,M>> getEdgeSet() {
    Set<Arc<T,M>> setArchi = new HashSet<Arc<T,M>>();
    Iterator<T> hashSetI = node.keySet().iterator();
    while (hashSetI.hasNext())
      setArchi.addAll(this.adjacentToArch(hashSetI.next()));
    return setArchi;
  }


/**
**  @param t: nodo di cui vogliamo la collezione di nodi
**  post: Ritorna set con tutti i nodi adiacenti di un nodo
**/
  public Set<T> adjacentToNode(T t){
    return node.get(t).keySet();
  }

/**
**  @param tmp: collezione di archi di cui vogliamo un vector
**  post: Ritorna un vector con gli archi contenuti in tmp
**/
  public Vector<Arc<T,M>> toVector(Collection<Arc<T,M>> tmp){
    Vector<Arc<T,M>> ris=new Vector<Arc<T,M>>();
    Iterator<Arc<T,M>> hashSetI = this.getEdgeSet().iterator();
    while (hashSetI.hasNext())
      ris.add(hashSetI.next());
    return ris;
  }
/**
**  @param tmp: set di nodi di cui vogliamo un vector
**  post: Ritorna un vector con i nodi contenuti in tmp
**/
  public Vector<T> toVectorNode(Set<T> tmp){
    Vector<T> ris=new Vector<T>();
    Iterator<T> hashSetI = this.nodeSet().iterator();
    while (hashSetI.hasNext())
      ris.add(hashSetI.next());
    return ris;
  }

/**
**  Utilizzo dell'HeapSort
**  @param arr: vector contenente gli archi da ordinare
**  post: arr è ordinato in ordine crescente rispetto all'etichetta
**/
  public void sort(Vector<Arc<T,M>> arr){
    int n = arr.size();
    for (int i = n / 2 - 1; i >= 0; i--)
      heapify(arr, n, i);
    for (int i=n-1; i>=0; i--){
      Arc<T,M> temp = arr.elementAt(0);
      arr.setElementAt(arr.elementAt(i),0);
      arr.setElementAt(temp,i);
      heapify(arr, i, 0);
    }
  }
/**
** Metodo di appoggio per sort
**/
  void heapify(Vector<Arc<T,M>> arr, int n, int i)  {
    int largest = i;
    int l = 2*i + 1;
    int r = 2*i + 2;

    if (l < n && comparator.compare(arr.elementAt(l).getEtic(),arr.elementAt(largest).getEtic())>0)
      largest = l;

    if (r < n && comparator.compare(arr.elementAt(r).getEtic(),arr.elementAt(largest).getEtic())>0)
      largest = r;

    if (largest != i){
      Arc<T,M> swap = arr.elementAt(i);
      arr.setElementAt(arr.elementAt(largest),i);
      arr.setElementAt(swap,largest);
      heapify(arr, n, largest);
    }
  }


/**
**  post: ritorna un vector cotenente l'insieme di archi della copertura minimale di un grafo
**/
  public Vector<Arc<T,M>> Kruskal(){
    Vector<Arc<T,M>> vector=this.toVector(this.getEdgeSet());
    Vector<T> elem=this.toVectorNode(this.nodeSet());
    UnionFindSet<T> set =new UnionFindSet<T>();
    Vector<Arc<T,M>> result=new Vector<Arc<T,M>>();
    set.MakeSet_Vector(elem);
    Vector<Arc<T,M>> tmp=vector;
    this.sort(tmp);

    for(int i=0;i<tmp.size();i++){
      if(set.Find(tmp.get(i).getNode())!=set.Find(tmp.get(i).getFinish())){
        set.Union(set.Find(tmp.get(i).getNode()),set.Find(tmp.get(i).getFinish()));
        result.add(tmp.get(i));
      }
    }
    return result;
  }


}
