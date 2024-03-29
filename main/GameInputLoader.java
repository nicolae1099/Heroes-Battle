package main;

import fileio.FileSystem;
import heroes.Hero;
import heroes.HeroFactory;

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
        ArrayList<ArrayList<String>> angels = new ArrayList<>();
        int rowDim = 0;
        int numPlayers = 0;
        int numRounds = 0;
        int colDim;
        try {
            FileSystem fs = new FileSystem(mInputPath, mOutputPath);
            rowDim = fs.nextInt();
            colDim = fs.nextInt();
            // setarea mapei cu Volacano,Land etc
            for (int i = 0; i < rowDim; ++i) {
                matrix.add(fs.nextWord());
            }

            // citirea pozitiei fiecarui jucator
            numPlayers = fs.nextInt();
            players = new ArrayList<>();
            HeroFactory heroFactory = new HeroFactory();
            for (int i = 0; i < numPlayers; ++i) {
                String race = fs.nextWord();
                players.add(heroFactory.getInstance(race, fs.nextInt(), fs.nextInt(), i));
            }

            // citirea mutarilor care vor fi executate de playeri
            numRounds = fs.nextInt();
            for (int i = 0; i < numRounds; ++i) {
                moves.add(fs.nextWord());
            }

            for (int i = 0; i < numRounds; ++i) {
                int numAngels = fs.nextInt();
                ArrayList<String> temp = new ArrayList<>(numAngels);
                for (int j = 0; j < numAngels; ++j) {
                    String word = fs.nextWord();
                    temp.add(word);
                }
                angels.add(temp);
            }
            fs.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return new GameInput(matrix, players, moves, numPlayers, numRounds, angels);
    }
}
