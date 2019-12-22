package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class LifeGiver implements Angel {
    //TODO creste hp ul curent sau max hp ul? sau ambele?
    // TODO ce se intampla daca un player are deja current hp == max hp?
    @Override
    public void visit(Knight knight) {
        knight.hp += Constants.LIFE_GIVER_VS_KNIGHT_HP;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.hp += Constants.LIFE_GIVER_VS_PYRO_HP;
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.hp += Constants.LIFE_GIVER_VS_ROGUE_HP;
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.hp += Constants.LIFE_GIVER_VS_WIZARD_HP;
    }
}
