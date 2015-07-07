package com.test.ormlite.db;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.test.ormlite.model.Person;

public class DatabaseManager {
	private static DatabaseManager instance;

	public static void init(Context ctx) {
		if (null == instance) {
			instance = new DatabaseManager(ctx);
		}
	}

	public static DatabaseManager getInstance() {
		return instance;
	}

	private DatabaseHelper helper;

	private DatabaseManager(Context ctx) {
		helper = new DatabaseHelper(ctx);
	}

	private DatabaseHelper getHelper() {
		return helper;
	}
	
	public List<Person> getAllPersons() {
        List<Person> Persons = null;
        try {
            Persons = getHelper().getPersonDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Persons;
    }
	
	public int savePerson(Person person){
		try {
			return getHelper().getPersonDao().create(person);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
