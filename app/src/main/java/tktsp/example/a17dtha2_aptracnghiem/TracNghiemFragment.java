package tktsp.example.a17dtha2_aptracnghiem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import io.paperdb.Paper;
import tktsp.example.a17dtha2_aptracnghiem.DAO.General;


public class TracNghiemFragment extends Fragment {

    LinearLayout btnQuanLy,hoctap,btnToan,btnVan;
    public TracNghiemFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_trac_nghiem, container, false);
        btnQuanLy=(LinearLayout)  view.findViewById(R.id.btnQuanLy);
        btnToan=view.findViewById(R.id.btnToan);
        btnVan=view.findViewById(R.id.btnVan);
        btnToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),readyQuiz.class);
                Paper.init(getContext());
                Paper.book().write(General.CATEGORY, 1);
                startActivity(intent);
            }
        });
        btnVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),readyQuiz.class);
                Paper.init(getContext());
                Paper.book().write(General.CATEGORY, 2);
                startActivity(intent);
            }
        });
        btnQuanLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),quanlyTracNghiem.class);
                startActivity(intent);

            }
        });
        hoctap=view.findViewById(R.id.hoctap);
        //txtPer.setText("hihihi");
        String username= Paper.book().read(General.USER_NAME);
        if (username.equals("admin") && !username.isEmpty()){
            btnQuanLy.setVisibility(View.VISIBLE);
            hoctap.setVisibility(View.INVISIBLE);
        }
        return view;
    }

}