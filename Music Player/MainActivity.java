package com.example.musicplayerapplication;

import androidx.appcompat.app.AppCompatActivity;




import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //Widgets
    Button forward_btn, back_btn, play_btn, stop_button;
    TextView time_txt, title_txt;
    SeekBar seekbar;

    //MediaPlayer
    MediaPlayer mediaPlayer;

    //Handlers
    Handler handler = new Handler();

    //Variables
    double startTime = 0;
    double finalTime = 0;
    int forwardTime = 10000;
    int backwardTime = 10000;
    static int oneTimeOnly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_btn = findViewById(R.id.play_btn);
        stop_button = findViewById(R.id.pause_btn);
        forward_btn = findViewById(R.id.forward_btn);
        back_btn = findViewById(R.id.back_btn);

        title_txt = findViewById(R.id.song_title);
        time_txt = findViewById(R.id.time_left_text);

        seekbar = findViewById(R.id.seekBar);

        //Media Player
        mediaPlayer = MediaPlayer.create(this, R.raw.roberry);

        title_txt.setText(getResources().getIdentifier(
                "Robbery",
                "raw",
                getPackageName()
        ));
        seekbar.setClickable(false);

        //Adding functionalities to the buttons

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayMusic();
            }
        });

        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        forward_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;
                if ((temp + forwardTime) < +finalTime) {

                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                } else {
                    Toast.makeText(MainActivity.this, "Can't Jump forward", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;

                if ((temp - backwardTime) > 0) {
                    startTime = startTime - backwardTime;
                    mediaPlayer.seekTo((int) startTime);
                } else {
                    Toast.makeText(MainActivity.this, "Can't Go Back", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void PlayMusic() {
        mediaPlayer.start();

        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();

        if (oneTimeOnly == 0) {
            seekbar.setMax((int) finalTime);
            oneTimeOnly = 1;
        }

        time_txt.setText(String.format(
                "%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime)))
        );

        seekbar.setProgress((int) startTime);
        handler.postDelayed(UpdateSongTime, 100);
    }

    private final Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            time_txt.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
            seekbar.setProgress((int) startTime);
            handler.postDelayed(this, 100);
        }
    };
}
