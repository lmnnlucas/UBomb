package fr.ubx.poo.ubomb.game;

public record Position (int x, int y) {

    public Position(Position position) {
        this(position.x, position.y);
    }

    public Position(String s) {
        this(Integer.parseInt(s.split("x")[0]),Integer.parseInt(s.split("x")[1]));
    }
}
