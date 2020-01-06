package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class LevelUpAngel implements Angel {
    @Override
    public void visit(final Knight knight) {
        knight.addExpToLevelUp();
        knight.angelsRaceMultiplier += Constants.LEVEL_UP_ANGEL_VS_KNIGHT_DMG;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
        pyromancer.addExpToLevelUp();
        pyromancer.angelsRaceMultiplier += Constants.LEVEL_UP_ANGEL_VS_PYRO_DMG;
    }

    @Override
    public void visit(final Rogue rogue) {
        rogue.addExpToLevelUp();
        rogue.angelsRaceMultiplier += Constants.LEVEL_UP_ANGEL_VS_ROGUE_DMG;
    }

    @Override
    public void visit(final Wizard wizard) {
        wizard.addExpToLevelUp();
        wizard.angelsRaceMultiplier += Constants.LEVEL_UP_ANGEL_VS_WIZARD_DMG;
    }
}
