package tktsp.example.a17dtha2_aptracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FoldingCube;

public class processbar_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_bar);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.processBar);
        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(new FoldingCube());

        new CountDownTimer(2000, 2000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                // Here do what you like...
                Intent intent = new Intent(processbar_Activity.this, bottomNavigation.class);
                startActivity(intent);
            }
        }.start();
    }
}