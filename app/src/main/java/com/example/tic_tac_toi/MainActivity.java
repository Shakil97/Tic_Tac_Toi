package com.example.tic_tac_toi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.GenericArrayType;

public class MainActivity extends AppCompatActivity {
    //yellow=o,red=1 2=empty
    int[][] winningPosition={{0,1,2},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};
    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    boolean gameActive=true;

    public  void  dropIn(View view) {


        ImageView counter = (ImageView) view;
        //Log.i("tag",counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1400);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1400).setDuration(300);

            for (int[] winningPosition : winningPosition) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    String winner = "";
                    gameActive = false;

                    if (activePlayer == 0) {
                        winner = "YELLOW";

                    } else {
                        winner = "RED";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.PlayAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.WinnerTextView);
                    winnerTextView.setText(winner + " is won");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);

                }
            }
        }
    }

       public void playAgain (View view) {
            Button playAgainButton = (Button) findViewById(R.id.PlayAgainButton);
            TextView winnerTextView = (TextView) findViewById(R.id.WinnerTextView);
            playAgainButton.setVisibility(View.INVISIBLE);
            winnerTextView.setVisibility(View.INVISIBLE);
            GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);


            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                ImageView counter = (ImageView) gridLayout.getChildAt(i);
                counter.setImageDrawable(null);

            }
            for (int i = 0; i < gameState.length; i++) {
                gameState[i] = 2;

            }
            activePlayer = 0;

            gameActive = true;

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
