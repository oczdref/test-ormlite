package com.test.ormlite;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.test.ormlite.db.DatabaseManager;
import com.test.ormlite.model.Person;

@SuppressLint("NewApi")
public class HelloAndroidActivity extends Activity {

	private TextView mTextName;
	private Button btn;
	private ListView personList;
	private ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		DatabaseManager.init(this);
		mTextName = (TextView) findViewById(R.id.name);
		btn = (Button) findViewById(R.id.btnSave);
		personList = (ListView) findViewById(R.id.personList);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getNames());
		personList.setAdapter(adapter);

	}

	private void notifyadapter() {
		adapter.clear();
		adapter.addAll(getNames());
		adapter.notifyDataSetChanged();
	}

	private List<String> getNames() {
		final List<Person> Persons = DatabaseManager.getInstance()
				.getAllPersons();
		List<String> names = new ArrayList<String>();
		for (Person p : Persons) {
			names.add(p.getName());
		}
		return names;
	}

	public void save(View view) {
		Person p = new Person();
		p.setName(mTextName.getText().toString());
		int i = DatabaseManager.getInstance().savePerson(p);
		if (i != 0) {
			Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();
			notifyadapter();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.test.ormlite.R.menu.main, menu);
		return true;
	}

}
