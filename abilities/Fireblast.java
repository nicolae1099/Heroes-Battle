package abilities;

import hero.Knight;
import hero.Pyromancer;
import hero.Rogue;
import hero.Wizard;
import hero.Constants;

public class Fireblast implements AmplifierByRace {
    public Fireblast() {

    }

    @Override
    public final float visit(final Rogue rogue) {
        return Constants.FIREBLAST_VS_ROGUE;
    }

    @Override
    public final float visit(final Knight knight) {
        return Constants.FIREBLAST_VS_KNIGHT;
    }

    @Override
    public final float visit(final Pyromancer pyromancer) {
        return Constants.FIREBLAST_VS_PYRO;
    }

    @Override
    public final float visit(final Wizard wizard) {
        return Constants.FIREBLAST_VS_WIZARD;
    }

}
