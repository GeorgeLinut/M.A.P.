package tests;

import domain.AssignStmt;
import domain.ConstantExpression;
import domain.Expression;
import domain.VarExpression;
import junit.framework.TestCase;
import utils.Heap;
import utils.HeapImpl;
import utils.SymbolTableImpl;

/**
 * Created by glinut on 11/8/2016.
 */
public class VarExpressionTest extends TestCase {
    private Expression exp1;
    private Expression exp3;
    private Expression exp2;
    private Expression exp4;
    SymbolTableImpl<String,Integer> symbolTable = new SymbolTableImpl<>();
    Heap<Integer,Integer>heap;
    public void setUp() throws Exception {
        heap = new HeapImpl<Integer,Integer>();
        exp1 = new VarExpression("a");
        exp2 = new VarExpression("b");
        exp3 = new VarExpression("aaaa");
        exp4 = new VarExpression("123");
        symbolTable.add("a",2);
        symbolTable.add("b",2);
        symbolTable.add("aaaa",2);
    }

    public void testEvaluate() throws Exception {
        assertEquals(exp1.evaluate(symbolTable,heap),2);
        assertEquals(exp2.evaluate(symbolTable,heap),2);
        assertEquals(exp3.evaluate(symbolTable,heap),2);
        try {
            assertEquals(exp4.evaluate(symbolTable,heap),3);
        }
        catch (Exception E){
            assert (true);
        }
    }

    public void testToString() throws Exception {

    }

}