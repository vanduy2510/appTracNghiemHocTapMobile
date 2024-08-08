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
import android.widget.Toast;

import java.util.regex.Pattern;



import tktsp.example.a17dtha2_aptracnghiem.DAO.userDao;
import tktsp.example.a17dtha2_aptracnghiem.model.User;

public class registerUser extends AppCompatActivity {
    Button btnBack;
    Button btnDangky;
    EditText edtUsename;
    EditText edtPassword;
    EditText edtRPassword;
    EditText edtName;
    EditText edtEmail;
    userDao userDao;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        anhxa();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Main=new Intent(registerUser.this,MainActivity.class);
                startActivity(Main);
                finish();
            }
        });
    }
    public void  anhxa(){
        btnBack=findViewById(R.id.btnBack);
        btnDangky=findViewById(R.id.btnRegister);
        edtUsename=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);
        edtRPassword=findViewById(R.id.edtRPassword);
        edtEmail=findViewById(R.id.edtEmail);
        edtName=findViewById(R.id.edtName);
    }
    public void addUser(View view){
        anhxa();
        //khởi tạo userDao để bắt đầu thực hiện nghiệp vụ
         userDao=new userDao(registerUser.this);
        //check điều kiện
        if (edtName.getText().toString().isEmpty() || edtUsename.getText().toString().isEmpty() ||
                edtPassword.getText().toString().isEmpty() || edtRPassword.getText().toString().isEmpty() || edtEmail.getText().toString().isEmpty()){
            Toast.makeText(registerUser.this,"Không Được Bỏ Tróng",Toast.LENGTH_LONG).show();
        }else if (edtUsename.getText().toString().length()<5 || edtPassword.getText().toString().length()<5){
            Toast.makeText(registerUser.this,"Mật Khẩu Hoặc USername phải ít nhất 5 ký tự",Toast.LENGTH_LONG).show();

       }else if (!((edtPassword.getText().toString()).equals(edtRPassword.getText().toString()))){
                Toast.makeText(registerUser.this,"Mật Khẩu Không Khớp",Toast.LENGTH_LONG).show();
        }else if (edtEmail.getText().toString().trim().matches(emailPattern)==false) {
            Toast.makeText(registerUser.this, "Bạn nhập sai Email", Toast.LENGTH_LONG).show();
        }else if( userDao.checkUsername(edtUsename.getText().toString())==true){
            Toast.makeText(registerUser.this, "Tài Khoản Tồn Tại", Toast.LENGTH_LONG).show();
        }
        else{
               // khởi tạo user mới
                User user=new User(edtUsename.getText().toString(),
                        edtPassword.getText().toString(),edtName.getText().toString(),edtEmail.getText().toString(),1);
                try {
                    if (userDao.insertUser(user)>0){
                        dialog_success();
                    }else{
                        Toast.makeText(registerUser.this,"Thêm Thất Bại "+user.getName_user()+"",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Log.e("loi",e.toString());
                }
            }

        }
    public void dialog_success(){
        Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_success);
        dialog.show();
        Button btnok=(Button) dialog.findViewById(R.id.btnOk);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(registerUser.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    }


