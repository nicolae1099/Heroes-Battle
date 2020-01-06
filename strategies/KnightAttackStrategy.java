package strategies;

import heroes.Hero;

public final class KnightAttackStrategy implements Strategy {
    @Override
    public void applyTo(final Hero player) {
        player.hp =  (player.hp - Math.round(Constants.FIFTH_OF * player.hp));
        player.strategyRaceMultiplier += Constants.FIFTY_PRECENT;
    }
}
