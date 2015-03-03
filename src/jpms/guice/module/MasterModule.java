package jpms.guice.module;

import com.google.inject.AbstractModule;

/**
 *
 * @author m.elz
 */
public class MasterModule extends AbstractModule {

    @Override
    protected void configure() {
        //install all needed modules
        
        install(new ApplicationUtilModule());
        install(new DatabaseModule());
        install(new ViewModule());        
    }
    
}
