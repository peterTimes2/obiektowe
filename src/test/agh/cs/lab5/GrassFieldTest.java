package agh.cs.lab5;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import agh.cs.lab4.IWorldMap;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void canMoveTo() {
        IWorldMap map = new GrassField(10);
        map.place(new Animal(map,new Vector2d(3,4)));

        assertTrue(map.canMoveTo(new Vector2d(1, 1)));
        assertFalse(map.canMoveTo(new Vector2d(3, 4)));
    }

    @Test
    void place() {
        IWorldMap map = new GrassField(4);
        Animal animal = new Animal(map,new Vector2d(3,4));
        try {
            map.place(animal);
        } catch (IllegalArgumentException e) {
            fail("animal wasn't placed on map");
        }
        assertThrows(IllegalArgumentException.class, () -> map.place(animal));
    }

    @Test
    void run() {
        String[] testArgs = {
                "f", "b", "r", "l", "f",
                "f", "r", "r", "f", "f",
                "f", "f", "f", "f", "f", "f"
        };
        List<MoveDirection> directions = OptionsParser.parse(testArgs);
        IWorldMap map = new GrassField(10);
        Animal first = new Animal(map);
        Animal second = new Animal(map, new Vector2d(3, 4));

        map.place(first);
        map.place(second);
        map.run(directions);

        assertEquals(first.getPosition(), new Vector2d(2, -1));
        assertEquals(first.getOrientation(), MapDirection.SOUTH);
        assertEquals(second.getPosition(), new Vector2d(3, 7));
        assertEquals(second.getOrientation(), MapDirection.NORTH);
    }

    @Test
    void isOccupied() {
        IWorldMap map = new GrassField(0);
        map.place(new Animal(map,new Vector2d(3,4)));

        assertTrue(map.isOccupied(new Vector2d(3, 4)));
        assertFalse(map.isOccupied(new Vector2d(5, 5)));
    }

    @Test
    void objectAt() {
        IWorldMap map = new GrassField(0);
        Animal animal = new Animal(map,new Vector2d(3,4));
        map.place(animal);

        assertTrue(map.objectAt(new Vector2d(3, 4)).isPresent());
        assertEquals(map.objectAt(new Vector2d(3, 4)).get(), animal);
        assertTrue(map.objectAt(new Vector2d(5, 5)).isEmpty());
    }

    @Test
    void testToString() {
        IWorldMap map = new GrassField(0);
        Animal animal1 = new Animal(map, new Vector2d(1, 1));
        Animal animal2 = new Animal(map, new Vector2d(1, 2));
        Animal animal3 = new Animal(map, new Vector2d(2, 1));
        Animal animal4 = new Animal(map, new Vector2d(2, 2));
        animal2.setOrientation(MapDirection.SOUTH);
        animal3.setOrientation(MapDirection.WEST);
        animal4.setOrientation(MapDirection.EAST);
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        map.place(animal4);

        assertEquals(map.toString(),
            " y\\x  1 2\r\n" +
            "  3: -----\r\n" +
            "  2: |↓|→|\r\n" +
            "  1: |↑|←|\r\n" +
            "  0: -----\r\n"
        );
    }
}