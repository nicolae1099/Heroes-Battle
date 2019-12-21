package hero;

import abilities.AmplifierByRace;
import abilities.Fireblast;
import abilities.Ignite;
import effects.DamageOverTime;
import effects.Effects;


public class Pyromancer extends Hero {
    public Pyromancer(final String race, final int rowPos, final int columnPos) {
        setRace(race);
        setRowPos(rowPos);
        setColumnPos(columnPos);

        maxHp = Constants.PYRO_INITIAL_HP;
        hp = Constants.PYRO_INITIAL_HP;
        initialHp = Constants.PYRO_INITIAL_HP;
        hpIncreasePerLevel = Constants.PYRO_HP_SCALE;
        setCritickAttack(1.0f);

        firstAbilityDmg = Constants.FIREBLAST_DMG;
        firstAbilityDmgScaling = Constants.FIREBLAST_DMG_SCALE;
        secondAbilityDmg = Constants.IGNITE_DMG;
        secondAbilityDmgScaling = Constants.IGNITE_DMG_SCALE;
        secondAbilityDamageOverTime = Constants.IGNITE_DOT;
        secondAbilityDamageOverTimeScaling = Constants.IGNITE_DOT_SCALE;

        firstAbility = new Fireblast();
        secondAbility = new Ignite();

    }
    @Override
    public final float isAttackedBy(final AmplifierByRace amplifierByRace) {
        return amplifierByRace.visit(this);
    }

    @Override
    public final void setLandMultiplier(final String land) {
        if (land.equals("V")) {
            landMultiplierDmg = Constants.VOLCANO_MULTIPLIER_DMG;
        } else {
            landMultiplierDmg = 1.0f;
        }
    }
    @Override
    public final void applyFirstAbility(final Hero opponent) {
        magicDamage = 0;
    }

    @Override
    public final void applySecondAbility(final Hero opponent) {
        int dmgOverTimeDealt = Math.round((secondAbilityDamageOverTime
                + (this.secondAbilityDamageOverTimeScaling * getLevel())) * raceMultiplierDmg
                * landMultiplierDmg);
        Effects dmgOverTime = new DamageOverTime(dmgOverTimeDealt, 2);
        dmgOverTime.apply(opponent);
    }

    @Override
    public final String toString() {
        if (hp <= 0) {
            return ("P" + " dead");
        } else {
            return ("P" + " " + getLevel() + " " + getExp() + " " + hp + " " + getRowPos() + " "
                    + getColumnPos());
        }
    }
}
