//John Hereshko - Java I - Project 3


package com.jeshko.android.hereshko_john_ji_project3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jeshko.android.hereshko_john_ji_project3.R;

public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String message = intent.getStringExtra("title");
        String extra = intent.getStringExtra("subtitle");
        String position = intent.getStringExtra("position");

        TextView textView;
        textView = (TextView) this.findViewById(R.id.DetailTextView);
        textView.setText("Title: " + message);
        textView.setTextSize(30);

        TextView positionTextView = (TextView) this.findViewById(R.id.PositionTextView);
        positionTextView.setText("Position: " + position);
        positionTextView.setTextSize(20);

        if (extra != null)
        {
            TextView extraTextView = (TextView) this.findViewById(R.id.ExtraTextView);
            extraTextView.setText("Subtitle: " + extra);
            extraTextView.setTextSize(20);
        }
        else
        {
            TextView extraTextView = (TextView) this.findViewById(R.id.ExtraTextView);
            extraTextView.setText("Subtitle: Not Entered");
            extraTextView.setTextSize(20);
        }
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
