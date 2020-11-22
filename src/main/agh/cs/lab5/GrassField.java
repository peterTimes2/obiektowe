package agh.cs.lab5;

import agh.cs.lab2.Vector2d;

import java.util.*;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap {
    private final List<Grass> grasses;

    public GrassField(int grassCount) {
        super(new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE), new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE));
        this.grasses = new LinkedList<>();
        plantGrassSeeds(grassCount);
    }

    private void plantGrassSeeds(int grassCount) {
        for (int i = 0; i < grassCount; i++) {
            Vector2d randomPosition = generateRandomPosition(grassCount);
            while (isOccupied(randomPosition)) {
                randomPosition = generateRandomPosition(grassCount);
            }
            Grass grass = new Grass(randomPosition);
            grasses.add(grass);
            placeElement(grass);
        }
    }

    private Vector2d generateRandomPosition(int grassCount) {
        int randomX = (int)(Math.random() * (StrictMath.sqrt(grassCount * 10) + 1));
        int randomY = (int)(Math.random() * (StrictMath.sqrt(grassCount * 10) + 1));
        return new Vector2d(randomX, randomY);
    }

    @Override
    protected Stream<? extends IMapElement> getMapElementsStream() {
        return Stream.concat(animals.stream(), grasses.stream());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        //noinspection OptionalGetWithoutIsPresent
        return super.canMoveTo(position) || !objectAt(position).get().isBlocking();
    }
}
