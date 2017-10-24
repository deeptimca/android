package demo.com.demo.SharedPreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import demo.com.demo.Activity.HomeActivity;
import demo.com.demo.Activity.LoginActivity;

public class SessionManager {
	public static final String KEY_USERID = "kuserid";

	// Sharedpref file name
	private static final String PREF_NAME = "Android";
	private static final String IS_LOGIN = "IsLoggedIn";

	// Shared Preferences
	SharedPreferences pref;
	// Editor for Shared preferences
	Editor editor;
	// Context
	Context _context;
	// Shared pref mode;
	int PRIVATE_MODE = 0;



	// Constructor
	public SessionManager(Context context)
	{
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}


	public void checklogin()
	{

		if(!this.isLoggedIn())
		{
			// user is not logged in redirect him to Login Activity
			Intent i = new Intent(_context,LoginActivity.class);
			// Closing all the Activities
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// Staring Login Activity
			_context.startActivity(i);

		}
		else
		{
			Intent i =new Intent(_context,HomeActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			_context.startActivity(i);
		}

	}
	public void createuseridsession(String userid,Boolean bol)
	{

		editor.putBoolean(IS_LOGIN, true);
		editor.putString(KEY_USERID, userid);
		editor.commit();
	}





	public String getuseridfromsession()

	{
		return pref.getString(KEY_USERID, null);
	}



	public void logoutUser()
	{

		editor.remove(IS_LOGIN);

		editor.remove(KEY_USERID);

		editor.commit();

		// After logout redirect user to Login Activity
		Intent i = new Intent(_context,LoginActivity.class);
		i.putExtra("has_logged_out", true);
		// Closing all the Activities
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		// Staring Login Activity
		_context.startActivity(i);
	}
	// Get Login State
	public boolean isLoggedIn()
	{
		return pref.getBoolean(IS_LOGIN, false);
	}
}
