package agh.cs.lab4;
import agh.cs.lab2.Vector2d;
import agh.cs.lab5.AbstractWorldMap;
import agh.cs.lab5.IMapElement;

import java.util.stream.Stream;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {
    public RectangularMap(int width, int height) {
        super(new Vector2d(0 ,0), new Vector2d(width - 1, height - 1));
    }

    @Override
    protected Stream<? extends IMapElement> getMapElementsStream() {
        return animals.stream();
    }
}
