package ru.scancode.serial;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.getserial.Foo;
import com.example.getserial.Bar;

public class MainActivity extends AppCompatActivity {

	static final String LOGTAG = "Serial";

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
				Logs.info(this,"Get serial error: "+e.getMessage());
				e.printStackTrace();
			}
		}else {
			serial_new_tv.append(LOW_SDK+": "+Build.VERSION.CODENAME);
		}

		Logs.info(this,"Software version: "+Build.FINGERPRINT);

		// тестовое подключение модуля

		NearestFoo nearestFoo = new NearestFoo();
		nearestFoo.printHelloWorld();

		Foo foo = new Foo();
		foo.printHelloWorld();

		Bar bar = new Bar();
		Bar.printHelloWorld();
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