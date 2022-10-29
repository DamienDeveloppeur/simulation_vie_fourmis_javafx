package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.utils.Time;

public abstract class Ant extends Animal{
    private Uid anthillId;

    public Ant(ToricPosition toricPosition,
               int hitpoints,
               Time lifespan,
               Uid anthillId) {
        super(toricPosition, hitpoints, lifespan);
        this.anthillId = anthillId;
    }

    public final Uid getAnthillId(){
        return  anthillId;
    }
}
