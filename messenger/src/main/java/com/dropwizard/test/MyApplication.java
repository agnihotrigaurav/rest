package com.dropwizard.test;

import com.dropwizard.test.exception.DataNotFoundExceptionMapper;
import com.dropwizard.test.resources.InjectDemoResource;
import com.dropwizard.test.resources.MessageResource;
import com.dropwizard.test.resources.ProfileResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.CommonProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyApplication extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyApplication.class);
    @Override
    public String getName() {
        return "messenger";
    }

    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        // Security.removeProvider("BC");
        // Security.addProvider(new BouncyCastleProvider());

    }

    @Override
    public void run(final Configuration configuration, final Environment environment) {

        environment.jersey().property(CommonProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
        environment.jersey().property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
        environment.jersey().property(CommonProperties.JSON_PROCESSING_FEATURE_DISABLE, true);
        environment.jersey().property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);

        final MessageResource messageResource = new MessageResource();
        environment.jersey().register(messageResource);

        final ProfileResource profileResource = new ProfileResource();
        environment.jersey().register(profileResource);

        final InjectDemoResource injectDemoResource = new InjectDemoResource();
        environment.jersey().register(injectDemoResource);

        environment.jersey().register(new DataNotFoundExceptionMapper());
    }

    /**
     * Parses command-line arguments and runs the application.
     *
     * @param args the command-line arguments.
     * @throws Exception if an uncaught exception is thrown from the application.
     */
    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    public static void main(final String[] args) throws Exception {
        new MyApplication().run(args);
    }
}
