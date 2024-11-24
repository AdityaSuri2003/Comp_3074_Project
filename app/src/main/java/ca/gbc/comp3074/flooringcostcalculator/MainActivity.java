
package ca.gbc.comp3074.flooringcostcalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText roomLength = findViewById(R.id.roomLength);
        EditText roomWidth = findViewById(R.id.roomWidth);
        EditText flooringCost = findViewById(R.id.flooringCost);
        EditText installationCost = findViewById(R.id.installationCost);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch unitToggle = findViewById(R.id.unitToggle);
        TextView resultText = findViewById(R.id.resultText);
        Button calculateButton = findViewById(R.id.calculateButton);
        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });


        calculateButton.setOnClickListener(v -> {
            try {
                double length = Double.parseDouble(roomLength.getText().toString());
                double width = Double.parseDouble(roomWidth.getText().toString());
                double floorCost = Double.parseDouble(flooringCost.getText().toString());
                double installCost = Double.parseDouble(installationCost.getText().toString());
                boolean isSquareMeters = unitToggle.isChecked();

                double area = length * width;
                if (!isSquareMeters) {
                    area *= 10.764; // Convert square meters to square feet
                }
                double flooringTotal = area * floorCost;
                double installationTotal = area * installCost;
                double tax = (flooringTotal + installationTotal) * 0.13;
                double total = flooringTotal + installationTotal + tax;

                @SuppressLint("DefaultLocale") String result = String.format(
                        "Area: %.2f units\nFlooring Cost: $%.2f\nInstallation Cost: $%.2f\nTax: $%.2f\nTotal: $%.2f",
                        area, flooringTotal, installationTotal, tax, total);

                resultText.setText(result);
            } catch (Exception e) {
                resultText.setText("Error: Please check your inputs.");
            }
        });

        aboutButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }
}
