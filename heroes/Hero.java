package heroes;

import abilities.AmplifierByRace;
import angels.Angel;

public abstract class Hero {
    private String race;
    private int rowPos;
    private int columnPos;
    private int level;
    private int exp;
    private float critickAttack;
    public int backstabCount;
    public int totalDamage;
    public int magicDamage;
    public int physicalDamage;
    public float firstAbilityRaceMultiplier;
    public float secondAbilityRaceMultiplier;
    public float landMultiplierDmg = 1.0f;
    public int hp;
    public int maxHp;
    public int initialHp;
    public int hpIncreasePerLevel;
    public int firstAbilityDmg;
    public int firstAbilityDmgScaling;
    public int secondAbilityDmg;
    public int secondAbilityDmgScaling;
    public int secondAbilityDamageOverTime;
    public int secondAbilityDamageOverTimeScaling;
    public int dotDuration;
    public int stunDuration;
    public int dmgToBeTaken;
    public boolean stun;
    public boolean deadFromDot;
    public AmplifierByRace firstAbility;
    public AmplifierByRace secondAbility;

    public abstract float isAttackedBy(AmplifierByRace amplifierByRace);
    public abstract void setLandMultiplier(String land);

    public abstract void applyFirstAbility(Hero opponent);

    public abstract void applySecondAbility(Hero opponent);
    public abstract void accept(Angel angel);

    public final void levelUp() {
        int localExp = this.exp;
        int localLevel = 0;
        while (localExp >= Constants.EXP_TO_LEVEL_UP) {
            if (localLevel == 0) {
                if (localExp >= Constants.EXP_FOR_FIRST_LEVEL) {
                    localExp -= Constants.EXP_FOR_FIRST_LEVEL;
                    localLevel++;
                } else {
                    break;
                }
            } else {
                localExp -= Constants.EXP_TO_LEVEL_UP;
                localLevel++;
            }
        }
        if (localLevel > this.level) {
            this.level = localLevel;
            this.hp = this.initialHp + (this.level * this.hpIncreasePerLevel);
            this.maxHp = this.hp;
        }
    }

    public final void addExpToLevelUp() {
        int nextLevel = getLevel() + 1;
        int expNecesarry = Constants.EXP_FOR_FIRST_LEVEL
                + Constants.EXP_TO_LEVEL_UP * nextLevel;
        setExp(expNecesarry);
        setLevel(nextLevel);
    }

    public final boolean isAlive() {
        if (hp <= 0) {
            return false;
        }
        return true;
    }

    public final void calculateDmgFirstAttack() {
        physicalDamage = Math.round((firstAbilityDmg + firstAbilityDmgScaling * level)
                * firstAbilityRaceMultiplier * landMultiplierDmg * critickAttack);
    }

    public final void calculateDmgSecondAttack() {
        physicalDamage = physicalDamage + Math.round((secondAbilityDmg + secondAbilityDmgScaling * level)
                * secondAbilityRaceMultiplier * landMultiplierDmg);
    }
    public final void calculateTotalDamage() {
        totalDamage = physicalDamage + magicDamage;
        physicalDamage = 0;
        magicDamage = 0;
    }

    public final void setExperience(final int opponentLevel) {
        exp = exp + Math.max(0, Constants.EXP_FORMULA1
                - (level - opponentLevel) * Constants.EXP_FORMULA2);
    }
    public final void addRaceMultiplier(float amount) {
        firstAbilityRaceMultiplier += amount;
        secondAbilityRaceMultiplier += amount;
    }
    public final void takeOverTimeDmg() {
        if (dotDuration == 0) {
            dmgToBeTaken = 0;
        }
        hp = hp - dmgToBeTaken;
        deadFromDot = false;
        if (hp <= 0) {
            totalDamage = 0;
            deadFromDot = true;
        }
        dotDuration--;
    }

    public final void movePositionIfPossible(final String direction) {

        if (stun) {
            stunDuration--;
            if (stunDuration <= 0) {
                stun = false;
            }
            return;
        }
        switch (direction) {
            case "U":
                rowPos--;
                break;
            case "D":
                rowPos++;
                break;
            case "L":
                columnPos--;
                break;
            case "R":
                columnPos++;
                break;
            default:
                break;
        }
    }


    public final String getRace() {
        return race;
    }

    public final void setRace(final String race) {
        this.race = race;
    }

    public final int getRowPos() {
        return rowPos;
    }

    public final void setRowPos(final int rowPos) {
        this.rowPos = rowPos;
    }

    public final int getColumnPos() {
        return columnPos;
    }

    public final void setColumnPos(final int columnPos) {
        this.columnPos = columnPos;
    }

    public final int getLevel() {
        return level;
    }

    public final void setLevel(final int level) {
        this.level = level;
    }

    public final int getExp() {
        return exp;
    }

    public final void setExp(final int exp) {
        this.exp = exp;
    }
    public final float getCritickAttack() {
        return critickAttack;
    }

    public final  void setCritickAttack(final float critickAttack) {
        this.critickAttack = critickAttack;
    }
}
