package tests;

import domain.ArithmeticExpression;
import domain.ConstantExpression;
import domain.Expression;
import junit.framework.TestCase;
import utils.Heap;
import utils.HeapImpl;
import utils.SymbolTableImpl;

/**
 * Created by glinut on 11/8/2016.
 */
public class ConstantExpressionTest extends TestCase {
    private Expression exp1;
    private Expression exp3;
    private Expression exp2;
    private Expression exp4;
    SymbolTableImpl<String,Integer> symbolTable;
    Heap<Integer,Integer>heap;
    public void setUp() throws Exception {
        heap = new HeapImpl<Integer,Integer>();
        exp1 = new ConstantExpression(3);
        exp2 = new ConstantExpression(212);
        exp3 = new ConstantExpression(12);
        exp4 = new ConstantExpression(-222);

    }

    public void testEvaluate() throws Exception {
        assertEquals(exp1.evaluate(symbolTable,heap),3);
        assertEquals(exp2.evaluate(symbolTable,heap),212);
        assertEquals(exp3.evaluate(symbolTable,heap),12);
        assertEquals(exp4.evaluate(symbolTable,heap),-222);
    }

    public void testToString() throws Exception {

    }
}