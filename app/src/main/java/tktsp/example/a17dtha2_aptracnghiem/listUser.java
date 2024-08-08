package tktsp.example.a17dtha2_aptracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import tktsp.example.a17dtha2_aptracnghiem.DAO.userDao;
import tktsp.example.a17dtha2_aptracnghiem.adapter.userAdapter;
import tktsp.example.a17dtha2_aptracnghiem.model.User;

public class listUser extends AppCompatActivity {

    ListView lvUser;
    userDao userDao;
    List<User> dsUser;
    userAdapter adt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        lvUser=(ListView) findViewById(R.id.listUser);
        userDao=new userDao(this);
        dsUser=userDao.getallUser();
        adt=new userAdapter(dsUser,this);
        lvUser.setAdapter(adt);
    }
}