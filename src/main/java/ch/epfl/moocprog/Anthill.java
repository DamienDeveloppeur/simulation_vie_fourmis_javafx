package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.random.UniformDistribution;
import ch.epfl.moocprog.utils.Time;

public class Anthill extends Positionable{
    private Uid anthillId;
    private double foodQuantity = 0;
    private double probAnt;
    private Time delayAnt = Time.ZERO;
    private Time ANTHILL_SPAWN_DELAY = Context.getConfig().getTime(Config.ANTHILL_SPAWN_DELAY);

    public Anthill(ToricPosition toricPosition, double probAnt) {
        super(toricPosition);
        this.anthillId = Uid.createUid();
        this.probAnt = probAnt;
    }
    public Anthill(ToricPosition toricPosition) {
        super(toricPosition);
        this.anthillId = Uid.createUid();
        this.probAnt = Context.getConfig().getDouble(Config.ANTHILL_WORKER_PROB_DEFAULT);
    }

    public void dropFood(double toDrop){
        if(toDrop < 0) throw new IllegalArgumentException("toDrop ne peut être null ou négatif");
        this.foodQuantity += toDrop;
    }
    public void update(Environment env, Time dt){
        this.delayAnt.plus(dt);
        while(this.delayAnt.compareTo(ANTHILL_SPAWN_DELAY) >= 0) {
            this.delayAnt.minus(ANTHILL_SPAWN_DELAY);
            double value = UniformDistribution.getValue(0,1);
            Ant ant = null;
            if(value <= probAnt) ant = new AntWorker(getPosition(),this.anthillId);
            else ant = new AntSoldier(getPosition(),this.anthillId);
            env.addAnt(ant);
        }
    }
    public Uid getAnthillId() {
        return anthillId;
    }

    public double getFoodQuantity() {
        return foodQuantity;
    }

    @Override
    public String toString() {
        return "Anthill{" +
                "Position :"+ getPosition()+
                "Quantity : " + foodQuantity +
                '}';
    }
}
