package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.random.UniformDistribution;
import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Vec2d;

/**
 * Go etape 5
 */
public abstract class Animal extends Positionable {
    private Double angle;
    private int hitpoints;
    private Time lifespan;

    public Animal(Double angle, int hitpoints, Time lifespan) {
        this.angle = angle;
        this.hitpoints = hitpoints;
        this.lifespan = lifespan;
    }

    public Animal(ToricPosition toricPosition, int hitpoints, Time lifespan) {
        super(toricPosition);
        this.hitpoints = hitpoints;
        this.lifespan = lifespan;
        this.angle = UniformDistribution.getValue(0, 2 * Math.PI);
    }

    public final Time getLifespan() {
        return lifespan;
    }

    public final double getDirection(){
        return this.angle;
    }

    public final void setDirection(double angle){
        this.angle= angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public final int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public final boolean isDead(){
        return this.lifespan.compareTo(Time.fromSeconds(0)) < 1 || this.hitpoints <= 0;
        //return false;
    }

    public void setLifespan(Time lifespan) {
        this.lifespan = lifespan;
    }

    /**
     * Abstracts Methods
     */
    public abstract double getSpeed();


    public void accept(AnimalVisitor visitor, RenderingMedia s){

    }
    /**
     *
     * @param env
     * @param dt
     */
    void update(AnimalEnvironmentView env, Time dt){

        if(!this.isDead()){
            setLifespan(getLifespan().minus(dt.times(Context.getConfig().getDouble(Config.ANIMAL_LIFESPAN_DECREASE_FACTOR))));
            move(dt);
        }

    }

    /**
     * Met à jour le déplacement de l'animal après un écoulement de temps
     * @param dt
     */
    protected final void move(Time dt) {
        // deplacement des animaux : position += dt * vitesse en gardant sa direction
        Vec2d vec2d = Vec2d.fromAngle(getDirection()).scalarProduct(dt.toSeconds()* getSpeed());
        this.setPosition(this.getPosition().add(vec2d));
    }
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Animal{" +
                "position"+ this.getPosition() +
                "angle=" + angle +
                ", hitpoints=" + hitpoints +
                ", lifespan=" + lifespan +
                '}';
    }
}
