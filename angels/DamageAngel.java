package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class DamageAngel implements Angel {
    @Override
    public void visit(Knight knight) {
        knight.addRaceMultiplier(Constants.DAMAGE_ANGEL_VS_KNIGHT_DMG);
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.addRaceMultiplier(Constants.DAMAGE_ANGEL_VS_PYRO_DMG);
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.addRaceMultiplier(Constants.DAMAGE_ANGEL_VS_ROGUE_DMG);
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.addRaceMultiplier(Constants.DAMAGE_ANGEL_VS_WIZARD_DMG);
    }
}
