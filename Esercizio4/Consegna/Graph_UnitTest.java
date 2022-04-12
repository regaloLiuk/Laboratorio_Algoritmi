import java.io.*;
import java.util.*;

//import java.util.Comparator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

public class Graph_UnitTest {

		boolean direct;
		//comparator per ogetti Integer
		IntegerComparator intComp = new IntegerComparator();
		//comparator per oggetti String
		StringComparator strComp = new StringComparator();

		//grafo di interi con etichetta stringa
		Graph<Integer,String> IntGraph = new Graph<Integer,String>(strComp);

		//grafo di stringhe con etichetta interi
		Graph<String,Integer> StrGraph = new Graph<String,Integer>(intComp);

		//aggiunge NodiStr a Grafo
	public void addNodeIntGraph(){
		IntGraph.addNode(1);
		IntGraph.addNode(2);
		IntGraph.addNode(3);
		IntGraph.addNode(4);
	}
		//aggiunge NodiStr a Grafo
	public void addNodeStringGraph(){
		StrGraph.addNode("ciccio");
		StrGraph.addNode("bello");
		StrGraph.addNode("brazorf");
		StrGraph.addNode("ajeje");
	}

		//aggiunge Archi a grafo, necessita dei NodiInt equivalenti
	public void addArchIntGraph(){
		addNodeIntGraph();
		IntGraph.addArch(1,2,"ciao");
		IntGraph.addArch(2,3,"come");
		IntGraph.addArch(3,4,"va");
	}
		//aggiunge Archi a grafo, necessita dei NodiInt equivalenti
	public void addArchStrGraph(){
		addNodeStringGraph();
		StrGraph.addArch("ajeje","brazorf",1);
		StrGraph.addArch("ciccio","bello",2);
		StrGraph.addArch("bello","brazorf",3);
	}

		/**********TEST adddNode***********/

 @Test
    public void test_addNodeInt_Empty() throws Exception{
		Integer i = 5;
		assertEquals(IntGraph.addNode(i),i);
	}
 @Test
    public void test_addNodeInt() throws Exception{
		Integer i = 5;
		addNodeIntGraph();
		assertEquals(IntGraph.addNode(i),i);
	}
 @Test
    public void test_addNodeString_Empty() throws Exception{
		String s = "test";
		assertEquals(StrGraph.addNode(s),s);
	}
@Test
    public void test_addNodeString() throws Exception{
		String s = "ajeje";
		addNodeStringGraph();
		assertEquals(StrGraph.addNode(s),s);
	}

		/***************TEST addArch***********************/
	@Test
    public void test_addArchInt_Empty() throws Exception{
		assertNull(IntGraph.addArch(4,3,"test"));
	}
	@Test
    public void test_addArchInt() throws Exception{
		addNodeIntGraph();
		Arc<Integer,String> arch = new Arc<Integer,String>(4,3,"test");
		assertEquals(IntGraph.addArch(4,3,"test").toString(),arch.toString());
	}
	@Test
    public void test_addArchStr_Empty() throws Exception{
		assertNull(StrGraph.addArch("ajeje","brazorf",6));
	}
	@Test
    public void test_addArchStr() throws Exception{
		addNodeStringGraph();
		Arc<String,Integer> arch = new Arc<String,Integer>("ajeje","brazorf",6);
		assertEquals(StrGraph.addArch("ajeje","brazorf",6).toString(), arch.toString());
	}


		/*********************TEST isDirect***********************/
 @Test
    public void test_isDirectStr_True() throws Exception{
		assertTrue(StrGraph.isDirect());
	}@Test
    public void test_isDirectInt_True() throws Exception{
		assertTrue(IntGraph.isDirect());
	}
 @Test
    public void test_isDirectStr_False() throws Exception{
		StrGraph = new Graph<String,Integer>(false,intComp);
		assertFalse(StrGraph.isDirect());
	}
 @Test
    public void test_isDirectInt_False() throws Exception{
		IntGraph = new Graph<Integer,String>(false,strComp);
		assertFalse(IntGraph.isDirect());
	}

