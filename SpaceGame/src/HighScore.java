/**
 * reads ,sorts and writes the highscores
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HighScore {

    private static List<Integer> highScores;

    /**
     * reads from file
     * @param game
     */
    public HighScore(GamePanel game){
        File file = new File("scores");
        highScores = new ArrayList<>();
        try {

            Scanner sc = new Scanner(file);
            sc.useDelimiter(",");
            for(int i=0 ; i<10 ; i++){
                highScores.add(Integer.parseInt(sc.next()));
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * sorts the scores
     */
    public static void sort(){
        for(int i=0 ; i<highScores.size() ; i++){
            for(int j=0 ; j<highScores.size() ; j++){
                if(highScores.get(i) > highScores.get(j)){
                    int temp = highScores.get(i);
                    highScores.set(i , highScores.get(j));
                    highScores.set(j, temp);
                }
            }
        }
    }

    public static List<Integer> getHighScores() {
        return highScores;
    }

    /**
     * writes to file
     */
    public static void writeFile(){
        Scanner in = new Scanner(System.in);
        File outFile = new File("scores");
        PrintWriter out = null;
        try {
            out = new PrintWriter(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0 ; i<10 ; i++){
            sb.append(String.valueOf(highScores.get(i)) + ",");
        }
        if (outFile.exists())
        {
            out.write(sb.toString());
        }
        out.close();
    }
}
