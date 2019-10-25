package com.anchore.jenkins.plugins.anchore;

import hudson.AbortException;
import java.io.PrintStream;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Logging mechanism for outputting messages to Jenkins build console
 */
public class ConsoleLog {

  private static final Logger LOG = Logger.getLogger(ConsoleLog.class.getName());
  private static final String LOG_FORMAT = "%1$-6s %2$s";

  private String name;
  private PrintStream logger;
  private boolean enableDebug;

  public PrintStream getLogger() {
    return logger;
  }

  public boolean isEnableDebug() {
    return enableDebug;
  }

  public ConsoleLog(String name, PrintStream logger, boolean enableDebug) throws AbortException {
    if (null != logger) {
      this.name = name;
      this.logger = logger;
      this.enableDebug = enableDebug;
    } else {
      LOG.warning("Cannot instantiate console logger");
      throw new AbortException("Cannot instantiate console logger");
    }
  }

  public void logDebug(String msg) {
    if (enableDebug) {
      logger.println(String.format(LOG_FORMAT, "DEBUG", msg));
    }
  }

  public void logDebug(String msg, Throwable t) {
    logDebug(msg);
    if (null != t) {
      t.printStackTrace(logger);
    }
  }

  public void logInfo(String msg) {
    logger.println(msg);
  }

  public void logWarn(String msg) {
    logger.println(String.format(LOG_FORMAT, "WARN", msg));
  }

  public void logWarn(String msg, Throwable t) {
    logWarn(msg);
    if (null != t) {
      t.printStackTrace(logger);
    }
  }

  public void logError(String msg) {
    logger.println(String.format(LOG_FORMAT, "ERROR", msg));
  }

  public void logError(String msg, Throwable t) {
    logError(msg);
    if (null != t) {
      t.printStackTrace(logger);
    }
  }
}