		/***************TEST thereIsNode************************/
 @Test
    public void test_thereIsNodeIntEmpy_False() throws Exception{
		assertFalse(IntGraph.thereIsNode(4));
	}
@Test
    public void test_thereIsNodeInt_False() throws Exception{
		assertFalse(IntGraph.thereIsNode(123));
	}
 @Test
    public void test_thereIsNodeStringEmpty_False() throws Exception{
		assertFalse(StrGraph.thereIsNode("SDgvs"));
	}
@Test
    public void test_thereIsNodeString_False() throws Exception{
		assertFalse(StrGraph.thereIsNode("SDgvs"));
	}
 @Test
    public void test_thereIsNodeInt_True() throws Exception{
		addNodeIntGraph();
		assertTrue(IntGraph.thereIsNode(3));
	}
 @Test
    public void test_thereIsNodeString_True() throws Exception{
		addNodeStringGraph();
		assertTrue(StrGraph.thereIsNode("ciccio"));
	}

		/************TEST thereIsAcrh******************/
	@Test
    public void test_thereIsArchInt_True() throws Exception{
		addArchIntGraph();
		assertTrue(IntGraph.thereIsArch(1,2));
	}
	@Test
    public void test_thereIsArchInt_False() throws Exception{
		addArchIntGraph();
		assertFalse(IntGraph.thereIsArch(4,6));
	}
	@Test
    public void test_thereIsArchInt_False_Empty() throws Exception{
		assertFalse(IntGraph.thereIsArch(1,2));
	}
	@Test
    public void test_thereIsArchStr_True() throws Exception{
		addArchStrGraph();
		assertTrue(StrGraph.thereIsArch("ciccio","bello"));
		assertTrue(StrGraph.thereIsArch("ajeje","brazorf"));
		assertTrue(StrGraph.thereIsArch("bello","brazorf"));
	}
	@Test
    public void test_thereIsArchStr_False() throws Exception{
		addArchStrGraph();
		assertFalse(StrGraph.thereIsArch("sdlvjn","afdv"));
	}
	@Test
    public void test_thereIsArchStr_False_Empty() throws Exception{
		assertFalse(StrGraph.thereIsArch("ciccio","bello"));
	}

	/****************TEST removeNode**************/
	@Test
	public void test_removeNode_sizeNodeInt_Empty() throws Exception{
		IntGraph.removeNode(3);
		assertEquals(0,IntGraph.sizeNode());
	}
	@Test
	public void test_removeNode_sizeNodeInt() throws Exception{
		addNodeIntGraph();
		IntGraph.removeNode(3);
		assertEquals(3,IntGraph.sizeNode());
	}
	@Test
	public void test_removeNode_sizeNodeInt1() throws Exception{
		addNodeIntGraph();
		IntGraph.removeNode(6);
		assertEquals(4,IntGraph.sizeNode());
	}

	@Test
	public void test_removeNode_sizeNodeStr_Empty() throws Exception{
		StrGraph.removeNode("asfav");
		assertEquals(0,StrGraph.sizeNode());
	}
	@Test
	public void test_removeNode_sizeNodeStr() throws Exception{
		addNodeStringGraph();
		StrGraph.removeNode("ciccio");
		assertEquals(3,StrGraph.sizeNode());
	}
	@Test
	public void test_removeNode_sizeNodeStr1() throws Exception{
		addNodeStringGraph();
		StrGraph.removeNode("sfgv");
		assertEquals(4,StrGraph.sizeNode());
	}

	/****************TEST sizeArch**************/
	@Test	//test sizeArch-int OK
	public void test_sizeArchInt() throws Exception{
		addArchIntGraph();
		assertEquals(3,IntGraph.sizeArch());
	}
	@Test	//test sizeArch-int OK
	public void test_sizeArchInt_Empty() throws Exception{
		assertEquals(0,IntGraph.sizeArch());
	}

