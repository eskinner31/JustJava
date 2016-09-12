/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int value = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox chocolateToppingCheckBox = (CheckBox) findViewById(R.id.chocolate_topping);
        EditText customerName = (EditText) findViewById(R.id.customer_name);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolateTopping = chocolateToppingCheckBox.isChecked();
        String customersName = String.valueOf(customerName.getText());
        int price = calculatePrice(hasWhippedCream, hasChocolateTopping);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + customersName);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price, hasWhippedCream, hasChocolateTopping, customersName));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view) {
        if (value == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        value = value + 1;
        displayQuantity(value);
    }

    public void decrement(View view) {
        if (value == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
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

//
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

    /**
     * This method is called when the order button is clicked.
     * @param  addWhippedCream is whether or not the user wants whipped cream.
     * @param  addChocolate is whether or not user wants chocolate toppings.
     * @return total price.
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        return value * basePrice;
    }

    private String createOrderSummary(int price, boolean hasCream, boolean hasChocolate, String customerName) {
        String name = customerName;
        int quantity = value;
        int total = price;


        return "Name: " + name +
                "\n Add whipped cream? " + hasCream +
                "\n Add Chocolate? " + hasChocolate +
                 "\n Quantity: " + value +
                "\n Total: $" + price +
                "\n Thank You!";
    }
}