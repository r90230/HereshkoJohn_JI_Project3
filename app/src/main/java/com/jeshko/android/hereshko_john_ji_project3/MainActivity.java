//John Hereshko - Java I - Project 3

package com.jeshko.android.hereshko_john_ji_project3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.nfc.Tag;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    EditText enteredText;
    EditText subEnteredText;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> subArrayList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private TextView lengthView;
    private int charLengthTotal;
    private TextView charView;
    private Spinner spinner;
    private ListView listView;
    final Context context = this;
    private static final String TAG = MainActivity.class.getSimpleName();
    //Place currentPlace;
    int check = 0;
    final String savedArray = "orientationArray";
    final String savedLength = "lengthString";
    final String savedSub = "subArray";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            arrayList = savedInstanceState.getStringArrayList(savedArray);
            charLengthTotal = savedInstanceState.getInt(savedLength);
            subArrayList = savedInstanceState.getStringArrayList(savedSub);
        } else {
        }

            if (getResources().getConfiguration().orientation ==
                    Configuration.ORIENTATION_PORTRAIT) {
                setContentView(R.layout.activity_main);
                spinner = (Spinner)this.findViewById(R.id.spinner);


            } else {
                setContentView(R.layout.activity_main_land);
                listView = (ListView) this.findViewById(R.id.listView);
            }

        enteredText = (EditText)this.findViewById(R.id.editText);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            spinner.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            Log.d(TAG, arrayList.toString());


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                    check = check + 1;
                    if (check > 1) {
                        final Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                        final String clickedLine = getResources().getString(R.string.clicked_line);
                        final String removeLine = getResources().getString(R.string.remove);
                        final String item = ((TextView) view).getText().toString();
                        final String lineNumber;
                        lineNumber = Integer.toString(position + 1);

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                        alertDialog.setTitle(clickedLine);
                        alertDialog.setMessage(lineNumber + ". " + item);
                        alertDialog.setCancelable(true);
                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                intent.putExtra("title", item);
                                if (subArrayList != null && position < subArrayList.size()) {
                                    intent.putExtra("subtitle", subArrayList.get(position));
                                }
                                else
                                {
                                    intent.putExtra("subtitle", "Nothing Added");
                                }
                                intent.putExtra("position", lineNumber);
                                startActivity(intent);
                            }
                        });
                        alertDialog.setNegativeButton(removeLine, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String itemLengthString = getResources().getString(R.string.item_length);
                                final String averageLengthString = getResources().getString(R.string.average_length);
                                arrayList.remove(position);
                                adapter.notifyDataSetChanged();

                                int lengthInt = arrayList.size();
                                String length = Integer.toString(lengthInt);

                                lengthView.setText(length + " " + itemLengthString);

                                charLengthTotal = charLengthTotal - item.length();
                                if (lengthInt != 0) {
                                    int charLengthCurrent = charLengthTotal / lengthInt;
                                    String charLengthString = Integer.toString(charLengthCurrent);
                                    charView.setText(charLengthString + " " + averageLengthString);
                                    adapter.notifyDataSetChanged();


                                } else if (lengthInt == 0) {
                                    charView.setText("0 " + averageLengthString);
                                }
                            }

                        });


                        AlertDialog alert = alertDialog.create();
                        alert.show();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });



        } else {
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    final Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                    final String clickedLine = getResources().getString(R.string.clicked_line);
                    final String removeLine = getResources().getString(R.string.remove);
                    final String item = ((TextView) view).getText().toString();
                    final String lineNumber;
                    lineNumber = Integer.toString(position + 1);

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    alertDialog.setTitle(clickedLine);
                    alertDialog.setMessage(lineNumber + ". " + item);
                    alertDialog.setCancelable(true);
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            intent.putExtra("title", item);
                            if (subArrayList != null)
                                intent.putExtra("subtitle", subArrayList.get(position));
                            intent.putExtra("position", lineNumber);
                            startActivity(intent);
                        }
                    });
                    alertDialog.setNegativeButton(removeLine, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String itemLengthString = getResources().getString(R.string.item_length);
                            final String averageLengthString = getResources().getString(R.string.average_length);
                            arrayList.remove(position);
                            adapter.notifyDataSetChanged();

                            int lengthInt = arrayList.size();
                            String length = Integer.toString(lengthInt);

                            lengthView.setText(length + " " + itemLengthString);

                            charLengthTotal = charLengthTotal - item.length();
                            if (lengthInt != 0) {
                                int charLengthCurrent = charLengthTotal / lengthInt;
                                String charLengthString = Integer.toString(charLengthCurrent);
                                charView.setText(charLengthString + " " + averageLengthString);
                                adapter.notifyDataSetChanged();


                            } else if (lengthInt == 0) {
                                charView.setText("0 " + averageLengthString);
                            }
                        }

                    });


                    AlertDialog alert = alertDialog.create();
                    alert.show();
                }
            });
        }

        lengthView = (TextView)this.findViewById(R.id.textView2);
        charView = (TextView)this.findViewById(R.id.textView3);



    }

    public void buttonClick(View v)
    {

        enteredText = (EditText)this.findViewById(R.id.editText);
        subEnteredText = (EditText)this.findViewById(R.id.editText2);
        final String s = enteredText.getText().toString();
        final String sub = subEnteredText.getText().toString();

        if (s.isEmpty())
        {
            //if empty, add nothing
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("No information");
            alertDialog.setMessage("Please enter text to the 'Add Title Here' line");
            alertDialog.setCancelable(true);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alert = alertDialog.create();
            alert.show();
        }
        else
        {

            if (arrayList.contains(s))
            {
                //add nothing when item is the same
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Duplicate Information");
                alertDialog.setMessage("The information added to the 'Add Title Here' line is already added to the list. Please add something different.");
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = alertDialog.create();
                alert.show();
            }
            else
            {
                final String itemLengthString = getResources().getString(R.string.item_length);
                final String averageLengthString = getResources().getString(R.string.average_length);
                arrayList.add(s);

                adapter.notifyDataSetChanged();

                //currentPlace = new Place();

                enteredText.setText("");
                subEnteredText.setText("");

                int lengthInt = arrayList.size();
                String length = Integer.toString(lengthInt);
                lengthView.setText(length + " " + itemLengthString);

                charLengthTotal = s.length() + charLengthTotal;
                int charLengthCurrent = charLengthTotal / lengthInt;
                String charLengthString = Integer.toString(charLengthCurrent);
                charView.setText(charLengthString + " " + averageLengthString);

                /*currentPlace.setName(s);
                currentPlace.setSubName(sub);
                Log.d(TAG, currentPlace.subName);*/

                //arrayList.add(currentPlace);

                    subArrayList.add(sub);

            }

        }
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putStringArrayList(savedArray, arrayList);
        savedInstanceState.putInt(savedLength, charLengthTotal);
        savedInstanceState.putStringArrayList(savedSub, subArrayList);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
}


