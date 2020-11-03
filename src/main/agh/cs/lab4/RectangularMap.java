package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RectangularMap implements IWorldMap {
    final static private Vector2d boardLowerLeftCorner = new Vector2d(0 ,0);;
    final private Vector2d boardUpperRightCorner;
    final private MapVisualiser visualiser;
    private List<Animal> animals;

    public RectangularMap(int width, int height) {
        boardUpperRightCorner = new Vector2d(width, height);
        animals = new ArrayList<>();
        visualiser = new MapVisualiser(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (!position.precedes(boardUpperRightCorner)) {
            return false;
        }
        if (!position.follows(boardLowerLeftCorner)) {
            return false;
        }
        if (isOccupied(position)) {
            return false;
        }
        return true;
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
        for (Animal animal: animals) {
            if (animal.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
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
