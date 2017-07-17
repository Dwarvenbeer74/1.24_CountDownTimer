package dwarvenbeer.a124_countdowntimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MalibuCountDownTimer mCountDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button button;
    private TextView text;
    private TextView timeElapsedView;
    private final long startTime = 50 * 1000;
    private final long interval = 1 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);

        text = (TextView)findViewById(R.id.timer);
        timeElapsedView = (TextView)findViewById(R.id.timeElapsed);
        mCountDownTimer = new MalibuCountDownTimer(startTime, interval);
        text.setText(text.getText() + String.valueOf(startTime));
    }

    @Override
    public void onClick(View v) {
        if (!timerHasStarted) {
            mCountDownTimer.start();
            timerHasStarted = true;
            button.setText("Start");
        } else {
            mCountDownTimer.cancel();
            timerHasStarted = false;
            button.setText("Reset");
        }
    }

    public class MalibuCountDownTimer extends CountDownTimer {
        public MalibuCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            text.setText("Time remain: " + millisUntilFinished);
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText("Time elapsed: " + String.valueOf(timeElapsed));
        }

        @Override
        public void onFinish() {
            text.setText("Time is up!");
            timeElapsedView.setText("Time elapsed: " + String.valueOf(startTime));
        }
    }
}
