package fr.ubx.poo.ubomb.go.decor.bonus;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public class Princess extends Bonus {
    public Princess(Position position) {
        super(position);
    }

    @Override
    public void takenBy(Player player) {
        player.take(this);
    }

    @Override
    public void explode() {}
}
