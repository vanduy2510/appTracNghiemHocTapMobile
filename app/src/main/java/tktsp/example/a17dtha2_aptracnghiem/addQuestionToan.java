package tktsp.example.a17dtha2_aptracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tktsp.example.a17dtha2_aptracnghiem.DAO.quizcontractDao;
import tktsp.example.a17dtha2_aptracnghiem.model.QuizContract;

public class addQuestionToan extends AppCompatActivity {
 EditText txtcauhoi,txtoption1,txtoption2,txtoption3,txtcaudung;
 Button btnadd,btnback;
 quizcontractDao quizcontractDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question_toan);
        anhxa();
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(addQuestionToan.this,quanlyTracNghiem.class);
                startActivity(intent);
            }
        });
    }
    public void addQuiz(View view){
        //Toast.makeText(addQuestionToan.this,"hihisadasdasdas",Toast.LENGTH_LONG).show();
        quizcontractDao=new quizcontractDao(addQuestionToan.this);
        String cauhoi=txtcauhoi.getText().toString();
        String option1=txtoption1.getText().toString();
        String option2=txtoption2.getText().toString();
        String option3=txtoption3.getText().toString();
        String caudung=txtcaudung.getText().toString();
        QuizContract quizContract=new QuizContract(null,cauhoi,option1,option2,option3,caudung,1);
        try {
            if (quizcontractDao.insertQuiz(quizContract)>0){
                int a=quizcontractDao.insertQuiz(quizContract);
                Log.d("hihihi", String.valueOf(a));

                showdialog();
            }else{
                Toast.makeText(addQuestionToan.this,"Thêm Thất Bại ",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Log.e("loi",e.toString());
        }
    }
    public  void anhxa(){
        txtcauhoi=findViewById(R.id.txtcauhoi);
        txtoption1=findViewById(R.id.txtcoption1);
        txtoption2=findViewById(R.id.txtcoption2);
        txtoption3=findViewById(R.id.txtcoption3);
        txtcaudung=findViewById(R.id.txtcaudung);
        btnback=findViewById(R.id.btnback);
    }
    public  void  showdialog(){
        Dialog dialog = new Dialog(addQuestionToan.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_success);
        dialog.show();
        Button btnok=dialog.findViewById(R.id.btnOk);
        TextView txt1=dialog.findViewById(R.id.txt1);
        TextView txt2=dialog.findViewById(R.id.txt2);
        txt1.setText("Thêm Câu Hỏi Thành Công");
        txt2.setVisibility(View.INVISIBLE);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(addQuestionToan.this,listTracNghiemToan.class);
                startActivity(intent);
            }
        });
    }
}