package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Dracula implements Angel {
    @Override
    public void visit(final Knight knight) {
        knight.angelsRaceMultiplier -= Constants.DRACULA_VS_KNIGHT_DMG;
        knight.hp -= Constants.DRACULA_VS_KNIGHT_HP;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
        pyromancer.angelsRaceMultiplier -= Constants.DRACULA_VS_PYRO_DMG;
        pyromancer.hp -= Constants.DRACULA_VS_PYRO_HP;
    }

    @Override
    public void visit(final Rogue rogue) {
        rogue.angelsRaceMultiplier -= Constants.DRACULA_VS_ROGUE_DMG;
        rogue.hp -= Constants.DRACULA_VS_ROGUE_HP;
    }

    @Override
    public void visit(final Wizard wizard) {
        wizard.angelsRaceMultiplier -= Constants.DRACULA_VS_WIZARD_DMG;
        wizard.hp -= Constants.DRACULA_VS_WIZARD_HP;
    }
}
