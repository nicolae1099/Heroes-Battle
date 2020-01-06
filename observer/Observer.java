package observer;

import heroes.Hero;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public final class Observer {
    private String filename;
    public Observer(final String filenamee) {
        this.filename = filenamee;
    }

    public void update(final int round) {
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
    public void update(final Hero dead, final Hero killer) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);

            fileWriter.write("Player " + convert(dead.getRace()) + " "
                    + dead.getId() + " was killed by "
                    + convert(killer.getRace()) + " " + killer.getId());
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(final String typeOfAngel, final int rowPos, final int columnPos) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            fileWriter.write("Angel " + typeOfAngel + " was spawned at "
                    + rowPos + " " + columnPos);
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(final String typeofAngel, final Hero player) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            if (typeofAngel.equals("Dracula") || typeofAngel.equals("DarkAngel")
                    || typeofAngel.equals("TheDoomer")) {
                fileWriter.write(typeofAngel + " hit "
                        + convert(player.getRace()) + " " + player.getId());
                fileWriter.write("\n");
            } else {
                fileWriter.write(typeofAngel + " helped " + convert(player.getRace())
                        + " " + player.getId());
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(final Hero player, final int hp, final String typeOfAngel) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            if (hp <= 0) {
                fileWriter.write("Player " + convert(player.getRace())
                        + " " + player.getId() + " was killed by an angel");
                fileWriter.write("\n");
            }
            if (hp > 0 && typeOfAngel.equals("Spawner")) {
                fileWriter.write("Player " + convert(player.getRace())
                        + " " + player.getId() + " was brought to life by an angel");
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(final Hero player) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            fileWriter.write(convert(player.getRace()) + " " + player.getId() + " reached level "
                    + player.getLevel());
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(final ArrayList<Hero> players) {
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

    public String convert(final String abbreviation) {
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
