package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class LevelUpAngel implements Angel {
    @Override
    public void visit(Knight knight) {
        knight.addExpToLevelUp();
        knight.addRaceMultiplier(Constants.LEVEL_UP_ANGEL_VS_KNIGHT_DMG);
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.addExpToLevelUp();
        pyromancer.addRaceMultiplier(Constants.LEVEL_UP_ANGEL_VS_PYRO_DMG);
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.addExpToLevelUp();
        rogue.addRaceMultiplier(Constants.LEVEL_UP_ANGEL_VS_ROGUE_DMG);
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.addExpToLevelUp();
        wizard.addRaceMultiplier(Constants.LEVEL_UP_ANGEL_VS_WIZARD_DMG);
    }
}
