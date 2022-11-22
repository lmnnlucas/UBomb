package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Position;

public class Bomb extends Decor {

    private int range;

    private Timer currentTimer;

    public Bomb(Position position,Timer timer, int range) {
        super(position);
        this.range = range;
        this.currentTimer = timer;
    }

    @Override
    public void explode() {
        super.explode();
    }
}
