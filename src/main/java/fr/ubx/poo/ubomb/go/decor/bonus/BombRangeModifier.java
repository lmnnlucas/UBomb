package fr.ubx.poo.ubomb.go.decor.bonus;

import fr.ubx.poo.ubomb.game.Position;
import fr.ubx.poo.ubomb.go.character.Player;

public class BombRangeModifier extends Bonus {
    private int rangeModifier;

    public BombRangeModifier(Position position, int rangeModifier) {
        super(position);
        this.rangeModifier = rangeModifier;
    }

    public int getRangeModifier() {
        return rangeModifier;
    }

    @Override
    public void takenBy(Player player) {
        player.take(this);
    }
}
