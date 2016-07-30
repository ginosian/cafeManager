import com.cafeManager.configuration.Configurations;
import com.cafeManager.configuration.Initializer;
import com.cafeManager.dao.UserDAO;
import com.cafeManager.exception.NoSuchRoleException;
import com.cafeManager.exception.RoleExistException;
import com.cafeManager.service.UserService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Martha on 7/30/2016.
 */
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = Configurations.class),
        @ContextConfiguration(classes = Initializer.class)
})
public class TestUserDAO extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @Autowired
    Environment environment;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAddRole() throws RoleExistException{
        userService.createRole("role_waiter");
        Assert.assertNotNull(userService.getRole("role_waiter"));
        Assert.assertNull(userService.getRole("invalid_role"));
        thrown.expect(RoleExistException.class);
        userService.createRole("role_waiter");
        thrown.expect(NoSuchRoleException.class);
        userService.getRole("invalid_role");
    }

}
