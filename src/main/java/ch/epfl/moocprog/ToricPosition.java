package ch.epfl.moocprog;

import ch.epfl.moocprog.utils.Vec2d;
import java.util.*;
import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.*;

public final class ToricPosition {
    private Vec2d position;


    /**
     * Chapitre 2 - 2
     * @param x
     * @param y
     */
    public ToricPosition(double x, double y) {
        this.position = clampedPosition(x,y);
    }

    /**
     *Chapitre 2 - 3
     * @param vec2d
     */
    public ToricPosition(Vec2d vec2d) {
        this.position = clampedPosition(vec2d.getX(), vec2d.getY());
    }

    /**
     * Chapitre 2 - 4
     */
    public ToricPosition() {
        this.position = new Vec2d(0,0);
    }

    /**
     * Chapitre 2 - 5
     * @param that
     * @return
     */
    public ToricPosition add(ToricPosition that){
        return new ToricPosition(position.add(that.getPosition()));
        //return new ToricPosition(this.vec2d.getX() + that.vec2d.getX(),this.vec2d.getY() + that.vec2d.getY());
    }

    /**
     * Chapitre 2 - 6
     * @param vec
     * @return
     */
    public ToricPosition add(Vec2d vec){
        return new ToricPosition(position.add(vec));
        //return new ToricPosition(this.vec2d.getX() + that.vec2d.getX(),this.vec2d.getY() + that.vec2d.getY());
    }

    /**
     * Chapitre 2 - 7
     * @return
     */
    public Vec2d toVec2d(){
        return this.position;
    }

    /**
     * Chapitre 2 - 8
     * @param that
     * @return
     */
    public Vec2d toricVector(ToricPosition that){
        // calculer le plus petit vecteur allant de l'instance courante à that
        int width = getConfig().getInt(WORLD_WIDTH);
        int heigth = getConfig().getInt(WORLD_HEIGHT);
//        width = 10;
//        heigth = 10;
        Vec2d vector = null;
        double minDistance = 999999999999999999999999.9;
        for(int i = - 1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                double distance = this.position.distance(that.position.add(new Vec2d(i*width, j*heigth)));
                if(distance < minDistance){
                    minDistance = distance;
                    vector = that.position.add(new Vec2d(i*width, j*heigth));
                }
            }
        }
        return vector.minus(this.position);
    }

    /**
     * Chapitre 2 _ 9
     * @param that
     * @return
     */
    public double toricDistance(ToricPosition that){

        return toricVector(that).length();
    }

    public Vec2d getPosition() {
        return position;
    }

    protected void setPosition(Vec2d position) {
        this.position = position;
    }

    /**
     * Chapitre 2 - 1
     *
     * @param x
     * @param y
     * @return
     */
    public static Vec2d clampedPosition(double x, double y) {
        int width = getConfig().getInt(WORLD_WIDTH);
        int heigth = getConfig().getInt(WORLD_HEIGHT);

        while(x < 0 || x >= width){
            if (x < 0) x += width;
            if (x >= width) x -= width;
        }

       while(y < 0 || y >= heigth){
           if (y < 0) y += heigth;
           if (y >= heigth) y -= heigth;
       }

        return new Vec2d(x, y);
    }
}
