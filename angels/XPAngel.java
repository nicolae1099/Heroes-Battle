package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class XPAngel implements Angel {
    @Override
    public void visit(final Knight knight) {
        knight.setExp(knight.getExp() + Constants.XP_ANGEL_VS_KNIGHT_EXP);
        knight.levelUp();
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
        pyromancer.setExp(pyromancer.getExp() + Constants.XP_ANGEL_VS_PYRO_EXP);
        pyromancer.levelUp();
    }

    @Override
    public void visit(final Rogue rogue) {
        rogue.setExp(rogue.getExp() + Constants.XP_ANGEL_VS_ROGUE_EXP);
        rogue.levelUp();
    }

    @Override
    public void visit(final Wizard wizard) {
        wizard.setExp(wizard.getExp() + Constants.XP_ANGEL_VS_WIZARD_EXP);
        wizard.levelUp();
    }
}
