package effects;
import heroes.Hero;

public class Stun implements Effects {
    private int duration;
    public Stun(final int duration) {
        this.duration = duration;
    }
    @Override
    public final void apply(final Hero defender) {
        defender.stunDuration = duration;
        defender.stun = true;
    }
}
