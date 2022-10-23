package ch.epfl.moocprog;

import ch.epfl.moocprog.gfx.EnvironmentRenderer;
import ch.epfl.moocprog.utils.Time;
import static ch.epfl.moocprog.app.Context.getConfig;
import static ch.epfl.moocprog.config.Config.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Etape 3
 * décrit le monde dans lequel les animaux évoluent
 * Ajout etape 4
 */
public final class Environment implements FoodGeneratorEnvironmentView {
    private FoodGenerator foodGenerator;
    private List<Food> foods;

    public Environment() {
        this.foodGenerator = new FoodGenerator();
        this.foods = new LinkedList<Food>();
    }
    public void renderEntities(EnvironmentRenderer environmentRenderer){
        foods.forEach(environmentRenderer::renderFood);
    }
    public void addAnthill(Anthill anthill){

    }
    public void addAnimal(Animal animal){

    }

    @Override
    public void addFood(Food food) {
        if(food == null) throw new IllegalArgumentException("food ne peut être null");
        this.foods.add(food);
    }
    public List<Double> getFoodQuantities(){
        List<Double> temp = new ArrayList<>();
        for(Food food : foods){
            temp.add(food.getQuantity());
        }
        return temp;
    }

    public void update(Time dt){
        foodGenerator.update(this,dt);
        foods.removeIf(food -> food.getQuantity() <= 0);
    }
    public int getWidth(){
        return getConfig().getInt(WORLD_WIDTH);
    }
    public int getHeight(){
        return getConfig().getInt(WORLD_HEIGHT);
    }
}
