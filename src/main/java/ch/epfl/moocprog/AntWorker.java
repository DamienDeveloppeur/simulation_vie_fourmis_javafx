package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.utils.Time;

public class AntWorker extends Ant{
    private double foodQuantity = 0;


    public AntWorker(ToricPosition toricPosition, Uid uid) {
        super(toricPosition,
                Context.getConfig().getInt(Config.ANT_WORKER_HP),
                Context.getConfig().getTime(Config.ANT_WORKER_LIFESPAN),
                uid);
    }

    public void accept(AnimalVisitor visitor, RenderingMedia s) {
        visitor.visit(this, s);
    }

    @Override
    void specificBehaviorDispatch(AnimalEnvironmentView env, Time dt) {
        env.selectSpecificBehaviorDispatch(this , dt);
    }

    public double getFoodQuantity() {
        return foodQuantity;
    }

    void seekForFood(AntWorkerEnvironmentView env, Time dt){
        move(dt);
    }
    @Override
    public String toString() {
        return "AntWorker{" +
                "Position: " + getPosition() +
                "Speed : " + getSpeed()+
                "HitPoints : " + getHitpoints()+
                "LifeSpan : " + getLifespan() +
                "Quantity=" + foodQuantity +
                '}';
    }

    @Override
    public double getSpeed(){
        return Context.getConfig().getDouble(Config.ANT_WORKER_SPEED);
    }
}
