package ch.epfl.moocprog;

public abstract class Animal extends Positionable {
    private Double angle;

    public Animal(ToricPosition toricPosition) {
        super(toricPosition);
        this.angle = 0.0;
    }

    public final double getDirection(){
        return this.angle;
    }
    public void accept(AnimalVisitor visitor, RenderingMedia s){

    }
}
