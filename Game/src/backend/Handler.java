package backend;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Handler {
    ArrayList<Obj> objects;
    ArrayList<Character> deadThings;
    boolean[] keys;
    private int xp, xm, yp, ym;
    
    public Handler() {
        objects = new ArrayList<>();
        deadThings = new ArrayList<>();
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

    public Obj removeObject(Obj object) {
        objects.remove(object);
        return object;
    }
    
    public void addCorpse(Character object) {
        deadThings.add(object);
    }
    
    private void handleKeyStrokes(Player tempPlayer) {

        if (keys[Keys.W.value]) {
            ym = 5;
            tempPlayer.setAnimation(tempPlayer.getWalkBack());
            tempPlayer.getAnimation().start();
        }
        else {
            ym = 0;
        }

        if (keys[Keys.A.value]) {
            xm = 5;
                tempPlayer.setAnimation(tempPlayer.getWalkLeft());
                tempPlayer.getAnimation().start();
        }
        else { 
            xm = 0;
        }
        
        if (keys[Keys.S.value]) {
            yp = 5;
            tempPlayer.setAnimation(tempPlayer.getWalkForward());
            tempPlayer.getAnimation().start();
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
        }
        boolean allFalse = true;
        
        for (int i = 0; i < keys.length; i++) {
            boolean key = keys[i];
            if (key) allFalse = false;
        }
        
        if (allFalse) {
            tempPlayer.getAnimation().stop();
            tempPlayer.getAnimation().reset();
            tempPlayer.setAnimation(tempPlayer.getStand());
        }
        
        tempPlayer.setVelX(xp - xm);
        tempPlayer.setVelY(yp - ym);
    }
}


