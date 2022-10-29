package ch.epfl.moocprog;

/**
 * Etape 3
 */
public final class Food extends Positionable{
    private double quantity;

    public Food(ToricPosition position, double quantity) {
        setPosition(position);
        if(quantity <= 0) this.quantity = 0.0;
        else this.quantity = quantity;

    }

    public double takeQuantity(double quantityWithdrawn){
        if(quantityWithdrawn < 0) throw new IllegalArgumentException("Not enought quantity");
        Double valueReturn = null;
        if(quantityWithdrawn > getQuantity()){
            valueReturn = getQuantity();
            this.quantity = 0;
        } else {
            this.quantity -= quantityWithdrawn;
            valueReturn = quantityWithdrawn;
        }
        return valueReturn;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Food{" +
                "position :" + getPosition() +
                ", quantity :" + String.format("Quantity : %.2f", getQuantity()) +
                '}';
    }
}
