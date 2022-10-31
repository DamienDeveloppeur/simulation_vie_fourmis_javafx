package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Vec2d;

import static java.lang.Integer.parseInt;

public abstract class Ant extends Animal{
    private Uid anthillId;
    private ToricPosition lastPos;
    private AntRotationProbabilityModel probModel;

    public Ant(ToricPosition toricPosition,
               int hitpoints,
               Time lifespan,
               Uid anthillId) {
        super(toricPosition, hitpoints, lifespan);
        this.anthillId = anthillId;
        this.lastPos = toricPosition;
        this.probModel = new PheromoneRotationProbabilityModel();
    }

    public Ant(ToricPosition toricPosition,
               int hitpoints,
               Time lifespan,
               Uid anthillId,
               AntRotationProbabilityModel probModel) {
        super(toricPosition, hitpoints, lifespan);
        this.anthillId = anthillId;
        this.lastPos = toricPosition;
        this.probModel = probModel;
    }

    public final Uid getAnthillId(){
        return  anthillId;
    }

    @Override
    public boolean isEnemy(Animal entity) {
        return !this.isDead() && !entity.isDead() && entity.isEnemyDispatch(this);
    }

    @Override
    public boolean isEnemyDispatch(Termite other) {
        return true;
    }

    @Override
    public boolean isEnemyDispatch(Ant other) {
        return false;
    }

    /**
     * Simple turnaround after take or drop food
     */
    public void turnaround(){
        double angleToAdd = this.getAngle()+Math.PI;
        if(angleToAdd > Math.PI*2) angleToAdd -= Math.PI;
        this.setAngle(angleToAdd);
    }

    /**
     * Formule : d * densite Pheromone entre lastpos et current pos
     * @param env
     */
    private void spreadPheromones(AntEnvironmentView env){
        double quantityPheromone = Context.getConfig().getDouble(Config.ANT_PHEROMONE_ENERGY);
        ToricPosition currentPos = getPosition();
        double distance = lastPos.toricDistance(currentPos);
        double densite = Context.getConfig().getDouble(Config.ANT_PHEROMONE_DENSITY);
        ToricPosition toricPheromone = new ToricPosition(this.lastPos.toricVector(currentPos));
        int N = (int) (distance * densite);
        Vec2d scalar = toricPheromone.getPosition().scalarProduct(distance);
        // on doit ajouter a chaque position entre lastpos et currentpos des pheromones
        lastPos = lastPos.add(scalar);

        for(int i = 0; i < N; i++){
            env.addPheromone(new Pheromone(lastPos, quantityPheromone));
            //lastPos = lastPos.add(scalar);
        }

    }
    public void afterMoveAnt(AntEnvironmentView env, Time dt){
        spreadPheromones(env);
    }

    @Override
    public void afterMoveDispatch(AnimalEnvironmentView env, Time dt){
        env.selectAfterMoveDispatch(this, dt);
    }

    public final RotationProbability computeRotationProbs(AntEnvironmentView env) {
        return probModel.computeRotationProbs(computeDefaultRotationProbs(), getPosition(), getDirection(), env);
    }
    @Override
    public RotationProbability computeRotationProbsDispatch(AnimalEnvironmentView env) {
        return env.selectComputeRotationProbsDispatch(this);
    }

    public void setAnthillId(Uid anthillId) {
        this.anthillId = anthillId;
    }

    public ToricPosition getLastPos() {
        return lastPos;
    }

    public void setLastPos(ToricPosition lastPos) {
        this.lastPos = lastPos;
    }

}
