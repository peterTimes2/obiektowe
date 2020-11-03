package agh.cs.lab3;
import agh.cs.lab2.*;
import agh.cs.lab4.IWorldMap;

public class Animal {
    final private IWorldMap map;
    private MapDirection orientation;
    private Vector2d position;

    public Animal(IWorldMap map) {
        this.map = map;
        this.position = new Vector2d(2,2);
        this.orientation = MapDirection.NORTH;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        this.orientation = MapDirection.NORTH;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public void setOrientation(MapDirection orientation) {
        this.orientation = orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return switch(orientation) {
            case NORTH -> "↑";
            case SOUTH -> "↓";
            case WEST -> "←";
            case EAST -> "→";
        };
    };

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT -> {
                setOrientation(orientation.previous());
            }
            case RIGHT -> {
                setOrientation(orientation.next());
            }
            case FORWARD -> {
                handleMove(false);
            }
            case BACKWARD -> {
                handleMove(true);
            }
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
