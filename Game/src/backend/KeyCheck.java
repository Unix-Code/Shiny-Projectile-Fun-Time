package backend;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author David
 */
public class KeyCheck extends KeyAdapter {

    private Handler handler;

    private int xp, xm, yp, ym; //xm stands for x minus and xp stands for x plus etc.

    public KeyCheck(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                handler.keys[Keys.W.value] = true;
                break;
            case KeyEvent.VK_S:
                handler.keys[Keys.S.value] = true;
                break;
            case KeyEvent.VK_A:
                handler.keys[Keys.A.value] = true;
                break;
            case KeyEvent.VK_D:
                handler.keys[Keys.D.value] = true;
                break;
            case KeyEvent.VK_UP:
                handler.keys[Keys.Up.value] = true;
                break;
            case KeyEvent.VK_DOWN:
                handler.keys[Keys.Down.value] = true;
                break;
            case KeyEvent.VK_LEFT:
                handler.keys[Keys.Left.value] = true;
                break;
            case KeyEvent.VK_RIGHT:
                handler.keys[Keys.Right.value] = true;
                break;
        }
    }

//        if (!handler.keyStrokes.containsKey(key)) {
//            handler.pressKey(e);
//
//        }
    /*
        for (int i = 0; i < handler.objects.size(); i++) {
            // Obj tempObject = handler.objects.get(i);

            if (handler.objects.get(i).getId() == ID.Player) {
                //key events for player
                Player tempPlayer = (Player) handler.objects.get(i);

                switch (key) {
                    case KeyEvent.VK_W:
                        
                        // ym = 5;
                        
                        if (!tempPlayer.isJumping()) {
                            ym = 5;
                            tempPlayer.jumping = true;
                            System.out.println("Fucking hell" + "\nJumping: " + tempPlayer.jumping);
                        }
                        else {
                            ym = 0;
                            System.out.println("Hello my Friend");
                        }
                        break;
                    case KeyEvent.VK_A:
                        xm = 5;
                        
                        tempPlayer.setAnimation(tempPlayer.getWalkLeft());
                        tempPlayer.getAnimation().start();
                        break;
                    case KeyEvent.VK_S:
                        // yp = 10;
                        break;
                    case KeyEvent.VK_D:
                        xp = 5;
                        tempPlayer.setAnimation(tempPlayer.getWalkRight());
                        tempPlayer.getAnimation().start();
                        break;
                    case KeyEvent.VK_UP:
                        if (!tempPlayer.isJumping()) {
                            ym = 5;
                            tempPlayer.jumping = true;
                        }
                        else {
                            ym = 0;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        xm = 5;
                        tempPlayer.setAnimation(tempPlayer.getWalkLeft());
                        tempPlayer.getAnimation().start();
                        break;
                    case KeyEvent.VK_DOWN:
                        // yp = 10;
                        break;
                    case KeyEvent.VK_RIGHT:
                        xp = 5;
                        tempPlayer.setAnimation(tempPlayer.getWalkRight());
                        tempPlayer.getAnimation().start();
                        break;
                }

                tempPlayer.setVelX(xp - xm);
                tempPlayer.setVelY(yp - ym);

            }
        }*/
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                handler.keys[Keys.W.value] = false;
                break;
            case KeyEvent.VK_S:
                handler.keys[Keys.S.value] = false;
                break;
            case KeyEvent.VK_A:
                handler.keys[Keys.A.value] = false;
                break;
            case KeyEvent.VK_D:
                handler.keys[Keys.D.value] = false;
                break;
            case KeyEvent.VK_UP:
                handler.keys[Keys.Up.value] = false;
                break;
            case KeyEvent.VK_DOWN:
                handler.keys[Keys.Down.value] = false;
                break;
            case KeyEvent.VK_LEFT:
                handler.keys[Keys.Left.value] = false;
                break;
            case KeyEvent.VK_RIGHT:
                handler.keys[Keys.Right.value] = false;
                break;
        }

//        if (handler.keysHeld.containsKey(key)) {
//            handler.releaseKey(e);
//
//        }
        /*
        for (int i = 0; i < handler.objects.size(); i++) {
            Obj tempObject = handler.objects.get(i);

            if (tempObject.getId() == ID.Player) {
                //key events for player
                Player tempPlayer = (Player) tempObject;
                switch (key) {
                    case KeyEvent.VK_W:
                        ym = 0;
                        
                        break;
                    case KeyEvent.VK_A:
                        xm = 0;
                        tempPlayer.getAnimation().stop();
                        tempPlayer.getAnimation().reset();
                        // tempPlayer.getAnimation().start();
                        // tempPlayer.setAnimation(tempPlayer.getStand());
                        break;
                    case KeyEvent.VK_S:
                        yp = 0;
                        break;
                    case KeyEvent.VK_D:
                        xp = 0;
                        tempPlayer.getAnimation().stop();
                        tempPlayer.getAnimation().reset();
                        // tempPlayer.getAnimation().start();
                        // tempPlayer.setAnimation(tempPlayer.getStand());
                        break;
                    case KeyEvent.VK_UP:
                        //ym = 0;
                        // if (tempPlayer.isJumping()) tempPlayer.jumping = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        xm = 0;
                        tempPlayer.getAnimation().stop();
                        tempPlayer.getAnimation().reset();
                        // tempPlayer.getAnimation().start();
                        // tempPlayer.setAnimation(tempPlayer.getStand());
                        break;
                    case KeyEvent.VK_DOWN:
                        yp = 0;
                        break;
                    case KeyEvent.VK_RIGHT:
                        xp = 0;
                        tempPlayer.getAnimation().stop();
                        tempPlayer.getAnimation().reset();
                        //tempPlayer.getAnimation().start();
                        // tempPlayer.setAnimation(tempPlayer.getStand());
                        break;
                }
                
                tempPlayer.setVelX(xp - xm);
                tempPlayer.setVelY(yp - ym);
            }
        }*/
    }
}
