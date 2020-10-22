package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    @DisplayName("Switching directions to left and right")
    void moveTest1() {
        Animal testAnimal = new Animal();
        testAnimal.setOrientation(MapDirection.NORTH);

        testAnimal.move(MoveDirection.RIGHT);
        assertTrue(testAnimal.getOrientation().equals(MapDirection.EAST));

        testAnimal.move(MoveDirection.LEFT);
        assertTrue(testAnimal.getOrientation().equals(MapDirection.NORTH));
    }

    @Test
    @DisplayName("Moving forward and backwards")
    void moveTest2() {
        Animal testAnimal = new Animal();
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
        Animal testAnimal = new Animal();
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