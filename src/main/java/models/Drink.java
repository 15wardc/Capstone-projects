package models;

import com.techelevator.application.Inventory;

import java.math.BigDecimal;

public class Drink extends Inventory {
    public Drink(String id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public String getSound() {
        return "\nDrinky, Drinky, Slurp Slurp!";
    }
}
