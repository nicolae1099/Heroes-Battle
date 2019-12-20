package hero;

import abilities.AmplifierByRace;
import abilities.Execute;
import abilities.Slam;
import effects.Effects;
import effects.InstantKill;
import effects.Stun;


public class Knight extends Hero {

    public Knight(final String race, final int rowPos, final int columnPos) {
        setRace(race);
        setRowPos(rowPos);
        setColumnPos(columnPos);

        maxHp = Constants.KNIGHT_INITIAL_HP;
        hp = Constants.KNIGHT_INITIAL_HP;
        initialHp = Constants.KNIGHT_INITIAL_HP;
        hpIncreasePerLevel = Constants.KNIGHT_HP_SCALE;
        setCritickAttack(1.0f);

        firstAbilityDmg = Constants.EXECUTE_DMG;
        firstAbilityDmgScaling = Constants.EXECUTE_DMG_SCALE;
        secondAbilityDmg = Constants.SLAM_DMG;
        secondAbilityDmgScaling = Constants.SLAM_DMG_SCALE;

        firstAbility = new Execute();
        secondAbility = new Slam();
    }

    @Override
    public final float isAttackedBy(final AmplifierByRace amplifierByRace) {
        return amplifierByRace.visit(this);
    }

    @Override
    public final void setLandMultiplier(final String land) {
        if (land.equals("L")) {
            landMultiplierDmg = Constants.LAND_MULTIPLIER_DMG;
        } else {
            landMultiplierDmg = 1.0f;
        }
    }

    @Override
    public final void applyFirstAbility(final Hero opponent) {
        Effects instantKill = new InstantKill();
        float hpLimit = Math.min(Constants.KNIGHT_EXECUTE_HP_LIMIT + opponent.getLevel()
                * Constants.KNIGHT_EXECUTE_HP_LIMIT_SCALE, Constants.KNIGHT_EXECUTE_HP_LIMIT_MAX);

        if (opponent.hp < opponent.hp * hpLimit) {
            this.totalDamage = opponent.hp;
            instantKill.apply(opponent);
        }

    }

    @Override
    public final void applySecondAbility(final Hero opponent) {
        Effects stun = new Stun(1);
        stun.apply(opponent);
    }
    @Override
    public final String toString() {
        if (hp <= 0) {
            return ("K" + " dead");
        } else {
            return ("K" + " " + getLevel() + " " + getExp() + " " + hp + " " + getRowPos() + " "
                    + getColumnPos());
        }
    }
}
