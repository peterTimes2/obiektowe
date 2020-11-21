package agh.cs.lab6;

import agh.cs.lab2.Vector2d;
import agh.cs.lab5.IMapElement;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Vector2d, List<IMapElement>> board;

    public Board () {
        this.board = new HashMap<>();
    }

    public void putOnBoard(Vector2d position, IMapElement element) {
        board.putIfAbsent(position, new LinkedList<>());
        board.get(position).add(element);
    }

    public List<IMapElement> getElements(Vector2d position) {
        return board.get(position);
    }
}
