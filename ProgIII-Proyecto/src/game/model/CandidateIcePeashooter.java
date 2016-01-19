package game.model;

import java.awt.*;

/**
 * Created by yiqin on 12/1/14.
 */
public class CandidateIcePeashooter extends Peashooter {

    public CandidateIcePeashooter(int x, int y){
        super(x,y);
        mainColor = Color.CYAN;
        typeIndicator = 1;
    }

    public CandidateIcePeashooter(Point newPoint){
        super(newPoint);
        mainColor = Color.CYAN;
        typeIndicator = 1;
    }

}