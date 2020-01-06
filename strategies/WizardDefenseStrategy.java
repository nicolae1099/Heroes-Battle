package strategies;

import heroes.Hero;

public final class WizardDefenseStrategy implements Strategy {
    @Override
    public void applyTo(final Hero player) {
        player.strategyRaceMultiplier -= Constants.TWENTY_PERCENT;
        player.hp = (int) (player.hp + (Constants.FIFTH_OF * player.hp));
    }
}
