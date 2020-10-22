package agh.cs.lab2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void next() {
        MapDirection north = MapDirection.NORTH;
        MapDirection south = MapDirection.SOUTH;
        MapDirection east = MapDirection.EAST;
        MapDirection west = MapDirection.WEST;

        assertEquals(north.next(), east);
        assertEquals(south.next(), west);
        assertEquals(east.next(), south);
        assertEquals(west.next(), north);
    }

    @Test
    void previous() {
        MapDirection north = MapDirection.NORTH;
        MapDirection south = MapDirection.SOUTH;
        MapDirection east = MapDirection.EAST;
        MapDirection west = MapDirection.WEST;

        assertEquals(north.previous(), west);
        assertEquals(south.previous(), east);
        assertEquals(east.previous(), north);
        assertEquals(west.previous(), south);
    }
}