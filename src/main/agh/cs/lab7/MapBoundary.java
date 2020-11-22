package agh.cs.lab7;

import agh.cs.lab2.Vector2d;
import agh.cs.lab5.IMapElement;
import java.util.LinkedList;
import java.util.List;

public class MapBoundary implements IPositionChangeObserver {
    private final List<Vector2d> sortedX;
    private final List<Vector2d> sortedY;

    public MapBoundary() {
        sortedX = new LinkedList<>();
        sortedY = new LinkedList<>();
    }

    public void addElement(IMapElement element) {
        Vector2d position = element.getPosition();
        sortedX.add(indexOfFirstBigger(position, false), position);
        sortedY.add(indexOfFirstBigger(position, true), element.getPosition());
    }

    public Vector2d getLowerLeft() {
        return new Vector2d(sortedX.get(0).x, sortedY.get(0).y);
    }

    public Vector2d getUpperRight() {
        return new Vector2d(sortedX.get(sortedX.size() - 1).x, sortedY.get(sortedY.size() - 1).y);
    }

    private int indexOfFirstBigger(Vector2d elementPosition, boolean useYCoordinates) {
        if (useYCoordinates) {
            int i = 0;
            for (Vector2d position: sortedY) {
                if(position.y < elementPosition.y ) {
                    i += 1;
                }
            }
            return i;
        }
        int i = 0;
        for (Vector2d position: sortedX) {
            if (position.x < elementPosition.x) {
                i += 1;
            }
        }
        return i;
    }

    @Override
    public void handlePositionChange(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition) {
        sortedX.remove(oldPosition);
        sortedX.add(indexOfFirstBigger(newPosition, false), newPosition);
        sortedY.remove(oldPosition);
        sortedY.add(indexOfFirstBigger(newPosition, true), newPosition);
    }
}
