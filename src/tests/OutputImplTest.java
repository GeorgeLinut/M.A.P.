package tests;

import junit.framework.TestCase;
import utils.Output;
import utils.OutputImpl;

import java.util.ArrayList;

/**
 * Created by glinut on 11/8/2016.
 */
public class OutputImplTest extends TestCase {
    OutputImpl<Integer> output = new OutputImpl<>();

    public void testAdd() throws Exception {
        output.add(1);
        output.add(2);
        output.add(3);
        assertEquals(output.isEmpty(),false);
    }

    public void testIsEmpty() throws Exception {
        assertEquals(output.isEmpty(),true);
        output.add(1);
        assertEquals(output.isEmpty(),false);
    }

    public void testGetAll() throws Exception {
        output.add(1);
        output.add(2);
        ArrayList<Integer>integers=output.getAll();
        assertEquals(integers.get(0),(Integer)1);
        assertEquals(integers.get(1),(Integer)2);
    }


    public void testToString() throws Exception {

    }
}