package tktsp.example.a17dtha2_aptracnghiem;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class processbarAnimation extends Animation {
    private Context context;
    private TextView txt;
    private ProgressBar progressBar;
    private  float from;
    private  float to;

    public processbarAnimation(Context context, TextView txt, ProgressBar progressBar, float from, float to) {
        this.context = context;
        this.txt = txt;
        this.progressBar = progressBar;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value=from + ( to - from)*interpolatedTime;
        progressBar.setProgress((int)value);
        txt.setText((int)value+"%");
        if (value==to){
            context.startActivity(new Intent(context,QuizActivity.class));
        }
    }
}
