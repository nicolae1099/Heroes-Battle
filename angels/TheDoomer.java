package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class TheDoomer implements Angel {
    @Override
    public void visit(final Knight knight) {
        knight.hp = 0;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
        pyromancer.hp = 0;
    }

    @Override
    public void visit(final Rogue rogue) {
        rogue.hp = 0;
    }

    @Override
    public void visit(final Wizard wizard) {
        wizard.hp = 0;
    }
}
