package abilities;

import hero.Knight;
import hero.Pyromancer;
import hero.Rogue;
import hero.Wizard;
import hero.Constants;

public class Backstab implements AmplifierByRace {
    public Backstab() {

    }

    @Override
    public final float visit(final Rogue rogue) {
        return Constants.BACKSTAB_VS_ROGUE;
    }

    @Override
    public final float visit(final Knight knight) {
        return Constants.BACKSTAB_VS_KNIGHT;
    }

    @Override
    public final float visit(final Pyromancer pyromancer) {
        return Constants.BACKSTAB_VS_PYRO;
    }

    @Override
    public final float visit(final Wizard wizard) {
        return Constants.BACKSTAB_VS_WIZARD;
    }
}
