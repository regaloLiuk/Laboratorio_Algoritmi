public class Node<T>{


  public T t;
  public Node<T> next;
  public Node<T> father;
  public int rank;



  public Node(T t){
    this.t=t;
    this.next=null;
    this.father=this;
    this.rank=0;
  }


}
