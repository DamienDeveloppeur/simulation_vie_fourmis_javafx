package ch.epfl.moocprog;

public class Termite extends Animal{
    public Termite(ToricPosition toricPosition) {
        super(toricPosition);
    }
    public void accept(AnimalVisitor visitor, RenderingMedia s) {
        visitor.visit(this, s);
    }
}
