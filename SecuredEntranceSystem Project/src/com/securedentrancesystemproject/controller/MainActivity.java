package com.securedentrancesystemproject.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.securedentrancesystemproject.model.Resident;
import com.securedentrancesystemproject.model.Visitor;

public class MainActivity extends Activity {

	private EditText rFirstNameView, rLastNameView, rGTIDView, vFirstNameView, vLastNameView, vGTIDView;

	Resident residentUser;
	Visitor visitor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		rFirstNameView = (EditText)findViewById(R.id.editText1);
		rLastNameView = (EditText)findViewById(R.id.editText2);
		rGTIDView = (EditText)findViewById(R.id.editText3);
		vFirstNameView = (EditText)findViewById(R.id.editText4);
		vLastNameView = (EditText)findViewById(R.id.editText5);
		vGTIDView = (EditText)findViewById(R.id.editText6);
		
		residentUser.setrFirstName(rFirstNameView.getText().toString());
		residentUser.setrLastName(rLastNameView.getText().toString());
		residentUser.setrGTid(rGTIDView.getText().toString());
		
		visitor.setvFirstName(vFirstNameView.getText().toString());
		visitor.setvLastName(vLastNameView.getText().toString());
		visitor.setvGTid(vGTIDView.getText().toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
