package model.balance;

import javafx.scene.text.Text;

import java.util.List;

public class Balance  {
    private float value;

    public Balance(float value) {
        this.value = value;
    }

    //subtract input from balances value if input is grater than value throw error
    public float subtractBalanceBy(float subtractBy) {
        if(subtractBy > value){
            throw new IllegalArgumentException("amount is grater than balance");
        }
        value = value - subtractBy;
        return value;
    }

    public float getValue() {
        return value;
    }


}
