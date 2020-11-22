package agh.cs.lab7;

import agh.cs.lab2.Vector2d;
import agh.cs.lab5.IMapElement;

public interface IPositionChangeObserver {
    void handlePositionChange(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition);
}
