package agh.cs.lab5;

import agh.cs.lab2.Vector2d;

public class Grass extends AbstractMapElement {
    public Grass(Vector2d position) {
        super(position);
    }

    @Override
    public boolean isBlocking() {
        return false;
    }

    @Override
    public String toString() {
        return "*";
    }
}
