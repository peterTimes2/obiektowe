package agh.cs.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void testToString() {
        Vector2d testVector = new Vector2d(1,2);

        assertEquals(testVector.toString(), "(1,2)");
    }

    @Test
    void testEquals() {
        Vector2d testVector = new Vector2d(1,2);
        Vector2d testVector2 = new Vector2d(1,2);
        Vector2d testVector3 = new Vector2d(3,4);

        assertTrue(testVector.equals(testVector2));
        assertTrue(!testVector.equals(testVector3));
    }

    @Test
    void precedes() {
        Vector2d testVector = new Vector2d(1,2);
        Vector2d testVector2 = new Vector2d(1,2);
        Vector2d testVector3 = new Vector2d(3,4);

        assertTrue(testVector.precedes(testVector2));
        assertTrue(testVector.precedes(testVector3));
        assertTrue(!testVector3.precedes(testVector));
    }

    @Test
    void follows() {
        Vector2d testVector = new Vector2d(1,2);
        Vector2d testVector2 = new Vector2d(1,2);
        Vector2d testVector3 = new Vector2d(3,4);

        assertTrue(testVector2.follows(testVector));
        assertTrue(testVector3.follows(testVector));
        assertTrue(!testVector.follows(testVector3));
    }

    @Test
    void upperRight() {
        Vector2d testVector = new Vector2d(1,2);
        Vector2d testVector2 = new Vector2d(3,4);

        assertTrue(testVector.upperRight(testVector2).equals(new Vector2d(3, 4)));
    }

    @Test
    void lowerLeft() {
        Vector2d testVector = new Vector2d(1,2);
        Vector2d testVector2 = new Vector2d(3,4);

        assertTrue(testVector.lowerLeft(testVector2).equals(new Vector2d(1, 2)));
    }

    @Test
    void add() {
        Vector2d testVector = new Vector2d(1,2);
        Vector2d testVector2 = new Vector2d(3,4);

        assertTrue(testVector.add(testVector2).equals(new Vector2d(4, 6)));
    }

    @Test
    void subtract() {
        Vector2d testVector = new Vector2d(1,2);
        Vector2d testVector2 = new Vector2d(3,4);

        assertTrue(testVector.subtract(testVector2).equals(new Vector2d(-2, -2)));
    }

    @Test
    void opposite() {
        Vector2d testVector = new Vector2d(1,2);

        assertTrue(testVector.opposite().equals(new Vector2d(-1, -2)));
    }
}