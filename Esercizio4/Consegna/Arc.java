public class Arc<T,M>{

  private T t;
  private T m;
  private M etic;


/**
**  param start: nodo di partenza
**  param finish: nodo di arrivo
**  param etichetta: etichetta dell'arco
**  post: crea un oggetto di tipo Arco tra start e finish con etic come etichetta
**/
  public Arc(T start, T finish,M etic){
    this.t=start;
    this.m=finish;
    this.etic=etic;
  }

/**
**  param start: nodo di partenza
**  param finish: nodo di arrivo
**  post: crea un oggetto di tipo Arco tra start e finish
**/
  public Arc(T start,T finish){
    this.t=start;
    this.m=finish;
    this.etic=null;
  }

/**
**  post: ritorna il valore del nodo di partenza della'arco
**/
  public T getNode(){
    return this.t;
  }

/**
**  post: ritorna il valore del nodo di arrivo della'arco
**/
  public T getFinish(){
    return this.m;
  }

/**
**  post: ritorna il valore dell'etichetta dell'arco
**/
  public M getEtic(){
    return this.etic;
  }

/**
**  post: ritorna una rappresentazione dell'arco come stringa
**/
  public String toString(){
    if(etic!=null)
      return "("+t.toString()+","+m.toString()+","+etic.toString()+")";
    else
        return "("+t.toString()+","+m.toString()+")";
  }

}
