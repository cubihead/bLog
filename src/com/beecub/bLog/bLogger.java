package com.beecub.bLog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;
import java.io.*;

public class bLogger {
	
    static int limit = 10000000; // 10 mb
    static int numLogFiles = 1000;
	static Formatter formatter = new Formatter() {
        public String format(LogRecord rec) {
            StringBuffer buf = new StringBuffer(1000);
            //buf.append(new java.util.Date());
            buf.append(calcDate(rec.getMillis()));
            buf.append(' ');
            buf.append(formatMessage(rec));
            buf.append('\n');
            return buf.toString();
            }
        };
        
public static Logger commlog;
	static {
	    try {
	      FileHandler fh = new FileHandler(bLog.path + "CommandLog/CommandLog" + ".log", limit, numLogFiles, true);
	      fh.setFormatter(formatter);
	      commlog = Logger.getLogger("Minecraft.bLog.Command");
	      commlog.addHandler(fh);
	      commlog.setUseParentHandlers(false);
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	public static Logger chatlog;
	static {
	    try {
	      FileHandler fh = new FileHandler(bLog.path + "ChatLog/ChatLog" + ".log", limit, numLogFiles, true);
	      fh.setFormatter(formatter);
	      chatlog = Logger.getLogger("Minecraft.bLog.Chat");
	      chatlog.addHandler(fh);
	      chatlog.setUseParentHandlers(false);
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	private static String calcDate(long millisecs) {
		//SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
		SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date resultdate = new Date(millisecs);
		return date_format.format(resultdate);
	}
	
}