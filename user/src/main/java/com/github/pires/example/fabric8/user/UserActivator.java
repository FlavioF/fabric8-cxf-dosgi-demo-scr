package com.github.pires.example.fabric8.user;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserActivator implements BundleActivator {

  private static final Logger log = LoggerFactory
      .getLogger(UserActivator.class);

  public void start(BundleContext context) throws Exception {
    log.info("UserService is ready.");
  }

  public void stop(BundleContext context) throws Exception {
    log.info("UserService is stopped.");
  }

}