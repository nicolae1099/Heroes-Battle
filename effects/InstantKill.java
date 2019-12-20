package effects;
import hero.Hero;

public final class InstantKill implements Effects {
    @Override
    public void apply(final Hero defender) {
        defender.hp = 0;
    }
}
