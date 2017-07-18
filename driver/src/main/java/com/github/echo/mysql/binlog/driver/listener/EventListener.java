package com.github.echo.mysql.binlog.driver.listener;

import com.github.echo.mysql.binlog.driver.event.Event;

/**
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 */
public interface EventListener {
    void onEvent(Event event);
}
