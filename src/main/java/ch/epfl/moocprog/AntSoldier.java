package ch.epfl.moocprog;

public class AntSoldier extends Ant{
    public AntSoldier(ToricPosition toricPosition) {
        super(toricPosition);

    }
    public void accept(AnimalVisitor visitor, RenderingMedia s) {
        visitor.visit(this, s);
    }
    @Override
    double getSpeed(){
        return 0.0;
    }
}
