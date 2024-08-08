package tktsp.example.a17dtha2_aptracnghiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import tktsp.example.a17dtha2_aptracnghiem.DAO.quizcontractDao;
import tktsp.example.a17dtha2_aptracnghiem.adapter.tracnghiemtoanAdapter;
import tktsp.example.a17dtha2_aptracnghiem.model.QuizContract;

public class listTracNghiemToan extends AppCompatActivity {
    RecyclerView recyclerView;
     List<QuizContract> listquiz;
    quizcontractDao quizcontractDao;
    tracnghiemtoanAdapter adapter;
    Button btnback,btnthem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trac_nghiem_toan);
        recyclerView = (RecyclerView) findViewById(R.id.listquiz);
        recyclerView.setLayoutManager(new LinearLayoutManager(listTracNghiemToan.this));
        quizcontractDao=new quizcontractDao(listTracNghiemToan.this);
        listquiz=quizcontractDao.getQuizbyCat(1);
        adapter=new tracnghiemtoanAdapter(listquiz,this);
        recyclerView.setAdapter(adapter);
        btnback=findViewById(R.id.btnback);
        btnthem=findViewById(R.id.btnthem);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(listTracNghiemToan.this,addQuestionToan.class);
                startActivity(intent);
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(listTracNghiemToan.this,quanlyTracNghiem.class);
                startActivity(intent);
            }
        });
    }
}