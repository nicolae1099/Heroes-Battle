package angels;

public class AngelFactory {

    public Angel getInstance(String typeOfAngel) {
        if (typeOfAngel.equals("DamageAngel")) {
            return new DamageAngel();
        }
        else if (typeOfAngel.equals("DarkAngel")) {
            return new DarkAngel();
        }
        else if (typeOfAngel.equals("Dracula")) {
            return new Dracula();
        }
        else if (typeOfAngel.equals("GoodBoy")) {
            return new GoodBoy();
        }
        else if (typeOfAngel.equals("LevelUpAngel")){
            return new LevelUpAngel();
        }
        else if (typeOfAngel.equals("LifeGiver")) {
            return new LifeGiver();
        }
        else if (typeOfAngel.equals("SmallAngel")) {
            return new SmallAngel();
        }
        else if (typeOfAngel.equals("Spawner")) {
            return new Spawner();
        }
        else {
            System.out.println("Unknown type of angel"); //TODO de facut cu try-catch later
        }
        return new XPAngel();
    }
}
