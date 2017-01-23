package tests;

import domain.statements.AssignStmt;
import domain.expressions.ConstantExpression;
import domain.statements.Statement;
import junit.framework.TestCase;
import utils.ExecStackImpl;

/**
 * Created by glinut on 11/8/2016.
 */
public class ExecStackImplTest extends TestCase {
    ExecStackImpl<Statement> execStack = new ExecStackImpl<>();
    Statement statement = new AssignStmt(new ConstantExpression(2),"A");
    Statement statement1 = new AssignStmt(new ConstantExpression(3),"b");
    Statement statement2 = new AssignStmt(new ConstantExpression(4),"c");
    public void setUp() throws Exception {
        super.setUp();

    }

    public void testPush() throws Exception {
        execStack.push(statement);
        execStack.push(statement1);
        execStack.push(statement2);
        assertEquals(execStack.isEmpty(),false);
    }

    public void testIsEmpty() throws Exception {
        assertEquals(execStack.isEmpty(),true);
        execStack.push(statement1);
        assertEquals(execStack.isEmpty(),false);
    }

    public void testPop() throws Exception {
        execStack.push(statement1);
        Statement st = execStack.pop();
        assertEquals(st.getClass(),AssignStmt.class);
        assertEquals(execStack.isEmpty(),true);
    }

    public void testTop() throws Exception {
        execStack.push(statement1);
        Statement st = execStack.top();
        assertEquals(st.getClass(),AssignStmt.class);
        assertEquals(execStack.isEmpty(),false);
    }

    public void testToString() throws Exception {

    }

}