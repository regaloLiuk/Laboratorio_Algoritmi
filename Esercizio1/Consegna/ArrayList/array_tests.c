#include <stdlib.h>
#include "merge.h"
#include "unity.h"
#include "unity_internals.h"


#define true 1
#define false 0

static int compare(void* obj1,void* obj2){
  return *(int*)obj1-*(int*)obj2;
}


static int* new_int(int value) {
  int* elem = (int*) malloc(sizeof(int));
  *elem = value;
  return elem;
}

/*crea una lista di tipo array*/
static List* build_array() {
  List* array = NewList();
  List_insert_on_tail(array,new_int(1));
  List_insert_on_tail(array,new_int(3));
  List_insert_on_tail(array,new_int(5));
  List_insert_on_tail(array,new_int(6));
  return array;
}
/*crea seconda lista per test merge*/
static List* build_array1() {
  List* a = NewList();
  List_insert_on_tail(a,new_int(2));
  List_insert_on_tail(a,new_int(4));
  List_insert_on_tail(a,new_int(7));
  List_insert_on_tail(a,new_int(9));
  return a;
}


static void test_not_null() {
  List* array = NewList();
  TEST_ASSERT_NOT_NULL(array);
  List* array1 = build_array();
  TEST_ASSERT_NOT_NULL(array1);
  destroy_list(array);
  destroy_list(array1);
}


static void test_array_free() {
  List* array = NewList();
  destroy_list(array);
  TEST_ASSERT_EQUAL_INT(1,1);
}

static void test_size() {
 List* array = build_array();
  TEST_ASSERT_EQUAL_INT(4, n_element(array));
  List_remove_on_tail(array);
  TEST_ASSERT_EQUAL_INT(3,n_element(array));
  List_remove_on_tail(array);
  TEST_ASSERT_EQUAL_INT(2, n_element(array));
  List_insert_on_tail(array,new_int(5));
  List_insert_on_tail(array,new_int(5));
  List_insert_on_tail(array,new_int(5));
  List_insert_on_tail(array,new_int(5));
  List_insert_on_tail(array,new_int(5));
  List_insert_on_tail(array,new_int(5));
  TEST_ASSERT_EQUAL_INT(8,n_element(array));
  List_remove_on_tail(array);
  List_remove_on_tail(array);
  List_remove_on_tail(array);
  List_remove_on_tail(array);
  List_remove_on_tail(array);
  List_remove_on_tail(array);
  List_remove_on_tail(array);
  List_remove_on_tail(array);
  TEST_ASSERT_EQUAL_INT(0,n_element(array));
  List_remove_on_tail(array);
  destroy_list(array);
}


static void test_empty() {
  List* array = NewList();
  List* array1 = build_array();
  TEST_ASSERT_EQUAL_INT(true, is_empty(array));
  TEST_ASSERT_EQUAL_INT(false, is_empty(array1));
  destroy_list(array);
  destroy_list(array1);
}

static void test_array_realloc() {
  List* array = NewList();
  TEST_ASSERT_EQUAL_INT(0,n_element(array));
  List_insert_on_tail(array,new_int(1));
  TEST_ASSERT_EQUAL_INT(1,n_element(array));
  List_insert_on_tail(array,new_int(3));
  TEST_ASSERT_EQUAL_INT(2,n_element(array));

  destroy_list(array);
}

static void test_array_at() {
  List* array = build_array();
  List_insert_on_index(array, new_int(123),0);
  List_insert_on_index(array, new_int(456),1);
  List_insert_on_index(array, new_int(678),2);
  TEST_ASSERT_EQUAL_INT(123, *(int*)Element_At_Index(array, 0) );
  TEST_ASSERT_EQUAL_INT(456, *(int*)Element_At_Index(array, 1) );
  TEST_ASSERT_EQUAL_INT(678, *(int*)Element_At_Index(array, 2) );
  TEST_ASSERT_EQUAL_INT(1, *(int*)Element_At_Index(array, 3) );
  TEST_ASSERT_EQUAL_INT(3, *(int*)Element_At_Index(array, 4) );
  destroy_list(array);
}


static void test_insert_at() {
  List* array = build_array();
  List_insert_on_index(array, new_int(9), 4);
  List_insert_on_index(array, new_int(123), 6);
  TEST_ASSERT_EQUAL_INT(5, n_element(array));
  TEST_ASSERT_EQUAL_INT(1, *(int*)Element_At_Index(array, 0));
  TEST_ASSERT_EQUAL_INT(3, *(int*)Element_At_Index(array, 1));
  TEST_ASSERT_EQUAL_INT(5, *(int*)Element_At_Index(array, 2));
  TEST_ASSERT_EQUAL_INT(6, *(int*)Element_At_Index(array, 3));
  TEST_ASSERT_EQUAL_INT(9, *(int*)Element_At_Index(array, 4));

  destroy_list(array);
}

static void test_insert_at_end() {
  List* array = build_array();
  List_insert_on_tail(array,new_int(8));
  TEST_ASSERT_EQUAL_INT(5, n_element(array));
  TEST_ASSERT_EQUAL_INT(8, *(int*)Element_At_Index(array,4));

  destroy_list(array);
}

      /* TEST FUNZIONE MERGE*/

