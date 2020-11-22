package agh.cs.lab5;

import agh.cs.lab2.Vector2d;
import agh.cs.lab7.IPositionChangedPublisher;

public interface IMapElement extends IPositionChangedPublisher {
    Vector2d getPosition();
    void setPosition(Vector2d position);
    boolean isBlocking();
}
