package ru.scancode.serial;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ru.scancode.testlib.StubFoo;
import ru.scancode.testlib2.StubBar;

public class MainActivity extends AppCompatActivity {

	private static final CharSequence LOW_SDK = "Low SDK";

	private static final int REQUEST_PERMISSION = 1;

	private TextView serial_old_tv;
	private TextView serial_new_tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView versionName = findViewById(R.id.version_name_tv);
		versionName.setText("Version name: "+BuildConfig.VERSION_NAME);

		serial_old_tv = findViewById(R.id.serial_old_tv);
		serial_new_tv = findViewById(R.id.serial_new_tv);

		checkPermissions();

		serial_old_tv.append(Build.SERIAL);
		showSerial(Build.SERIAL);

		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {

			try {
				serial_new_tv.append(Build.getSerial());
			} catch (SecurityException e) {
				Logs.info(this,"Ошибка при получении SN: "+e.getMessage());
				e.printStackTrace();
			}
		}else {
			serial_new_tv.append(LOW_SDK+": "+Build.VERSION.CODENAME);
		}

		Logs.info(this,"Software version: "+Build.FINGERPRINT);

		// package org.xmlpull.v1

		XmlPullParserFactory factory = null;

		try {

			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = null;
			xpp = factory.newPullParser();

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		// package javax.xml

		DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
		factory2.setNamespaceAware(true);

		try {

			DocumentBuilder builder = factory2.newDocumentBuilder();

			//Document doc = builder.parse(new File("path"));

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		// SAX parser

		SAXParserFactory factory3 = SAXParserFactory.newInstance();
		try {

			SAXParser parser = factory3.newSAXParser();
			//parser.parse(new File("path"),new DefaultHandler());

		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}

		char divChar = 0x01;

		String divString = String.valueOf(divChar);

		//Logs.info(this,"NumericValue: "+Character.getNumericValue(divChar)+", char: "+divChar+", String: "+divString);

		// Тестирование подключённого модуля

		StubFoo stubFoo = new StubFoo(1,"Mike");
		Logs.info(this,"StubFoo: "+stubFoo);

		StubBar stubBar = new StubBar(2,"John");
		Logs.info(this,"StubBar: "+stubBar);

	}

	private void checkPermissions() {

		if (ActivityCompat.checkSelfPermission(this,
				Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

			ActivityCompat.requestPermissions(this,
					new String[]{Manifest.permission.READ_PHONE_STATE},
					REQUEST_PERMISSION);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

		if (requestCode== REQUEST_PERMISSION){

			for (int i=0; i<permissions.length; i++){
				Logs.info(this, "Permission and grandResult: "+permissions[i]+": "+grantResults[i]);
			}
		}
	}

	private void showSerial(String mSerial){
		Logs.info(this, "serial: "+ mSerial);
		Toast.makeText(this,"serial: "+mSerial,Toast.LENGTH_LONG).show();
	}
}
