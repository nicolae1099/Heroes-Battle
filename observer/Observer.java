package observer;

import heroes.Hero;

import java.io.*;
import java.util.ArrayList;

public final class Observer {
    public String filename;
    public Observer(String filenamee) {
        this.filename = filenamee;
    }

    public void update(int round) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            fileWriter.write("~~ Round " + round + " ~~\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public void update() {
            try {
                FileWriter fileWriter = new FileWriter(filename, true);
                fileWriter.write("\n");
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public void update(Hero dead, Hero killer) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);

            fileWriter.write("Player " + convert(dead.getRace()) + " " + dead.id + " was killed by "
                    + convert(killer.getRace()) + " " + killer.id);
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(String typeOfAngel, int rowPos, int columnPos) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            fileWriter.write("Angel " + typeOfAngel + " was spawned at " + rowPos + " " + columnPos);
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(String typeofAngel, Hero player) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            if (typeofAngel.equals("Dracula") || typeofAngel.equals("DarkAngel")
                    || typeofAngel.equals("TheDoomer")) {
                fileWriter.write(typeofAngel + " hit " + convert(player.getRace()) + " " + player.id);
                fileWriter.write("\n");
            } else {
                fileWriter.write(typeofAngel + " helped " + convert(player.getRace())
                        + " " + player.id);
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Hero player, int hp, String typeOfAngel) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            if (hp <= 0) {
                fileWriter.write("Player " + convert(player.getRace())
                        + " " + player.id + " was killed by an angel");
                fileWriter.write("\n");
            }
            if (hp > 0 && typeOfAngel.equals("Spawner")) {
                fileWriter.write("Player " + convert(player.getRace())
                        + " " + player.id + " was brought to life by an angel");
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Hero player) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            fileWriter.write(convert(player.getRace()) + " " + player.id + " reached level "
                    + player.getLevel());
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(ArrayList<Hero> players) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            fileWriter.write("~~ Results ~~");
            fileWriter.write("\n");
            for (Hero it : players) {
                if (it.hp <= 0) {
                    fileWriter.write(it.getRace() + " dead");
                    fileWriter.write("\n");
                } else {
                    fileWriter.write((it.getRace() + " " + it.getLevel()
                            + " " + it.getExp() + " " + (int) it.hp + " "
                            + it.getRowPos() + " " + it.getColumnPos()));
                    fileWriter.write("\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
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
