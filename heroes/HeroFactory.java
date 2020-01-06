package heroes;

public class HeroFactory {
    public final Hero getInstance(final String typeOfHero, final int rowPos,
                                  final int colPos, final int id) {
        if (typeOfHero.equals("K")) {
            return new Knight(typeOfHero, rowPos, colPos, id);
        } else if (typeOfHero.equals("P")) {
            return new Pyromancer(typeOfHero, rowPos, colPos, id);
        } else if (typeOfHero.equals("W")) {
            return new Wizard(typeOfHero, rowPos, colPos, id);
        } else if (typeOfHero.equals("R")) {
            return new Rogue(typeOfHero, rowPos, colPos, id);
        }
        return null;
    }
}
