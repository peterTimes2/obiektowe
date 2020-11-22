package agh.cs.lab5;

import agh.cs.lab2.Vector2d;
import agh.cs.lab7.IPositionChangeObserver;
import java.util.LinkedList;
import java.util.List;

abstract public class AbstractMapElement implements IMapElement {
    protected Vector2d position;
    private final List<IPositionChangeObserver> positionObservers;

    public AbstractMapElement(Vector2d position) {
        this.position = position;
        this.positionObservers = new LinkedList<>();
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2d position) {
        notifyPositionObservers(this.position, position);
        this.position = position;
    }

    @Override
    abstract public boolean isBlocking();

    @Override
    public void addObserver(IPositionChangeObserver observer) {
        positionObservers.add(observer);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        positionObservers.remove(observer);
    }

    private void notifyPositionObservers(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer: positionObservers) {
            observer.handlePositionChange(this, oldPosition, newPosition);
        }
    }
}
