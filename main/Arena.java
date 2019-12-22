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

        if (opponent.hp > 0 && p.hp <= 0 && !p.deadFromDot) {
            notifyObservers(p, opponent);
            opponent.setExperience(p.getLevel());
        }
        if (p.hp > 0 && opponent.hp <= 0 && !opponent.deadFromDot) {
            notifyObservers(opponent, p);
            p.setExperience(opponent.getLevel());
        }
    }

    public final void fight(final Hero p, final Hero opponent) {
        p.firstAbilityRaceMultiplier = opponent.isAttackedBy(p.firstAbility);
        if (p.firstAbilityRaceMultiplier != 1.0f) {
            p.firstAbilityRaceMultiplier += p.strategyRaceMultiplier;
        }
        p.applyFirstAbility(opponent);
        p.calculateDmgFirstAttack();

        p.secondAbilityRaceMultiplier = opponent.isAttackedBy(p.secondAbility);
        if (p.secondAbilityRaceMultiplier != 1.0f) {
            p.secondAbilityRaceMultiplier += p.strategyRaceMultiplier;
        }
        p.applySecondAbility(opponent);
        p.calculateDmgSecondAttack();

        p.calculateTotalDamage();
    }

    public void notifyObservers(Hero dead, Hero killer) {
        observer.update(dead, killer);
    }
}
