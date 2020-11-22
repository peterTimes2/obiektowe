package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab5.AbstractMapElement;

public class Animal extends AbstractMapElement {
    private final IWorldMap map;
    private MapDirection orientation;

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2,2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        super(initialPosition);
        this.map = map;
        this.orientation = MapDirection.NORTH;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public void setOrientation(MapDirection orientation) {
        this.orientation = orientation;
    }

    @Override
    public boolean isBlocking() {
        return true;
    }

    @Override
    public String toString() {
        return switch(orientation) {
            case NORTH -> "↑";
            case SOUTH -> "↓";
            case WEST -> "←";
            case EAST -> "→";
        };
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT -> setOrientation(orientation.previous());
            case RIGHT -> setOrientation(orientation.next());
            case FORWARD -> handleMove(false);
            case BACKWARD -> handleMove(true);
        }
    }

    private void handleMove(boolean isBackward) {
        Vector2d moveVector = orientation.toUnitVector();
        if (isBackward) {
            moveVector = moveVector.opposite();
        }

        Vector2d nextPosition = position.add(moveVector);
        if (map.canMoveTo(nextPosition)) {
            setPosition(nextPosition);
        }
    }
}
