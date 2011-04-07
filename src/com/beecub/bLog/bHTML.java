package com.beecub.bLog;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class bHTML {
	
	
	static bLog bLog;
	static String filename;
	
	static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	static DateFormat shortDate = new SimpleDateFormat("yyyy-MM-dd");
	static DateFormat year = new SimpleDateFormat("yyyy");
	static DateFormat month = new SimpleDateFormat("MM");
	static DateFormat day = new SimpleDateFormat("dd");
	

	@SuppressWarnings({ "static-access" })
	public bHTML(bLog bLog) {
		this.bLog = bLog;
		
		filename = bLog.getDataFolder().getPath() + "/ChatLog/" + "log.html";
	}
	
	@SuppressWarnings("unused")
	public static void writeToFile(String playername, String line) {
		
		Date date = new Date();
        String datetime = dateFormat.format(date);
        String sShortDate = shortDate.format(date);
        String sYear = year.format(date);
        String sMonth = month.format(date);
        
        String message;
        
        /*filename = bLog.getDataFolder().getPath() + "//" + sYear + "//" + sMonth + "//" + sShortDate + ".html";
        new File(filename).mkdir();
        File f = new File(filename);
        try {
			File.createTempFile(sShortDate, ".html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
		message = "<span class=\"time\">" + datetime + ": </span> <span class=\"nickname\">" + playername + ": </span> <span class=\"message\">" + line + "</span>";
		message += "</br>";
		
		BufferedWriter out = null;
        
        try {
            
        	out = new BufferedWriter(new FileWriter(filename, true));
            out.write(message);
            out.newLine();
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (out != null) {
                	out.flush();
                	out.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }	
}
