package com.github.pires.example.fabric8.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pires.example.fabric8.billing.BillingService;

/**
 * TODO
 */
@ManagedBean
@Path("/user")
public class UserService {

  private static final Logger log = LoggerFactory.getLogger(UserService.class);

  @Resource
  private BundleContext context;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Double> executeSomething() {
    log.info("Executing something...");

    // validate that bundle context has been injected
    if (context == null)
      throw new IllegalStateException("BundleContext not injected");

    // retrieve billing service
    final ServiceReference<BillingService> sr = context
        .getServiceReference(BillingService.class);
    final BillingService bs = context.getService(sr);

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

}