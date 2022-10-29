package ch.epfl.moocprog;

public interface AntWorkerEnvironmentView extends AntEnvironmentView{
    // retourne une référence sur la source de nourriture la plus proche perceptible par une fourmi antworker
    // return null si aucune est détecté
    Food getClosestFoodForAnt(AntWorker antWorker);

    // return true si antworker peut ramener de la nourriture à sa anthill (si elle est perceptible)
    boolean dropFood(AntWorker antWorker);
}
