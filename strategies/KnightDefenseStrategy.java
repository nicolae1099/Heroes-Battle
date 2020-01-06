package strategies;

import heroes.Constants;
import heroes.Hero;

public final class KnightDefenseStrategy implements Strategy {
    @Override
    public void applyTo(final Hero player) {
        player.hp +=  (heroes.Constants.QUARTER_OF * player.hp);
        player.strategyRaceMultiplier -= Constants.TWENTY_PERCENT;
    }
}
