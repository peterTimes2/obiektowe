package agh.cs.lab5;

import agh.cs.lab2.Vector2d;
import agh.cs.lab4.IWorldMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private final List<IMapElement> grasses;

    public GrassField(int grassCount) {
        super(new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE), new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE));
        this.grasses = new ArrayList<>();
        plantGrassSeeds(grassCount);
    }

    private void plantGrassSeeds(int grassCount) {
        for (int i = 0; i < grassCount; i++) {
            int randomX = (int)(Math.random() * (StrictMath.sqrt(grassCount * 10) + 1));
            int randomY = (int)(Math.random() * (StrictMath.sqrt(grassCount * 10) + 1));
            Vector2d randomPosition = new Vector2d(randomX, randomY);

            while (isOccupied(randomPosition)) {
                randomX = (int)(Math.random() * (StrictMath.sqrt(grassCount * 10) + 1));
                randomY = (int)(Math.random() * (StrictMath.sqrt(grassCount * 10) + 1));
                randomPosition = new Vector2d(randomX, randomY);
            }
            grasses.add(new Grass(randomPosition));
        }
    }

    @Override
    protected Stream<IMapElement> getMapElementsStream() {
        return Stream.concat(animals.stream(), grasses.stream());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        //noinspection OptionalGetWithoutIsPresent
        return super.canMoveTo(position) || objectAt(position).get() instanceof Grass;
    }

    @Override
    public String toString() {
        Vector2d lowerLeft;
        Vector2d upperRight;

        Optional<IMapElement> first = getMapElementsStream().findFirst();
        if (first.isPresent()) {
            lowerLeft = first.get().getPosition();
            upperRight = lowerLeft;
        } else {
            lowerLeft = new Vector2d(0, 0);
            upperRight = new Vector2d(0, 0);
        }

        Iterator<IMapElement> elementsIterator = getMapElementsStream().iterator();
        while (elementsIterator.hasNext()) {
            Vector2d position = elementsIterator.next().getPosition();
            if (!position.follows(lowerLeft)) {
                lowerLeft = new Vector2d(Math.min(position.x, lowerLeft.x), Math.min(position.y, lowerLeft.y));
            }
            if (!position.precedes(upperRight)) {
                upperRight = new Vector2d(Math.max(position.x, upperRight.x), Math.max(position.y, upperRight.y));
            }
        }

        return visualiser.draw(lowerLeft, upperRight);
    }
}
