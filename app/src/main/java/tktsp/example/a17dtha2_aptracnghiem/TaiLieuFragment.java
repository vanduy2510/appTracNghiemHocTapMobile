package tktsp.example.a17dtha2_aptracnghiem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import io.paperdb.Paper;
import tktsp.example.a17dtha2_aptracnghiem.DAO.General;

public class TaiLieuFragment extends Fragment {
    LinearLayout btnQuanLy,hoctap;

    // TODO: Rename parameter arguments, choose names that match

    public TaiLieuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view=inflater.inflate(R.layout.fragment_tai_lieu, container, false);

        btnQuanLy=(LinearLayout)  view.findViewById(R.id.btnQuanLy);
        hoctap=view.findViewById(R.id.hoctap);
        //txtPer.setText("hihihi");
        Paper.init(getContext());
        String username= Paper.book().read(General.USER_NAME);
        if (username.equals("admin") && !username.isEmpty()){
            btnQuanLy.setVisibility(View.VISIBLE);
            hoctap.setVisibility(View.INVISIBLE);

        }
        btnQuanLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"hihi",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}