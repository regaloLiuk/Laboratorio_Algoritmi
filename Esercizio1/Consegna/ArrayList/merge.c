
#include "merge.h"


List* merge(List* l1,List* l2,int (*Compare)(void*, void*)){
  List* result=NewList();
  int indexl1=0;
  int indexl2=0;
  while(indexl1<l1->size && indexl2<l2->size){
     if(Compare(Element_At_Index(l1,indexl1),Element_At_Index(l2,indexl2))<0){
        List_insert_on_tail(result,Element_At_Index(l1,indexl1));
        indexl1++;
      }
      else if(Compare(Element_At_Index(l1,indexl1),Element_At_Index(l2,indexl2))==0){
        List_insert_on_tail(result,Element_At_Index(l1,indexl1));
        List_insert_on_tail(result,Element_At_Index(l2,indexl2));
        indexl1++;
        indexl2++;
      }
      else{
          List_insert_on_tail(result,Element_At_Index(l2,indexl2));
          indexl2++;
      }
    }
    while(indexl1<l1->size){
        List_insert_on_tail(result,Element_At_Index(l1,indexl1));
        indexl1++;
    }
    while(indexl2<l2->size){
        List_insert_on_tail(result,Element_At_Index(l2,indexl2));
        indexl2++;
    }
    return result;
  }
