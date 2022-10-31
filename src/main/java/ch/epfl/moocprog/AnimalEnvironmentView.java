package ch.epfl.moocprog;

import ch.epfl.moocprog.utils.Time;

import java.util.List;

public interface AnimalEnvironmentView {
    void selectSpecificBehaviorDispatch(AntWorker antWorker, Time dt);
    void selectSpecificBehaviorDispatch(AntSoldier antSoldier, Time dt);
    void selectSpecificBehaviorDispatch(Termite termite, Time dt) ;
    RotationProbability selectComputeRotationProbsDispatch(Ant ant);
    void selectAfterMoveDispatch(Ant ant, Time dt);
    void selectAfterMoveDispatch(Termite termite, Time dt);
    RotationProbability selectComputeRotationProbsDispatch(Termite termite);
    List<Animal> getVisibleEnemiesForAnimal(Animal from);
    boolean isVisibleFromEnemies(Animal from);
}