	/*******************TEST removeArch***************************/
	@Test
	public void test_removeArch_sizeArchInt_Empty() throws Exception{
		IntGraph.removeArch(1,2);
		assertEquals(0,IntGraph.sizeArch());
	}
	@Test
	public void test_removeArch_sizeArchInt() throws Exception{
		addArchIntGraph();
		IntGraph.removeArch(2,3);
		assertEquals(2,IntGraph.sizeArch());
	}
	@Test
	public void test_removeArch_sizeArchInt1() throws Exception{
		addArchIntGraph();
		IntGraph.removeArch(5,6);
		assertEquals(3,IntGraph.sizeArch());
	}

		//test graf Str

	@Test
	public void test_removeArch_sizeArchStr_Empty() throws Exception{
		StrGraph.removeArch("ciccio","bello");
		assertEquals(0,StrGraph.sizeArch());
	}
	@Test
	public void test_removeArch_sizeArchStr() throws Exception{
		addArchStrGraph();
		StrGraph.removeArch("ciccio","bello");
		assertEquals(2,StrGraph.sizeArch());
	}
	@Test
	public void test_removeArch_sizeArchStr1() throws Exception{
		addArchStrGraph();
		StrGraph.removeArch("sfgv","sdgfe");
		assertEquals(3,StrGraph.sizeArch());
	}

	/**********TEST nodeSet***********/
	@Test
	public void test_nodeSet_toString_IntGraph_Empty(){
		String s = "[]";
		assertEquals(IntGraph.nodeSet().toString(),s);
	}
	@Test
	public void test_nodeSet_toString_IntGraph(){
		addNodeIntGraph();
		String s = "[1, 2, 3, 4]";
		assertEquals(IntGraph.nodeSet().toString(),s);
	}
	@Test
	public void test_nodeSet_toString_StrGraph_Empty(){
		String s = "[]";
		assertEquals(StrGraph.nodeSet().toString(),s);
	}
	@Test
	public void test_nodeSet_toString_StrGraph(){
		addNodeStringGraph();
		String s = "[brazorf, bello, ciccio, ajeje]";
		assertEquals(StrGraph.nodeSet().toString(),s);
	}

	/**********TEST getEtic***********/
	@Test
	public void test_getEtic_IntGraph(){
		addArchIntGraph();
		assertEquals(IntGraph.getEtic(1,2),"ciao");
	}
	@Test
	public void test_getEtic_StrGraph(){
		addArchStrGraph();
		Integer i = 2;
		assertEquals(StrGraph.getEtic("ciccio","bello"),i);
	}

	/*************TEST getArc*******************/
/*	@Test //metodo privato
	public void test_getArc_IntGraph(){
		addArchIntGraph();
		Arc tmp = new Arc(2,1,"ciao");
		assertEquals(IntGraph.getArc(1,2),tmp);
	}*/


	/***********TEST adjacentToArch**********/

	@Test
	public void test_adjacentToArch_toString_IntGraph(){
		addArchIntGraph();
		String s = "[(1,2,ciao)]";
		assertEquals(IntGraph.adjacentToArch(1).toString(),s);
	}
	@Test
	public void test_adjacentToArch_toString_StrGraph(){
		addArchStrGraph();
		String s = "[(ciccio,bello,2)]";
		assertEquals(StrGraph.adjacentToArch("ciccio").toString(),s);
	}

	/****************TEST getEdgeSet**************/
	@Test  //cambia ordine di stringhe
	public void test_getEdgeSet_sort_IntGraph(){
		addArchIntGraph();
		Vector<Arc<Integer,String>> v;
		v =IntGraph.toVector(IntGraph.getEdgeSet());
		IntGraph.sort(v);
		String s = "[(1,2,ciao), (2,3,come), (3,4,va)]";
		assertEquals(v.toString(),s);
	}
	@Test
	public void test_getEdgeSet_sort_StrGraph(){
		addArchStrGraph();
		Vector<Arc<String,Integer>> v;
		v =StrGraph.toVector(StrGraph.getEdgeSet());
		StrGraph.sort(v);
		String s = "[(ajeje,brazorf,1), (ciccio,bello,2), (bello,brazorf,3)]";
		assertEquals(v.toString(),s);
	}

