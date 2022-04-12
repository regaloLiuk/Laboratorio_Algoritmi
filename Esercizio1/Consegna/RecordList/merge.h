#include <stdlib.h>
#include <stdio.h>
#include "List.h"


typedef int (*Compare)(void*, void*);

List* merge(List* l1,List* l2,int (*Compare)(void*, void*));
