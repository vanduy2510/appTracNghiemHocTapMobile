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

public class listTracNghiemVan extends AppCompatActivity {

    RecyclerView recyclerView;
    List<QuizContract> listquiz;
   quizcontractDao quizcontractDao;
    tracnghiemtoanAdapter adapter;
    Button btnback,btnthem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trac_nghiem_van);
        recyclerView = (RecyclerView) findViewById(R.id.listquiz);
        recyclerView.setLayoutManager(new LinearLayoutManager(listTracNghiemVan.this));
        quizcontractDao=new quizcontractDao(listTracNghiemVan.this);
        listquiz=quizcontractDao.getQuizbyCat(2);
        adapter=new tracnghiemtoanAdapter(listquiz,this);
        recyclerView.setAdapter(adapter);
        btnback=findViewById(R.id.btnback);
        btnthem=findViewById(R.id.btnthem);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(listTracNghiemVan.this,addQuestionVan.class);
                startActivity(intent);
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(listTracNghiemVan.this,quanlyTracNghiem.class);
                startActivity(intent);
            }
        });
    }

}