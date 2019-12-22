package heroes;

import abilities.AmplifierByRace;
import abilities.Backstab;
import abilities.Paralysis;
import angels.Angel;
import effects.DamageOverTime;
import effects.Effects;
import effects.Stun;

public class Rogue extends Hero {
    public Rogue(final String race, final int rowPos, final int columnPos) {
        setRace(race);
        setRowPos(rowPos);
        setColumnPos(columnPos);

        maxHp = Constants.ROGUE_INITIAL_HP;
        hp = Constants.ROGUE_INITIAL_HP;
        initialHp = Constants.ROGUE_INITIAL_HP;
        hpIncreasePerLevel = Constants.ROGUE_HP_SCALE;
        setCritickAttack(1.0f);
        firstAbilityDmg = Constants.BACKSTAB_DMG;
        firstAbilityDmgScaling = Constants.BACKSTAB_DMG_SCALE;
        secondAbilityDmg = Constants.PARALYSIS_DMG;
        secondAbilityDmgScaling = Constants.PARALYSIS_DMG_SCALE;
        backstabCount = 0;

        firstAbility = new Backstab();
        secondAbility = new Paralysis();
    }
    @Override
    public final float isAttackedBy(final AmplifierByRace amplifierByRace) {
        return amplifierByRace.visit(this);
    }

    @Override
    public final void setLandMultiplier(final String land) {
        if (land.equals("W")) {
            landMultiplierDmg = Constants.WOODS_MULTIPLIER_DMG;
        } else {
            landMultiplierDmg = 1.0f;
        }
    }

    @Override
    public final void applyFirstAbility(final Hero opponent) {
        setCritickAttack(1.0f);
        if (backstabCount % Constants.BACKSTAB_CRITICAL_ROUND == 0) {
            if (landMultiplierDmg == Constants.WOODS_MULTIPLIER_DMG) {
                setCritickAttack(Constants.CRITICK_DMG_MULTIPLIER);
            } else {
                backstabCount = 0;
            }
        }
        backstabCount++;
    }

    @Override
    public final void applySecondAbility(final Hero opponent) {
        int duration = Constants.PARALYSIS_DURATION;
        if (landMultiplierDmg == Constants.WOODS_MULTIPLIER_DMG) {
            duration = Constants.PARALYSIS_DURATION_ON_WOODS;
        }
        Effects paralysis = new Stun(duration);
        paralysis.apply(opponent);

        int damage = Math.round((secondAbilityDmg + secondAbilityDmgScaling * getLevel())
                * secondAbilityRaceMultiplier * landMultiplierDmg);
        Effects dmgOverTime = new DamageOverTime(damage, duration);
        dmgOverTime.apply(opponent);
    }

    @Override
    public void accept(Angel angel) {
        angel.visit(this);
    }

    @Override
    public final String toString() {
        if (hp <= 0) {
            return ("R" + " dead");
        } else {
            return ("R" + " " + getLevel() + " " + getExp() + " " + hp + " " + getRowPos() + " "
                    + getColumnPos());
        }
    }
}
