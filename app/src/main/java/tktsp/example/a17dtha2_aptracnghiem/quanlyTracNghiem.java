package tktsp.example.a17dtha2_aptracnghiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class quanlyTracNghiem extends AppCompatActivity {
    ConstraintLayout btnToan,btnVan;
    Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quanly_trac_nghiem);
        btnback=findViewById(R.id.thoat);
        btnToan=findViewById(R.id.toan);
        btnVan=findViewById(R.id.van);
        btnToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(quanlyTracNghiem.this,listTracNghiemToan.class);
                startActivity(intent);
            }
        });
        btnVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(quanlyTracNghiem.this,listTracNghiemVan.class);
                startActivity(intent);
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(quanlyTracNghiem.this,bottomNavigation.class);
                //intent.putExtra("item",2);
                startActivity(intent);


            }
        });
    }
}