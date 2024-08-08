package tktsp.example.a17dtha2_aptracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class processbar_quiz extends AppCompatActivity {
    ProgressBar progressBar;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processbar_quiz);
        progressBar=findViewById(R.id.pro);
        txt=findViewById(R.id.txt);
        progressBar.setMax(100);
        progressBar.setScaleY(2f);
        processAnimation();
    }
    private void processAnimation(){
        processbarAnimation processbarAnimation=new processbarAnimation(processbar_quiz.this,txt,
                progressBar,0f,100f);
        processbarAnimation.setDuration(6000);
        progressBar.setAnimation(processbarAnimation);
    }
}