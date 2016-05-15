package backend;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author David
 */
public class MouseCheck extends MouseAdapter {

    private Handler handler;

    public MouseCheck(Handler handler) {
        this.handler = handler;
    }

    public void mousePressed(MouseEvent mouse) {
        if (mouse.getButton() == 1) {
            for (int i = 0; i < handler.objects.size(); i++) {
                Obj tempObject = handler.objects.get(i);
                if (tempObject.getId() == ID.Player) {
                    // Calculate vector of new Projectile
                    double theta = Math.atan2(mouse.getY() - (tempObject.getY() + tempObject.getH()/2.0), mouse.getX() - (tempObject.getX() + tempObject.getW()/2.0));
                    double dx =  12.0 * Math.cos(theta);
                    double dy = 12.0 * Math.sin(theta);
                    
                    double ratioX = (tempObject.getW()/2)/(Math.abs(dx));
                    double ratioY = (tempObject.getH()/2)/(Math.abs(dy));
                    
                    double spawnX;
                    double spawnY;
                    
                    if (ratioX < ratioY) {
                        spawnX = dx * ratioX * 1.2 + ((Movable)tempObject).getVelX();
                        spawnY = dy * ratioX * 1.2 + ((Movable)tempObject).getVelY();
                    }
                    else {
                        spawnX = dx * ratioY * 1.2 + ((Movable)tempObject).getVelX();
                        spawnY = dy * ratioY * 1.2 + ((Movable)tempObject).getVelY();
                    }
                    
                    
                    handler.addObject(new Projectile((int)(tempObject.getX() + tempObject.getW()/2.0 + spawnX), (int)(tempObject.getY() + tempObject.getH()/2.0 + spawnY), 4, 4, dx , dy, 0.2, 15, ID.FriendlyProjectile, handler));
                    // System.out.println("Bullet Created"); // Diagnostic
                }
            }
        }
    }
}
