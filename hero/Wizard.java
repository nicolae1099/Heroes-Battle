package hero;


import abilities.AmplifierByRace;
import abilities.Deflect;
import abilities.Drain;

public class Wizard extends Hero {
    public Wizard(final String race, final int rowPos, final int columnPos) {
        setRace(race);
        setRowPos(rowPos);
        setColumnPos(columnPos);

        maxHp = Constants.WIZARD_INITIAL_HP;
        hp = Constants.WIZARD_INITIAL_HP;
        initialHp = Constants.WIZARD_INITIAL_HP;
        hpIncreasePerLevel = Constants.WIZARD_HP_SCALE;

        firstAbility = new Drain();
        secondAbility = new Deflect();
    }
    @Override
    public final float isAttackedBy(final AmplifierByRace amplifierByRace) {
        return amplifierByRace.visit(this);
    }

    @Override
    public final void setLandMultiplier(final String land) {
        if (land.equals("D")) {
            landMultiplierDmg = Constants.DESERT_MULTIPLIER_DMG;
        } else {
            landMultiplierDmg = 1.0f;
        }
    }

    @Override
    public final void applyFirstAbility(final Hero opponent) {
        float hpPercent = Constants.DRAIN_PRECENT + Constants.DRAIN_PRECENT_SCALE * getLevel();
        magicDamage = (int) Math.round(hpPercent * raceMultiplierDmg
                * Math.min(Constants.DRAIN_MAX_PROCENT * opponent.maxHp, opponent.hp)
                * landMultiplierDmg);
    }

    @Override
    public final void applySecondAbility(final Hero opponent) {
        float deflectPercent = Math.min(Constants.DEFLECT_PERCENT
                + Constants.DEFLECT_PERCENT_SCALE * getLevel(), Constants.DEFLECT_MAX_PERCENT);
        //daca oponentul nu a apucat sa atace, ii simulez atacul sau fara race multiplieri.

        Hero player = null;
        if (opponent.getRace().equals("P")) {
            player = new Pyromancer("P", opponent.getRowPos(), opponent.getColumnPos());
        }
        if (opponent.getRace().equals("K")) {
            player = new Knight("K", opponent.getRowPos(), opponent.getColumnPos());
        }
        if (opponent.getRace().equals("W")) {
            player = new Wizard("W", opponent.getRowPos(), opponent.getColumnPos());
        }
        if (opponent.getRace().equals("R")) {
            player = new Rogue("R", getRowPos(), opponent.getColumnPos());
        }
        player.setLevel(opponent.getLevel());
        player.landMultiplierDmg = opponent.landMultiplierDmg;

        if (opponent.getCritickAttack() == Constants.CRITICK_DMG_MULTIPLIER) {
            player.backstabCount = opponent.backstabCount - 1;
        } else {
            player.backstabCount = opponent.backstabCount;
        }

        int damage = 0;
        if (this.raceMultiplierDmg == 0) {
            return;
        }

        player.raceMultiplierDmg = 1.0f;
        player.totalDamage = 0;
        player.applyFirstAbility(this);
        player.totalDamage = Math.round(((player.firstAbilityDmg + player.firstAbilityDmgScaling
                * player.getLevel()) * player.landMultiplierDmg) * player.getCritickAttack());
        player.applySecondAbility(this);
        player.totalDamage = player.totalDamage +  Math.round((player.secondAbilityDmg
                + player.secondAbilityDmgScaling * player.getLevel()) * player.landMultiplierDmg);

        this.magicDamage = this.magicDamage + Math.round(deflectPercent * player.totalDamage
                * raceMultiplierDmg * landMultiplierDmg);

    }
    @Override
    public final String toString() {
        if (hp <= 0) {
            return ("W" + " dead");
        } else {
            return ("W" + " " + getLevel() + " " + getExp() + " " + hp + " " + getRowPos() + " "
                    + getColumnPos());
        }
    }
}
