package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import observer.Observer;

public final class TheDoomer implements Angel {
    @Override
    public void visit(Knight knight) {
        knight.hp = 0;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.hp = 0;
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.hp = 0;
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.hp = 0;
    }
}
