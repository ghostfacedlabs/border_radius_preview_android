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

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String ERROR_MESSAGE = "Issue with field, enter valid number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText topLeft = findViewById(R.id.top_left);
        EditText topRight = findViewById(R.id.top_right);
        EditText bottomLeft = findViewById(R.id.bottom_left);
        EditText bottomRight = findViewById(R.id.bottom_right);
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

            Toast toast = Toast.makeText(getApplicationContext(), "Copied!", Toast.LENGTH_LONG);
            toast.show();

        });

        resetButton.setOnClickListener(view -> {
            Arrays.fill(radii, 0);
            topLeft.getText().clear();
            topRight.getText().clear();
            bottomRight.getText().clear();
            bottomLeft.getText().clear();

            box.setCornerRadii(radii);
        });

        changeButton.setOnClickListener(view -> {

            try {
                String topL = topLeft.getText().toString();
                String topR = topRight.getText().toString();
                String bottomR = bottomRight.getText().toString();
                String bottomL = bottomLeft.getText().toString();

                if (topL.isEmpty()) {
                    topL = "0";
                }
                if (topR.isEmpty()) {
                    topR = "0";
                }
                if (bottomR.isEmpty()) {
                    bottomR = "0";
                }
                if (bottomL.isEmpty()) {
                    bottomL = "0";
                }

                try {
                    int tL = Integer.parseInt(topL);
                    radii[0] = tL;
                    radii[1] = tL;
                } catch (NumberFormatException n) {
                    topLeft.setError(ERROR_MESSAGE);
                }
                try {
                    int tR = Integer.parseInt(topR);
                    radii[2] = tR;
                    radii[3] = tR;
                } catch (NumberFormatException n) {
                    topRight.setError(ERROR_MESSAGE);
                }
                try {
                    int bL = Integer.parseInt(bottomL);
                    radii[6] = bL;
                    radii[7] = bL;
                } catch (NumberFormatException n) {
                    bottomLeft.setError(ERROR_MESSAGE);
                }
                try {
                    int bR = Integer.parseInt(bottomR);
                    radii[4] = bR;
                    radii[5] = bR;
                } catch (NumberFormatException n) {
                    bottomRight.setError(ERROR_MESSAGE);
                }


            } catch (NumberFormatException n) {
                System.out.println(n.getMessage());

            }
            box.setCornerRadii(radii);
        });
    }
}