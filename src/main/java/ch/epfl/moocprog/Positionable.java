package ch.epfl.moocprog;

/**
 * Chap 2 Vecteur
 */
public class Positionable {
    private ToricPosition position;

    public Positionable() {
        this.position = new ToricPosition(0.0,0.0);
    }

    public Positionable(ToricPosition toricPosition) {
        this.position = toricPosition;
    }

    public ToricPosition getPosition() {
        return position;
    }

    final protected void setPosition(ToricPosition position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Positionable{" +
                "toricPosition=" + position +
                '}';
    }
}
