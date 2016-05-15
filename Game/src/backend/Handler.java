package backend;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author David
 */
public class Handler {
    ArrayList<Obj> objects;
    boolean[] keys;
    private int xp, xm, yp, ym;
    
    public Handler() {
        objects = new ArrayList();
        keys = new boolean[8];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = false;
        }
        xp = 0;
        xm = 0;
        yp = 0;
        ym = 0;
    }

    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            Obj tempObject = objects.get(i);
            
            if (tempObject.getId() == ID.Player) {
                handleKeyStrokes((Player) tempObject);
                ((Player)tempObject).tick();
            }
            else {
                tempObject.tick();
            }
        }
        System.out.println(Arrays.toString(keys)); // Diagnostic

    }

    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            Obj tempObject = objects.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(Obj object) {
        objects.add(object);
    }

    public void removeObject(Obj object) {
        objects.remove(object);
    }

    private void handleKeyStrokes(Player tempPlayer) {

        if (keys[Keys.W.value]) {
            ym = 5;
            tempPlayer.jumping = true;
        }
        else {
            ym = 0;
            tempPlayer.jumping = false;
        }

        if (keys[Keys.A.value]) {
            xm = 5;
                tempPlayer.setAnimation(tempPlayer.getWalkLeft());
                tempPlayer.getAnimation().start();
            
        }
        else { 
            xm = 0;
            tempPlayer.getAnimation().stop();
            tempPlayer.getAnimation().reset();
//            tempPlayer.getAnimation().start();
//            tempPlayer.setAnimation(tempPlayer.getStand());
        }
        
        if (keys[Keys.S.value]) {
            yp = 5;
        }
        else {
            yp = 0;
        }

        if (keys[Keys.D.value]) {
            xp = 5;
                tempPlayer.setAnimation(tempPlayer.getWalkRight());
                tempPlayer.getAnimation().start();
            
        }
        else {
            xp = 0;
            tempPlayer.getAnimation().stop();
            tempPlayer.getAnimation().reset();
//            tempPlayer.getAnimation().start();
//            tempPlayer.setAnimation(tempPlayer.getStand());
        }

//        if (keys[Keys.A.value] && keys[Keys.D.value]) {
//            xp = 0;
//            xm = 0;
//            tempPlayer.getAnimation().stop();
//            tempPlayer.getAnimation().reset();
//        }

        tempPlayer.setVelX(xp - xm);
        tempPlayer.setVelY(yp - ym);
    }
}


