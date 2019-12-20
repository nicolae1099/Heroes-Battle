package abilities;

import hero.Knight;
import hero.Pyromancer;
import hero.Rogue;
import hero.Wizard;

public interface AmplifierByRace {
    float visit(Pyromancer pyromancer);
    float visit(Knight knight);
    float visit(Wizard wizard);
    float visit(Rogue rogue);
}
