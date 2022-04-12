#include "merge.h"
#include "unity.h"
#include "unity_internals.h"
#include <stdlib.h>

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
  /*crea lista per test*/
static List* build_list() {
  List* l = NewList();
  List_insert_on_tail(l,new_int(1));
  List_insert_on_tail(l,new_int(3));
  List_insert_on_tail(l,new_int(5));
  List_insert_on_tail(l,new_int(7));
  return l;
}
  /*costruisce senda lista per test merge*/
static List* build_list1() {
  List* l = NewList();
  List_insert_on_tail(l,new_int(2));
  List_insert_on_tail(l,new_int(4));
  List_insert_on_tail(l,new_int(6));
  List_insert_on_tail(l,new_int(8));
  List_insert_on_tail(l,new_int(9));

  return l;
}

static void test_not_null() {
  List* l = NewList();
  TEST_ASSERT_NOT_NULL(l);
  List* l1 = build_list();
  TEST_ASSERT_NOT_NULL(l1);
  destroy_list(l1);
  destroy_list(l);
}


static void test_list_free() {
  List* l = NewList();
  destroy_list(l);
  TEST_ASSERT_NULL(l);
  List* l1 = build_list();
  destroy_list(l1);
  TEST_ASSERT_EQUAL_INT(1,1);
  List* l3 = NULL;
  destroy_list(l3);
  TEST_ASSERT_EQUAL_INT(1,1);
}

static void test_size() {
  List* l = NewList();
  TEST_ASSERT_EQUAL_INT(0, n_element(l));
  l = build_list();
  TEST_ASSERT_EQUAL_INT(4,n_element(l));
  List_remove_on_tail(l);
  List_remove_on_tail(l);
  TEST_ASSERT_EQUAL_INT(2, n_element(l));
  List_insert_on_tail(l,new_int(3));
  TEST_ASSERT_EQUAL_INT(3,n_element(l));
  List_remove_on_tail(l);
  List_remove_on_tail(l);
  List_remove_on_tail(l);
  List_remove_on_tail(l);
  TEST_ASSERT_EQUAL_INT(0,n_element(l));
  destroy_list(l);
}


static void test_empty() {
  List* l = NewList();
  TEST_ASSERT_EQUAL_INT(true, is_empty(l));
  List* l1 = build_list();
  TEST_ASSERT_EQUAL_INT(false, is_empty(l1));
  destroy_list(l);
  destroy_list(l1);
}

static void test_Element_At_Index() {
  List* l = build_list();
  List_insert_on_index(l,new_int(567),2);
  List_insert_on_index(l,new_int(123),3);
  TEST_ASSERT_EQUAL_INT(1, *(int*)Element_At_Index(l, 0) );
  TEST_ASSERT_EQUAL_INT(3, *(int*)Element_At_Index(l, 1) );
  TEST_ASSERT_EQUAL_INT(567, *(int*)Element_At_Index(l, 2));
  TEST_ASSERT_EQUAL_INT(123, *(int*)Element_At_Index(l, 3));
  TEST_ASSERT_EQUAL_INT(5, *(int*)Element_At_Index(l, 4));
  TEST_ASSERT_EQUAL_INT(7, *(int*)Element_At_Index(l, 5));
  destroy_list(l);
}


static void test_insert_at() {
  List* l = build_list();
  List_insert_on_index(l,new_int(4),3);
  TEST_ASSERT_EQUAL_INT(4, *(int*)Element_At_Index(l,3));
  List_insert_on_index(l,new_int(6),0);
  TEST_ASSERT_EQUAL_INT(6,*(int*)Element_At_Index(l,0));
  List_insert_on_index(l,new_int(123),1);
  TEST_ASSERT_EQUAL_INT(123,*(int*)Element_At_Index(l,1));
  List_insert_on_index(l,new_int(123456),0);
  TEST_ASSERT_EQUAL_INT(123456,*(int*)Element_At_Index(l,0));
  destroy_list(l);
}

static void test_insert_at_end() {
  List* l = build_list();
  List_insert_on_tail(l,new_int(5));
  TEST_ASSERT_EQUAL_INT(5, *(int*)Element_At_Index(l,4));
  List_insert_on_tail(l,new_int(123));
  TEST_ASSERT_EQUAL_INT(123, *(int*)Element_At_Index(l,5));
  List_insert_on_tail(l,new_int(3));
  TEST_ASSERT_EQUAL_INT(3, *(int*)Element_At_Index(l,6));
  destroy_list(l);
}

