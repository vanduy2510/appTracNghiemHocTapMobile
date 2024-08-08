package tktsp.example.a17dtha2_aptracnghiem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.VibrationEffect;
import android.view.ActionMode;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;
import tktsp.example.a17dtha2_aptracnghiem.DAO.General;
import tktsp.example.a17dtha2_aptracnghiem.DAO.quizcontractDao;
import tktsp.example.a17dtha2_aptracnghiem.model.QuizContract;
import android.os.Vibrator;

public class QuizActivity extends AppCompatActivity {
    Button btnHuy,btnNext;
    RadioButton rb1,rb2,rb3;
    RadioGroup radio_button;
    MediaPlayer mp3;
    Vibrator v;
    ObjectAnimator animator;
    TextView txtDiem,txtCountQuiz,txtQuestion,txtCountDown;
    LinearLayout bgquiz;
    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;
    private List<QuizContract> quizContractList;
    private int questionCounter;
    private int questionTotal;
    private QuizContract questionCurrent;
    private int score;
    private boolean answered;
    quizcontractDao quizcontractDao;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        anhxa();
        textColorDefaultRb=rb1.getTextColors();
        textColorDefaultCd=txtCountDown.getTextColors();
        quizcontractDao=new quizcontractDao(QuizActivity.this);
        Paper.init(QuizActivity.this);
        int id_cat= Paper.book().read(General.CATEGORY);
        quizContractList=quizcontractDao.getQuizbyCat(id_cat);
        questionTotal=quizContractList.size();
        Collections.shuffle(quizContractList); //dòng này cho phép sáo trộn phần tử trong mảng
        showNextQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // showNextQuestion();

                if(!answered){
                    if (rb1.isChecked() || rb3.isChecked() || rb2.isChecked() ){
                        checkAnswerd();
                    }else{
                        Toast.makeText(QuizActivity.this,"Vui lòng chọn đáp án",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    showNextQuestion();
                }
            }
        });
    }
    public void anhxa(){
         btnHuy=(Button) findViewById(R.id.btnHuy);
         btnNext=(Button) findViewById(R.id.btnNext);
         txtDiem=(TextView) findViewById(R.id.txtdiem);
         txtCountQuiz=(TextView) findViewById(R.id.txtCountQuiz);
         radio_button=(RadioGroup) findViewById(R.id.radio_button);
         rb1=(RadioButton) findViewById(R.id.btnOption1);
         rb2=(RadioButton) findViewById(R.id.btnOption2);
         rb3=(RadioButton) findViewById(R.id.btnOption3);
        txtQuestion=(TextView) findViewById(R.id.txtQuestion);
        txtCountDown=(TextView) findViewById(R.id.txtCountDown);
        bgquiz=findViewById(R.id.bgquiz);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(QuizActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_fail);
                dialog.show();
                Button btnok = (Button) dialog.findViewById(R.id.btnOk);
                btnok.setOnClickListener(new View.OnClickListener() {
                   // @RequiresApi(api = Build.VERSION_CODES.P)
                    @Override
                    public void onClick(View view) {

                        Intent intent=new Intent(QuizActivity.this,bottomNavigation.class);
                        startActivity(intent);
                    }
                });
                Button btnhuy = (Button) dialog.findViewById(R.id.btnHuy);
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }
    private void showNextQuestion(){
        btnNext.setText("Xác Nhận");
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        radio_button.clearCheck();
        if (questionCounter < questionTotal){
            questionCurrent=quizContractList.get(questionCounter);
            rb1.setText("A. "+questionCurrent.getOption1());
            rb2.setText("B. "+questionCurrent.getOption2());
            rb3.setText("C. "+questionCurrent.getOption3());
            //int stt=questionCounter+1;
            txtQuestion.setText("Câu Hỏi "+(questionCounter+1)+": "+questionCurrent.getQuestion().toString());
            txtCountQuiz.setText("Quiz : "+(questionCounter+1)+" / "+questionTotal);
            answered=false;
            questionCounter++;
            //chạy countdown
            timeLeftInMillis=20000; //30 giây
            startCountDown();

        }else{
            finishQuiz();
        }
    }
    private void   startCountDown(){
        countDownTimer =new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long l) {  //cứ sau 1 giây chạy hàm onTick
                timeLeftInMillis=l;  //l cho biết số mili giây còn lại
                updateCountDown();
            }

            @Override
            public void onFinish() {

                timeLeftInMillis=0;
                updateCountDown();
                checkAnswerd();
            }
        }.start();
    }

    private  void updateCountDown(){
        int minustes =(int) (timeLeftInMillis / 1000) /60; // lấy phút
        int seconds =(int) (timeLeftInMillis / 1000) % 60; // lấy  giay

        /// quy chuẩn này đảm bảo răng số phút và giây đẩm bảo đúng với timeLeftInMillis đã set
        String timeFormat=String.format(Locale.getDefault(),"%02d : %02d",minustes,seconds);
        txtCountDown.setText(timeFormat);
        if (timeLeftInMillis < 6000){
            txtCountDown.setTextColor(Color.RED);
            mp3=MediaPlayer.create(this,R.raw.cat_sound);
            mp3.start();
             v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }
            animator=ObjectAnimator.ofInt(bgquiz,"backgroundColor",Color.RED,Color.YELLOW,Color.parseColor("#ECA53E"));
            animator.setDuration(500);
            animator.setEvaluator(new ArgbEvaluator());
           // animator.setRepeatMode(Animation.REVERSE);
            //animator.setRepeatCount(Animation.INFINITE);
            animator.start();

        }else {
            txtCountDown.setTextColor(textColorDefaultCd);
        }
    }
    private  void checkAnswerd(){

        bgquiz.clearAnimation();
        answered=true;
        countDownTimer.cancel();
        RadioButton selected=findViewById(radio_button.getCheckedRadioButtonId());
        //ánh xạ buttom đã check
        int vitrichon=radio_button.indexOfChild(selected)+1;
        if (vitrichon==Integer.parseInt(questionCurrent.getAnswer())){
            score=score+10;
            txtDiem.setText("score : "+score);
        }
        ketqua();
    }
    private  void ketqua(){
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        switch (Integer.parseInt(questionCurrent.getAnswer()) ){
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
        }
        if (questionCounter < questionTotal){
            btnNext.setText("Tiếp Tục");
        }else {
            btnNext.setText("Kết Thúc");
        }
    }
    private void finishQuiz(){
        Intent intent=new Intent(QuizActivity.this,KetQuaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("total_score",score);
        intent.putExtra("DATA", bundle);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (v.hasVibrator() && v!=null){
            v.cancel();
        }
        if (mp3.isPlaying() && mp3 !=null) {
            mp3.pause();
            mp3.stop();
            mp3.release();
            mp3 = null;
        }
        if (countDownTimer !=null){
            countDownTimer.cancel();
        }
    }

}