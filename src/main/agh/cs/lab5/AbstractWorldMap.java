package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualiser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class AbstractWorldMap implements IWorldMap {
    protected final Vector2d boardLowerLeftCorner;
    protected final Vector2d boardUpperRightCorner;
    protected final MapVisualiser visualiser;
    protected final List<IMapElement> animals;

    public AbstractWorldMap(Vector2d boardLowerLeft, Vector2d boardUpperRight) {
        animals = new ArrayList<>();
        visualiser = new MapVisualiser(this);
        boardLowerLeftCorner = boardLowerLeft;
        boardUpperRightCorner = boardUpperRight;
    }

    // returns stream from every object at the map
    abstract protected Stream<IMapElement> getMapElementsStream();

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(boardUpperRightCorner)
            && position.follows(boardLowerLeftCorner)
            && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public void run(List<MoveDirection> directions) {
        int animalIndex = 0;
        int animalsLength = animals.size();
        for (MoveDirection direction: directions) {
            ((Animal) animals.get(animalIndex)).move(direction);
            animalIndex = (animalIndex + 1) % animalsLength;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public Optional<IMapElement> objectAt(Vector2d position) {
        return getMapElementsStream()
                .filter((IMapElement mapElement) -> (mapElement.getPosition().equals(position)))
                .findFirst();
    }

    @Override
    public String toString() {
        return visualiser.draw(boardLowerLeftCorner, boardUpperRightCorner);
    }
}
