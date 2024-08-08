package tktsp.example.a17dtha2_aptracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.paperdb.Paper;

public class TEST extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printKeyHash();
    }
    public  void printKeyHash(){
        try {
            PackageInfo info=getPackageManager().getPackageInfo("tktsp.example.a17dtha2_aptracnghiem", PackageManager.GET_SIGNATURES);
            for(Signature signature: info.signatures)
            {
                MessageDigest md=MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }

        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
}