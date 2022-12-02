package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    int currentPlayer = 0;
    boolean gameActive = true;
    int[] gameState = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };
    String winner = "O";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View view) {
        String winner = "O";
        ImageView ivClicked = (ImageView) view;
        ivClicked.setAlpha(1.0f);
        ivClicked.setVisibility(View.VISIBLE);
        View player = (View) findViewById(R.id.current_player);
        ImageView current_player = (ImageView) player;
        current_player.setVisibility(View.VISIBLE);
        int clickedImageView = Integer.parseInt(ivClicked.getTag().toString());
        if(gameState[clickedImageView] == -1 && gameActive){
            gameState[clickedImageView] = currentPlayer;
            if(currentPlayer == 0){
                ivClicked.setImageResource(R.drawable.x);
                current_player.setImageResource(R.drawable.oplay);

                currentPlayer = 1;
            } else {
                ivClicked.setImageResource(R.drawable.o);
                current_player.setImageResource(R.drawable.xplay);

                currentPlayer = 0;
            }
            ivClicked.setVisibility(View.VISIBLE);
            for (int[] winningPosition: winningPositions
            ) {
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != -1){
                    gameActive = false;
                    if(gameState[winningPosition[0]] == 0){
                        winner = "x";
                    }
                    String f_cell = "cell"+winningPosition[0];
                    String s_cell = "cell"+winningPosition[1];
                    String t_cell = "cell"+winningPosition[2];
                    int f_id = getResources().getIdentifier(f_cell, "id", getPackageName());
                    int s_id = getResources().getIdentifier(s_cell, "id", getPackageName());
                    int t_id = getResources().getIdentifier(t_cell, "id", getPackageName());


                    ImageView f_v = findViewById(f_id);
                    ImageView s_v = findViewById(s_id);
                    ImageView t_v = findViewById(t_id);

                    f_v.setBackgroundColor(Color.rgb(255,255,0));
                    s_v.setBackgroundColor(Color.rgb(255,255,0));
                    t_v.setBackgroundColor(Color.rgb(255,255,0));

                    TextView tvMessage = findViewById(R.id.tvMessage);
                    tvMessage.setText(winner + " has won!");
                    LinearLayout layout = findViewById(R.id.restartLayout);
                    layout.setVisibility(View.VISIBLE);
                }else{
                    boolean gameOver = true;
                    for (int currentPlayer: gameState
                    ) {
                        if(currentPlayer == -1){
                            gameOver = false;
                        }
                    }
                    if(gameOver){
                        TextView tvMessage = findViewById(R.id.tvMessage);
                        tvMessage.setText("It's a draw");
                        LinearLayout layout = findViewById(R.id.restartLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view) {
        winner = "X";
        gameActive = true;
        currentPlayer = 0;
        LinearLayout layout = findViewById(R.id.restartLayout);
        View player = (View) findViewById(R.id.current_player);
        ImageView current_player = (ImageView) player;
        current_player.setVisibility(View.VISIBLE);
        current_player.setImageResource(R.drawable.xplay);
        layout.setVisibility(View.INVISIBLE);
        for (int i=0; i< gameState.length; i++){
            int id = getResources().getIdentifier("cell"+i, "id", getPackageName());
            ImageView f_v = findViewById(id);
            f_v.setBackgroundColor(Color.rgb(255,255,255));
            gameState[i] = -1;
        }
        LinearLayout gameLayout = findViewById(R.id.gameLayout);
        for (int i=0; i < gameLayout.getChildCount(); i++){
            View subView = gameLayout.getChildAt(i);
            if(subView instanceof LinearLayout){
                LinearLayout linearLayout = (LinearLayout) subView;
                for(int j=0; j < linearLayout.getChildCount(); j++){
                    View linearSubView = linearLayout.getChildAt(j);
                    if(linearSubView instanceof ImageView){
                        linearSubView.setAlpha(0.0f);
                    }
                }
            }
        }
    }
}