package com.morce.globalquake.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.morce.globalquake.main.Main;

public final class Settings {

	private static final File optionsFile = new File(Main.MAIN_FOLDER, "globalQuake.properties");
	private static Properties properties = new Properties();
	
	public static String zejfSeisIP;
	public static Integer zejfSeisPort;
	public static Boolean zejfSeisAutoReconnect;
	
	public static Boolean enableAlarmDialogs;
	
	public static Double homeLat;
	public static Double homeLon;
	

	static {
		load();
	}

	private static void load() {
		try {
			properties.load(new FileInputStream(optionsFile));
		} catch (IOException e) {
			System.out.println("Created GlobalQuake properties file at "+optionsFile.getAbsolutePath());
		}
		zejfSeisIP = (String) properties.getOrDefault("zejfSeisIP", "25.27.127.112");
		zejfSeisPort = Integer.valueOf((String) properties.getOrDefault("zejfSeisPort", "6222"));
		zejfSeisAutoReconnect = Boolean.valueOf((String) properties.getOrDefault("zejfSeisAutoReconnect", "true"));
		
		enableAlarmDialogs = Boolean.valueOf((String) properties.getOrDefault("enableAlarmDialogs", "true"));
		
		homeLat = Double.valueOf((String) properties.getOrDefault("homeLat", "50.262"));
		homeLon = Double.valueOf((String) properties.getOrDefault("homeLon", "17.262"));
		save();
	}
	
	
	public static void save() {
		properties.setProperty("zejfSeisIP", zejfSeisIP);
		properties.setProperty("zejfSeisPort", zejfSeisPort+"");
		properties.setProperty("zejfSeisAutoReconnect", zejfSeisAutoReconnect+"");
		properties.setProperty("enableAlarmDialogs", enableAlarmDialogs+"");
		
		properties.setProperty("homeLat", homeLat+"");
		properties.setProperty("homeLon", homeLon+"");
		try {
			properties.store(new FileOutputStream(optionsFile), "magic");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
