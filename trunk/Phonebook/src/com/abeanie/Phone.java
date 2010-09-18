package com.abeanie;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class Phone extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ListView list = (ListView)findViewById(R.id.ListView01);
        
        List<Phonebook> listOfPhonebook = new ArrayList<Phonebook>();
        listOfPhonebook.add(new Phonebook("Test","9981728","test@test.com"));
        listOfPhonebook.add(new Phonebook("Test1","1234455","test1@test.com"));
        listOfPhonebook.add(new Phonebook("Test2","00000","test2@test.com"));
        
        PhonebookAdapter adapter = new PhonebookAdapter(this, listOfPhonebook);
        
        list.setAdapter(adapter);
    }
}