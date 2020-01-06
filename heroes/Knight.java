package heroes;

import abilities.AmplifierByRace;
import abilities.Execute;
import abilities.Slam;
import angels.Angel;
import effects.Effects;
import effects.InstantKill;
import effects.Stun;
import strategies.KnightAttackStrategy;
import strategies.KnightDefenseStrategy;
import strategies.Strategy;


public final class Knight extends Hero {
    private Strategy attackStrategy = new KnightAttackStrategy();
    private Strategy defenseStrategy = new KnightDefenseStrategy();
    public Knight(final String race, final int rowPos, final int columnPos, final int id) {
        setRace(race);
        setRowPos(rowPos);
        setColumnPos(columnPos);
        setId(id);

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
    public float isAttackedBy(final AmplifierByRace amplifierByRace) {
        return amplifierByRace.visit(this);
    }

    @Override
    public void setLandMultiplier(final String land) {
        if (land.equals("L")) {
            landMultiplierDmg = Constants.LAND_MULTIPLIER_DMG;
        } else {
            landMultiplierDmg = 1.0f;
        }
    }

    @Override
    public void applyFirstAbility(final Hero opponent) {
        Effects instantKill = new InstantKill();
        float hpLimit = Math.min(Constants.KNIGHT_EXECUTE_HP_LIMIT + opponent.getLevel()
                * Constants.KNIGHT_EXECUTE_HP_LIMIT_SCALE, Constants.KNIGHT_EXECUTE_HP_LIMIT_MAX);

        if (opponent.hp < opponent.maxHp * hpLimit) {
            setMagicDamage(opponent.hp);
            instantKill.apply(opponent);
        }
    }

    @Override
    public void applySecondAbility(final Hero opponent) {
        Effects stun = new Stun(1);
        stun.apply(opponent);
    }

    @Override
    public void accept(final Angel angel) {
        angel.visit(this);
    }

    @Override
    public void applyStrategy() {
        int maxLevelHp = maxHp;
        if (Math.round(Constants.THIRD_OF * maxLevelHp) < hp
                && hp < Math.round(Constants.HALF_OF * maxLevelHp)) {
            attackStrategy.applyTo(this);
        } else if (hp < Math.round(Constants.THIRD_OF * maxLevelHp)) {
            defenseStrategy.applyTo(this);
        }
    }

    @Override
    public String toString() {
        if (hp <= 0) {
            return ("K" + " dead");
        } else {
            return ("K" + " " + getLevel() + " " + getExp() + " " + hp + " " + getRowPos() + " "
                    + getColumnPos());
        }
    }
}
