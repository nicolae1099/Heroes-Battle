package main;

import angels.Angel;
import angels.AngelFactory;
import heroes.Hero;
import observer.Observer;
import java.util.ArrayList;


public final class Main {
    private Main() {
    }
    public static void main(final String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();
        ArrayList<Hero> players = gameInput.getPlayers();
        BattleMap battleMap = BattleMap.getInstance();
        battleMap.getInstance().setBattleground(gameInput.getMatrix());
        ArrayList<ArrayList<String>> angels = gameInput.getAngels();
        Observer observer = new Observer(args[1]);
        Arena arena = new Arena(observer);
        for (int k = 0; k < gameInput.getMoves().size(); ++k) {
            observer.update(k + 1);
            playersTakeDmgOverTime(players);
            playersMove(players, gameInput, k, observer);
            playersFight(players, arena);
            levelUpPlayers(players);
            angelsCome(players, angels, k, observer);
        }
        observer.update(players);
    }

    private static void angelsCome(final ArrayList<Hero> players,
                                   final ArrayList<ArrayList<String>> angels, final int index,
                                   final Observer observer) {
        for (String word : angels.get(index)) {
            String[] namesList = word.split(",");
            String typeOfAngel = namesList[0];
            int rowPos = Integer.parseInt(namesList[1]);
            int columnPos = Integer.parseInt(namesList[2]);
            AngelFactory angelFactory =  new AngelFactory();
            Angel angel = angelFactory.getInstance(typeOfAngel);
            angel.notifyObserver(typeOfAngel, rowPos, columnPos, observer);
            for (Hero it : players) {
                if (it.getRowPos() == rowPos && it.getColumnPos() == columnPos) {
                    if (it.isAlive() && !typeOfAngel.equals("Spawner")) {
                        angel.notifyObserver(typeOfAngel, it, observer);
                        it.accept(angel);
                        angel.notifyObserver(it, it.hp, typeOfAngel, observer);
                    }
                    if (!it.isAlive() && typeOfAngel.equals("Spawner")) {
                        angel.notifyObserver(typeOfAngel, it, observer);
                        it.accept(angel);
                        angel.notifyObserver(it, it.hp, typeOfAngel, observer);
                    }
                }
            }
        }
        observer.update();
    }
    private static void levelUpPlayers(final ArrayList<Hero> players) {
        for (int p = 0; p < players.size(); p++) {
            players.get(p).levelUp();
        }
    }
    private static void playersFight(final ArrayList<Hero> players, final Arena arena) {
        for (int i = 0; i < players.size(); ++i) {
            for (int j = i + 1; j < players.size(); ++j) {
                if (players.get(i).getRowPos() == players.get(j).getRowPos()
                        && players.get(i).getColumnPos() == players.get(j).getColumnPos()) {
                    if (players.get(i).hp > 0  && players.get(j).hp > 0) {
                        arena.battle(players.get(i), players.get(j));
                    }
                }
            }
        }
    }
    private static void playersMove(final ArrayList<Hero> players, final GameInput gameInput,
                                    final int index, final Observer observer) {
        for (int j = 0; j < gameInput.getMoves().get(index).length(); ++j) {
            players.get(j).registerObserver(observer);
            char c = gameInput.getMoves().get(index).charAt(j);
            if (!players.get(j).isStun() && players.get(j).isAlive()) {
                players.get(j).applyStrategy();
            }
            if (players.get(j).isAlive()) {
                players.get(j).movePositionIfPossible(String.valueOf(c));
                if (players.get(j).getColumnPos() >= 0 && players.get(j).getRowPos() >= 0) {
                    ArrayList<String>  board = BattleMap.getInstance().getBattleground();
                    String landType = String.valueOf(board.get(players.get(j).getRowPos()).
                            charAt(players.get(j).getColumnPos()));
                    players.get(j).setLandMultiplier(landType);
                }
            }
        }
    }
    private static void playersTakeDmgOverTime(final ArrayList<Hero> players) {
        for (Hero player : players) {
            player.takeOverTimeDmg();
        }
    }
}
