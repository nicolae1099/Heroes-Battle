package abilities;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import heroes.Constants;

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
