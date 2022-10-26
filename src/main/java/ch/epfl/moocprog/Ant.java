package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;

public abstract class Ant extends Animal{
    public Ant(ToricPosition toricPosition) {
        super(toricPosition, Context.getConfig().getInt(Config.TERMITE_HP), Context.getConfig().getTime(Config.TERMITE_LIFESPAN));
    }
}
