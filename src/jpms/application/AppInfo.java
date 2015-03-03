package jpms.application;

import com.google.inject.Singleton;

/**
 *
 * @author m.elz
 */
@Singleton
public class AppInfo implements IAppInfo{
    
    private String loginname;

    @Override
    public String getLoginname() {
        return loginname;
    }

    @Override
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    
}
