package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.RectangularMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    @DisplayName("Switching directions to left and right")
    void moveTest1() {
        IWorldMap map = new RectangularMap(10, 5);
        Animal testAnimal = new Animal(map);
        testAnimal.setOrientation(MapDirection.NORTH);

        testAnimal.move(MoveDirection.RIGHT);
        assertEquals(testAnimal.getOrientation(), MapDirection.EAST);

        testAnimal.move(MoveDirection.LEFT);
        assertEquals(testAnimal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    @DisplayName("Moving forward and backwards")
    void moveTest2() {
        IWorldMap map = new RectangularMap(10, 5);
        Animal testAnimal = new Animal(map);
        testAnimal.setPosition( new Vector2d(2, 2));
        testAnimal.setOrientation(MapDirection.SOUTH);

        testAnimal.move(MoveDirection.FORWARD);
        assertTrue(testAnimal.getPosition().equals(new Vector2d(2 ,1)));

        testAnimal.move(MoveDirection.BACKWARD);
        assertTrue(testAnimal.getPosition().equals(new Vector2d(2 ,2)));
    }

    @Test
    @DisplayName("Cannot move outside of the board")
    void moveTest3() {
        IWorldMap map = new RectangularMap(5, 5);
        Animal testAnimal = new Animal(map);
        testAnimal.setPosition( new Vector2d(5, 2));
        testAnimal.setOrientation(MapDirection.EAST);

        testAnimal.move(MoveDirection.FORWARD);
        assertTrue(testAnimal.getPosition().equals(new Vector2d(5 ,2)));

        // test 2
        testAnimal.setPosition( new Vector2d(3, 0));
        testAnimal.setOrientation(MapDirection.SOUTH);

        testAnimal.move(MoveDirection.FORWARD);
        assertTrue(testAnimal.getPosition().equals(new Vector2d(3 ,0)));
    }
}