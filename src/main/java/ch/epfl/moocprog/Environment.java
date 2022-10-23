package ch.epfl.moocprog;

import ch.epfl.moocprog.utils.Time;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Etape 3
 * décrit le monde dans lequel les animaux évoluent
 *
 */
public final class Environment implements FoodGeneratorEnvironmentView {
    private FoodGenerator foodGenerator;
    private List<Food> food;

    public Environment() {

        this.foodGenerator = new FoodGenerator();
        this.food = new LinkedList<Food>();
    }

    @Override
    public void addFood(Food food) {
        if(food == null) throw new IllegalArgumentException("food ne peut être null");
        this.food.add(food);
    }
    public List<Double> getFoodQuantities(){
        List<Double> temp = new ArrayList<>();
        for(Food food : food){
            temp.add(food.getQuantity());
        }
        return temp;
    }

    public void update(Time dt){
        foodGenerator.update(this,dt);
        food.removeIf(food -> food.getQuantity() <= 0);
    }

}
