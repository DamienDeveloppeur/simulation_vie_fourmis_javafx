package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;

import ch.epfl.moocprog.random.UniformDistribution;
import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Utils;
import ch.epfl.moocprog.utils.Vec2d;

/**
 * Go etape 5
 */
public abstract class Animal extends Positionable {
    private Double angle;
    private int hitpoints;
    private Time lifespan;
    private Time rotationDelay = Time.ZERO;
    private  final Time ANIMAL_NEXT_ROTATION_DELAY = Context.getConfig().getTime(Config.ANIMAL_NEXT_ROTATION_DELAY);

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


    /**
     *
     * @return une instance de RotationProbability
     */
    protected RotationProbability computeRotationProbs(){
        RotationProbability rp = new RotationProbability(
                new double[] {-180.0, -100.0, -55.0, -25.0, -10.0,0.0,10.0,25.0,55.0,100.0,180.0},
                //new double[] {0.0000, 0.0000, 0.0005, 0.0010, 0.0150,0.4870,0.0150,0.0010,0.0005,0.0000,0.0000}
                new double[] {0.0000, 0.0000, 0.0005, 0.0010, 0.0050,0.9870,0.0050,0.0010,0.0005,0.0000,0.0000}
        );
        return rp;
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
        Double value = Utils.pickValue(computeRotationProbs().getAngles(), computeRotationProbs().getProbabilities());
        setRotationDelay(getRotationDelay().plus(dt));
        while(getRotationDelay().compareTo(getANIMAL_NEXT_ROTATION_DELAY()) >= 0){
            setRotationDelay(getRotationDelay().minus(getANIMAL_NEXT_ROTATION_DELAY()));
            rotate(value);
        }
        // deplacement des animaux : position += dt * vitesse en gardant sa direction
        Vec2d vec2d = Vec2d.fromAngle(getDirection()).scalarProduct(dt.toSeconds()* getSpeed());
        this.setPosition(this.getPosition().add(vec2d));

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
    }

    public Double getAngle() {
        return angle;
    }

    public Time getRotationDelay() {
        return rotationDelay;
    }

    public void setRotationDelay(Time rotationDelay) {
        this.rotationDelay = rotationDelay;
    }

    public Time getANIMAL_NEXT_ROTATION_DELAY() {
        return ANIMAL_NEXT_ROTATION_DELAY;
    }

    public void setLifespan(Time lifespan) {
        this.lifespan = lifespan;
    }

    public void rotate(double rotate){
        this.angle += rotate;
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
