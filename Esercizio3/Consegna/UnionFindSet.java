import java.util.*;

public class UnionFindSet<T>{

  private int size;
  private Node<T> head;
  private Node<T> tail;
  private HashMap<T,Node<T>> defHashMap = new HashMap<T,Node<T>>(100);

/*
**  post: crea un nuovo oggetto di tipo Union_Find_Set
*/

  public UnionFindSet(){
    this.head=this.tail=null;
    this.size=0;
  }

/*
**  @param t: elemento da aggiungere al nuovo set
**  post: crea un nuovo set con t come elemento
*/
  private void addSet(Node<T> t){
    if(this.head==null){
      this.head=t;
      this.head.next=null;
      this.size++;
    }
    else if(this.tail==null){
      this.tail=t;
      this.head.next=tail;
      this.tail.next=null;
      this.size++;
    }
    else{
      this.tail.next=t;
      this.tail=this.tail.next;
      this.tail.next=null;
      this.size++;
    }
  }

/*
** @param element: array contenente gli elementi da aggiungere dentro i set
** post: popola la struttura con tanti set quanti sono gli elementi di t.
**       Ritorna un ArrayList contenente i riferimenti agli elementi della UnionFind
*/
  public ArrayList<Node<T>> MakeSet_ArrList(T[] element){
    ArrayList<Node<T>> tmp=new ArrayList<Node<T>>(element.length);
    for(int i=0;i<element.length;i++){
      tmp.add(new Node<T>(element[i]));
      this.addSet(tmp.get(i));
      defHashMap.put(element[i],tmp.get(i));
    }
    return tmp;
  }


/*
** @param element: array contenente gli elementi da aggiungere dentro i set
** post: popola la struttura con tanti set quanti sono gli elementi di t.
**       Ritorna un Vector contenente i riferimenti agli elementi della UnionFind
*/
  public Vector<Node<T>> MakeSet_Vector(T[] element){
    Vector<Node<T>> tmp=new Vector<Node<T>>(element.length);
    for(int i=0;i<element.length;i++){
      tmp.add(new Node<T>(element[i]));
      this.addSet(tmp.get(i));
      defHashMap.put(element[i],tmp.get(i));
    }
    return tmp;
  }


/*
** @param element: Vector contenente gli elementi da aggiungere dentro i set
** post: popola la struttura con tanti set quanti sono gli elementi di t.
**       Ritorna un Vector contenente i riferimenti agli elementi della UnionFind
*/
  public Vector<Node<T>> MakeSet_Vector(Vector<T> element){
    Vector<Node<T>> tmp=new Vector<Node<T>>(element.size());
    for(int i=0;i<element.size();i++){
      tmp.add(new Node<T>(element.get(i)));
      this.addSet(tmp.get(i));
      defHashMap.put(element.get(i),tmp.get(i));
    }
    return tmp;
  }

/*
** @param t: nodo contenente l'elemento di cui vogliamo conoscere il rappresentante
** post: ritorna il nodo contenente il rappresentante di t
*/
  public Node<T> Find(Node<T> t){
	if(t!=null){
		if(t==t.father)
		  return t;
		else
		  return t.father=this.Find(t.father);
	}
	else
		return null;
  }

/*
** @param t: l'elemento di cui vogliamo conoscere il rappresentante
** post: ritorna il nodo contenente il rappresentante di t
*/
  public Node<T> Find(T t){
    Node<T> tmp=defHashMap.get(t);
	if(tmp!=null){
		if(tmp==tmp.father)
		  return tmp;
		else
		return tmp.father=this.Find(tmp.father);
	}
	else
		return null;
  }

/*
** @param t: nodo contenente l'elemento da voler unire con il nodo m
** @param m: nodo contenente l'elemento da voler unire con il nodo t
** post: unisce i set di t e m in uno unico
*/
  public void Union(Node<T> t,Node<T> m){
      this.Link(this.Find(t),this.Find(m));
  }

  /*
** @param t: l'elemento da voler unire con l'elemento m
** @param m: l'elemento da voler unire con l'elemento t
** post: unisce i set di t e m in uno unico
*/
  public void Union(T t,T m){
      this.Link(this.Find(defHashMap.get(t)),this.Find(defHashMap.get(m)));
  }

/*
** @param t: nodo contenente l'elemento da voler unire con il nodo m
** @param m: nodo contenente l'elemento da voler unire con il nodo t
** Metodo di appoggio per Union()
*/
  private void Link(Node<T> t,Node<T> m){
    if(t.rank>m.rank)
      m.father=t;
    else{
        t.father=m;
        if(t.rank==m.rank)
          m.rank++;
    }
  }


/*
** post: restituisce una rappresentazione della struttura come stringa
*/
  public String toString(){
    String ris="\n";
    Node<T> tmp=this.head;
    for(int i=0;i<this.size;i++){
      ris=ris+tmp.t+" rank: "+tmp.rank+" father: "+tmp.father.t+"\n";
      tmp=tmp.next;
    }
    return ris;
  }

}
