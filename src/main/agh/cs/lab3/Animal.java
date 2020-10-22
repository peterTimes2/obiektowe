package agh.cs.lab3;
import agh.cs.lab2.*;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);
    final private Vector2d boardUpperRightCorner = new Vector2d(5, 5);
    final private Vector2d boardLowerLeftCorner = new Vector2d(0 ,0 );

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
        return "position: " + this.position + ", orientation: " + this.orientation;
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
                handleMove(true);            }
        }
    }

    private void handleMove(boolean isBackward) {
        Vector2d moveVector = orientation.toUnitVector();
        if (isBackward) {
            moveVector = moveVector.opposite();
        }

        Vector2d nextPosition = position.add(moveVector);
        if (nextPosition.precedes(boardUpperRightCorner) && nextPosition.follows(boardLowerLeftCorner)) {
            setPosition(nextPosition);
        }
    }
}
