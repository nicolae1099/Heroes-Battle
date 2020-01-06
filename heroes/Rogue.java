package heroes;

import abilities.AmplifierByRace;
import abilities.Backstab;
import abilities.Paralysis;
import angels.Angel;
import effects.DamageOverTime;
import effects.Effects;
import effects.Stun;
import strategies.RogueAttackStrategy;
import strategies.RogueDefenseStrategy;
import strategies.Strategy;

public final class Rogue extends Hero {
    private Strategy attackStrategy = new RogueAttackStrategy();
    private Strategy defenseStrategy = new RogueDefenseStrategy();
    public Rogue(final String race, final int rowPos, final int columnPos, final int id) {
        setRace(race);
        setRowPos(rowPos);
        setColumnPos(columnPos);
        setId(id);

        maxHp = Constants.ROGUE_INITIAL_HP;
        hp = Constants.ROGUE_INITIAL_HP;
        initialHp = Constants.ROGUE_INITIAL_HP;
        hpIncreasePerLevel = Constants.ROGUE_HP_SCALE;
        setCritickAttack(1.0f);
        firstAbilityDmg = Constants.BACKSTAB_DMG;
        firstAbilityDmgScaling = Constants.BACKSTAB_DMG_SCALE;
        secondAbilityDmg = Constants.PARALYSIS_DMG;
        secondAbilityDmgScaling = Constants.PARALYSIS_DMG_SCALE;
        setBackstabCount(0);

        firstAbility = new Backstab();
        secondAbility = new Paralysis();
    }
    @Override
    public float isAttackedBy(final AmplifierByRace amplifierByRace) {
        return amplifierByRace.visit(this);
    }

    @Override
    public void setLandMultiplier(final String land) {
        if (land.equals("W")) {
            landMultiplierDmg = Constants.WOODS_MULTIPLIER_DMG;
        } else {
            landMultiplierDmg = 1.0f;
        }
    }

    @Override
    public void applyFirstAbility(final Hero opponent) {
        setCritickAttack(1.0f);
        if (getBackstabCount() % Constants.BACKSTAB_CRITICAL_ROUND == 0) {
            if (landMultiplierDmg == Constants.WOODS_MULTIPLIER_DMG) {
                setCritickAttack(Constants.CRITICK_DMG_MULTIPLIER);
            } else {
                setBackstabCount(0);
            }
        }
        setBackstabCount(getBackstabCount() + 1);
    }

    @Override
    public void applySecondAbility(final Hero opponent) {
        int duration = Constants.PARALYSIS_DURATION;
        if (landMultiplierDmg == Constants.WOODS_MULTIPLIER_DMG) {
            duration = Constants.PARALYSIS_DURATION_ON_WOODS;
        }
        Effects paralysis = new Stun(duration);
        paralysis.apply(opponent);
        //secondAbilityRaceMultiplier += angelsRaceMultiplier;
        int damage = Math.round(Math.round((secondAbilityDmg + secondAbilityDmgScaling * getLevel())
                * landMultiplierDmg) * secondAbilityRaceMultiplier);
        Effects dmgOverTime = new DamageOverTime(damage, duration);
        dmgOverTime.apply(opponent);
    }

    @Override
    public void accept(final Angel angel) {
        angel.visit(this);
    }

    @Override
    public void applyStrategy() {
        if (Math.round(Constants.SEVENTH_OF * maxHp) < hp
                && hp < Math.round(Constants.FIFTH_OF * maxHp)) {
            attackStrategy.applyTo(this);
        } else if (hp < Math.round(Constants.SEVENTH_OF * maxHp)) {
            defenseStrategy.applyTo(this);
        }
    }


    @Override
    public String toString() {
        if (hp <= 0) {
            return ("R" + " dead");
        } else {
            return ("R" + " " + getLevel() + " " + getExp() + " " + hp + " " + getRowPos() + " "
                    + getColumnPos());
        }
    }
}
