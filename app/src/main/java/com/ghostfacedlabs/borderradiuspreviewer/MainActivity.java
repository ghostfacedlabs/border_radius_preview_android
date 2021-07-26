package com.ghostfacedlabs.borderradiuspreviewer;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        float[] radii = new float[]{0, 0, 0, 0, 0, 0, 0, 0};

        // draw rectangle on screen
        GradientDrawable box = new GradientDrawable();
        box.setColor(ContextCompat.getColor(this,
                R.color.orange_main));
        View boxView = findViewById(R.id.boxView);
        boxView.setBackground(box);


        resetButton.setOnClickListener(view -> {
            Arrays.fill(radii, 0);
            topLeft.setText(getResources().getText(R.string.default_dp));
            topRight.setText(getResources().getText(R.string.default_dp));
            bottomRight.setText(getResources().getText(R.string.default_dp));
            bottomLeft.setText(getResources().getText(R.string.default_dp));

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