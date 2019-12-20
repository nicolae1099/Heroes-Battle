package abilities;

import hero.Knight;
import hero.Pyromancer;
import hero.Rogue;
import hero.Wizard;
import hero.Constants;

public class Paralysis implements AmplifierByRace {
    public Paralysis() {

    }

    @Override
    public final float visit(final Rogue rogue) {
        return Constants.PARALYSIS_VS_ROGUE;
    }

    @Override
    public final float visit(final Knight knight) {
        return Constants.PARALYSIS_VS_KNIGHT;
    }

    @Override
    public final float visit(final Pyromancer pyromancer) {
        return Constants.PARALYSIS_VS_PYRO;
    }

    @Override
    public final float visit(final Wizard wizard) {
        return Constants.PARALYSIS_VS_WIZARD;
    }
}
