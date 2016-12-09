package tests;
import domain.ArithmeticExpression;
import domain.ConstantExpression;
import domain.Expression;
import junit.framework.TestCase;
import utils.Heap;
import utils.HeapImpl;
import utils.SymbolTable;
import utils.SymbolTableImpl;

/**
 * Created by glinut on 11/4/2016.
 */
public class ArithmeticExpressionTest extends TestCase {

    private Expression exp1;
    private Expression exp3;
    private Expression exp2;
    private Expression exp4;
    SymbolTableImpl<String,Integer> symbolTable;
    Heap<Integer,Integer>heap;

    public void setUp() throws Exception {
        symbolTable = new SymbolTableImpl<>();
        heap = new HeapImpl<Integer,Integer>();
        exp1 = new ArithmeticExpression('-', new ConstantExpression(3),new ConstantExpression(1));
        exp2 = new ArithmeticExpression('*', new ConstantExpression(3),new ConstantExpression(2));
        exp3 = new ArithmeticExpression('+', new ConstantExpression(2),new ConstantExpression(1));
        exp4 = new ArithmeticExpression('/', new ConstantExpression(3),new ConstantExpression(1));
        exp4 = new ArithmeticExpression('/', new ConstantExpression(1),new ConstantExpression(0));
    }

    public void testEvaluate() {
        assertEquals(exp1.evaluate(symbolTable,heap),2);
        assertEquals(exp2.evaluate(symbolTable,heap),6);
        assertEquals(exp3.evaluate(symbolTable,heap),3);
        assertEquals(exp3.evaluate(symbolTable,heap),3);
        try{
            exp4.evaluate(symbolTable,heap);
            assert(false);
        }
        catch (ArithmeticException e){
            assert(true);
        }

    }

    public void testToString() throws Exception {

    }

}