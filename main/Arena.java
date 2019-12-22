package main;
import heroes.Hero;

public class Arena {
    public Arena() {

    }
    public final void battle(final Hero p, final Hero opponent) {
        fight(p, opponent);
        fight(opponent, p);

        p.hp -= opponent.totalDamage;
        opponent.hp -= p.totalDamage;

        if (opponent.hp > 0 && p.hp <= 0 && !p.deadFromDot) {
            opponent.setExperience(p.getLevel());
        }
        if (p.hp > 0 && opponent.hp <= 0 && !opponent.deadFromDot) {
            p.setExperience(opponent.getLevel());
        }
    }

    public final void fight(final Hero p, final Hero opponent) {
        p.firstAbilityRaceMultiplier = opponent.isAttackedBy(p.firstAbility);
        p.applyFirstAbility(opponent);
        p.calculateDmgFirstAttack();

        p.secondAbilityRaceMultiplier = opponent.isAttackedBy(p.secondAbility);
        p.applySecondAbility(opponent);
        p.calculateDmgSecondAttack();

        p.calculateTotalDamage();
    }
}
