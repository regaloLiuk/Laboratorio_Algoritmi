#include "List.h"


/*
** Crea una lista vuota
*/
List* NewList(){
  List* tmp=(List*)malloc(sizeof(List));
  tmp->head=NULL;
  tmp->tail=NULL;
  tmp->size=0;
  return tmp;
}


/*
** @param l: lista che vogliamo distruggere
** post: distrugge la lista
*/
void destroy_list(List* l){
  free(l);
}

/*
** @param l: lista della quale vogliamo sapere se è vuota
** post: Ritorna 1 se la lista è vuota
*/
int is_empty(List* l){
  return l->size==0;
}

/*
** @param l: lista della quale vogliamo conoscere il numero di elementi
** post: Ritorna il numero di record
*/
int n_element(List* l){
  return l->size;
}


/*
** @param l: lista a cui vogliamo aggiungere un nuovo record
** @param node: nuovo record che vogliamo aggiungere alla lista
** post: aggiunge il nuovo record alla lista.
*/
void List_insert_on_tail(List* l,void* element){
  Record* node=(Record*)malloc(sizeof(Record));
  node->elem=element;
  if(l->head==NULL){
    l->head=node;
    l->tail=node;
    node->next=NULL;
    node->prev=NULL;
    l->size++;
  }
  else if(l->size==1){
    node->prev=l->tail;
    l->tail->next=node;
    node->next= NULL;
    l->tail=node;
    l->head->next=l->tail;
    l->size++;
  }
  else{
    l->tail->next=node;
    node->prev=l->tail;
    node->next=NULL;
    l->tail=node;
    l->size++;


  }

}



/*
**  @param l: puntatore alla testa della lista
**  @param element: elemento da aggiungere alla lista
**  @param index: posizione in cui inserire il nuovo record
**  post: Inserisce node nella posizione index della lista
*/
void List_insert_on_index(List* l,void* element,int index){
  Record* node=(Record*)malloc(sizeof(Record));
  node->elem=element;
  Record* tmp=l->head;
  if(l->head==NULL){
    l->head=node;
    l->tail=node;
    node->next=NULL;
    node->prev=NULL;
    l->size++;
  }
  else if(index==0){
    l->head->prev=node;
    node->next=l->head;
    l->head=node;
    l->size++;
  }
  else if(index==l->size){
    l->tail->next=node;
    node->prev=l->tail;
    node->next=NULL;
    l->tail=node;
    l->size++;
  }
  else{
    while(index>1){
      tmp=tmp->next;
      index--;
    }
      node->prev=tmp;
      node->next=tmp->next;
      tmp->next=node;
      l->size++;
  }
}

/*
** @param l: puntatore alla lista da cui vogliamo eliminare l'elemento in coda
** post: rimuove l'elemento in coda alla lista
**
*/
void List_remove_on_tail(List* l){
  if(l!=NULL && l->head!=NULL && l->tail!=NULL){
    if(l->size==1){
      free(l->head);
      l->head=NULL;
      l->tail=NULL;
    }
    else{
      free(l->tail->next);
      l->tail=l->tail->prev;
      l->tail->next=NULL;
    }
    l->size-=1;
  }
}

/*
** @param l: puntatore alla lista da cui vogliamo eliminare il record
** @param index: posizione dell'elemento che vogliamo eliminare
** post: elimina l'elemento di posizione index dalla lista l
*/

void List_remove_on_index(List* l,int index){
  if(l!=NULL && l->head!=NULL && l->tail!=NULL){
    if(index==0 && l->size==1){
      l->head=NULL;
      l->tail=NULL;
      l->size-=1;
    }
    else if(index==0){
        l->head=l->head->next;
        free((l->head->prev));
        l->head->prev=NULL;
        l->size-=1;
    }
    else if(index==l->size && l->size==1){
      l->head=NULL;
      l->tail=NULL;
      l->size-=1;
    }
    else if(index==l->size-1){
      Record* del=l->tail;
      l->tail=l->tail->prev;
      free(del);
      l->tail->next=NULL;
      l->size-=1;
    }
    else{
      Record* tmp = l->head;
      Record* del;
      while(index>1){
        tmp=tmp->next;
        index--;
      }
      del=tmp->next;
      tmp->next=tmp->next->next;
      tmp->next->prev=tmp;
      free(del);
      l->size-=1;
    }
  }
}

/*
** @param l: lista da cui vogliamo prelevare l'elemento
** @param index: posizione dell'elemento che ci interessa
** post: restituisce il puntatore all'elemento in posizone index
**
*/
void* Element_At_Index(List* l,int index){
  if(l!=NULL && l->head!=NULL && index>=0 && index<l->size){
    Record* tmp=l->head;
    for(int i=0;i<index;i++){
      tmp=tmp->next;
    }
    return tmp->elem;
  }
  else {
    fprintf(stderr, "List index (%d) out of bounds (0:%ld)\n", index, l->size-1);
    exit(EXIT_FAILURE);
  }
}



/*
** @param l: puntatore alla lista di cui vogliamo creare l'iteratore
** post: ritorna il puntatore ad un iteratore della lista l
*/

Iterator* newIterator(List* l){
	Iterator* i = (Iterator*)malloc(sizeof(Iterator));
	i->first = l->head;
	i->current = l->head;
	i->index = 0;
	return i;
}

/*
** @param l: puntatore alla lista di cui vogliamo conoscere la validità
** post: ritorna un intero maggiore di 0 se valido, -1 altrimenti
*/
int valid(Iterator *i){
	if(i->current!=NULL)
		return i->index;
	else
		return -1;
}

/*
** @param l: puntatore alla lista di cui vogliamo conoscere l'elemento
** post: ritorna il puntatore dell'elemento
*/
void* element(Iterator* i){
	if(valid(i)!=-1)
		return i->current->elem;
  else
    return NULL;
}

/*
** @param l: puntatore all'iteratore che vogliamo far scorrere in avanti
** post: l'iteratore si posiziona sull'elemento successivo
*/
void moveForward(Iterator *i){
	if(valid(i)!=-1){
		i->current = i->current->next;
		i->index +=1;
	}
}

/*
** @param l: puntatore all'iteratore che vogliamo far scorrere indietro
** post: l'iteratore si posiziona sull'elemento precedendente
*/
void moveBackward(Iterator *i){
	if(valid((Iterator*)i->current->prev)!=-1){
		i->current = i->current->prev;
		i->index -=1;
	}
}
/*
** @param l: puntatore all'iteratore che vogliamo resettare
** post: l'iteratore si posiziona sul primo elemento
*/
void reset(Iterator* i){
	i->current = i->first;
  i->index=0;
}

/*
** @param i: iteratore da distruggere
** post: l'iteratore viene ditrutto
*/

void destroy_Iterator(Iterator* i){
	free(i);
}
