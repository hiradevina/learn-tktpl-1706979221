package id.ac.ui.cs.mobileprogramming.hira.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private boolean running;
    private int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button startButton = (Button) findViewById(R.id.button_start);
        final Button stopButton = (Button) findViewById(R.id.button_stop);
        final Button resetButton = (Button) findViewById(R.id.button_reset);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStart(v);
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickReset(v);
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStop(v);
            }
        });
        runTimer();
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        second = 0;
    }

    public void runTimer() {
        final TextView placeholder = (TextView) findViewById(R.id.text_second);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second/3600;
                int minutes = (second%3600)/60;
                int sec = second%60;
                String timeFormat = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, sec);
                placeholder.setText(timeFormat);
                if (running) {
                    second++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
