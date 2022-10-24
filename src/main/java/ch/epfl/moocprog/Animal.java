package ch.epfl.moocprog;

import ch.epfl.moocprog.utils.Time;

/**
 * Go etape 5
 */
public abstract class Animal extends Positionable {
    private Double angle;
    private int hitpoints;
    private Time lifespan;


    public Animal(ToricPosition toricPosition) {
        super(toricPosition);
        this.angle = 0.0;
    }

    public final double getDirection(){
        return this.angle;
    }
    public void accept(AnimalVisitor visitor, RenderingMedia s){

    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public final boolean isDead(){
        if(this.lifespan.compareTo(Time.fromSeconds(0)) < 1 || this.hitpoints <= 0) return true;
        return false;
    }

    public void setLifespan(Time lifespan) {
        this.lifespan = lifespan;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "position"+ getPosition() +
                "angle=" + angle +
                ", hitpoints=" + hitpoints +
                ", lifespan=" + lifespan +
                '}';
    }
}
