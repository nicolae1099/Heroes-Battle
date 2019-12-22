package effects;

import heroes.Hero;

public class DamageOverTime implements Effects {
    private int damage;
    private int duration;
    public DamageOverTime(final int damage, final int duration) {
        this.damage = damage;
        this.duration = duration;
    }

    @Override
    public final void apply(final Hero defender) {
        defender.dmgToBeTaken = damage;
        defender.stunDuration = duration;
        defender.dotDuration = duration;
    }
}
