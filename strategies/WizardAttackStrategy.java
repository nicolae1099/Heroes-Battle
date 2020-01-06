package strategies;

import heroes.Hero;

public final class WizardAttackStrategy implements Strategy {
    @Override
    public void applyTo(final Hero player) {
        player.hp = (int) (player.hp - (Constants.TENTH_OF * player.hp));
        player.strategyRaceMultiplier += Constants.SIXTY_PRECENT;
    }
}
