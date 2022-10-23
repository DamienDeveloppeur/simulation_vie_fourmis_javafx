package ch.epfl.moocprog;

public class Pheromone extends Positionable {
    public Pheromone(ToricPosition toricPosition) {
        super(toricPosition);
    }
    public double getQuantity(){
        return 0.0;
    }
    public double getFoodQuantity(){
        return 0.0;
    }
}
