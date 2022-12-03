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
                    if(winningPosition[0]==0 && winningPosition[1]==4 && winningPosition[2]==8){
                        String cross2 = "cross2";
                        int cross2_id = getResources().getIdentifier(cross2, "id", getPackageName());
                        ImageView cross2_v = findViewById(cross2_id);
                        cross2_v.setVisibility(View.VISIBLE);
                        cross2_v.bringToFront();
                    } else if (winningPosition[0]==2 && winningPosition[1]==4 && winningPosition[2]==6) {
                        String cross1 = "cross1";
                        int cross1_id = getResources().getIdentifier(cross1, "id", getPackageName());
                        ImageView cross1_v = findViewById(cross1_id);
                        cross1_v.setVisibility(View.VISIBLE);
                        cross1_v.bringToFront();
                    } else if (winningPosition[0]==0 && winningPosition[1]==3 && winningPosition[2]==6){
                        String veritcal1 = "vertical1";
                        int veritcal1_id = getResources().getIdentifier(veritcal1, "id", getPackageName());
                        ImageView veritcal1_v = findViewById(veritcal1_id);
                        veritcal1_v.setVisibility(View.VISIBLE);
                        veritcal1_v.bringToFront();
                    } else if (winningPosition[0]==1 && winningPosition[1]==4 && winningPosition[2]==7){
                        String vertical2 = "vertical2";
                        int veritcal2_id = getResources().getIdentifier(vertical2, "id", getPackageName());
                        ImageView veritcal2_v = findViewById(veritcal2_id);
                        veritcal2_v.setVisibility(View.VISIBLE);
                        veritcal2_v.bringToFront();
                    } else if (winningPosition[0]==2 && winningPosition[1]==5 && winningPosition[2]==8){
                        String vertical3 = "vertical3";
                        int veritcal3_id = getResources().getIdentifier(vertical3, "id", getPackageName());
                        ImageView veritcal3_v = findViewById(veritcal3_id);
                        veritcal3_v.setVisibility(View.VISIBLE);
                        veritcal3_v.bringToFront();
                    } else if (winningPosition[0]==6 && winningPosition[1]==7 && winningPosition[2]==8){
                        String horizontal3 = "horizontal3";
                        int horizontal3_id = getResources().getIdentifier(horizontal3, "id", getPackageName());
                        ImageView horizontal3_v = findViewById(horizontal3_id);
                        horizontal3_v.setVisibility(View.VISIBLE);
                        horizontal3_v.bringToFront();
                    } else if (winningPosition[0]==3 && winningPosition[1]==4 && winningPosition[2]==5) {
                        String horizontal2 = "horizontal2";
                        int horizontal2_id = getResources().getIdentifier(horizontal2, "id", getPackageName());
                        ImageView horizontal2_v = findViewById(horizontal2_id);
                        horizontal2_v.setVisibility(View.VISIBLE);
                        horizontal2_v.bringToFront();
                    }else if (winningPosition[0]==0&& winningPosition[1]==1 && winningPosition[2]==2){
                        String horizontal1 = "horizontal1";
                        int horizontal1_id = getResources().getIdentifier(horizontal1, "id", getPackageName());
                        ImageView horizontal1_v = findViewById(horizontal1_id);
                        horizontal1_v.setVisibility(View.VISIBLE);
                        horizontal1_v.bringToFront();
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
                    layout.bringToFront();
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
        for (int i=1;i<4;i++){
            int id = getResources().getIdentifier("horizontal"+i, "id", getPackageName());
            ImageView f_v = findViewById(id);
            f_v.setVisibility(View.INVISIBLE);
            id = getResources().getIdentifier("vertical"+i, "id", getPackageName());
            f_v = findViewById(id);
            f_v.setVisibility(View.INVISIBLE);
            if (i==1 || i ==2){
                id = getResources().getIdentifier("cross"+i, "id", getPackageName());
                f_v = findViewById(id);
                f_v.setVisibility(View.INVISIBLE);
            }
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