
package com.playercore.LogBase;
import java.util.List;

public interface LogFilter {
    List<LogItem> getFilteredItems(Priority logLevel);
}
