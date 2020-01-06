package strategies;

import heroes.Hero;

public final class RogueDefenseStrategy implements Strategy {
    @Override
    public void applyTo(final Hero player) {
        player.strategyRaceMultiplier -= Constants.TEN_PERCENT;
        player.hp = (int) (player.hp + (Constants.HALF_OF * player.hp));
    }
}
