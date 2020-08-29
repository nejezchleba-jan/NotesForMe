package com.example.notesforme.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.notesforme.R;

import java.util.Locale;

public class PreferenceActivity extends AppCompatActivity {

	private Button btnToggleDark;
	private boolean isDarkModeOn;
	private SharedPreferences sharedPreferences;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		findViewById(R.id.imageSave).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				savePreferences();
				finish();
			}
		});

		btnToggleDark = findViewById(R.id.buttonTheme);
		sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
		isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn",true);
		if (isDarkModeOn) {
			btnToggleDark.setText(getResources().getString(R.string.dark));
		} else {
			btnToggleDark.setText(getResources().getString(R.string.light));
		}

		btnToggleDark.setOnClickListener(new View.OnClickListener() {
			final SharedPreferences.Editor editor = sharedPreferences.edit();

			@Override
			public void onClick(View view) {
				if (isDarkModeOn) {
					AppCompatDelegate
							.setDefaultNightMode(
									AppCompatDelegate
											.MODE_NIGHT_NO);
					editor.putBoolean(
							"isDarkModeOn", false);
					editor.apply();
					btnToggleDark.setText(getResources().getString(R.string.light));
				} else {
					AppCompatDelegate
							.setDefaultNightMode(
									AppCompatDelegate
											.MODE_NIGHT_YES);

					editor.putBoolean(
							"isDarkModeOn", true);
					editor.apply();
					btnToggleDark.setText(getResources().getString(R.string.dark));
				}
			}
		});
	}


	private void switchLocale(Locale loc) {
		Configuration config = getBaseContext().getResources().getConfiguration();
		Locale.setDefault(loc);
		config.locale = loc;
		getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
		getApplicationContext().getResources().updateConfiguration(config, getApplicationContext().getResources().getDisplayMetrics());
		Configuration configChanged = getBaseContext().getResources().getConfiguration();
		if (config.locale.equals(configChanged.locale)) {
			Toast.makeText(this, R.string.locale_err, Toast.LENGTH_SHORT).show();
			return;
		}
		recreate();
	}

	private void savePreferences() {
		setResult(RESULT_OK);
		super.onBackPressed();
	}

	@Override
	public void onBackPressed() {
		savePreferences();
	}

	private void changeThemeOnClick() {
		btnToggleDark = findViewById(R.id.buttonTheme);
		SharedPreferences sharedPreferences
				= getSharedPreferences(
				"sharedPrefs", MODE_PRIVATE);
		final SharedPreferences.Editor editor
				= sharedPreferences.edit();
		final boolean isDarkModeOn
				= sharedPreferences
				.getBoolean(
						"isDarkModeOn", false);

		btnToggleDark.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (isDarkModeOn) {

					AppCompatDelegate
							.setDefaultNightMode(
									AppCompatDelegate
											.MODE_NIGHT_NO);
					editor.putBoolean(
							"isDarkModeOn", false);
					editor.apply();

					btnToggleDark.setText(getResources().getString(R.string.light));
				} else {

					AppCompatDelegate
							.setDefaultNightMode(
									AppCompatDelegate
											.MODE_NIGHT_YES);

					editor.putBoolean(
							"isDarkModeOn", true);
					editor.apply();

					btnToggleDark.setText(getResources().getString(R.string.dark));
				}
			}
		});
	}
}

