package ch.epfl.moocprog;

public class AntWorker extends Ant{
    public AntWorker(ToricPosition toricPosition) {
        super(toricPosition);
    }
    public void accept(AnimalVisitor visitor, RenderingMedia s) {
        visitor.visit(this, s);
    }

    @Override
    public double getSpeed(){
        return 0.0;
    }
}