static void test_insert_at_begin() {
  List* l = NewList();
  //build_list();compare
  List_insert_on_tail(l,new_int(33));
  TEST_ASSERT_EQUAL_INT(33, *(int*)Element_At_Index(l,0));
  List_insert_on_tail(l,new_int(3445));
  TEST_ASSERT_EQUAL_INT(3445, *(int*)Element_At_Index(l,1));
  List_insert_on_tail(l,new_int(0));
  TEST_ASSERT_EQUAL_INT(0, *(int*)Element_At_Index(l,2));
  destroy_list(l);
}


static void test_remove_at_end() {
  List* l = build_list();
  List_remove_on_tail(l);
  TEST_ASSERT_EQUAL_INT(3,n_element(l));
  List_remove_on_tail(l);
  TEST_ASSERT_EQUAL_INT(2,n_element(l));
  List_remove_on_tail(l);
  List_remove_on_tail(l);
  TEST_ASSERT_EQUAL_INT(0,n_element(l));
  List_remove_on_tail(l);
  TEST_ASSERT_EQUAL_INT(0,n_element(l));
  destroy_list(l);
}

            /*TEST FUNZIONE MERGE*/

static void test_merge_empty(){
  List* lE1 = NewList();
  List* lE2 = NewList();
  List* lm = merge(lE2,lE1,compare);
  TEST_ASSERT_EQUAL_INT(0,n_element(lm));
  destroy_list(lE2);
  destroy_list(lE1);
  destroy_list(lm);
}

static void test_merge() {
  List* l1 = build_list();
  List* l2 = build_list1();
  List* lm = merge(l2,l1,compare);

  TEST_ASSERT_EQUAL_INT(1,*(int*)Element_At_Index(lm, 0));
  TEST_ASSERT_EQUAL_INT(2,*(int*)Element_At_Index(lm, 1));
  TEST_ASSERT_EQUAL_INT(3,*(int*)Element_At_Index(lm, 2));
  TEST_ASSERT_EQUAL_INT(4,*(int*)Element_At_Index(lm, 3));  //6?
  TEST_ASSERT_EQUAL_INT(5,*(int*)Element_At_Index(lm, 4));
  TEST_ASSERT_EQUAL_INT(6,*(int*)Element_At_Index(lm, 5));
  TEST_ASSERT_EQUAL_INT(7,*(int*)Element_At_Index(lm, 6));
  TEST_ASSERT_EQUAL_INT(8,*(int*)Element_At_Index(lm, 7));
  TEST_ASSERT_EQUAL_INT(9,*(int*)Element_At_Index(lm, 8));

  destroy_list(l2);
  destroy_list(l1);
  destroy_list(lm);
}

        /*TEST ITERATOR*/

static void test_iterator_not_null() {
  List* l = NewList();
  Iterator* i = newIterator(l);

  TEST_ASSERT_NOT_NULL(i);

  reset(i);
  destroy_list(l);
}


static void test_iterator_free() {
  List* l = NewList();
  Iterator* i = newIterator(l);

  reset(i);
  destroy_list(l);

  TEST_ASSERT_EQUAL_INT(1, 1);
}


static void test_iterator_at() {
  List* l = NewList();

  List_insert_on_tail(l,new_int(4));
  /*List_insert_on_index(l,new_int(5),1);
  List_insert_on_index(l,new_int(7),2);
  List_insert_on_index(l,new_int(2),3);*/
  List_insert_on_tail(l,new_int(6));
  List_insert_on_tail(l,new_int(11));
  List_insert_on_tail(l,new_int(16));
  List_insert_on_index(l,new_int(5),1);
  List_insert_on_index(l,new_int(7),2);
  List_insert_on_index(l,new_int(2),3);

  Iterator* i = newIterator(l);

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
  destroy_list(l);
  reset(i);
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
  moveBackward(i);
  TEST_ASSERT_EQUAL_INT(2,  valid(i));
  reset(i);
  TEST_ASSERT_EQUAL_INT(0,  valid(i));
  destroy_list(l);
  reset(i);
}


int main() {
  UNITY_BEGIN();
  RUN_TEST(test_not_null);
  RUN_TEST(test_size);
//  RUN_TEST(test_list_free);
  RUN_TEST(test_empty);
  RUN_TEST(test_Element_At_Index);
  RUN_TEST(test_insert_at);
  RUN_TEST(test_insert_at_end);
  RUN_TEST(test_insert_at_begin);
  RUN_TEST(test_remove_at_end);
  RUN_TEST(test_merge);
  RUN_TEST(test_merge_empty);
  RUN_TEST(test_iterator_not_null);
  RUN_TEST(test_iterator_free);
  RUN_TEST(test_iterator_at);
  RUN_TEST(test_iterator_index);
  UNITY_END();

  return 0;
}
