package com.sungard.energy.jarvis;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by cyberdanes on 13/05/15.
 */
public class DisplayMessage extends Activity {


    int from_Where_I_Am_Coming = 0;
    private MessageDataHelper mydb ;
    TextView name ;
    TextView phone;
    TextView content;
    TextView street;
    TextView place;
    int id_To_Update = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        content = (TextView) findViewById(R.id.product_label);

        mydb = new MessageDataHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                String id = rs.getString(rs.getColumnIndex(MessageDataHelper.MESSAGES_COLUMN_ID));
                String header = rs.getString(rs.getColumnIndex(MessageDataHelper.MESSAGES_COLUMN_HEADER));
                String contents = rs.getString(rs.getColumnIndex(MessageDataHelper.MESSAGES_COLUMN_CONTENT));
                if (!rs.isClosed())
                {
                    rs.close();
                }
//                Button b = (Button)findViewById(R.id.button1);
//                b.setVisibility(View.INVISIBLE);

                content.setText((CharSequence)contents);
                content.setFocusable(false);
                content.setClickable(false);

            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
                getMenuInflater().inflate(R.menu.menu_mail_list, menu);
            }
            else{
                getMenuInflater().inflate(R.menu.menu_mail_list, menu);
            }
        }
        return true;
    }

//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        super.onOptionsItemSelected(item);
//        switch(item.getItemId())
//        {
//            case R.id.Edit_Contact:
//                Button b = (Button)findViewById(R.id.button1);
//                b.setVisibility(View.VISIBLE);
//                name.setEnabled(true);
//                name.setFocusableInTouchMode(true);
//                name.setClickable(true);
//
//                phone.setEnabled(true);
//                phone.setFocusableInTouchMode(true);
//                phone.setClickable(true);
//
//                email.setEnabled(true);
//                email.setFocusableInTouchMode(true);
//                email.setClickable(true);
//
//                street.setEnabled(true);
//                street.setFocusableInTouchMode(true);
//                street.setClickable(true);
//
//                place.setEnabled(true);
//                place.setFocusableInTouchMode(true);
//                place.setClickable(true);
//
//                return true;
//            case R.id.Delete_Contact:
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setMessage(R.string.deleteContact)
//                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                mydb.deleteContact(id_To_Update);
//                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(),com.example.addressbook.MainActivity.class);
//                                startActivity(intent);
//                            }
//                        })
//                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // User cancelled the dialog
//                            }
//                        });
//                AlertDialog d = builder.create();
//                d.setTitle("Are you sure");
//                d.show();
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//
//        }
//    }

    public void run(View view)
    {
        Bundle extras = getIntent().getExtras();
//        if(extras !=null)
//        {
//            int Value = extras.getInt("id");
//            if(Value>0){
//                if(mydb.updateContact(id_To_Update,name.getText().toString(), phone.getText().toString(), email.getText().toString(), street.getText().toString(), place.getText().toString())){
//                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),com.example.addressbook.MainActivity.class);
//                    startActivity(intent);
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
//                }
//            }
//            else{
//                if(mydb.insertContact(name.getText().toString(), phone.getText().toString(), email.getText().toString(), street.getText().toString(), place.getText().toString())){
//                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
//                }
//                Intent intent = new Intent(getApplicationContext(),com.example.addressbook.MainActivity.class);
//                startActivity(intent);
//            }
//        }
    }
}