package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RectangularMap implements IWorldMap {
    static private final Vector2d boardLowerLeftCorner = new Vector2d(0 ,0);
    private final Vector2d boardUpperRightCorner;
    private final MapVisualiser visualiser;
    private final List<Animal> animals;

    public RectangularMap(int width, int height) {
        boardUpperRightCorner = new Vector2d(width, height);
        animals = new ArrayList<>();
        visualiser = new MapVisualiser(this);
    }

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
            animals.get(animalIndex).move(direction);
            animalIndex = (animalIndex + 1) % animalsLength;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        for (Animal animal: animals) {
            if(animal.getPosition().equals(position)) {
                return Optional.of(animal);
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return visualiser.draw(boardLowerLeftCorner, boardUpperRightCorner);
    }
}
