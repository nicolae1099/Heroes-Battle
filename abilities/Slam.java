package abilities;

import hero.Knight;
import hero.Pyromancer;
import hero.Rogue;
import hero.Wizard;
import hero.Constants;

public class Slam implements AmplifierByRace {
    public Slam() {

    }
    @Override
    public final float visit(final Rogue rogue) {
        return Constants.SLAM_VS_ROGUE;
    }

    @Override
    public final float visit(final Knight knight) {
        return Constants.SLAM_VS_KNIGHT;
    }

    @Override
    public final float visit(final Pyromancer pyromancer) {
        return Constants.SLAM_VS_PYRO;
    }

    @Override
    public final float visit(final Wizard wizard) {
        return Constants.SLAM_VS_WIZARD;
    }
}
