package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.utils.Time;

public class AntWorker extends Ant{
    private double foodQuantity = 0;

    public AntWorker(ToricPosition toricPosition, Uid uid) {
        super(
                toricPosition,
                Context.getConfig().getInt(Config.ANT_WORKER_HP),
                Context.getConfig().getTime(Config.ANT_WORKER_LIFESPAN),
                uid
        );
    }
    public AntWorker(ToricPosition toricPosition, Uid uid, AntRotationProbabilityModel probModel) {
        super(
                toricPosition,
                Context.getConfig().getInt(Config.ANT_WORKER_HP),
                Context.getConfig().getTime(Config.ANT_WORKER_LIFESPAN),
                uid,
                probModel

        );
    }

    public void accept(AnimalVisitor visitor, RenderingMedia s) {
        visitor.visit(this, s);
    }



    public double getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(double foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    /**
     *
     * @param env
     * @param dt
     */
    public void seekForFood(AntWorkerEnvironmentView env, Time dt) {
//        while(env.getClosestFoodForAnt(this) != null || (env.dropFood(this) && this.foodQuantity > 0 ) ){
//            System.out.println("loop of loop");
//            if (this.foodQuantity == 0) {
//                Food f = env.getClosestFoodForAnt(this);
//                if (f != null) {
//                    double quantityTaked = f.takeQuantity(Context.getConfig().getDouble(Config.ANT_MAX_FOOD));
//                    this.foodQuantity += quantityTaked;
//                    turnaround();
//                    env.dropFood(this);
//                }
//            } else {
//                env.dropFood(this);
//            }
//        }
        if (this.foodQuantity == 0) {
            Food f = env.getClosestFoodForAnt(this);
            if (f != null) {
                double quantityTaked = f.takeQuantity(Context.getConfig().getDouble(Config.ANT_MAX_FOOD));
                this.foodQuantity += quantityTaked;
                turnaround();
                env.dropFood(this);
            }
        } else {
            env.dropFood(this);
        }
        move(env, dt);
    }


    @Override
    public RotationProbability computeRotationProbsDispatch(AnimalEnvironmentView env) {
        return computeDefaultRotationProbs();
    }

    @Override
    public double getSpeed(){
        return Context.getConfig().getDouble(Config.ANT_WORKER_SPEED);
    }

    @Override
    void specificBehaviorDispatch(AnimalEnvironmentView env, Time dt) {
        env.selectSpecificBehaviorDispatch(this , dt);
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
}
