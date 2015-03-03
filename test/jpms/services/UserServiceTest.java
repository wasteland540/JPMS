package jpms.services;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.List;
import jpms.guice.module.MasterModule;
import jpms.model.PmsUser;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author m.elz
 */
public class UserServiceTest {
    
    private static Injector injector;
    
    private final String sampleUser = "M.Elz";
    private final String samplePassword = "test123";
    private final IUserService userService;
    
    public UserServiceTest() {
        userService = injector.getInstance(IUserService.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
        injector = Guice.createInjector(new MasterModule());
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void exsistUser(){
        
        boolean shouldBeTrue = userService.exsistUser(sampleUser);
        Assert.assertTrue(shouldBeTrue);
        
        boolean shouldBeFalse = userService.exsistUser("Michgibtsnit");
        Assert.assertFalse(shouldBeFalse);
    }
    
    @Test
    public void checkLogin(){
        
        boolean shouldBeTrue = userService.checkLogin(sampleUser, samplePassword);
        Assert.assertTrue(shouldBeTrue);
        
        boolean shouldBeFalse = userService.checkLogin(sampleUser, "wrongwrong");
        Assert.assertFalse(shouldBeFalse);
    }
    
    //TODO: mocking the database? or something? for the 2 follwoing test...
    
    @Test
    public void createUser(){
        boolean shouldBeTrue = userService.createUser("IchbinNeu", "test123");
        Assert.assertTrue(shouldBeTrue);
        
        boolean shouldBeFalse = userService.createUser("IchbinNeu", "test123");
        Assert.assertFalse(shouldBeFalse);
    }
    
    @Test
    public void changePassword(){
        boolean shouldBeTrue = userService.createUser("IchbinNeu22", "test12322");
        Assert.assertTrue(shouldBeTrue);
        
        boolean shouldBeTrue2 = userService.changePassword("IchbinNeu22", "test12322", "22test123");
        Assert.assertTrue(shouldBeTrue2);
        
        boolean shouldBeFalse = userService.changePassword("IchbinNeu22", "test12322", "22test123");
        Assert.assertFalse(shouldBeFalse);
    }
    
    @Test
    public void getUsers(){
        List<PmsUser> users = userService.getUsers();
        Assert.assertTrue(users != null && (users.size() == 1 || users.size() == 3));
    }
}
