package main;
import heroes.Hero;
import observer.Observer;

public class Arena {
    public Observer observer = new Observer();

    public Arena() {

    }
    public final void battle(final Hero p, final Hero opponent) {
        fight(p, opponent);
        fight(opponent, p);

        p.hp -= opponent.totalDamage;
        opponent.hp -= p.totalDamage;

        if (opponent.hp <= 0 && !opponent.deadFromDot) {
            if (p.hp > 0) {
                p.setExperience(opponent.getLevel());
            }
            notifyObservers(opponent, p);
        }

        if (p.hp <= 0 && !p.deadFromDot) {
            if (opponent.hp > 0) {
                opponent.setExperience(p.getLevel());
            }
            notifyObservers(p, opponent);
        }
    }

    public final void fight(final Hero p, final Hero opponent) {
        p.firstAbilityRaceMultiplier = opponent.isAttackedBy(p.firstAbility);
        if (Float.compare(p.firstAbilityRaceMultiplier, 1.0f) != 0) {
            p.firstAbilityRaceMultiplier += p.strategyRaceMultiplier;
            p.firstAbilityRaceMultiplier += p.angelsRaceMultiplier;
        }
        p.applyFirstAbility(opponent);
        p.calculateDmgFirstAttack();

        p.secondAbilityRaceMultiplier = opponent.isAttackedBy(p.secondAbility);
        if (Float.compare(p.secondAbilityRaceMultiplier, 1.0f) != 0
                && Float.compare(p.secondAbilityRaceMultiplier, 0.0f) != 0) {
            p.secondAbilityRaceMultiplier += p.strategyRaceMultiplier;
            p.secondAbilityRaceMultiplier += p.angelsRaceMultiplier;
        }
        p.applySecondAbility(opponent);
        p.calculateDmgSecondAttack();

        p.calculateTotalDamage();
    }

    public void notifyObservers(Hero dead, Hero killer) {
        observer.update(dead, killer);
    }
}
