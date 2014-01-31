package com.github.pires.example.fabric8.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pires.example.fabric8.billing.BillingService;

@Component(name = "com.github.pires.example.fabric8.user",
    label = "User Service",
    description = "Enabling this service will provide user operations.",
    policy = ConfigurationPolicy.OPTIONAL,
    immediate = true)
@Service(UserService.class)
@Path("/user")
public class UserService {

  @Reference(bind = "bindBilling", unbind = "unbindBilling")
  private BillingService bs;

  private static final Logger log = LoggerFactory.getLogger(UserService.class);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Double> executeSomething() {
    log.info("Executing something...");
    List<Double> balances = new ArrayList<Double>();
    final String userId = "testUser";
    bs.createBillingAcount(userId);
    bs.addCash(1000);
    balances.add(bs.getBalance());
    log.info("Current balance: {}", bs.getBalance());
    bs.removeCash(500);
    balances.add(bs.getBalance());
    log.info("Current balance: {}", bs.getBalance());
    return balances;
  }

  @Activate
  void activate(Map<String, ?> configuration) {
    log.info("Activating User service.");
  }

  @Modified
  void modified(Map<String, ?> configuration) {
    // TODO
  }

  @Deactivate
  void deactivate() {
    log.info("Deactivating User service.");
  }

  protected void bindBilling(BillingService bs) {
    this.bs = bs;
    // TODO enable billing functionality
  }

  protected void unbindBilling(BillingService bs) {
    this.bs = null;
    // TODO disable billing functionality
  }

}