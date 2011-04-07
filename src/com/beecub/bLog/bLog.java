package com.beecub.bLog;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;


public class bLog extends JavaPlugin {
	private final bLogPlayerListener playerListener = new bLogPlayerListener(this);
	public Logger log = Logger.getLogger("Minecraft");
	public static PluginDescriptionFile pdfFile;
	public static Configuration conf;
	public static String path;
	public static String date;

	@SuppressWarnings({ "static-access", "unused" })
	public void onEnable() {

		path = getDataFolder().getPath() + "/";
		date = getDateTime();
		pdfFile = this.getDescription();
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, playerListener, Priority.Low, this);
		pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Priority.Low, this);
		
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info("[" +  pdfFile.getName() + "]" + " version " + pdfFile.getVersion() + " is enabled!" );
		
		bConfigManager bConfigManager = new bConfigManager(this);
		bConfigManager.reload();
		
		bHTML bHTML = new bHTML(this);
		
		bLogPlayerListener.commlog = bLogger.commlog;
		bLogPlayerListener.chatlog = bLogger.chatlog;
	}
	
	public void onDisable() {
		log.info("[" + pdfFile.getName() + "]" + " version " + pdfFile.getVersion() + " disabled!");
	}
	
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
