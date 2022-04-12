#include "List.h"


/*
** Crea una lista vuota e ne restituisce il puntatore
*/
List* NewList() {
  List* result = (List*) malloc(sizeof(List));
  result->list = (void**) malloc(sizeof(void*)*10);
  result->size = 0;
  result->capacity = 10;
  return result;
}

/*
** @param list: lista che vogliamo distruggere
** post: distrugge la lista
*/

void destroy_list(List* l) {
  free(l);
}

/*
** @param list: lista della quale vogliamo conoscere il numero di elementi
** post: Ritorna il numero di record nella lista
*/
int n_element(List* l) {
  return l->size;
}

/*
** @param list: lista della quale vogliamo sapere se è vuota
** post: Ritorna 1 se la lista è vuota
*/
int is_empty(List* l) {
  return l->size == 0;
}

/*
** @param list: lista da cui vogliamo prelevare l'elemento
** @param position: posizione dell'elemento che ci interessa
** post: restituisce il puntatore all'elemento in posizone position
**
*/


void*Element_At_Index(List* l, int index) {
  if(index >= l->size || l==NULL || l->list==NULL || l->list[0]==NULL) {
    fprintf(stderr, "list index (%d) out of bounds (0:%ld)\n", index, l->size);
    exit(EXIT_FAILURE);
  }
 return l->list[index];
}



/*
** @param list: lista a cui vogliamo aggiungere un nuovo record
** @param element: nuovo record che vogliamo aggiungere alla lista
** post: aggiunge il nuovo record alla lista.
*/
void List_insert_on_tail(List* l, void* element) {
  if( l->capacity == l->size ){
    l->capacity *= 2;
    l->list = realloc(l->list, sizeof(void*) * l->capacity);
  }
  l->list[l->size]=element;
  l->size+=1;
}

/*
** @param list: puntatore alla lista da cui vogliamo eliminare l'elemento in coda
** post: rimuove l'elemento in coda alla lista
**
*/
void List_remove_on_tail(List* l){
  if(l->size!=0){
    l->list[l->size]=NULL;
    l->size-=1;
  }
}

/*
**  @param list: puntatore alla testa della lista
**  @param element: elemento da aggiungere alla lista
**  @param index: posizione in cui inserire il nuovo record
**  post: Inserisce node nella posizione index della lista
*/
void List_insert_on_index(List* l, void* element,int index){
  if( l->capacity == l->size ){
    l->capacity *= 2;
    l->list = realloc(l->list, sizeof(void*) * l->capacity);
  }
  if(index<=l->size){
      for(int i=l->size-1;i>index-1;i--)
        l->list[i+1]=l->list[i];
      l->list[index]=element;
    l->size+=1;
  }
}


/*
** @param list: puntatore alla lista da cui vogliamo eliminare il record
** @param index: posizione dell'elemento che vogliamo eliminare
** post: elimina l'elemento di posizione index dalla lista list
*/
void List_remove_on_index(List* l,int index){
  for(int i=index;i<l->size-1;i++)
    l->list[i]=l->list[i+1];
  l->size-=1;
  l->list[l->size]=NULL;

}



/*
** @param l: puntatore alla lista di cui vogliamo creare l'iteratore
** post: ritorna il puntatore ad un iteratore della lista l
*/
  Iterator* newIterator(List* a){
  	Iterator* i = (Iterator*)malloc(sizeof(Iterator));
  	i->first = a;
  	i->current = i->first->list[0];
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
** @param l: puntatore all'iteratore di cui vogliamo conoscere l'elemento
** post: ritorna il puntatore dell'elemento
*/
  void* element(Iterator* i){
  	if(valid(i)!=-1)
  		return i->current;
    else
      return NULL;
  }

/*
** @param l: puntatore all'iteratore che vogliamo far scorrere in avanti
** post: l'iteratore si posiziona sull'elemento successivo
*/
  void moveForward(Iterator *i){
  	if(valid(i)!=-1){
  		i->current = i->first->list[i->index+1];
  		i->index +=1;
  	}
  }

/*
** @param l: puntatore all'iteratore che vogliamo far scorrere indietro
** post: l'iteratore si posiziona sull'elemento precedendente
*/
  void moveBackward(Iterator *i){
  		if(valid(i)!=-1){
  			i->current = i->first->list[i->index-1];
  		i->index-=1;
    }
  }

/*
** @param l: puntatore all'iteratore che vogliamo resettare
** post: l'iteratore si posiziona sul primo elemento
*/
  void reset(Iterator* i){
  	i->current = i->first->list[0];
    i->index=0;
  }

  /*
  ** @param i: iteratore da distruggere
  ** post: l'iteratore viene ditrutto
  */
  void destroy_Iterator(Iterator* i){
  	free(i);
  }
