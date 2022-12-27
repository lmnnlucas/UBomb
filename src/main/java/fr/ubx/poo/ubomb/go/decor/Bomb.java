package fr.ubx.poo.ubomb.go.decor;

import fr.ubx.poo.ubomb.engine.Timer;
import fr.ubx.poo.ubomb.game.Game;
import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.Takeable;

public class Bomb extends Decor implements Takeable {
    private Timer timer;

    private int range;

    public Bomb(Game game, Position position) {
        super(game, position);
        timer = new Timer(3000);
        timer.start();
        range = game.player().getBombRange();
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public void explode() {
        game.player().postExplosionTreatment(this);
        this.remove();
    }
}
