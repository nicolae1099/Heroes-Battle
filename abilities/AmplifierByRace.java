package abilities;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public interface AmplifierByRace {
    float visit(Pyromancer pyromancer);
    float visit(Knight knight);
    float visit(Wizard wizard);
    float visit(Rogue rogue);
}
