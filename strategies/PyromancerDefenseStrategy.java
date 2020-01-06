package strategies;

import heroes.Hero;

public final class PyromancerDefenseStrategy implements Strategy {
    @Override
    public void applyTo(final Hero player) {
        player.strategyRaceMultiplier -= Constants.THIRTY_PRECENT;
        player.hp = (int) (player.hp + (Constants.THIRD_OF * player.hp));
    }
}
