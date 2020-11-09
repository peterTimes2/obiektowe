package agh.cs.lab4;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapIntegrationTest {
    @Test
    void runningMap() {
        String[] testArgs = {
                "f", "b", "r", "l", "f",
                "f", "r", "r", "f", "f",
                "f", "f", "f", "f", "f", "f"
        };
        List<MoveDirection> directions = OptionsParser.parse(testArgs);
        IWorldMap map = new RectangularMap(10, 5);
        Animal first = new Animal(map);
        Animal second = new Animal(map, new Vector2d(3, 4));

        map.place(first);
        map.place(second);
        map.run(directions);

        assertEquals(first.getPosition(), new Vector2d(2, 0));
        assertEquals(first.getOrientation(), MapDirection.SOUTH);
        assertEquals(second.getPosition(), new Vector2d(3, 4));
        assertEquals(second.getOrientation(), MapDirection.NORTH);
    }
}
