package agh.cs.lab2;

import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("Vectors: ");
        printVectors();

        System.out.println("Map Directions: ");
        printMapDirections();

        System.out.println("Animal: ");
        walkAnimal(args);
    }

    private static void walkAnimal(String[] args) {
        Animal testAnimal = new Animal();
        System.out.println(testAnimal);

        List<MoveDirection> directionList = OptionsParser.parse(args);

        for (MoveDirection direction : directionList) {
            testAnimal.move(direction);
            System.out.println(testAnimal);
        }
    }

    private static void printVectors() {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
    }

    private static void printMapDirections() {
        MapDirection east = MapDirection.EAST;
        System.out.println(east.toString());
        System.out.println(east.next());
        System.out.println(east.previous());
        System.out.println(east.toUnitVector());
    }

}

