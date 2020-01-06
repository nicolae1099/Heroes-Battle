package strategies;

import heroes.Hero;

public final class RogueAttackStrategy implements Strategy {
    @Override
    public void applyTo(final Hero player) {
        player.hp = (int) (player.hp  - (Constants.SEVENTH_OF * player.hp));
        player.strategyRaceMultiplier += Constants.FORTY_PRECENT;
    }
}
