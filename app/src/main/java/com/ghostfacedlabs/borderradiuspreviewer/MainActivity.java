package com.ghostfacedlabs.borderradiuspreviewer;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // public static final String ERROR_MESSAGE = "Issue with field, enter valid number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText topLeftX = findViewById(R.id.top_left_x);
        EditText topLeftY = findViewById(R.id.top_left_y);
        EditText topRightX = findViewById(R.id.top_right_x);
        EditText topRightY = findViewById(R.id.top_right_y);
        EditText bottomRightX = findViewById(R.id.bottom_right_x);
        EditText bottomRightY = findViewById(R.id.bottom_right_y);
        EditText bottomLeftX = findViewById(R.id.bottom_left_x);
        EditText bottomLeftY = findViewById(R.id.bottom_left_y);

        Button changeButton = findViewById(R.id.button);
        Button resetButton = findViewById(R.id.reset);
        Button copyButton = findViewById(R.id.copy);
        float[] radii = new float[]{0, 0, 0, 0, 0, 0, 0, 0};

        // draw rectangle on screen
        GradientDrawable box = new GradientDrawable();
        box.setColor(ContextCompat.getColor(this,
                R.color.orange_main));
        View boxView = findViewById(R.id.boxView);
        boxView.setBackground(box);

        copyButton.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            StringBuilder builder = new StringBuilder();

            // build string with values from radius array and format in CSS style
            builder.append("border-radius: ")
                    .append((int) radii[0])
                    .append("px ")
                    .append((int) radii[2])
                    .append("px ")
                    .append((int) radii[4])
                    .append("px ")
                    .append((int) radii[6])
                    .append("px;");

            // copy css string to clipboard
            ClipData clip = ClipData.newPlainText("CSS:", builder);
            clipboard.setPrimaryClip(clip);
            System.out.println(builder);

            Toast toast = Toast.makeText(getApplicationContext(), "Copied", Toast.LENGTH_SHORT);
            toast.show();

        });

        resetButton.setOnClickListener(view -> {
            Arrays.fill(radii, 0);
            topLeftX.getText().clear();
            topLeftY.getText().clear();
            topRightX.getText().clear();
            topRightY.getText().clear();
            bottomRightX.getText().clear();
            bottomRightY.getText().clear();
            bottomLeftX.getText().clear();
            bottomLeftY.getText().clear();

            box.setCornerRadii(radii);
        });

        changeButton.setOnClickListener(view -> {

            try {
                String topLX = topLeftX.getText().toString();
                String topLY = topLeftY.getText().toString();
                String topRX = topRightX.getText().toString();
                String topRY = topRightY.getText().toString();
                String bottomRX = bottomRightX.getText().toString();
                String bottomRY = bottomRightY.getText().toString();
                String bottomLX = bottomLeftX.getText().toString();
                String bottomLY = bottomLeftY.getText().toString();

                ArrayList<String> stringsOfEditTexts = new ArrayList<>();
                stringsOfEditTexts.add(topLX);
                stringsOfEditTexts.add(topLY);
                stringsOfEditTexts.add(topRX);
                stringsOfEditTexts.add(topRY);
                stringsOfEditTexts.add(bottomRX);
                stringsOfEditTexts.add(bottomRY);
                stringsOfEditTexts.add(bottomLX);
                stringsOfEditTexts.add(bottomLY);

                // loop through all edit texts, parse them to ints, add them to radii array
                int index = 0;
                int toAdd;
                for (String s : stringsOfEditTexts) {
                    if (s.isEmpty()) {
                        s = "0";
                    }
                    try {
                        toAdd = Integer.parseInt(s);
                    } catch (NumberFormatException n) {
                        toAdd = 0;
                        Toast toast = Toast.makeText(getApplicationContext(), "Issue with numbers, check format", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    radii[index] = toAdd;
                    index++;


                }
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            box.setCornerRadii(radii);
        });
    }
}