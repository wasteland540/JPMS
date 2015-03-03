package jpms.guice.module;

import com.google.inject.AbstractModule;
import jpms.application.AppInfo;
import jpms.application.IAppInfo;

/**
 *
 * @author m.elz
 */
public class ApplicationUtilModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IAppInfo.class).to(AppInfo.class);
    }
    
}
