package main;
import fileio.FileSystem;
import hero.Hero;
import hero.Knight;
import hero.Pyromancer;
import hero.Rogue;
import hero.Wizard;

import java.util.ArrayList;


public class GameInputLoader {
	private final String mInputPath;
	private final String mOutputPath;

    GameInputLoader(final String inputPath, final String outputPath) {
        mInputPath = inputPath;
        mOutputPath = outputPath;
    }

    public final GameInput load() {
        ArrayList<String> matrix = new ArrayList<>();
       	ArrayList<Hero> players = new ArrayList<>();
        ArrayList<String> moves = new ArrayList<>();
        int rowDim = 0;
        int colDim = 0;
        int noPlayers = 0;
        int noRounds = 0;
    	try {
			FileSystem fs = new FileSystem(mInputPath, mOutputPath);
			rowDim = fs.nextInt();
			colDim = fs.nextInt();
			// setarea mapei cu Volacano,Land etc
			for (int i = 0; i < rowDim; ++i) {
				matrix.add(fs.nextWord());
			}

			// citirea pozitiei fiecarui jucator
			noPlayers = fs.nextInt();
			players = new ArrayList<>();
			for (int i = 0; i < noPlayers; ++i) {
				String race = fs.nextWord();
				if (race.equals("P")) {
					players.add(new Pyromancer("P", fs.nextInt(),
							fs.nextInt()));
				}
				if (race.equals("K")) {
					players.add(new Knight("K", fs.nextInt(), fs.nextInt()));
				}
				if (race.equals("W")) {
					players.add(new Wizard("W", fs.nextInt(), fs.nextInt()));
				}
				if (race.equals("R")) {
					players.add(new Rogue("R", fs.nextInt(), fs.nextInt()));
				}
			}

			// citirea mutarilor care vor fi executate de playeri

			noRounds = fs.nextInt();
			for (int i = 0; i < noRounds; ++i) {
				moves.add(fs.nextWord());
			}

			fs.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

        return new GameInput(matrix, players, moves, noPlayers, noRounds);
    }
}
