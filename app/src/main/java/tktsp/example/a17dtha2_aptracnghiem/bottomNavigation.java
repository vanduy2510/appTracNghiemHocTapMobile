package tktsp.example.a17dtha2_aptracnghiem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import io.paperdb.Paper;
import tktsp.example.a17dtha2_aptracnghiem.DAO.General;
import tktsp.example.a17dtha2_aptracnghiem.adapter.viewpagerAdapter;

public class bottomNavigation extends AppCompatActivity {
    BottomNavigationView navigationView;
    ViewPager viewpager;
    MenuItem item;
    Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        navigationView= findViewById(R.id.nav1);
        viewpager=findViewById(R.id.viewpager1);
        Intent intent=getIntent();
//        menu=viewpager.findViewById(R.id.nav1);
//        item=menu.findItem(3);
//        String username= Paper.book().read(General.USER_NAME);
//        if (!username.equals("admin")){
//            item.setVisible(false);
//        }
        setUpViewpager();
       navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               switch (item.getItemId()){
                   case R.id.trangchu:
                       //Toast.makeText(bottomNavigation.this,"1",Toast.LENGTH_SHORT).show();
                      viewpager.setCurrentItem(0);
                       break;
                   case R.id.tailieu:
                       //Toast.makeText(bottomNavigation.this,"2",Toast.LENGTH_SHORT).show();
                       viewpager.setCurrentItem(1);
                       break;
                   case  R.id.tracnghiem:
                       //Toast.makeText(bottomNavigation.this,"3",Toast.LENGTH_SHORT).show();
                       viewpager.setCurrentItem(2);
                       break;
                   case  R.id.taikhoan:
                       //Toast.makeText(bottomNavigation.this,"4",Toast.LENGTH_SHORT).show();
                       viewpager.setCurrentItem(3);
                       break;
                   default:
                       viewpager.setCurrentItem(0);
                       break;
               }

               return true;
           }
       });
    }
    private void setUpViewpager(){
        viewpagerAdapter viewpagerAdapter=new viewpagerAdapter(getSupportFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewpager.setAdapter(viewpagerAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.trangchu).setChecked(true);
                        break;
                    case  1:
                        navigationView.getMenu().findItem(R.id.tailieu).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.tracnghiem).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.taikhoan).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
