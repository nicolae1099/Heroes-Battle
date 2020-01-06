package heroes;


import abilities.AmplifierByRace;
import abilities.Deflect;
import abilities.Drain;
import angels.Angel;
import strategies.Strategy;
import strategies.WizardAttackStrategy;
import strategies.WizardDefenseStrategy;

public final class Wizard extends Hero {
    private Strategy attackStrategy = new WizardAttackStrategy();
    private Strategy defenseStrategy = new WizardDefenseStrategy();
    public Wizard(final String race, final int rowPos, final int columnPos, final int id) {
        setRace(race);
        setRowPos(rowPos);
        setColumnPos(columnPos);
        setId(id);

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
        setMagicDamage(Math.round(hpPercent * Math.min(Constants.DRAIN_MAX_PROCENT
                * opponent.maxHp, opponent.hp) * landMultiplierDmg * firstAbilityRaceMultiplier));
    }

    @Override
    public void applySecondAbility(final Hero opponent) {
        float deflectPercent = Math.min(Constants.DEFLECT_PERCENT
                + Constants.DEFLECT_PERCENT_SCALE * getLevel(), Constants.DEFLECT_MAX_PERCENT);
        //daca oponentul nu a apucat sa atace, ii simulez atacul sau fara race multiplieri.
        HeroFactory heroFactory = new HeroFactory();
        Hero opponentClone = heroFactory.getInstance(opponent.getRace(),
                opponent.getRowPos(), opponent.getColumnPos(), opponent.getId());
        Hero wizardClone = heroFactory.getInstance(this.getRace(),
                this.getRowPos(), this.getColumnPos(), this.getId());
        opponentClone.setLevel(opponent.getLevel());
        opponentClone.landMultiplierDmg = opponent.landMultiplierDmg;
        wizardClone.hp = this.hp;
        if (opponent.getCritickAttack() == Constants.CRITICK_DMG_MULTIPLIER) {
            opponentClone.setBackstabCount(opponent.getBackstabCount() - 1);
        } else {
            opponentClone.setBackstabCount(opponent.getBackstabCount());
        }

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
        opponentClone.totalDamage += Math.round((opponentClone.secondAbilityDmg
                + opponentClone.secondAbilityDmgScaling * opponentClone.getLevel())
                * opponentClone.landMultiplierDmg);

        setMagicDamage(getMagicDamage() + Math.round(deflectPercent * opponentClone.totalDamage
                * landMultiplierDmg * secondAbilityRaceMultiplier));
    }

    @Override
    public void accept(final Angel angel) {
        angel.visit(this);
    }

    @Override
    public void applyStrategy() {
        if (Math.round(Constants.QUARTER_OF * maxHp) < hp
                && hp < Math.round(Constants.HALF_OF * maxHp)) {
            attackStrategy.applyTo(this);
        } else if (hp < Math.round(Constants.QUARTER_OF * maxHp)) {
            defenseStrategy.applyTo(this);
        }
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
