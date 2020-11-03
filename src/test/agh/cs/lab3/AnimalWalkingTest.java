package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.RectangularMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalWalkingTest {

    @Test
    @DisplayName("Whole animal walking process")
    void test() {
        IWorldMap map = new RectangularMap(10, 5);
        Animal testAnimal = new Animal(map);
        testAnimal.setPosition(new Vector2d(2, 2));
        testAnimal.setOrientation(MapDirection.NORTH);
        Vector2d expectedFinalPosition = new Vector2d(2, 2);

        // args are parsed correctly and animal moves as expected
        String[] mockArgs = {
                "l", "forward", "wrongOne", "r",
                "backward", "unexpectedOne", "b",
                "right", "f", "left",
        };
        List<MoveDirection> directions =  OptionsParser.parse(mockArgs);
        for (MoveDirection direction : directions) {
            testAnimal.move(direction);
        }
        expectedFinalPosition = new Vector2d(2,0);
        assertTrue(testAnimal.getOrientation() == MapDirection.NORTH);
        assertTrue(testAnimal.getPosition().equals(expectedFinalPosition));

        // animal doesn't move outside of the board
        mockArgs = new String[] {"b", "backward"};
        directions =  OptionsParser.parse(mockArgs);
        for (MoveDirection direction : directions) {
            testAnimal.move(direction);
        }
        expectedFinalPosition = new Vector2d(2,0);
        assertTrue(testAnimal.getPosition().equals(expectedFinalPosition));
    }
}
