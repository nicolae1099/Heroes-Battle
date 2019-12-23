package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.image.WritableRenderedImage;

public final class GoodBoy implements Angel {
    @Override
    public void visit(Knight knight) {
        knight.angelsRaceMultiplier += Constants.GOOD_BOY_VS_KNIGHT_DMG;
        if (knight.hp + Constants.GOOD_BOY_VS_KNIGHT_HP <= knight.maxHp) {
            knight.hp += Constants.GOOD_BOY_VS_KNIGHT_HP;
        }
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.angelsRaceMultiplier += Constants.GOOD_BOY_VS_PYRO_DMG;
        if (pyromancer.hp + Constants.GOOD_BOY_VS_PYRO_HP < pyromancer.maxHp) {
            pyromancer.hp += Constants.GOOD_BOY_VS_PYRO_HP;
        }
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.angelsRaceMultiplier += Constants.GOOD_BOY_VS_ROGUE_DMG;
        if (rogue.hp + Constants.GOOD_BOY_VS_ROGUE_HP < rogue.maxHp) {
            rogue.hp += Constants.GOOD_BOY_VS_ROGUE_HP;
        }
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.angelsRaceMultiplier += Constants.GOOD_BOY_VS_WIZARD_DMG;
        if (wizard.hp + Constants.GOOD_BOY_VS_WIZARD_HP < wizard.maxHp) {
            wizard.hp += Constants.GOOD_BOY_VS_WIZARD_HP;
        }
    }
}
