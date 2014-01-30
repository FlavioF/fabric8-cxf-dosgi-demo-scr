package com.github.pires.example.fabric8.user;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS bootstrap.
 */
@ApplicationPath(value = "/bikeemotion")
public class UserApplication extends Application {

  // TODO override methods to add features like JacksonFeature

}