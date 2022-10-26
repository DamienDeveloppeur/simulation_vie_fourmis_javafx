package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.gfx.EnvironmentRenderer;
import ch.epfl.moocprog.utils.Time;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Etape 3
 * décrit le monde dans lequel les animaux évoluent
 * Ajout etape 4
 */
public final class Environment implements FoodGeneratorEnvironmentView, AnimalEnvironmentView {
    private FoodGenerator foodGenerator;
    private List<Food> foods;
    private List<Animal> animal;

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
        if(animal == null) throw new IllegalArgumentException("Animal ne peut être null");
        this.animal.add(animal);
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
//        this.animal.stream()
//                .flatMap(an -> an.isDead() ? animal.remove(an) : an.move(dt))
//        ;
        Iterator<Animal> i = this.animal.iterator();
        while(i.hasNext()){
            Animal a = i.next();
            if(a.isDead()) this.animal.remove(a);
            else a.move(dt);
        }



    }
    public int getWidth(){
        return Context.getConfig().getInt(Config.WORLD_WIDTH);
    }
    public int getHeight(){
        return Context.getConfig().getInt(Config.WORLD_HEIGHT);
    }
}
