package agh.cs.lab3;

import agh.cs.lab2.MoveDirection;
import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
    static public List<MoveDirection> parse(String[] rawDirections) {
        List<MoveDirection> result = new LinkedList();

        for (String dir : rawDirections) {
            switch (dir) {
                case "f", "forward" -> {
                    result.add(MoveDirection.FORWARD);
                }
                case "b", "backward" -> {
                    result.add(MoveDirection.BACKWARD);
                }
                case "l", "left" -> {
                    result.add(MoveDirection.LEFT);
                }
                case "r", "right" -> {
                    result.add(MoveDirection.RIGHT);
                }
            }
        }

        return result;
    }
}
