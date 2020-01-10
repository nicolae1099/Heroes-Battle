package heroes;

import abilities.AmplifierByRace;
import angels.Angel;
import observer.Observer;

import java.math.BigDecimal;
import java.math.RoundingMode;


public abstract class Hero {

    public int totalDamage;
    public float firstAbilityRaceMultiplier;
    public float secondAbilityRaceMultiplier;
    public float strategyRaceMultiplier;
    public float angelsRaceMultiplier;
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
    public AmplifierByRace firstAbility;
    public AmplifierByRace secondAbility;

    private Observer observer;
    private int id;
    private int magicDamage;
    private int physicalDamage;
    private String race;
    private int rowPos;
    private int columnPos;
    private int level;
    private int exp;
    private float critickAttack;
    private boolean stun;
    private int backstabCount;
    private int dotDuration;
    private int stunDuration;
    private int dmgToBeTaken;

    public final void registerObserver(final Observer observere) {
        this.observer = observere;
    }

    public abstract float isAttackedBy(AmplifierByRace amplifierByRace);
    public abstract void setLandMultiplier(String land);

    public abstract void applyFirstAbility(Hero opponent);

    public abstract void applySecondAbility(Hero opponent);
    public abstract void accept(Angel angel);

    public abstract void applyStrategy();

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
            while (level < localLevel) {
                level++;
                notifyObserver(this);

            }
            if (this.hp > 0) {
                this.hp = this.initialHp + (this.level * this.hpIncreasePerLevel);
                this.maxHp = this.hp;
            }
        }
    }

    public final void addExpToLevelUp() {
        int expNecesarry = Constants.EXP_FOR_FIRST_LEVEL
                + Constants.EXP_TO_LEVEL_UP * level;
        exp = expNecesarry;
        level++;
        hp = initialHp + (level * hpIncreasePerLevel);
        maxHp = hp;
        notifyObserver(this);
    }

    private void notifyObserver(final Hero player) {
        observer.update(player);
    }

    public final boolean isAlive() {
        if (hp <= 0) {
            return false;
        }
        return true;
    }

    public final void calculateDmgFirstAttack() {
        physicalDamage = Math.round(Math.round((firstAbilityDmg + firstAbilityDmgScaling * level)
                * landMultiplierDmg) * firstAbilityRaceMultiplier * critickAttack);
    }

    public final void calculateDmgSecondAttack() {
        physicalDamage = (int) (physicalDamage + roundHalfDown(Math.round((secondAbilityDmg
                        + secondAbilityDmgScaling * level)
                        * landMultiplierDmg) * secondAbilityRaceMultiplier));
    }
    public static double roundHalfDown(final double d) {
        return new BigDecimal(d).setScale(0, RoundingMode.HALF_DOWN)
                .doubleValue();
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
    public final void takeOverTimeDmg() {
        if (dotDuration <= 0) {
            dmgToBeTaken = 0;
        }
        hp = hp - dmgToBeTaken;
        if (hp <= 0) {
            totalDamage = 0;
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

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }
    public final int getMagicDamage() {
        return magicDamage;
    }

    public final void setMagicDamage(final int magicDamage) {
        this.magicDamage = magicDamage;
    }
    public final boolean isStun() {
        return stun;
    }

    public final void setStun(final boolean stun) {
        this.stun = stun;
    }

    public final int getBackstabCount() {
        return backstabCount;
    }

    public final void setBackstabCount(final int backstabCount) {
        this.backstabCount = backstabCount;
    }

    public final int getDotDuration() {
        return dotDuration;
    }

    public final void setDotDuration(final int dotDuration) {
        this.dotDuration = dotDuration;
    }

    public final void setStunDuration(final int stunDuration) {
        this.stunDuration = stunDuration;
    }

    public final void setDmgToBeTaken(final int dmgToBeTaken) {
        this.dmgToBeTaken = dmgToBeTaken;
    }
}
