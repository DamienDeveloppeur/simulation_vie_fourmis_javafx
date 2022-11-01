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
    private Time timeduration;
    private State state;
    private Time attackDuration = Time.ZERO;
    private  final Time ANIMAL_NEXT_ROTATION_DELAY = Context.getConfig().getTime(Config.ANIMAL_NEXT_ROTATION_DELAY);

    public enum State {
        IDLE, ESCAPING, ATTACK;
    }
    public final State getState(){
        return this.state;
    }
    public void setState(State State){
        this.state = state;
    }



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
        this.timeduration = Time.ZERO;
        this.state = State.IDLE;
    }
    /**
     * Abstracts Methods
     */
    public abstract double getSpeed();
    abstract void specificBehaviorDispatch(AnimalEnvironmentView env, Time dt);
    protected abstract void afterMoveDispatch(AnimalEnvironmentView env, Time dt);
    protected abstract  RotationProbability computeRotationProbsDispatch(AnimalEnvironmentView env);
    protected abstract boolean isEnemy(Animal entity);
    protected abstract boolean isEnemyDispatch(Termite other);
    protected abstract boolean isEnemyDispatch(Ant other);
    public abstract int getMinAttackStrength();
    public abstract int getMaxAttackStrength();
    public abstract Time getMaxAttackDuration();


    public void accept(AnimalVisitor visitor, RenderingMedia s){

    }

    /**
     *
     * @return une instance de RotationProbability
     */
    protected final RotationProbability computeDefaultRotationProbs(){
        RotationProbability rp = new RotationProbability(
                new double[] {-180.0, -100.0, -55.0, -25.0, -10.0,0.0,10.0,25.0,55.0,100.0,180.0},
                //new double[] {0.0000, 0.0100, 0.0900, 0.1500, 0.5000, 0.1500, 0.0900, 0.0100, 0.0000}
                new double[] {0.0000, 0.0000, 0.0005, 0.0010, 0.0050,0.9870,0.0050,0.0010,0.0005,0.0000,0.0000}
        );
        return rp;
    }

    /**
     *
     * @return
     */
    public boolean canAttack(){
        return !this.state.toString().equals("ESCAPING") && getAttackDuration().compareTo(getMaxAttackDuration()) <= 0;
    }

    /**
     *
     * @param env
     * @param dt
     */
    public void escape(AnimalEnvironmentView env, Time dt){
        move(env, dt);
    }
    public void fight(AnimalEnvironmentView env, Time dt){
        // target l'ennemie le plus proche, si il existe :
        Animal target = Utils.closestFromPoint(this,env.getVisibleEnemiesForAnimal(this));
        if(target != null){
            setState(State.ATTACK);
            target.setHitpoints((int) (target.getHitpoints() - UniformDistribution.getValue(getMinAttackStrength(), getMaxAttackStrength())));
            setAttackDuration(this.getAttackDuration().plus(dt));
        } else {
            setState(State.ESCAPING);
            setAttackDuration(Time.ZERO);
        }
        // state = ATTACK
        //  infliger degat ; attackduration += dt

        // sinon attackDuration = 0
        // state de ATTACK à ESCAPING
    }


    /**
     *
     * @param env
     * @param dt
     */
    public final void update(AnimalEnvironmentView env, Time dt){
        if(!this.isDead()){
            switch (this.state){
                case ATTACK:
                    fight(env, dt);
                    break;
                case ESCAPING:
                     escape(env,dt);
                    break;
                case IDLE:
                    this.specificBehaviorDispatch(env, dt);
                    break;
                default:
                    this.specificBehaviorDispatch(env, dt);

            }
            this.specificBehaviorDispatch(env, dt);
            setLifespan(getLifespan().minus(dt.times(Context.getConfig().getDouble(Config.ANIMAL_LIFESPAN_DECREASE_FACTOR))));
            //move(dt);
        }
    }

    /**
     * Met à jour le déplacement de l'animal après un écoulement de temps
     * @param dt
     */
    protected final void move(AnimalEnvironmentView env, Time dt) {
        //Pour mettre en oeuvre cela, vous pouvez ajouter à l’animal un compteur de
        //type Time (initialisé à Time.ZERO), nommé rotationDelay, mesurant le temps
        //écoulé depuis la précédente rotation.
        setRotationDelay(getRotationDelay().plus(dt));
        //compareTo --> -1 rotationDelay est strictement plus petit que la constante
        while(getRotationDelay().compareTo(getANIMAL_NEXT_ROTATION_DELAY())  >= 0){
            setRotationDelay(getRotationDelay().minus(getANIMAL_NEXT_ROTATION_DELAY()));
            Double value = Utils.pickValue(computeDefaultRotationProbs().getAngles(), computeDefaultRotationProbs().getProbabilities());
            //value = Math.toRadians(value);
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

    public Time getTimeduration() {
        return timeduration;
    }

    public void setTimeduration(Time timeduration) {
        this.timeduration = timeduration;
    }

    public Time getAttackDuration() {
        return attackDuration;
    }

    public void setAttackDuration(Time attackDuration) {
        this.attackDuration = attackDuration;
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
                ", State="+ state +
                '}';
    }
}
