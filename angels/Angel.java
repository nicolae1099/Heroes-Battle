package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import heroes.Hero;
import observer.Observer;

public interface Angel {
    void visit(Knight knight);
    void visit(Pyromancer pyromancer);
    void visit(Rogue rogue);
    void visit(Wizard wizard);
    Observer OBSERVER = new Observer();
    default void notifyObserver(String typeOfAngel, int rowPos, int colPos) {
        OBSERVER.update(typeOfAngel, rowPos, colPos);
    }
    default void notifyObserver(String typeOfAngel, Hero player) {
        OBSERVER.update(typeOfAngel, player);
    }
    default void notifyObserver(Hero player, int hp, String typeOfAngel) {
        OBSERVER.update(player, hp, typeOfAngel);
    }
}
