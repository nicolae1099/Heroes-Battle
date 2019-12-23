package observer;

import heroes.Hero;

import java.io.IOException;
import java.util.ArrayList;

public final class Observer {
    String filename;
    public void update(String filename) {
        this.filename = filename;
    }
    public void update(int round) {
        try {
            fileio.implementations.FileWriter fileWriter =
                    new fileio.implementations.FileWriter(filename);
            fileWriter.writeWord("~~ Round " + round + " ~~");
            fileWriter.writeNewLine();
            //System.out.println("~~ Round " + round + " ~~");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void update(Hero player, int hp, String typeOfAngel) {
        if (hp <= 0) {
            System.out.println("Player " + convert(player.getRace())
                    + " " + player.id + " was killed by an angel");
        }
        if (hp > 0 && typeOfAngel.equals("Spawner")) {
            System.out.println("Player " + convert(player.getRace())
                    + " " + player.id + " was brought to life by an angel");
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
