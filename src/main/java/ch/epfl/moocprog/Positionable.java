package ch.epfl.moocprog;

public class Positionable extends Animal {
    private ToricPosition position;
    public double getDirection(){
        return 2.0;
    }

    public Positionable() {
        position = new ToricPosition(0,0);
    }

    public Positionable(ToricPosition toricPosition) {
        this.position = toricPosition;
    }


    public ToricPosition getPosition() {
        return position;
    }

    public void setPosition(ToricPosition position) {
        this.position = position;
    }

    public void toVec2d(){

    }
}
