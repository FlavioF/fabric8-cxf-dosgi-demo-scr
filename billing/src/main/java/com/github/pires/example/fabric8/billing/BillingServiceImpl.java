package com.github.pires.example.fabric8.billing;

import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(name = "com.github.pires.example.fabric8.billing",
    label = "Billing Service",
    description = "Enabling this service will provide billing related operations.",
    policy = ConfigurationPolicy.OPTIONAL,
    immediate = true)
@Service(BillingService.class)
public class BillingServiceImpl implements BillingService {

  private static final Logger log = LoggerFactory
      .getLogger(BillingService.class);

  private double balance = 0;

  public BillingServiceImpl() {
    this.balance = 0;
  }

  public void createBillingAcount(final String userId) {
    log.info("Creating account for {}");
  }

  public void addCash(final double value) {
    this.balance += value;
    log.info("{} added", value);
  }

  public void removeCash(final double value) {
    balance -= value;
    log.info("{} removed", value);
  }

  public double getBalance() {
    return balance;
  }

  @Activate
  void activate(Map<String, ?> configuration) {
    log.info("Activating Billing service.");
  }

  @Modified
  void modified(Map<String, ?> configuration) {
    // TODO
  }

  @Deactivate
  void deactivate() {
    log.info("Deactivating Billing service.");
  }

}