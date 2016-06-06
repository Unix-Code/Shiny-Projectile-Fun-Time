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
    private Player player;
    boolean[] keys;
    private int xp, xm, yp, ym;
    
    public Handler() {
        player = new Player(Game.width / 2, Game.height / 2, 64, 64, 100, this);
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
        handleKeyStrokes(player);
        player.tick();
        
        for (int i = 0; i < objects.size(); i++) {
            Obj object = objects.get(i);
            object.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            Obj object = objects.get(i);
            object.render(g);
        }
        player.render(g);
    }
    
    public Player getPlayer() {
        return player;
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


