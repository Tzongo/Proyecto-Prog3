package game.model;

import java.awt.*;
import java.util.ArrayList;

import controller.Game;

/**
 * Created by yiqin on 12/3/14.
 */
public class CrazyZombie extends Sprite {

    private final static int ZOMBIE_RADIUS = 30;
    private final static int SCALER = 1;

    private boolean isSwingToLeft = true;
    public int stepLength = 10;
    private int leftFootX = -stepLength;
    private int rightFootX = stepLength;

    public int speed = 1;
    private int newSpeed = 1;

    public int speedRatio = 1;

    private boolean isFozen = false;

    public int nSize = 3;

    private int iceTime = 0;


    public Color mainColor = Color.red;



    public Point handPoint;

    public CrazyZombie(int y){

        super();

        stepLength = 6;

        nSize = 2;
        speedRatio = 5;

        // setCenter(new Point((int)getCenter().getX(), (int)getCenter().getY()));


        ArrayList<Point> pntCs = new ArrayList<Point>();

        setRadius(ZOMBIE_RADIUS);


        if (y<100){
            y = 300;
        }

        setCenter(new Point(1190, y));

        setDeltaX(-speed*0.5);

        pntCs.add(new Point(0*SCALER, 18*SCALER-10));

        //right points
        pntCs.add(new Point(10*SCALER, 18*SCALER-10));
        pntCs.add(new Point(12*SCALER, 20*SCALER-10));
        pntCs.add(new Point(12*SCALER, 18*SCALER-10));
        pntCs.add(new Point(12*SCALER, 0*SCALER-10));
        pntCs.add(new Point(11*SCALER, -2*SCALER-10));
        pntCs.add(new Point(4*SCALER, -3*SCALER-10));
        pntCs.add(new Point(2*SCALER, -10*SCALER-10));


        //left points
        pntCs.add(new Point(-2*SCALER, -10*SCALER-10));
        pntCs.add(new Point(-4*SCALER, -3*SCALER-10));
        pntCs.add(new Point(-11*SCALER, -2*SCALER-10));
        pntCs.add(new Point(-12*SCALER, 0*SCALER-10));
        pntCs.add(new Point(-12*SCALER, 18*SCALER-10));

        assignPolarPoints(pntCs);
        setOrientation(-100);
    }


    public void move(){
        super.move();

        int x = getCenter().x;
        int y = getCenter().y;

        int xUpdate = x+(int)getDeltaX();
        setCenter(new Point(xUpdate, y));

        if(xUpdate<50){
            System.out.println("Game Over....................");
            CommandCenter.zombieCrossYard();
        }
    }

    @Override
    public void draw(Graphics g) {
        updateSpeed();
        if(speed==0){
            g.setColor(Color.cyan);
            g.fillRect(getCenter().x-30, getCenter().y-20, 61, 62);
            iceTime++;
        }

        if(iceTime==200){
            recover();
        }

        super.draw(g);
            // 1
        g.setColor(mainColor);
        g.fillPolygon(getXcoords(), getYcoords(), dDegrees.length);
            // 2
        g.setColor(Color.lightGray);
        g.drawPolygon(getXcoords(), getYcoords(), dDegrees.length);
            // 4
        g.setColor(Color.darkGray);
        g.fillOval(getCenter().x-15, getCenter().y-5, 12, 10);




        g.setColor(mainColor);
        if(leftFootX < -stepLength){
            isSwingToLeft = false;
        }
        else if(leftFootX > stepLength){
            isSwingToLeft = true;
        }

        if (isSwingToLeft){
            leftFootX=leftFootX-speed;
            rightFootX=rightFootX+speed;
        }
        else {
            leftFootX=leftFootX+speed;
            rightFootX=rightFootX-speed;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(getCenter().x+6,getCenter().y+20,getCenter().x-leftFootX, getCenter().y+40);
        g2.drawLine(getCenter().x+6,getCenter().y+20,getCenter().x-rightFootX, getCenter().y+40);

        g2.setStroke(new BasicStroke(2));
        handPoint = new Point(getCenter().x-15, getCenter().y+20+10);
        g2.drawLine(getCenter().x,getCenter().y+10+15, (int)handPoint.getX(), (int)handPoint.getY());
        g2.drawLine(getCenter().x,getCenter().y+10+10, getCenter().x+12, getCenter().y+18+10);

        g.setColor(Color.black);
        g.drawLine(getCenter().x-13,getCenter().y+14,getCenter().x, getCenter().y+9);

        g2.setStroke(new BasicStroke(1));
    }

    public int getSize(){
        return nSize;
    }

    public void isHit(int bulletType){
        nSize--;

        if (bulletType==1){
            frozen();

        }
        else {


        }
    }

    public void frozen(){
        speed = 0;
        iceTime = 0;
        setDeltaX(-speed*0.25);
        isFozen = true;
    }

    public void recover(){
        speed =checkSpeed();
        iceTime = 0;
        setDeltaX(-speed*0.5);
    }

    public int checkSpeed(){
        if(isFozen){
            newSpeed = 0;
            return newSpeed;
        }

        if(CommandCenter.getLevel()>2){
            newSpeed = 2;
            setDeltaX(-speed*0.5);
            stepLength = 8;
        }
        else {
            newSpeed = 1; // double size?
        }
        return newSpeed;
    }

    public void updateSpeed(){
        speed = checkSpeed()*speedRatio;
        setDeltaX(-speed*0.5);
    }


}
