package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.random.NormalDistribution;
import ch.epfl.moocprog.random.UniformDistribution;
import ch.epfl.moocprog.utils.Time;

/**
 * Etape 3
 */
public final class FoodGenerator {

    private Time time;

    public FoodGenerator() {
        this.time = Time.ZERO;
    }

    public void update(FoodGeneratorEnvironmentView env, Time dt){
        Time FOOD_GENERATOR_DELAY = Context.getConfig().getTime(Config.FOOD_GENERATOR_DELAY);
        double NEW_FOOD_QUANTITY_MIN = Context.getConfig().getDouble(Config.NEW_FOOD_QUANTITY_MIN);
        double NEW_FOOD_QUANTITY_MAX = Context.getConfig().getDouble(Config.NEW_FOOD_QUANTITY_MAX);

        double WIDTH = Context.getConfig().getInt(Config.WORLD_WIDTH);
        double HEIGTH = Context.getConfig().getInt(Config.WORLD_HEIGHT);

        this.time = this.time.plus(dt);
        while(this.time.compareTo(FOOD_GENERATOR_DELAY) >= 0){
           this.time = this.time.minus(FOOD_GENERATOR_DELAY);

           double foodToPut = UniformDistribution.getValue(NEW_FOOD_QUANTITY_MIN,NEW_FOOD_QUANTITY_MAX);
           //double positionFood = NormalDistribution.getValue(WIDTH / 2.0, HEIGTH * (HEIGTH / 16.0));
            env.addFood(new Food(
                    new ToricPosition(
                            NormalDistribution.getValue(WIDTH / 2.0, HEIGTH * (HEIGTH / 16.0)),
                            NormalDistribution.getValue(WIDTH / 2.0, HEIGTH * (HEIGTH / 16.0))
                    ),
                    foodToPut
            ));

        }
    }
}
