package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public interface Angel {
    void visit(Knight knight);
    void visit(Pyromancer pyromancer);
    void visit(Rogue rogue);
    void visit(Wizard wizard);
}
