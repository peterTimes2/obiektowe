package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualiser;
import agh.cs.lab6.Board;
import agh.cs.lab7.IPositionChangeObserver;
import agh.cs.lab7.MapBoundary;

import java.util.*;
import java.util.stream.Stream;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    private final Vector2d boardLowerLeftCorner;
    private final Vector2d boardUpperRightCorner;
    private final Board board;
    private final MapBoundary boundary;
    protected final MapVisualiser visualiser;
    protected final List<Animal> animals;


    public AbstractWorldMap(Vector2d boardLowerLeft, Vector2d boardUpperRight) {
        animals = new ArrayList<>();
        visualiser = new MapVisualiser(this);
        boardLowerLeftCorner = boardLowerLeft;
        boardUpperRightCorner = boardUpperRight;
        board = new Board();
        boundary = new MapBoundary();
    }

    // returns stream from every object at the map
    abstract protected Stream<? extends IMapElement> getMapElementsStream();

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(boardUpperRightCorner)
            && position.follows(boardLowerLeftCorner)
            && !isOccupied(position);
    }

    @Override
    public void place(Animal animal) {
        placeElement(animal);
        animals.add(animal);
    }

    @Override
    public void placeElement(IMapElement element) {
        if (canMoveTo(element.getPosition())) {
            board.putOnBoard(element.getPosition(), element);
            boundary.addElement(element);
            element.addObserver(boundary);
            element.addObserver(this);
            return;
        }
        throw new IllegalArgumentException("field " + element.getPosition() + " is occupied or beyond map borders");
    }

    @Override
    public void run(List<MoveDirection> directions) {
        int animalIndex = 0;
        int animalsLength = animals.size();
        for (MoveDirection direction: directions) {
            Animal animal = animals.get(animalIndex);
            animal.move(direction);
            animalIndex = (animalIndex + 1) % animalsLength;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public Optional<? extends IMapElement> objectAt(Vector2d position) {
        List<IMapElement> value = board.getElements(position);
        if (value == null || value.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(value.get(value.size() - 1));
    }

    @Override
    public String toString() {
        return visualiser.draw(boundary.getLowerLeft(), boundary.getUpperRight());
    }

    public void handlePositionChange(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition) {
        List<IMapElement> oldList = board.getElements(oldPosition);
        oldList.remove(movedElement);
        board.putOnBoard(newPosition, movedElement);
    }
}
