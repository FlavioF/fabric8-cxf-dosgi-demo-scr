package com.github.pires.example.fabric8.billing;

import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.log.LogService;

@Component(name = "com.github.pires.example.fabric8.billing",
    label = "Billing Service",
    description = "Enabling this service will provide billing related operations.",
    policy = ConfigurationPolicy.REQUIRE,
    immediate = true,
    metatype = true)
@Service(BillingService.class)
public class BillingServiceImpl implements BillingService {

  @Reference(bind = "bindLog", unbind = "unbindLog")
  private LogService log;

  @Property(name = "balance", label = "Initial Balance", doubleValue = 0)
  private double balance = 0;

  public BillingServiceImpl() {
    this.balance = 0;
  }

  public void createBillingAcount(final String userId) {
    log.log(LogService.LOG_INFO, "Creating account for " + userId);
  }

  public void addCash(final double value) {
    this.balance += value;
    log.log(LogService.LOG_INFO, value + " added");
  }

  public void removeCash(final double value) {
    balance -= value;
    log.log(LogService.LOG_INFO, value + " removed");
  }

  public double getBalance() {
    return balance;
  }

  @Activate
  void activate(Map<String, ?> configuration) {
    log.log(LogService.LOG_INFO, "Activating Billing service.");
  }

  @Modified
  void modified(Map<String, ?> configuration) {
    // TODO
  }

  @Deactivate
  void deactivate() {
    log.log(LogService.LOG_INFO, "Deactivating Billing service.");
  }

  protected void bindLog(LogService log) {
    this.log = log;
  }

  protected void unbindLog(LogService log) {
    this.log = null;
  }

}