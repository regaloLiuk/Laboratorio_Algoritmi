import java.io.*;
import java.util.*;

//import java.util.Comparator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

public class UnionFindSet_UnitTest {

    private UnionFindSet<Integer> u = new UnionFindSet<Integer>();

	/**CREA STRINGHE PER TEST*/
	public String createStringEmpty(){
		String ris = "\n";
		return ris;
	}
	public String createString(){
		String ris = "\n";
		ris= ris + "1 rank: 0 father: 1\n" +
				   "2 rank: 0 father: 2\n" +
				   "3 rank: 0 father: 3\n" ;
		return ris;
	}

					/**************TEST FIND*************/

	/**test per ArrayList*/
    @Test	//test null
    public void testFindNull_ArrayList() throws Exception{
		Integer [] T = {};
		ArrayList<Node<Integer>> al;
		al = u.MakeSet_ArrList(T);
		assertNull(u.Find(0));
		assertNull(u.Find((Node<Integer>)null));
    }
	@Test	//test con nodi
    public void testFind_ArrayList() throws Exception{
		Integer [] T = {1,2,3,4,5};
		ArrayList<Node<Integer>> al;
		al = u.MakeSet_ArrList(T);
		String s = "4 rank: 0 father: 4";
		assertEquals(u.Find(al.get(3)).toString(),s);
    }
    @Test	//test con elementi
    public void testFind2_ArrayList() throws Exception{
		Integer [] T = {1,2,3,4,5};
		ArrayList<Node<Integer>> al;
		al = u.MakeSet_ArrList(T);
		String s = "3 rank: 0 father: 3";
		assertEquals(u.Find(3).toString(),s);
	}

	/**test per Vector*/
	@Test	//test null
    public void testFindNull_Vector() throws Exception{
		Integer [] T = {};
		Vector<Node<Integer>> v;
		v = u.MakeSet_Vector(T);
		assertNull(u.Find(0));
		assertNull(u.Find((Node<Integer>)null));
	}
	@Test	//test con Nodi
    public void testFind_Vector() throws Exception{
		Integer [] T = {1,2,3,4,5};
		Vector<Node<Integer>> v;
		v = u.MakeSet_Vector(T);
		String s = "4 rank: 0 father: 4";
		assertEquals(u.Find(v.get(3)).toString(),s);
    }
    @Test	//test con elementi
    public void testFind2_Vector() throws Exception{
		Integer [] T = {1,2,3,4,5};
		Vector<Node<Integer>> v;
		v = u.MakeSet_Vector(T);
		String s = "3 rank: 0 father: 3";
		assertEquals(u.Find(3).toString(),s);
	}

						/********TEST UNION*******/

	/**Test Union Vector con Nodi*/
	@Test
    public void testUnion_Vector() throws Exception{
		Vector<Node<Integer>> v;
		Integer [] T = {1,2,3,4,5};
		String s = "\n";
		s = s + "1 rank: 0 father: 5\n" +
				"2 rank: 0 father: 2\n" +
				"3 rank: 0 father: 3\n" +
				"4 rank: 0 father: 4\n" +
				"5 rank: 1 father: 5\n" ;
		v = u.MakeSet_Vector(T);
		u.Union(v.get(0),v.get(4));
		assertEquals(u.toString(),s);
    }

	/**Test Union Vector con elementi*/
	@Test
    public void testUnion2_Vector() throws Exception{
		Integer [] T = {1,2,3,4,5};
		Vector<Node<Integer>> v;
		String s = "\n";
		s = s + "1 rank: 0 father: 5\n" +
				"2 rank: 0 father: 2\n" +
				"3 rank: 0 father: 3\n" +
				"4 rank: 0 father: 4\n" +
				"5 rank: 1 father: 5\n" ;
		v = u. MakeSet_Vector(T);
		u.Union(1,5);
		assertEquals(u.toString(),s);
    }
	/**Test Union ArrayList con Nodi*/
	@Test
    public void testUnion_ArrayList() throws Exception{
		Integer [] T = {1,2,3,4,5};
		ArrayList<Node<Integer>> al;
		String s = "\n";
		s = s + "1 rank: 0 father: 5\n" +
				"2 rank: 0 father: 2\n" +
				"3 rank: 0 father: 3\n" +
				"4 rank: 0 father: 4\n" +
				"5 rank: 1 father: 5\n" ;
		al = u. MakeSet_ArrList(T);
		u.Union(al.get(0),al.get(4));
		assertEquals(u.toString(),s);
    }
	/**Test Union ArrayList con elementi*/
	@Test
    public void testUnion2_ArrayList() throws Exception{
		Integer [] T = {1,2,3,4,5};
		ArrayList<Node<Integer>> al;
		String s = "\n";
		s = s + "1 rank: 0 father: 5\n" +
				"2 rank: 0 father: 2\n" +
				"3 rank: 0 father: 3\n" +
				"4 rank: 0 father: 4\n" +
				"5 rank: 1 father: 5\n" ;
		al = u. MakeSet_ArrList(T);
		u.Union(1,5);
		assertEquals(u.toString(),s);
    }


				/************* TEST MAKESET*************/
	/**test MakeSet ArrayList vuoti*/
	@Test
    public void testMakeSet_ArrayList_Empty() throws Exception{
		Integer[] i = {};
		String s = createStringEmpty();
		u.MakeSet_ArrList(i);
		assertEquals(u.toString(),s);
	}
	/**test MakeSet ArrayList*/
	@Test
    public void testMakeSet_ArrayList() throws Exception{
		Integer[] i = {1,2,3};
		String s = "\n";
		s = s + "1 rank: 0 father: 1\n" +
				"2 rank: 0 father: 2\n" +
				"3 rank: 0 father: 3\n" ;
		u.MakeSet_ArrList(i);
		assertEquals(u.toString(),s);
	}

	

	/**test MakeSet Vector vuoti*/
	@Test
    public void testMakeSet_Vector_Empty() throws Exception{
		Integer[] i = {};
		String s = createStringEmpty();
		u.MakeSet_Vector(i);
		assertEquals(u.toString(),s);
	}
	/**test MakeSet Vector */
	@Test
    public void testMakeSet_Vector() throws Exception{
		Integer[] i = {1,2,3};
		String s = "\n";
		s = s + "1 rank: 0 father: 1\n" +
				"2 rank: 0 father: 2\n" +
				"3 rank: 0 father: 3\n" ;
		u.MakeSet_Vector(i);
		assertEquals(u.toString(),s);
	}

} // class
