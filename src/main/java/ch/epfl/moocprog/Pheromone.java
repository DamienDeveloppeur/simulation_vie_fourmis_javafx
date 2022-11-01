package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.utils.Time;

public class Pheromone extends Positionable {
    private double quantity;
    private final double PHEROMONE_THRESHOLD = Context.getConfig().getDouble(Config.PHEROMONE_THRESHOLD);
    private final double PHEROMONE_EVAPORATION_RATE = Context.getConfig().getDouble(Config.PHEROMONE_EVAPORATION_RATE);
    private final double ANT_PHEROMONE_DENSITE = Context.getConfig().getDouble(Config.ANT_PHEROMONE_DENSITY);

    public Pheromone(ToricPosition toricPosition, double quantity) {
        super(toricPosition);
        this.quantity = quantity;
    }
    public double getQuantity(){
        return this.quantity;
    }
    public boolean isNegligible(){
        return getQuantity() <= PHEROMONE_THRESHOLD;
    }

    /**
     *
     * @param dt
     */
    public void update(Time dt) {
        while(quantity >= 0){
            if(isNegligible()) this.quantity -= dt.toSeconds() * PHEROMONE_EVAPORATION_RATE;
            else if(this.quantity < 0) this.quantity = 0;
            else break;
        }
    }


}
