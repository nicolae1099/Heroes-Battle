package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class GoodBoy implements Angel {
    @Override
    public void visit(Knight knight) {
        knight.addRaceMultiplier(Constants.GOOD_BOY_VS_KNIGHT_DMG);
        knight.hp += Constants.GOOD_BOY_VS_KNIGHT_HP;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.addRaceMultiplier(Constants.GOOD_BOY_VS_PYRO_DMG);
        pyromancer.hp += Constants.GOOD_BOY_VS_PYRO_HP;
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.addRaceMultiplier(Constants.GOOD_BOY_VS_ROGUE_DMG);
        rogue.hp += Constants.GOOD_BOY_VS_ROGUE_HP;
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.addRaceMultiplier(Constants.GOOD_BOY_VS_WIZARD_DMG);
        wizard.hp += Constants.GOOD_BOY_VS_WIZARD_HP;
    }
}
