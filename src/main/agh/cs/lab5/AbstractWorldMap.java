package agh.cs.lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualiser;

import java.util.*;
import java.util.stream.Stream;

public abstract class AbstractWorldMap implements IWorldMap {
    private final Vector2d boardLowerLeftCorner;
    private final Vector2d boardUpperRightCorner;
    protected final Map<Vector2d, List<IMapElement>> board;
    protected final MapVisualiser visualiser;
    protected final List<Animal> animals;


    public AbstractWorldMap(Vector2d boardLowerLeft, Vector2d boardUpperRight) {
        animals = new ArrayList<>();
        visualiser = new MapVisualiser(this);
        boardLowerLeftCorner = boardLowerLeft;
        boardUpperRightCorner = boardUpperRight;
        board = new HashMap<>();
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
        if (canMoveTo(animal.getPosition())) {
            animals.add(animal);
            putOnBoard(animal.getPosition(), animal);
            return;
        }
        throw new IllegalArgumentException("field " + animal.getPosition() + " is occupied or beyond map borders");
    }

    @Override
    public void run(List<MoveDirection> directions) {
        int animalIndex = 0;
        int animalsLength = animals.size();
        for (MoveDirection direction: directions) {
            Animal animal = animals.get(animalIndex);
            Vector2d oldAnimalPosition = animal.getPosition();
            animal.move(direction);
            animalIndex = (animalIndex + 1) % animalsLength;
            if (!animal.getPosition().equals(oldAnimalPosition)) {
                List<IMapElement> oldList = board.get(oldAnimalPosition);
                oldList.remove(animal);
                putOnBoard(animal.getPosition(), animal);
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public Optional<? extends IMapElement> objectAt(Vector2d position) {
        List<IMapElement> value = board.get(position);
        if (value == null || value.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(value.get(value.size() - 1));
    }

    @Override
    public String toString() {
        return visualiser.draw(boardLowerLeftCorner, boardUpperRightCorner);
    }

    @Override
    public void putOnBoard(Vector2d position, IMapElement element) {
        board.putIfAbsent(position, new LinkedList<>());
        board.get(position).add(element);
    }
}
