package ch.epfl.moocprog;

import java.util.List;

public class RotationProbability {
    private double[] angles;
    private double[] probabilities;

    public RotationProbability(double[] angles, double[] probabilities) {
        if(angles == null
                || probabilities == null
                || angles.length != probabilities.length){
            throw new IllegalArgumentException("Les tableaux ne doivent pas être null");
        }
        this.angles = angles.clone();
        this.probabilities = probabilities.clone();
    }

    public double[] getAngles() {
        return angles.clone();
    }

    public void setAngles(double[] angles) {
        this.angles = angles;
    }

    public double[] getProbabilities() {
        return probabilities.clone();
    }

    public void setProbabilities(double[] probabilities) {
        this.probabilities = probabilities;
    }
}
