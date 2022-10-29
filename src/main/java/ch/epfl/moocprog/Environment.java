package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.gfx.EnvironmentRenderer;
import ch.epfl.moocprog.utils.Time;
import ch.epfl.moocprog.utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Etape 3
 * décrit le monde dans lequel les animaux évoluent
 * Ajout etape 4
 */
public final class Environment implements FoodGeneratorEnvironmentView,
        AnimalEnvironmentView,
        AnthillEnvironmentView,
        AntEnvironmentView,
        AntWorkerEnvironmentView
        {

    private FoodGenerator foodGenerator;
    private List<Food> foods;
    private List<Animal> animal;
    private List<Anthill> anthills;
    private double RAYON_ANT = Context.getConfig().getDouble(Config.ANT_MAX_PERCEPTION_DISTANCE);

    public Environment() {
        this.foodGenerator = new FoodGenerator();
        this.foods = new LinkedList<Food>();
        this.animal = new LinkedList<>();
        this.anthills = new LinkedList<>();
    }

    /**
     * Draw food and animals
     * @param environmentRenderer
     */
    public void renderEntities(EnvironmentRenderer environmentRenderer){
        foods.forEach(environmentRenderer::renderFood);
        this.animal.forEach(a -> environmentRenderer.renderAnimal(a));
        this.anthills.forEach(a -> environmentRenderer.renderAnthill(a));
    }
    /**
     * Add some entity to the env
     */
    /**
     *
     * @param anthill
     */
    public void addAnthill(Anthill anthill){
        if(anthill == null) throw new IllegalArgumentException("Une anthill ne peux être null lors d'un ajout");
        this.anthills.add(anthill);
    }
    public void addAnimal(Animal animal){
        if(animal == null) throw new IllegalArgumentException("Animal ne peut être null lors d'un ajout");
        this.animal.add(animal);
    }

    @Override
    public void addFood(Food food) {
        if(food == null) throw new IllegalArgumentException("food ne peut être null");
        this.foods.add(food);
    }

    @Override
    public void addAnt(Ant ant) {
        addAnimal(ant);
    }
    // retourne une référence sur la source de nourriture la plus proche perceptible par une fourmi antworker
    // return null si aucune est détecté
    @Override
    public Food getClosestFoodForAnt(AntWorker antWorker) {
        if(antWorker == null) throw new IllegalArgumentException("Antwork ne peut être null lors d'un drop food");
        Food f = Utils.closestFromPoint(antWorker, foods);
        if(f == null || f.getPosition().toricDistance(antWorker.getPosition()) > RAYON_ANT) return null;
        else return Utils.closestFromPoint(antWorker, foods);

    }

    // return true si antworker peut ramener de la nourriture à sa anthill (si elle est perceptible)
    @Override
    public boolean dropFood(AntWorker antWorker) {
        if(antWorker == null) throw new IllegalArgumentException("Antwork ne peut être null lors d'un drop food");
        Anthill hisAnthill = this.anthills.stream()
                                            .filter(a -> a.getAnthillId().equals(antWorker.getAnthillId()))
                                            .findFirst()
                                            .orElse(null)
                ;
        if(hisAnthill == null) return false;
        double distance = antWorker.getPosition().toricDistance(hisAnthill.getPosition());
        return distance > RAYON_ANT ? false : true;
    }

    /**
     *
     * @return
     */
    public List<Double> getFoodQuantities(){
        List<Double> temp = new ArrayList<>();
        for(Food food : foods){
            temp.add(food.getQuantity());
        }
        return temp;
    }

    /**
     *
     * @param dt
     */
    public void update(Time dt){
        foodGenerator.update(this,dt);
        Iterator<Animal> i = this.animal.iterator();
        while(i.hasNext()){
            Animal a = i.next();
            if(a.isDead()) i.remove();
            else a.update(this, dt);
        }
        foods.removeIf(food -> food.getQuantity() <= 0);
        for(Anthill a : anthills){
            a.update(this,dt);
        }
    }

    /**
     *
     * @return
     */
    public List<ToricPosition> getAnimalsPosition(){
        return this.animal.stream()
                .map(a -> a.getPosition())
                .collect(Collectors.toList());
    }

    public FoodGenerator getFoodGenerator() {
        return foodGenerator;
    }

    public void setFoodGenerator(FoodGenerator foodGenerator) {
        this.foodGenerator = foodGenerator;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(List<Animal> animal) {
        this.animal = animal;
    }

    public int getWidth(){
        return Context.getConfig().getInt(Config.WORLD_WIDTH);
    }
    public int getHeight(){
        return Context.getConfig().getInt(Config.WORLD_HEIGHT);
    }

    @Override
    public void selectSpecificBehaviorDispatch(AntWorker antWorker, Time dt) {
        antWorker.seekForFood(this, dt);
    }

    @Override
    public void selectSpecificBehaviorDispatch(AntSoldier antSoldier, Time dt) {
        antSoldier.seekForEnemies(this,dt);
    }
}
