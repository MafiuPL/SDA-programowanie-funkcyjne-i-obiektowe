package Finances.model;

/**
 * Created by RENT on 2017-02-20.
 */
public class Product {
    private String name;

    private int amount;

    private double unitPrice;

    public Product(String name, int amount, double eachPrice) {
        this.name = name;
        this.amount = amount;
        this.unitPrice = eachPrice;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
