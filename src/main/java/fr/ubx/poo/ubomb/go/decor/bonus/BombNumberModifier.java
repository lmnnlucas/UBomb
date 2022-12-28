package fr.ubx.poo.ubomb.go.decor.bonus;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public class BombNumberModifier extends Bonus {

    private int modifier;

    public BombNumberModifier(Position position, int modifier) {
        super(position);
        this.modifier = modifier;
    }

    public int getModifier() {
        return modifier;
    }

    @Override
    public void takenBy(Player player) {
        player.take(this);
    }
}
