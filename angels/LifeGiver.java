package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class LifeGiver implements Angel {
    @Override
    public void visit(final Knight knight) {
        knight.hp = Math.min(knight.hp + Constants.LIFE_GIVER_VS_KNIGHT_HP, knight.maxHp);
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
        pyromancer.hp = Math.min(pyromancer.hp + Constants.LIFE_GIVER_VS_PYRO_HP, pyromancer.maxHp);
    }

    @Override
    public void visit(final Rogue rogue) {
        rogue.hp = Math.min(rogue.hp + Constants.LIFE_GIVER_VS_ROGUE_HP, rogue.maxHp);
    }

    @Override
    public void visit(final Wizard wizard) {
        wizard.hp = Math.min(wizard.hp + Constants.LIFE_GIVER_VS_WIZARD_HP, wizard.maxHp);
    }
}
