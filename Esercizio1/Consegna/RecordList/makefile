
TestList:list_test.o merge.o List.o unity.o
	gcc -o TestList list_test.o merge.o List.o unity.o


clean:
	rm -f *.o
	rm TestList
	echo Clean done

unity.o: unity.c unity.h unity_internals.h
	gcc -c unity.c

List.o: List.c List.h
	gcc -c List.c

merge.o: merge.c merge.h List.h
	gcc -c merge.c

list_test.o: list_test.c merge.h List.h unity.h unity_internals.h
	gcc -c list_test.c