static void test_merge_empty(){
  List* aE1 = NewList();
  List* aE2 = NewList();
  List* am = merge(aE2,aE1,compare);
  TEST_ASSERT_EQUAL_INT(0,n_element(am));
  destroy_list(aE2);
  destroy_list(aE1);
  destroy_list(am);
}

static void test_merge() {
  List* a = build_array();
  List* a1 = build_array1();
  List* am = merge(a,a1,compare);

  TEST_ASSERT_EQUAL_INT(1, *(int*)Element_At_Index(am, 0));
  TEST_ASSERT_EQUAL_INT(2, *(int*)Element_At_Index(am, 1));
  TEST_ASSERT_EQUAL_INT(3, *(int*)Element_At_Index(am, 2));
  TEST_ASSERT_EQUAL_INT(4, *(int*)Element_At_Index(am, 3));
  TEST_ASSERT_EQUAL_INT(5, *(int*)Element_At_Index(am, 4));
  TEST_ASSERT_EQUAL_INT(6, *(int*)Element_At_Index(am, 5));
  TEST_ASSERT_EQUAL_INT(7, *(int*)Element_At_Index(am, 6));
  TEST_ASSERT_EQUAL_INT(9, *(int*)Element_At_Index(am, 7));

  List* array = NewList();
  List* array1 = NewList();
  List* array2 = NewList();
  List* arraym = merge(array,array1,compare);
  TEST_ASSERT_EQUAL_INT(0,n_element(arraym));

  destroy_list(a1);
  destroy_list(a);
  destroy_list(am);
}

          /*TEST FUNZIONI ITERATORE*/

static void test_iterator_not_null() {
  List* l = NewList();
  Iterator* i = newIterator(l);

  TEST_ASSERT_NOT_NULL(i);

  destroy_Iterator(i);
  destroy_list(l);
}


static void test_iterator_free() {
  List* l = NewList();
  Iterator* i = newIterator(l);

  destroy_Iterator(i);
  destroy_list(l);

  TEST_ASSERT_EQUAL_INT(1, 1);
}


static void test_iterator_at() {
  List* l = NewList();

  List_insert_on_tail(l,new_int(4));
  List_insert_on_tail(l,new_int(6));
  List_insert_on_tail(l,new_int(11));
  List_insert_on_tail(l,new_int(16));
  List_insert_on_index(l,new_int(5),1);
  List_insert_on_index(l,new_int(7),2);
  List_insert_on_index(l,new_int(2),3);

  Iterator* i =newIterator(l);

  TEST_ASSERT_EQUAL_INT(4, *(int*)element(i));
  moveForward(i);
  TEST_ASSERT_EQUAL_INT(5, *(int*)element(i));
  moveForward(i);
  TEST_ASSERT_EQUAL_INT(7, *(int*)element(i));
  moveForward(i);
  TEST_ASSERT_EQUAL_INT(2, *(int*)element(i));
  moveBackward(i);
  TEST_ASSERT_EQUAL_INT(7, *(int*)element(i));
  reset(i);
  TEST_ASSERT_EQUAL_INT(4, *(int*)element(i));
  destroy_Iterator(i);
  destroy_list(l);
}

static void test_iterator_index() {
  List* l = NewList();
  List_insert_on_tail(l,new_int(4));
  List_insert_on_tail(l,new_int(6));
  List_insert_on_tail(l,new_int(11));
  List_insert_on_tail(l,new_int(16));
  List_insert_on_index(l,new_int(5),1);
  List_insert_on_index(l,new_int(7),2);
  List_insert_on_index(l,new_int(2),3);

  Iterator* i =newIterator(l);

  TEST_ASSERT_EQUAL_INT(0, valid(i));
  moveForward(i);
  TEST_ASSERT_EQUAL_INT(1, valid(i));
  moveForward(i);
  TEST_ASSERT_EQUAL_INT(2, valid(i));
  moveForward(i);
  TEST_ASSERT_EQUAL_INT(3, valid(i));
  moveForward(i);
  moveForward(i);
  TEST_ASSERT_EQUAL_INT(5, valid(i));
  moveBackward(i);
  TEST_ASSERT_EQUAL_INT(4,valid(i));
  reset(i);
  TEST_ASSERT_EQUAL_INT(0,valid(i));
  destroy_Iterator(i);
  destroy_list(l);
}


int main() {
  UNITY_BEGIN();
  RUN_TEST(test_not_null);
  RUN_TEST(test_size);
  RUN_TEST(test_empty);
  RUN_TEST(test_array_realloc);
  RUN_TEST(test_array_at);
  RUN_TEST(test_insert_at);
  RUN_TEST(test_insert_at_end);
  RUN_TEST(test_merge);
  RUN_TEST(test_iterator_not_null);
  RUN_TEST(test_iterator_free);
  RUN_TEST(test_iterator_at);
  RUN_TEST(test_iterator_index);
  UNITY_END();

  return 0;
}
