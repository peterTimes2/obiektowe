package agh.cs.lab5;

import agh.cs.lab2.Vector2d;

abstract public class AbstractMapElement implements IMapElement {
    protected Vector2d position;

    public AbstractMapElement(Vector2d position) {
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2d position) {
        this.position = position;
    }

    @Override
    abstract public boolean isBlocking();
}
