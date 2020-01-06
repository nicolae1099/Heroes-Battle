package heroes;

import abilities.AmplifierByRace;
import abilities.Fireblast;
import abilities.Ignite;
import angels.Angel;
import effects.DamageOverTime;
import effects.Effects;
import strategies.PyromancerAttackStrategy;
import strategies.PyromancerDefenseStrategy;
import strategies.Strategy;


public final class Pyromancer extends Hero {
    private Strategy attackStrategy = new PyromancerAttackStrategy();
    private Strategy defenseStrategy = new PyromancerDefenseStrategy();
    public Pyromancer(final String race, final int rowPos, final int columnPos, final int id) {
        setRace(race);
        setRowPos(rowPos);
        setColumnPos(columnPos);
        setId(id);

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
    public float isAttackedBy(final AmplifierByRace amplifierByRace) {
        return amplifierByRace.visit(this);
    }

    @Override
    public void setLandMultiplier(final String land) {
        if (land.equals("V")) {
            landMultiplierDmg = Constants.VOLCANO_MULTIPLIER_DMG;
        } else {
            landMultiplierDmg = 1.0f;
        }
    }
    @Override
    public void applyFirstAbility(final Hero opponent) {
        setMagicDamage(0);
    }

    @Override
    public void applySecondAbility(final Hero opponent) {
        int dmgOverTimeDealt = Math.round(Math.round((secondAbilityDamageOverTime
                + (secondAbilityDamageOverTimeScaling * getLevel())) * landMultiplierDmg)
                * secondAbilityRaceMultiplier);
        Effects dmgOverTime = new DamageOverTime(dmgOverTimeDealt, 2);
        dmgOverTime.apply(opponent);
    }

    @Override
    public void accept(final Angel angel) {
        angel.visit(this);
    }

    @Override
    public void applyStrategy() {
        if (Math.round(Constants.QUARTER_OF * maxHp) < hp
                && hp < Math.round(Constants.THIRD_OF * maxHp)) {
            attackStrategy.applyTo(this);
        } else if (hp < Math.round(Constants.QUARTER_OF * maxHp)) {
            defenseStrategy.applyTo(this);
        }
    }

    @Override
    public String toString() {
        if (hp <= 0) {
            return ("P" + " dead");
        } else {
            return ("P" + " " + getLevel() + " " + getExp() + " " + hp + " " + getRowPos() + " "
                    + getColumnPos());
        }
    }
}
