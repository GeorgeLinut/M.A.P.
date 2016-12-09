package tests;

import junit.framework.TestCase;
import utils.SymbolTable;
import utils.SymbolTableImpl;

/**
 * Created by glinut on 11/8/2016.
 */
public class SymbolTableImplTest extends TestCase {
    SymbolTableImpl<String,Integer> symbolTable= new SymbolTableImpl<>();

    public void setUp() throws Exception {
        super.setUp();
        symbolTable.add("a",3);
        symbolTable.add("b",4);
        symbolTable.add("c",5);

    }

    public void testAdd() throws Exception {
        symbolTable.add("d",3);
        assert(symbolTable.has("d"));
    }

    public void testGetValue() throws Exception {
        assertEquals(symbolTable.getValue("a"),(Integer)3);
        assertEquals(symbolTable.getValue("b"),(Integer)4);
        assertEquals(symbolTable.getValue("c"),(Integer)5);
    }

    public void testHas() throws Exception {
        assertEquals(symbolTable.has("a"),true);
        assertEquals(symbolTable.has("b"),true);
        assertEquals(symbolTable.has("d"),false);
    }

    public void testSetValue() throws Exception {
        symbolTable.setValue("a",4);
        assertEquals(symbolTable.getValue("a"),(Integer)4);
        symbolTable.setValue("a",3);
        assertEquals(symbolTable.getValue("a"),(Integer)3);
    }

    public void testSize() throws Exception {
        assertEquals(symbolTable.size(),3);
        symbolTable.add("d",4);
        assertEquals(symbolTable.size(),4);
    }

    public void testToString() throws Exception {

    }

}