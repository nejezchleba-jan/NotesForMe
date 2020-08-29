package com.example.notesforme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.notesforme.R;

import java.util.Locale;

public class PreferenceActivity extends AppCompatActivity {

	private final static Locale en_US = new Locale("en");
	private final static Locale cs_CZ = new Locale("cs", "CZ");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		findViewById(R.id.buttonLanguage).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Configuration config = getBaseContext().getResources().getConfiguration();
				Button b = (Button)view;
				if(config.locale.getDisplayLanguage().equals(en_US.getDisplayLanguage())) {
					switchLocale(cs_CZ);
				} else if (config.locale.getDisplayLanguage().equals(cs_CZ.getDisplayLanguage())) {
					switchLocale(en_US);
				}

				b.setText(getResources().getString(R.string.lang));
			}
		});

		findViewById(R.id.imageSave).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				savePreferences();
				finish();
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
		if(config.locale.equals(configChanged.locale)) {
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
}