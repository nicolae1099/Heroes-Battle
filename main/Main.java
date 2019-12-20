package main;

import hero.Hero;

import java.util.ArrayList;


public final class Main {
    private Main() {
    }
    public static void main(final String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();
        ArrayList<Hero> players = gameInput.getPlayers();
        ArrayList<String> board = gameInput.getMatrix();
        Arena arena = new Arena();
        for (int k = 0; k < gameInput.getMoves().size(); ++k) {
            for (Hero player : players) {
                player.takeOverTimeDmg();
            }

            for (int j = 0; j < gameInput.getMoves().get(k).length(); ++j) {
                char c = gameInput.getMoves().get(k).charAt(j);
                players.get(j).movePositionIfPossible(String.valueOf(c));
                String landType = String.valueOf(board.get(players.get(j).getRowPos()).
                        charAt(players.get(j).getColumnPos()));
                players.get(j).setLandMultiplier(landType);
            }
            for (int i = 0; i < players.size(); ++i) {
                for (int j = i + 1; j < players.size(); ++j) {
                    if (players.get(i).getRowPos() == players.get(j).getRowPos()
                            && players.get(i).getColumnPos() == players.get(j).getColumnPos()) {
                        if (players.get(i).hp > 0  && players.get(j).hp > 0) {
                            arena.battle(players.get(i), players.get(j));
                        }
                    }
                }
                for (int p = 0; p < players.size(); p++) {
                    players.get(i).levelUp();
                }
            }
        }

        String filename = args[1];
        try {
            fileio.implementations.FileWriter fileWriter =
                    new fileio.implementations.FileWriter(filename);
            for (Hero it : players) {
                if (it.hp <= 0) {
                    fileWriter.writeWord(it.getRace() + " dead");
                    fileWriter.writeNewLine();
                } else {
                    fileWriter.writeWord((it.getRace() + " " + it.getLevel()
                            + " " + it.getExp() + " " + (int) it.hp + " "
                            + it.getRowPos() + " " + it.getColumnPos()));
                    fileWriter.writeNewLine();
                }
            }
            fileWriter.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
