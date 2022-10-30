package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.utils.Time;

public class Termite extends Animal{
    public Termite(ToricPosition toricPosition) {
        super(toricPosition, Context.getConfig().getInt(Config.TERMITE_HP), Context.getConfig().getTime(Config.TERMITE_LIFESPAN));

    }
    public void accept(AnimalVisitor visitor, RenderingMedia s) {
        visitor.visit(this, s);
    }

    @Override
    void specificBehaviorDispatch(AnimalEnvironmentView env, Time dt) {

    }

    @Override
    protected void afterMoveDispatch(AnimalEnvironmentView env, Time dt) {

    }

    @Override
    public double getSpeed(){
        return Context.getConfig().getDouble(Config.TERMITE_SPEED);
    }
}
