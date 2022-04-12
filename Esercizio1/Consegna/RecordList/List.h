#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct _Record{
  void* elem;
  struct _Record* next;
  struct _Record* prev;
}Record;

// Struttura che rappresenta la lista
typedef struct {
  Record* head;
  Record* tail;
  size_t size;
}List;

typedef struct{
	Record* first;
	Record* current;
	int index;
}Iterator;

/*
** Crea una lista vuota
*/
List* NewList();


/*
** @param l: lista che vogliamo distruggere
** post: distrugge la lista
*/
void destroy_list(List* l);



/*
** @param l: lista della quale vogliamo conoscere il numero di elementi
** post: Ritorna il numero di record
*/
int n_element(List* l);


/*
** @param l: lista della quale vogliamo sapere se è vuota
** post: Ritorna 1 se la lista è vuota
*/
int is_empty(List* l);


/*
** @param l: lista da cui vogliamo prelevare l'elemento
** @param index: posizione dell'elemento che ci interessa
** post: restituisce il puntatore all'elemento in posizone index
**
*/
void* Element_At_Index(List* l,int index);


/*
** @param l: lista a cui vogliamo aggiungere un nuovo record
** @param node: nuovo record che vogliamo aggiungere alla lista
** post: aggiunge il nuovo record alla lista.
*/
void List_insert_on_tail(List* l,void* element);


/*
** @param l: puntatore alla lista da cui vogliamo eliminare l'elemento in coda
** post: rimuove l'elemento in coda alla lista
**
*/
void List_remove_on_tail(List* l);



/*
**  @param l: puntatore alla testa della lista
**  @param element: elemento da aggiungere alla lista
**  @param index: posizione in cui inserire il nuovo record
**  post: Inserisce node nella posizione index della lista
*/
void List_insert_on_index(List* l,void* element,int index);



/*
** @param l: puntatore alla lista da cui vogliamo eliminare il record
** @param index: posizione dell'elemento che vogliamo eliminare
** post: elimina l'elemento di posizione index dalla lista l
*/

void List_remove_on_index(List* l,int index);

/*
** @param l: puntatore alla lista di cui vogliamo creare l'iteratore
** post: ritorna il puntatore ad un iteratore della lista l
*/
Iterator* newIterator(List* l);

/*
** @param l: puntatore alla lista di cui vogliamo conoscere la validità
** post: ritorna un intero maggiore di 0 se valido, -1 altrimenti
*/
int valid(Iterator *i);

/*
** @param l: puntatore all'iteratore di cui vogliamo conoscere l'elemento
** post: ritorna il puntatore dell'elemento
*/
void* element(Iterator* i);

/*
** @param l: puntatore all'iteratore che vogliamo far scorrere in avanti
** post: l'iteratore si posiziona sull'elemento successivo
*/
void moveForward(Iterator *i);

/*
** @param l: puntatore all'iteratore che vogliamo far scorrere indietro
** post: l'iteratore si posiziona sull'elemento precedendente
*/
void moveBackward(Iterator *i);

/*
** @param l: puntatore all'iteratore che vogliamo resettare
** post: l'iteratore si posiziona sul primo elemento
*/
void reset(Iterator* i);

/*
** @param i: iteratore da distruggere
** post: l'iteratore viene ditrutto
*/

void destroy_Iterator(Iterator* i);
