package abilities;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import heroes.Constants;

public class Execute implements AmplifierByRace {
    public Execute() {

    }

    @Override
    public final float visit(final Rogue rogue) {
        return Constants.EXECUTE_VS_ROGUE;
    }

    @Override
    public final float visit(final Knight knight) {
        return Constants.EXECUTE_VS_KNIGHT;
    }

    @Override
    public final float visit(final Pyromancer pyromancer) {
        return Constants.EXECUTE_VS_PYRO;
    }

    @Override
    public final float visit(final Wizard wizard) {
        return Constants.EXECUTE_VS_WIZARD;
    }

}
