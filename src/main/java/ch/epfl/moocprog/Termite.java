package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.utils.Time;

public class Termite extends Animal{
    public Termite(ToricPosition toricPosition) {
        super(toricPosition, Context.getConfig().getInt(Config.TERMITE_HP), Context.getConfig().getTime(Config.TERMITE_LIFESPAN));

    }
    public void accept(AnimalVisitor visitor, RenderingMedia s) {
        visitor.visit(this, s);
    }

    @Override
    void specificBehaviorDispatch(AnimalEnvironmentView env, Time dt) {
        env.selectSpecificBehaviorDispatch(this,  dt);
    }

    @Override
    protected void afterMoveDispatch(AnimalEnvironmentView env, Time dt) {
    env.selectAfterMoveDispatch(this, dt);
    }

    @Override
    public RotationProbability computeRotationProbsDispatch(AnimalEnvironmentView env) {
        return env.selectComputeRotationProbsDispatch(this);
    }

    @Override
    public boolean isEnemy(Animal entity) {
        return !this.isDead() && !entity.isDead() && entity.isEnemyDispatch(this);
    }

    @Override
    public boolean isEnemyDispatch(Termite other) {
        return false;
    }

    @Override
    public boolean isEnemyDispatch(Ant other) {
        return true;
    }


    RotationProbability computeRotationProbs(TermiteEnvironmentView env){
        return computeDefaultRotationProbs();
    }
    public void afterMoveTermite(TermiteEnvironmentView env, Time dt) {

    }
    @Override
    public double getSpeed(){
        return Context.getConfig().getDouble(Config.TERMITE_SPEED);
    }

    public void seekForEnemies(AnimalEnvironmentView env, Time dt){
        move(env, dt);
    }

    @Override
    public int getMinAttackStrength() {
        return Context.getConfig().getInt(Config.TERMITE_MIN_STRENGTH);
    }

    @Override
    public int getMaxAttackStrength() {
        return Context.getConfig().getInt(Config.TERMITE_MAX_STRENGTH);
    }

    @Override
    public Time getMaxAttackDuration() {
        return Context.getConfig().getTime(Config.TERMITE_ATTACK_DURATION);
    }
}
