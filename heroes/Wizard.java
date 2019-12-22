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
        magicDamage = (int) Math.round(Math.round(hpPercent * landMultiplierDmg
                * Math.min(Constants.DRAIN_MAX_PROCENT * opponent.maxHp, opponent.hp))
                * firstAbilityRaceMultiplier);
    }

    @Override
    public void applySecondAbility(final Hero opponent) {
        float deflectPercent = Math.min(Constants.DEFLECT_PERCENT
                + Constants.DEFLECT_PERCENT_SCALE * getLevel(), Constants.DEFLECT_MAX_PERCENT);
        //daca oponentul nu a apucat sa atace, ii simulez atacul sau fara race multiplieri.

        Hero player = null;
        if (opponent.getRace().equals("P")) {
            player = new Pyromancer("P", opponent.getRowPos(), opponent.getColumnPos(), player.id);
        }
        if (opponent.getRace().equals("K")) {
            player = new Knight("K", opponent.getRowPos(), opponent.getColumnPos(), player.id);
        }
        if (opponent.getRace().equals("W")) {
            player = new Wizard("W", opponent.getRowPos(), opponent.getColumnPos(), player.id);
        }
        if (opponent.getRace().equals("R")) {
            player = new Rogue("R", getRowPos(), opponent.getColumnPos(), player.id);
        }
        player.setLevel(opponent.getLevel());
        player.landMultiplierDmg = opponent.landMultiplierDmg;

        if (opponent.getCritickAttack() == Constants.CRITICK_DMG_MULTIPLIER) {
            player.backstabCount = opponent.backstabCount - 1;
        } else {
            player.backstabCount = opponent.backstabCount;
        }

        int damage = 0;
        if (this.secondAbilityRaceMultiplier == 0) {
            return;
        }

        player.secondAbilityRaceMultiplier = 1.0f;
        player.totalDamage = 0;
        player.applyFirstAbility(this);
        player.totalDamage = Math.round(((player.firstAbilityDmg + player.firstAbilityDmgScaling
                * player.getLevel()) * player.landMultiplierDmg) * player.getCritickAttack());
        player.applySecondAbility(this);
        player.totalDamage = player.totalDamage +  Math.round((player.secondAbilityDmg
                + player.secondAbilityDmgScaling * player.getLevel()) * player.landMultiplierDmg);

        this.magicDamage = this.magicDamage + Math.round(Math.round(deflectPercent * player.totalDamage
                * landMultiplierDmg) * secondAbilityRaceMultiplier);

    }

    @Override
    public void accept(Angel angel) {
        angel.visit(this);
    }

    @Override
    public void applyStrategy() {

    }

    @Override
    public void playAttackStrategy() {

    }

    @Override
    public void playDefenseStrategy() {

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