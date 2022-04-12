#include <stdlib.h>
#include <stdio.h>
#include <string.h>


typedef struct _list {
  void** list;
  size_t size;
  size_t capacity;
}List;


typedef struct{
	List* first;
	void* current;
	int index;
}Iterator;


/*
** Crea una lista vuota e ne restituisce il puntatore
*/
List* NewList();

/*
** @param list: lista che vogliamo distruggere
** post: distrugge la lista
*/
void destroy_list(List* l);

/*
** @param list: lista della quale vogliamo conoscere il numero di elementi
** post: Ritorna il numero di record nella lista
*/
int n_element(List* l);

/*
** @param list: lista della quale vogliamo sapere se è vuota
** post: Ritorna 1 se la lista è vuota
*/
int is_empty(List* l);


/*
** @param list: lista da cui vogliamo prelevare l'elemento
** @param position: posizione dell'elemento che ci interessa
** post: restituisce il puntatore all'elemento in posizone position
**
*/


void* Element_At_Index(List* l, int index);


/*
** @param list: lista a cui vogliamo aggiungere un nuovo record
** @param element: nuovo record che vogliamo aggiungere alla lista
** post: aggiunge il nuovo record alla lista.
*/
void List_insert_on_tail(List* l, void* element);

/*
** @param list: puntatore alla lista da cui vogliamo eliminare l'elemento in coda
** post: rimuove l'elemento in coda alla lista
**
*/
void List_remove_on_tail(List* l);

/*
**  @param list: puntatore alla testa della lista
**  @param element: elemento da aggiungere alla lista
**  @param index: posizione in cui inserire il nuovo record
**  post: Inserisce node nella posizione index della lista
*/
void List_insert_on_index(List* l, void* element,int index);


/*
** @param list: puntatore alla lista da cui vogliamo eliminare il record
** @param index: posizione dell'elemento che vogliamo eliminare
** post: elimina l'elemento di posizione index dalla lista list
*/
void List_remove_on_index(List* l,int index);


/*
** @param l: puntatore alla lista di cui vogliamo creare l'iteratore
** post: ritorna il puntatore ad un iteratore della lista l
*/
Iterator* newIterator(List* l);

/*
** @param i: puntatore alla lista di cui vogliamo conoscere la validità
** post: ritorna un intero maggiore di 0 se valido, -1 altrimenti
*/
int valid(Iterator *i);

/*
** @param i: puntatore all'iteratore di cui vogliamo conoscere l'elemento
** post: ritorna il puntatore dell'elemento
*/
void* element(Iterator* i);

/*
** @param i: puntatore all'iteratore che vogliamo far scorrere in avanti
** post: l'iteratore si posiziona sull'elemento successivo
*/
void moveForward(Iterator *i);

/*
** @param i: puntatore all'iteratore che vogliamo far scorrere indietro
** post: l'iteratore si posiziona sull'elemento precedendente
*/
void moveBackward(Iterator *i);

/*
** @param i: puntatore all'iteratore che vogliamo resettare
** post: l'iteratore si posiziona sul primo elemento
*/
void reset(Iterator* i);

/*
** @param : puntatore all'iteratore che vogliamo distruggere
** post: l'iteratore viene distrutto
*/
void destroy_Iterator(Iterator* i);
