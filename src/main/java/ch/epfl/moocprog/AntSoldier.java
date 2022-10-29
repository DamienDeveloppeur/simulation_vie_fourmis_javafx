package ch.epfl.moocprog;

import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;
import ch.epfl.moocprog.utils.Time;

public class AntSoldier extends Ant{
    public AntSoldier(ToricPosition toricPosition, Uid anthillId ) {
        super(toricPosition,
                Context.getConfig().getInt(Config.ANT_SOLDIER_HP),
                Context.getConfig().getTime(Config.ANT_SOLDIER_LIFESPAN),
                anthillId
        );
    }
    void seekForEnemies(AntEnvironmentView env, Time dt){
        move(dt);
    }
    public void accept(AnimalVisitor visitor, RenderingMedia s) {
        visitor.visit(this, s);
    }

    @Override
    void specificBehaviorDispatch(AnimalEnvironmentView env, Time dt) {
        env.selectSpecificBehaviorDispatch(this , dt);
    }

    @Override
    public double getSpeed(){
        return Context.getConfig().getDouble(Config.ANT_SOLDIER_SPEED);
    }
}
