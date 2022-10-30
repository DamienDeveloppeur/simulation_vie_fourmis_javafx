package ch.epfl.moocprog;
import ch.epfl.moocprog.app.Context;
import ch.epfl.moocprog.config.Config;

import static ch.epfl.moocprog.config.Config.ALPHA;

public class PheromoneRotationProbabilityModel implements AntRotationProbabilityModel{

    @Override
    public RotationProbability computeRotationProbs(RotationProbability movementMatrix, ToricPosition position, double directionAngle, AntEnvironmentView env) {
        // formules :

        //P' [i] = (P[i] * (D(Q[i])) ALPHA) / S
        // traduction :
        // numerateur[i] = P[i] * Math.pow( detection(Q[i]), alpha ); S += numerateur[i];
        // numerateur[i] = P[i] * Math.pow( detection(Q[i]), alpha );
        double[] angles = { -180.0, -100.0, -55.0, -25.0, -10.0, 0.0, 10.0, 25.0, 55.0, 100.0, 180.0};
        double[] Q = env.getPheromoneQuantitiesPerIntervalForAnt(position,directionAngle,angles);
        double[] P = movementMatrix.getAngles();
        int ALPHA = Context.getConfig().getInt(Config.ALPHA);
        double BETA_D = Context.getConfig().getDouble(Config.BETA_D);
        double Q_ZERO = Context.getConfig().getDouble(Config.Q_ZERO);

        return null;
    }
}
