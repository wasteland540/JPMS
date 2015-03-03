package jpms.guice.module;

import com.google.inject.AbstractModule;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpms.services.IPersonService;
import jpms.services.IUserService;
import jpms.services.PersonService;
import jpms.services.UserService;

/**
 *
 * @author m.elz
 */
public class DatabaseModule extends AbstractModule {

    @Override
    protected void configure() {
        
        //bind em factory, so we can inject it in every service
        bind(EntityManagerFactory.class).toInstance(Persistence.createEntityManagerFactory("JPMSPU"));
        
        //bind all database service
        bind(IPersonService.class).to(PersonService.class);
        bind(IUserService.class).to(UserService.class);
    }    
}
