package agh.cs.lab2;

import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab5.GrassField;

import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            IWorldMap map = new GrassField(10);
            List<MoveDirection> directions = OptionsParser.parse(args);
            map.place(new Animal(map));
            map.place(new Animal(map));
            System.out.println(map);
            map.run(directions);
            System.out.println(map);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}

