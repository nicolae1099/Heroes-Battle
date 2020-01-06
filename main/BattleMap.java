package main;

import java.util.ArrayList;

public final class BattleMap {
    private static BattleMap instance;
    private static ArrayList<String> battleground = new ArrayList<>();
    private BattleMap() {
    }
    public static BattleMap getInstance() {
        if (instance == null) {
            return new BattleMap();
        }
        return instance;
    }
    public ArrayList<String> getBattleground() {
        return battleground;
    }
    public void setBattleground(final ArrayList<String> battleground) {
        this.battleground = battleground;
    }
}
