package ch.epfl.moocprog;

import ch.epfl.moocprog.utils.Vec2d;

import java.util.*;

import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.*;

public final class ToricPosition {
    private Vec2d position;


    /**
     * Chapitre 1 - 2
     * @param x
     * @param y
     */
    public ToricPosition(double x, double y) {
        this.position = clampedPosition(x,y);
    }

    /**
     *Chapitre 1 - 3
     * @param vec2d
     */
    public ToricPosition(Vec2d vec2d) {
        this.position = clampedPosition(vec2d.getX(), vec2d.getY());
    }

    /**
     * Chapitre 1 - 4
     */
    public ToricPosition() {
        this.position = new Vec2d(0,0);
    }

    /**
     * Chapitre 1 - 5
     * @param that
     * @return
     */
    public ToricPosition add(ToricPosition that){
        return new ToricPosition(position.add(that.getPosition()));
        //return new ToricPosition(this.vec2d.getX() + that.vec2d.getX(),this.vec2d.getY() + that.vec2d.getY());
    }

    /**
     * Chapitre 1 - 6
     * @param vec
     * @return
     */
    public ToricPosition add(Vec2d vec){
        return new ToricPosition(position.add(vec));
        //return new ToricPosition(this.vec2d.getX() + that.vec2d.getX(),this.vec2d.getY() + that.vec2d.getY());
    }

    /**
     * Chapitre 1 - 7
     * @return
     */
    public Vec2d toVec2d(){
        return this.position;
    }

    /**
     * Chapitre 1 - 8
     * @param that
     * @return
     */
    public Vec2d toricVector(ToricPosition that){
        // calculer le plus petit vecteur allant de l'instance courante à that
        //p = √ (x − x )2 + (y − y )2
        int width = getConfig().getInt(WORLD_WIDTH);
        int heigth = getConfig().getInt(WORLD_HEIGHT);
        width = 10;
        heigth = 10;
        System.out.println("width : "+ width+ "height : "+ heigth);
        HashMap<Vec2d, Double> map = new HashMap<>();

        // that lui même
        map.put(that.position,this.position.distance(that.position));
        System.out.println("that : "+this.position.distance(that.position));
        // 0, + WORLD_HEIGHT
        map.put(new Vec2d(that.position.getX(), that.position.getY() + heigth),
                this.position.distance(new Vec2d(that.position.getX(), that.position.getY() + heigth)));

        System.out.println(new Vec2d(that.position.getX(), that.position.getY() + heigth) +"0, + WORLD_HEIGHT : "+this.position.distance(that.position.add(new Vec2d(that.position.getX(), that.position.getY() + heigth))));
        // 0, - WORLD_HEIGHT
        map.put(new Vec2d(that.position.getX(), that.position.getY() - heigth),
                this.position.distance(new Vec2d(that.position.getX(), that.position.getY() - heigth)));
        System.out.println(new Vec2d(that.position.getX(), that.position.getY() - heigth)+ "0, - WORLD_HEIGHT : "+this.position.distance(that.position.add(new Vec2d(that.position.getX(), that.position.getY() - heigth))));

        // - WORLD_WITH, 0
        map.put(new Vec2d(that.position.getX() - width, that.position.getY()),
                this.position.distance(that.position.add(new Vec2d(that.position.getX() - width, that.position.getY()))));
        System.out.println(new Vec2d(that.position.getX() - width, that.position.getY())+ "-  WORLD_WITH, 0 : "+this.position.distance(that.position.add(new Vec2d(that.position.getX() - width, that.position.getY()))));

        // + WORLD_WITH, 0
        map.put(new Vec2d(that.position.getX() + width, that.position.getY()),
                this.position.distance(that.position.add(new Vec2d(that.position.getX() + width, that.position.getY()))));
        System.out.println(new Vec2d(that.position.getX() + width, that.position.getY()) + "+  WORLD_WITH, 0 : "+this.position.distance(that.position.add(new Vec2d(that.position.getX() + width, that.position.getY()))));

        // + width, + height
        map.put(new Vec2d(that.position.getX() + width, that.position.getY() + heigth),
                this.position.distance(that.position.add(new Vec2d(that.position.getX() + width, that.position.getY() + heigth))));
        System.out.println(new Vec2d(that.position.getX() + width, that.position.getY() + heigth) +"+ width, + height : "+this.position.distance(that.position.add(new Vec2d(that.position.getX() + width, that.position.getY() + heigth))));

        // + width, - height
        map.put(new Vec2d(that.position.getX() + width, that.position.getY() - heigth),
                this.position.distance(that.position.add(new Vec2d(that.position.getX() + width, that.position.getY() - heigth))));
        System.out.println(new Vec2d(that.position.getX() + width, that.position.getY() - heigth) +"+ width, - height : "+this.position.distance(that.position.add(new Vec2d(that.position.getX() + width, that.position.getY() - heigth))));


        // - width, + height
        map.put(new Vec2d(that.position.getX() - width, that.position.getY() + heigth),
                this.position.distance(new Vec2d(that.position.getX() - width, that.position.getY() + heigth)));
        System.out.println(new Vec2d(that.position.getX() - width, that.position.getY() + heigth) +"- width, + height : "+this.position.distance(that.position.add(new Vec2d(that.position.getX() - width, that.position.getY() + heigth))));

        // - width, - height
        map.put(new Vec2d(that.position.getX() - width, that.position.getY() - heigth),
                this.position.distance(new Vec2d(that.position.getX() - width, that.position.getY() - heigth)));
        System.out.println(new Vec2d(that.position.getX() - width, that.position.getY() - heigth) + "- width, - height : "+this.position.distance(new Vec2d(that.position.getX() - width, that.position.getY() - heigth)));

        //System.out.println(this.position.distance(that.position));
        Vec2d vector = map.entrySet()
                .stream()
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .findFirst()
                .map(Map.Entry::getKey)
                .get();


//        double dist1 = Math.sqrt(Math.pow((this.vec2d.getX() - that.vec2d.getX()),2) + Math.pow((this.vec2d.getY() - that.vec2d.getY()),2));
//        double dist2 = Math.sqrt(Math.pow((this.vec2d.getX() - that.vec2d.getX()),2) + Math.pow((this.vec2d.getY() - that.vec2d.getY()),2));
        System.out.println("-------------------");
        System.out.println(this.position);
        System.out.println(vector);
        //Vec2d newVector = new Vec2d();

        //return this.position.minus(vector);
        return vector.minus(this.position);
    }

    /**
     * Chapitre 1 _ 9
     * @param that
     * @return
     */
    public double toricDistance(ToricPosition that){

        return toricVector(that).length();
    }

    public Vec2d getPosition() {
        return position;
    }

    public void setPosition(Vec2d position) {
        this.position = position;
    }

    /**
     * Chapitre 1 - 1
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
