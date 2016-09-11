/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice(value);
        displayMessage(createOrderSummary(price));


    }

    public void increment(View view) {
        value = value + 1;
        displayQuantity(value);
    }

    public void decrement(View view) {
        value = value - 1;
        displayQuantity(value);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the order button is clicked.
     *
     * @return total price
     */
    private int calculatePrice(int quantity) {
        return quantity * 5;
    }

    private String createOrderSummary(int price) {
        String name = "Eugene Skinner";
        int quantity = value;
        int total = price;

        return "Name: " + name + "\n Quantity: " + value + "\n Total: $" + price + "\n Thank You!";
    }
}