	/***********************TEST adjacentToNode**************************/
	@Test
	public void test_adjacentToNode_toString_IntGraph(){
		addArchIntGraph();
		String s = "[2]";
		assertEquals(IntGraph.adjacentToNode(1).toString(),s);
	}
	@Test
	public void test_adjacentToNode_toString_StrGraph(){
		addArchStrGraph();
		String s = "[bello]";
		assertEquals(StrGraph.adjacentToNode("ciccio").toString(),s);
	}

	/******************TEST toVector*****************************/
	@Test
	public void test_toVector_sort_IntGraph(){
		addArchIntGraph();
		Vector<Arc<Integer,String>> v;
		v =IntGraph.toVector(IntGraph.adjacentToArch(2));
		IntGraph.sort(v);
		String s = "[(1,2,ciao), (2,3,come), (3,4,va)]";
		assertEquals(v.toString(),s);
	}
	@Test
	public void test_toVector_sort_StrGraph(){
		addArchStrGraph();
		Vector<Arc<String,Integer>> v;
		v =StrGraph.toVector(StrGraph.adjacentToArch("ajeje"));
		StrGraph.sort(v);
		String s = "[(ajeje,brazorf,1), (ciccio,bello,2), (bello,brazorf,3)]";
		assertEquals(v.toString(),s);
	}

	/************************TEST toVectorNode***************************/
	@Test
	public void test_toVectorNode_sort_IntGraph(){
		addArchIntGraph();
		Vector<Integer> v;
		v = IntGraph.toVectorNode(IntGraph.adjacentToNode(2));
		v.sort(intComp);
		String s = "[1, 2, 3, 4]";
		assertEquals(v.toString(),s);
	}
	@Test
	public void test_toVectorNode_sort_StrGraph(){
		addArchStrGraph();
		Vector<String> v;
		v = StrGraph.toVectorNode(StrGraph.adjacentToNode("ajeje"));
		v.sort(strComp);
		String s = "[ajeje, bello, brazorf, ciccio]";
		assertEquals(v.toString(),s);
	}

	/************************TEST KRUSKAL**************************************/
	@Test
	public void test_Kruskal(){
		ArcoComparator fltComp = new ArcoComparator();
		Graph<String,Float> FltGraph = new Graph<String,Float>(fltComp);
		Vector<Arc<String,Float>> ris=new Vector<Arc<String,Float>>();
		Set<String> node=new HashSet<String>();
		FltGraph.addNode("ciao");
		FltGraph.addNode("mamma");
		FltGraph.addNode("guarda");
		FltGraph.addNode("come");
		FltGraph.addArch("ciao","mamma",new Float(2));
		FltGraph.addArch("guarda","come",new Float(1));
		FltGraph.addArch("guarda","ciao",new Float(4));
		FltGraph.addArch("mamma","come",new Float(9));
		String s = "Numero nodi : 4 Numero di archi: 3 Peso: 7.0";
		ris=FltGraph.Kruskal();
		float weight=0;

    for(int j=0;j<ris.size();j++){
      weight=weight+((Float)ris.elementAt(j).getEtic());
      node.add(ris.elementAt(j).getNode());
      node.add(ris.elementAt(j).getFinish());
		}
		assertEquals("Numero nodi : "+node.size()+" Numero di archi: "+ris.size()+" Peso: "+weight,s);

	}
	public void test_Kruskal_Empty(){
		ArcoComparator fltComp = new ArcoComparator();
		Graph<String,Float> FltGraph = new Graph<String,Float>(fltComp);
		Vector<Arc<String,Float>> ris=new Vector<Arc<String,Float>>();
		ris=FltGraph.Kruskal();
		assertEquals(ris.size(),0);

	}

} // class
