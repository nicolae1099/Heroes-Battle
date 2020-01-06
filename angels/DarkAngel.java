package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class DarkAngel implements Angel {
    @Override
    public void visit(final Knight knight) {
        knight.hp -= Constants.DARK_ANGEL_VS_KNIGHT_HP;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
        pyromancer.hp -= Constants.DARK_ANGEL_VS_PYRO_HP;
    }

    @Override
    public void visit(final Rogue rogue) {
        rogue.hp -= Constants.DARK_ANGEL_VS_ROGUE_HP;
    }

    @Override
    public void visit(final Wizard wizard) {
        wizard.hp -= Constants.DARK_ANGEL_VS_WIZARD_HP;
    }
}
