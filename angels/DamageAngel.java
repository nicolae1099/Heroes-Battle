package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class DamageAngel implements Angel {
    @Override
    public void visit(final Knight knight) {
        knight.angelsRaceMultiplier += Constants.DAMAGE_ANGEL_VS_KNIGHT_DMG;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
        pyromancer.angelsRaceMultiplier += Constants.DAMAGE_ANGEL_VS_PYRO_DMG;
    }

    @Override
    public void visit(final Rogue rogue) {
        rogue.angelsRaceMultiplier += Constants.DAMAGE_ANGEL_VS_ROGUE_DMG;
    }

    @Override
    public void visit(final Wizard wizard) {
        wizard.angelsRaceMultiplier += Constants.DAMAGE_ANGEL_VS_WIZARD_DMG;
    }
}
