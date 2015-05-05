package com.tenpearls.android.contactsresolver;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    TextView mTxtContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initUI();
        fetchContacts();
    }

    private void initUI() {

        setContentView(R.layout.activity_main);
        mTxtContacts = (TextView) findViewById(R.id.txtContacts);
    }

    public void fetchContacts() {

        String output = "";

        String[] projection = { ContactsContract.RawContacts._ID, ContactsContract.Contacts.DISPLAY_NAME };

        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, projection, null, null, null);

        if (cursor != null && cursor.getCount () > 0) {
            while (cursor.moveToNext ()) {

                String id = cursor.getString (cursor.getColumnIndex (ContactsContract.RawContacts._ID));
                String name = cursor.getString (cursor.getColumnIndex (ContactsContract.Contacts.DISPLAY_NAME));

                output = output + name + "\n";
            }

            cursor.close ();

            if (output.length() == 0) {
                mTxtContacts.setText("No contacts found.");
                return;
            }

            mTxtContacts.setText(output);
        }
    }

}
