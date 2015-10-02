package com.example.android.lab4_modernartui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends ActionBarActivity {

    private SeekBar seekBar;
    private LinearLayout layout_leftUpper;
    private LinearLayout layout_leftBottom;
    private LinearLayout layout_rightUpper;
    private LinearLayout layout_rightMid;
    private LinearLayout layout_rightBottom;

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();

        // Initialize layouts and set background colors
        layout_leftUpper = (LinearLayout) findViewById(R.id.layout_left_upper);
        layout_leftUpper.setBackgroundColor(Color.argb(255, 100, 255, 255));

        layout_leftBottom = (LinearLayout) findViewById(R.id.layout_left_bottom);
        layout_leftBottom.setBackgroundColor(Color.argb(255, 255, 255, 220));

        layout_rightUpper = (LinearLayout) findViewById(R.id.layout_right_upper);
        layout_rightUpper.setBackgroundColor(Color.argb(255, 200, 255, 255));

        layout_rightMid = (LinearLayout) findViewById(R.id.layout_right_mid);
        layout_rightMid.setBackgroundColor(Color.argb(255, 255, 0, 0));

        layout_rightBottom = (LinearLayout) findViewById(R.id.layout_right_bottom);
        layout_rightBottom.setBackgroundColor(Color.argb(255, 255, 255, 255));


        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                layout_leftUpper.setBackgroundColor(Color.argb(255, 100 - progress, 255, 255));
                layout_leftBottom.setBackgroundColor(Color.argb(255, 255, 255, 220 - (progress * 2)));
                layout_rightUpper.setBackgroundColor(Color.argb(255, 200 - progress, 255, 255));
                layout_rightMid.setBackgroundColor(Color.argb(255, 255 - progress, 0, 0));
                layout_rightBottom.setBackgroundColor(Color.argb(255, 255, 255, 255 - progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_more_information) {
            createDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setTitle("More information");

        dialogBuilder
                .setMessage("Inspired by the works of artists such as Piet Mondrian " +
                        "and Ben Nicholson." + System.lineSeparator() +
                        "Click below to learn more!" + System.lineSeparator())
                .setPositiveButton("Visit MOMA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri webpage = Uri.parse("http://www.moma.org");
                        Intent visitMOMAintent = new Intent(Intent.ACTION_VIEW, webpage);
                        startActivity(visitMOMAintent);
                    }
                })
                .setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
