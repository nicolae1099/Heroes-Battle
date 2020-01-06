package strategies;


import heroes.Hero;

public final class PyromancerAttackStrategy implements Strategy {
    @Override
    public void applyTo(final Hero player) {
        player.hp = (int) (player.hp - (heroes.Constants.QUARTER_OF * player.hp));
        player.strategyRaceMultiplier += Constants.SEVENTY_PRECENT;
    }
}
