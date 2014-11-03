
package com.playercore.LogBase;

public interface LogItemFactory {
    public LogItem create(String line) throws ParseException;
}
