package observer;

import heroes.Hero;

import java.util.ArrayList;

public final class Observer {
    public void update(int round) {
        System.out.println("~~ Round " + round + " ~~");
    }

    public void update() {
        System.out.println("");
    }
    public void update(Hero dead, Hero killer) {
        System.out.println("Player " + convert(dead.getRace()) + " " + dead.id + " was killed by "
                + convert(killer.getRace()) + " " + killer.id);
    }
    public void update(String typeOfAngel, int rowPos, int columnPos) {
        System.out.println("Angel " + typeOfAngel + " was spawned at " + rowPos + " " + columnPos);
    }
    public void update(String typeofAngel, Hero player) {
        if (typeofAngel.equals("Dracula") || typeofAngel.equals("DarkAngel")
                || typeofAngel.equals("TheDoomer")) {
            System.out.println(typeofAngel + " hit " + convert(player.getRace()) + " " + player.id);
        } else {
            System.out.println(typeofAngel + " helped " + convert(player.getRace())
                    + " " + player.id);
        }
    }

    public void update(Hero player) {
        System.out.println(convert(player.getRace()) + " " + player.id + " reached level "
                + player.getLevel());
    }

    public void update(ArrayList<Hero> players) {
        System.out.println("~~ Results ~~");
        for (Hero it : players) {
            if (it.hp <= 0) {
                System.out.println(it.getRace() + " dead");
            } else {
                System.out.println(it.getRace() + " " + it.getLevel()
                        + " " + it.getExp() + " " + (int) it.hp + " "
                        + it.getRowPos() + " " + it.getColumnPos());
            }
        }
    }

    public String convert(String abbreviation) {
        if (abbreviation.equals("K")) {
            return "Knight";
        } else if (abbreviation.equals("P")) {
            return "Pyromancer";
        } else if (abbreviation.equals("R")) {
            return "Rogue";
        } else {
            return "Wizard";
        }

    }

}
