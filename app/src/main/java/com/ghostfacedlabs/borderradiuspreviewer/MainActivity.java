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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText topLeft = findViewById(R.id.top_left);
        EditText topRight = findViewById(R.id.top_right);
        EditText bottomLeft = findViewById(R.id.bottom_left);
        EditText bottomRight = findViewById(R.id.bottom_right);
        Button changeButton = findViewById(R.id.button);
        float[] radii = new float[] {0, 0, 0, 0, 0, 0, 0, 0};

        GradientDrawable box = new GradientDrawable();
        box.setColor(ContextCompat.getColor(this,
                R.color.orange_main));
        View boxView = findViewById(R.id.boxView);
        boxView.setBackground(box);



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

                int tL = Integer.parseInt(topL);
                int tR = Integer.parseInt(topR);
                int bL = Integer.parseInt(bottomL);
                int bR = Integer.parseInt(bottomR);

                radii[0] = tL;
                radii[1] = tL;
                radii[2] = tR;
                radii[3] = tR;
                radii[4] = bR;
                radii[5] = bR;
                radii[6] = bL;
                radii[7] = bL;

            } catch (NumberFormatException n) {
                topLeft.setError("Issue with top left");
                System.out.println(Arrays.toString(n.getStackTrace()));
                System.out.println(n.getMessage());

            }
            box.setCornerRadii(radii);
        });
    }
}