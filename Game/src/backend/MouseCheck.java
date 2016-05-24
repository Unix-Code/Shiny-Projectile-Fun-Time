package backend;

import Graphics.Camera;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author David
 */
public class MouseCheck extends MouseAdapter {

    private Handler handler;
    private Camera cam;

    public MouseCheck(Handler handler, Camera cam) {
        this.handler = handler;
        this.cam = cam;
    }

    public void mousePressed(MouseEvent mouse) {
        if (mouse.getButton() == 1) {
            for (int i = 0; i < handler.objects.size(); i++) {
                Obj tempObject = handler.objects.get(i);
                if (tempObject.getId() == ID.Player) {
                    // Calculate vector of new Projectile
                    double theta = Math.atan2(mouse.getY()- (Game.height/2.0 + tempObject.getH()/2.0), mouse.getX() - (Game.width/2.0 + tempObject.getW()/2.0));
                    // System.out.println(theta);
                    double dx =  12.0 * Math.cos(theta);
                    double dy = 12.0 * Math.sin(theta);
                    // System.out.println("dx: " + dx + ", dy: " + dy + "\nX: " + tempObject.getX() + ", Y: " + tempObject.getY() + "\nMouseX: " + mouse.getX() + ", MouseY: " + mouse.getY() + "\nCamX: " + cam.getX() + ", CamY: " + cam.getY());
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
                    
                    double adjustment = 1;
                    
                    // if (dx/((Movable)tempObject).getVelX() > 0 && dy/((Movable)tempObject).getVelY() > 0) adjustment = 3;
                    // System.out.println("Adjustment: " + adjustment);
                    handler.addObject(new Projectile((int)(tempObject.getX() + tempObject.getW()/2.0 + spawnX), (int)(tempObject.getY() + tempObject.getH()/2.0 + spawnY), 4, 4, dx, dy, 0.2 * adjustment, 15, ID.FriendlyProjectile, handler));
                    // System.out.println("Bullet Created"); // Diagnostic
                }
            }
        }
    }
}
