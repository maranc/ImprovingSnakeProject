import javax.swing.*;
import java.awt.*;

class ScoreboardWindow extends JFrame{
    public static JFrame f;
    public static JLabel l;
    public static String p1Score,p2Score,pInfo;


    public ScoreboardWindow(){

        JFrame frame = new JFrame("Score");
        JLabel s1 = new JLabel();
        JLabel s2 = new JLabel();
        JLabel s3 = new JLabel();
        JPanel panel = new JPanel();


        s1.setText(p1Score);
        s2.setText(p2Score);
        s3.setText(pInfo);

        s1.setFont (s1.getFont ().deriveFont (20.0f));
        s2.setFont (s2.getFont ().deriveFont (20.0f));
        s3.setFont(s3.getFont().deriveFont(20.0f));
        panel.add(s1);
        panel.add(s2);
        panel.add(s3);

        frame.add(panel);
        getContentPane().setLayout(new GridLayout(10,10,0,0));
        getContentPane().add(s1);
        getContentPane().add(s2);
        getContentPane().add(s3);



    }
    public void setScore(int p1, int p2, String message){
        p1Score = "Player 1: " + Integer.toString(p1);
        p2Score = "Player 2: " + Integer.toString(p2);
        pInfo = message;

        if (p2 == -1){
            p2Score = "";
            pInfo = "Game Over";
        }


    }
}
