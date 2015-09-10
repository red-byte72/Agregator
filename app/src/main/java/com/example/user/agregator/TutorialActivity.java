package com.example.user.agregator;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
//        ViewPager vpTutorial = (ViewPager) findViewById(R.id.pa);
/*        final RelativeLayout bg = (RelativeLayout)findViewById(R.id.tutorialLayout);
        bg.setOnTouchListener(new */
        final ImageView imgTutorial = (ImageView) findViewById(R.id.imageTutorial);
        imgTutorial.setImageResource(R.drawable.eat1);
        imgTutorial.setOnTouchListener(new OnSwipeTouchListener(TutorialActivity.this){
            public void onSwipeTop() {
                Toast.makeText(TutorialActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(TutorialActivity.this, "right", Toast.LENGTH_SHORT).show();
                imgTutorial.setImageResource(R.drawable.eat1);
            }
            public void onSwipeLeft() {
                Toast.makeText(TutorialActivity.this, "left", Toast.LENGTH_SHORT).show();
                imgTutorial.setImageResource(R.drawable.eat2);
            }
            public void onSwipeBottom() {
                Toast.makeText(TutorialActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

            public boolean onTouch(View v, MotionEvent event) {
               // return gestureDetector.onTouchEvent(event);
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tutorial, menu);
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
