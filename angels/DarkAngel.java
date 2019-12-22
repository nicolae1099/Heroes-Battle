package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class DarkAngel implements Angel {
    @Override
    public void visit(Knight knight) {
        knight.hp -= Constants.DARK_ANGEL_VS_KNIGHT_HP;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.hp -= Constants.DARK_ANGEL_VS_PYRO_HP;
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.hp -= Constants.DARK_ANGEL_VS_ROGUE_HP;
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.hp -= Constants.DARK_ANGEL_VS_WIZARD_HP;
    }
}
