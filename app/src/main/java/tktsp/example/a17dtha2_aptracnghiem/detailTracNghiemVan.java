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

public class detailTracNghiemVan extends AppCompatActivity {
    EditText txtcauhoi,txtoption1,txtoption2,txtoption3,txtcaudung;
    Button btnUpdate,btnback;
    quizcontractDao quizcontractDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trac_nghiem_van);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("DATA");
        int id=bundle.getInt("id");
        String question=bundle.getString("question");
        String option1=bundle.getString("option1");
        String option2=bundle.getString("option2");
        String option3=bundle.getString("option3");
        String answer=bundle.getString("answer");
        anhxa();
        quizcontractDao quizcontractDao=new quizcontractDao(detailTracNghiemVan.this);
        txtcauhoi.setText(question);
        txtoption1.setText(option1);
        txtoption2.setText(option2);
        txtoption3.setText(option3);
        txtcaudung.setText(answer);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizContract quiz=new QuizContract(
                        id,
                        txtcauhoi.getText().toString(),
                        txtoption1.getText().toString(),
                        txtoption2.getText().toString(),
                        txtoption3.getText().toString(),
                        txtcaudung.getText().toString(),
                        2
                );
                try {

                    if (quizcontractDao.updateQuiz(quiz)>0){
                        showdialog();
                    }else{
                        Toast.makeText(detailTracNghiemVan.this,"lỗi xảy ra",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.e("loi", e.toString());
                }
               // Toast.makeText(detailTracNghiemVan.this,"lỗi",Toast.LENGTH_SHORT).show();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(detailTracNghiemVan.this,listTracNghiemVan.class);
                startActivity(intent);
            }
        });
    }
    public  void anhxa(){
        txtcauhoi=findViewById(R.id.txtcauhoi);
        txtoption1=findViewById(R.id.txtcoption1);
        txtoption2=findViewById(R.id.txtcoption2);
        txtoption3=findViewById(R.id.txtcoption3);
        txtcaudung=findViewById(R.id.txtcaudung);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnback=findViewById(R.id.btnback);
    }
    public  void  showdialog(){
        Dialog dialog = new Dialog(detailTracNghiemVan.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_success);
        dialog.show();
        Button btnok=dialog.findViewById(R.id.btnOk);
        TextView txt1=dialog.findViewById(R.id.txt1);
        TextView txt2=dialog.findViewById(R.id.txt2);
        txt1.setText("Cập Nhật Thành Công");
        txt2.setVisibility(View.INVISIBLE);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(detailTracNghiemVan.this,listTracNghiemVan.class);
                startActivity(intent);
            }
        });
    }
}