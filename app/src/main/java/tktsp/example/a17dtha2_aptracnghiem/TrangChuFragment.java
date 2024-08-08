package tktsp.example.a17dtha2_aptracnghiem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.paperdb.Paper;
import tktsp.example.a17dtha2_aptracnghiem.DAO.General;


public class TrangChuFragment extends Fragment {
    TextView txtPer;
   // LinearLayout all;
    ImageView img;
    public TrangChuFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_trang_chu, container, false);
        txtPer= view.findViewById(R.id.txtPer);
        //all=view.findViewById(R.id.all);
        img=view.findViewById(R.id.img);
        Paper.init(getContext());
        String username= Paper.book().read(General.USER_NAME);
        String a="admin";
        if (a.equals(username) && !username.isEmpty()){
            txtPer.setText("Hello Admin");
          //  all.setVisibility(View.INVISIBLE);
            img.setImageResource(R.drawable.manager);
        }

        return view;

    }
}