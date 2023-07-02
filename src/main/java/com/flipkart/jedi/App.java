package com.flipkart.jedi;

import com.flipkart.jedi.restController.*;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class App extends Application<Configuration> {

    public static void main(final String[] args) throws Exception{
        new App().run(args);
    }
    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        environment.jersey().register(AdminRestController.class);
        environment.jersey().register(UserRestController.class);
        environment.jersey().register(CustomerRestController.class);
        environment.jersey().register(GymRestController.class);
        environment.jersey().register(SlotRestController.class);
    }
}
