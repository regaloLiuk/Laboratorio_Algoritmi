//package Esercizio2;

import java.io.*;
import java.util.*;

//import java.util.Comparator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class EditDistance_UnitTest {

    private String s1,s2;

	/**Test per editDistance versione dinamica*/
    
	@Test	/*test con stringhe vuote*/
    public void testVoidVoidDyn() throws Exception{
		s1 = "";
		s2 = "";
        assertEquals(0, EditDistance.editDistance_dyn(s1,s2));
    }
	
	@Test	/*test tra stringa vuota e stringa*/
    public void testStringVoidDyn() throws Exception{
        s1 = "qualcosa";
		s2 = ""; 
        assertEquals(8, EditDistance.editDistance_dyn(s1,s2));
    }
	
	@Test	/*test tra stringa vuota e stringa*/
    public void testVoidStringDyn() throws Exception{
        s1 = "";
		s2 = "parola"; 
        assertEquals(6,EditDistance.editDistance_dyn(s1,s2));
    }
	
	@Test	/*test tra stringhe uguali*/
    public void testEqStringStringDyn() throws Exception{
        s1 = "parola";
		s2 = "parola"; 
        assertEquals(0,EditDistance.editDistance_dyn(s1,s2));
    }
	
	@Test	/*test tra stringhe simili*/
    public void testStringStringDyn() throws Exception{
        s1 = "casa";
		s2 = "cassa"; 
        assertEquals(1,EditDistance.editDistance_dyn(s1,s2));
    }
	
	
	/**Test per editDistance versione ricorsiva*/
	
	@Test	/*test con stringhe vuote*/
    public void testVoidVoid() throws Exception{
		s1 = "";
		s2 = "";
        assertEquals(0,EditDistance.editDistance(s1,s2));
    }
	
	@Test	/*test tra stringa vuota e stringa*/
    public void testStringVoid() throws Exception{
        s1 = "qualcosa";
		s2 = ""; 
        assertEquals(8,EditDistance.editDistance(s1,s2));
    }
	
	@Test	/*test tra stringa vuota e stringa*/
    public void testVoidString() throws Exception{
        s1 = "";
		s2 = "parola"; 
        assertEquals(6,EditDistance.editDistance(s1,s2));
    }
	
	@Test	/*test tra stringhe uguali*/
    public void testEqStringString() throws Exception{
        s1 = "stringa";
		s2 = "stringa"; 
        assertEquals(0, EditDistance.editDistance(s1,s2));
    }
	
	@Test	/*test tra stringhe simili*/
    public void testStringString() throws Exception{
        s1 = "test";
		s2 = "testo"; 
        assertEquals(1, EditDistance.editDistance(s1,s2));
	}
	
} // class








