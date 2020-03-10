package ru.scancode.serial;

import android.app.Application;
import android.content.Context;
//import android.support.multidex.MultiDex;
//import android.support.multidex.MultiDexApplication;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class App extends MultiDexApplication {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
}
