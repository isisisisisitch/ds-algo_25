package ca.bytetube._15_greedy;

public class Item {
    public int weight;
    public int value;

    public double density;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.density = value * 1.0 / weight;
    }

    @Override
    public String toString() {
        return "Item{" +
                "weight=" + weight +
                ", value=" + value +
                ", density=" + density +
                '}';
    }
}
