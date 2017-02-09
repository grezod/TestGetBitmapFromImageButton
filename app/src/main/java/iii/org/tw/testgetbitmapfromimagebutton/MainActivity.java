package iii.org.tw.testgetbitmapfromimagebutton;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import java.io.IOException;
import java.net.URL;

import static iii.org.tw.testgetbitmapfromimagebutton.R.id.fab;
import static iii.org.tw.testgetbitmapfromimagebutton.R.id.image;

public class MainActivity extends AppCompatActivity {
    Bitmap i_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ImageButton t1 = (ImageButton)findViewById(R.id.t1);

        final ImageButton t2 = (ImageButton)findViewById(R.id.t2);

        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://i.imgur.com/616Z6CY.jpg");
                     i_image = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            t1.setImageBitmap(i_image);
                        }
                    });


                } catch(IOException e) {
                    System.out.println(e);
                }
            }
        });
        thread.start();




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Bitmap bitmap = ((BitmapDrawable)t1.getDrawable()).getBitmap();

                t2.setImageBitmap(bitmap);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
