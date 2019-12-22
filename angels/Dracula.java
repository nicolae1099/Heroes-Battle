package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Dracula implements Angel {
    @Override
    public void visit(Knight knight) {
        knight.addRaceMultiplier(-Constants.DRACULA_VS_KNIGHT_DMG);
        knight.hp -= Constants.DRACULA_VS_KNIGHT_HP;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.addRaceMultiplier(-Constants.DRACULA_VS_PYRO_DMG);
        pyromancer.hp -= Constants.DRACULA_VS_PYRO_HP;
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.addRaceMultiplier(-Constants.DRACULA_VS_ROGUE_DMG);
        rogue.hp -= Constants.DRACULA_VS_ROGUE_HP;
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.addRaceMultiplier(-Constants.DRACULA_VS_WIZARD_DMG);
        wizard.hp -= Constants.DRACULA_VS_WIZARD_HP;
    }
}
