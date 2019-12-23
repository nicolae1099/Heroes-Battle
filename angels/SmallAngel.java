package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class SmallAngel implements Angel {
    @Override
    public void visit(Knight knight) {
        knight.angelsRaceMultiplier += Constants.SMALL_ANGEL_VS_KNIGHT_DMG;
        knight.hp += Constants.SMALL_ANGEL_VS_KNIGHT_HP;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.angelsRaceMultiplier += Constants.SMALL_ANGEL_VS_PYRO_DMG;
        pyromancer.hp += Constants.SMALL_ANGEL_VS_PYRO_HP;
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.angelsRaceMultiplier += Constants.SMALL_ANGEL_VS_ROGUE_DMG;
        rogue.hp += Constants.SMALL_ANGEL_VS_ROGUE_HP;
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.angelsRaceMultiplier += Constants.SMALL_ANGEL_VS_WIZARD_DMG;
        wizard.hp += Constants.SMALL_ANGEL_VS_WIZARD_HP;
    }
}
