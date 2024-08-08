package tktsp.example.a17dtha2_aptracnghiem.Screenshot;

import android.graphics.Bitmap;
import android.view.View;

public class Screenshot {
    public  static Bitmap takscreenshot(View v){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap bitmap=Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return bitmap;
    }
    public static Bitmap takscreenshotOfRootView(View v){
        return takscreenshot(v.getRootView());
    }

}
