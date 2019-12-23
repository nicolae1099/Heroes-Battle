package heroes;


import abilities.AmplifierByRace;
import abilities.Deflect;
import abilities.Drain;
import angels.Angel;

public final class Wizard extends Hero {
    public Wizard(final String race, final int rowPos, final int columnPos, int id) {
        setRace(race);
        setRowPos(rowPos);
        setColumnPos(columnPos);
        this.id = id;

        maxHp = Constants.WIZARD_INITIAL_HP;
        hp = Constants.WIZARD_INITIAL_HP;
        initialHp = Constants.WIZARD_INITIAL_HP;
        hpIncreasePerLevel = Constants.WIZARD_HP_SCALE;

        firstAbility = new Drain();
        secondAbility = new Deflect();
    }
    @Override
    public float isAttackedBy(final AmplifierByRace amplifierByRace) {
        return amplifierByRace.visit(this);
    }

    @Override
    public void setLandMultiplier(final String land) {
        if (land.equals("D")) {
            landMultiplierDmg = Constants.DESERT_MULTIPLIER_DMG;
        } else {
            landMultiplierDmg = 1.0f;
        }
    }

    @Override
    public void applyFirstAbility(final Hero opponent) {
        float hpPercent = Constants.DRAIN_PRECENT + Constants.DRAIN_PRECENT_SCALE * getLevel();
        magicDamage =  Math.round(hpPercent
                * Math.min(Constants.DRAIN_MAX_PROCENT * opponent.maxHp, opponent.hp) * landMultiplierDmg
                * firstAbilityRaceMultiplier);
    }

    @Override
    public void applySecondAbility(final Hero opponent) {
        float deflectPercent = Math.min(Constants.DEFLECT_PERCENT
                + Constants.DEFLECT_PERCENT_SCALE * getLevel(), Constants.DEFLECT_MAX_PERCENT);
        //daca oponentul nu a apucat sa atace, ii simulez atacul sau fara race multiplieri.
        HeroFactory heroFactory = new HeroFactory();
        Hero opponentClone = heroFactory.getInstance(opponent.getRace(),
                opponent.getRowPos(), opponent.getColumnPos(), opponent.id);
        Hero wizardClone = heroFactory.getInstance(this.getRace(), this.getRowPos(), this.getColumnPos(), this.id);
        opponentClone.setLevel(opponent.getLevel());
        opponentClone.landMultiplierDmg = opponent.landMultiplierDmg;

        if (opponent.getCritickAttack() == Constants.CRITICK_DMG_MULTIPLIER) {
            opponentClone.backstabCount = opponent.backstabCount - 1;
        } else {
            opponentClone.backstabCount = opponent.backstabCount;
        }

        int damage = 0;
        if (this.secondAbilityRaceMultiplier == 0) {
            return;
        }

        opponentClone.secondAbilityRaceMultiplier = 1.0f;
        opponentClone.totalDamage = 0;
        opponentClone.applyFirstAbility(wizardClone);
        opponentClone.totalDamage = Math.round(((opponentClone.firstAbilityDmg
                + opponentClone.firstAbilityDmgScaling
                * opponentClone.getLevel()) * opponentClone.landMultiplierDmg)
                * opponentClone.getCritickAttack());
        opponentClone.applySecondAbility(wizardClone);
        opponentClone.totalDamage = opponentClone.totalDamage +  Math.round((opponentClone.secondAbilityDmg
                + opponentClone.secondAbilityDmgScaling
                * opponentClone.getLevel()) * opponentClone.landMultiplierDmg);

        this.magicDamage = this.magicDamage + Math.round(deflectPercent * opponentClone.totalDamage
                * landMultiplierDmg * secondAbilityRaceMultiplier);
    }

    @Override
    public void accept(Angel angel) {
        angel.visit(this);
    }

    @Override
    public void applyStrategy() {
        if (Math.round(Constants.QUARTER_OF * maxHp) < hp && hp < Math.round(Constants.HALF_OF * maxHp)) {
            playAttackStrategy();
        } else if (hp <= Constants.QUARTER_OF * maxHp) {
            playDefenseStrategy();
        }
    }

    @Override
    public void playAttackStrategy() {
        hp = hp - Math.round(Constants.TENTH_OF * hp);
        strategyRaceMultiplier += Constants.SIXTY_PRECENT;
    }

    @Override
    public void playDefenseStrategy() {
        strategyRaceMultiplier -= Constants.TWENTY_PERCENT;
        hp += Math.round(Constants.FIFTH_OF * hp);
    }


    @Override
    public String toString() {
        if (hp <= 0) {
            return ("W" + " dead");
        } else {
            return ("W" + " " + getLevel() + " " + getExp() + " " + hp + " " + getRowPos() + " "
                    + getColumnPos());
        }
    }
}
