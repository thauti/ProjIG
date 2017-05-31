package Controller;


import java.io.*;

public class ScoreController{
    public static int loadScore()
    {
        try {
            FileReader fr = new FileReader("score.txt");
            BufferedReader br = new BufferedReader(fr);
            String ligne = "";
            int score;
           ligne = br.readLine();


            if(ligne != null) {
                System.out.println("ok");
                score = Integer.parseInt(ligne);
                return score;
            }
            else return 0;
        } catch (Exception e) {

            return 0;
        }
    }
    public static void saveScore(int score)
    {
        try {
            Writer wr = new FileWriter("score.txt");
            wr.write(String.valueOf(score));
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
