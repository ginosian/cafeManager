import com.cafeManager.configuration.Configurations;
import com.cafeManager.configuration.Initializer;
import com.cafeManager.dto.RoleDTO;
import com.cafeManager.exception.*;
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
// TODO Extend Configurations class, override database URL from original to test one and pass as ContextConfiguration class for tests
public class TestUserDAO extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    UserService userService;

    @Autowired
    Environment environment;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testRole() throws RoleExistException, NoSuchRoleException, NullOrEmptyArgumentsException{

        // Tests if role creates.
        userService.createRole(environment.getProperty("role_waiter"));
        Assert.assertNotNull(userService.getRole(environment.getProperty("role_waiter")));

        // Tests if proper exception is thrown if an existing role is passed
        thrown.expect(RoleExistException.class);
        userService.createRole(environment.getProperty("role_waiter"));

        // Tests if proper exception is thrown if an invalid role is passed
        thrown.expect(NoSuchRoleException.class);
        userService.getRole(TestUtil.INVALID_ROLE);

        // Tests if proper exception is thrown if a null is passed.
        thrown.expect(NullOrEmptyArgumentsException.class);
        userService.createRole(null);
        thrown.expect(NullOrEmptyArgumentsException.class);
        userService.getRole(null);
    }

    @Test
    public void testUser()throws CustomException{
        RoleDTO roleDTO = userService.createRole(environment.getProperty("role_waiter"));

        userService.createUser(TestUtil.USERNAME_2, TestUtil.PASSWORD, roleDTO.getRole());

        // Tests if user creates.
        userService.createUser(TestUtil.USERNAME, TestUtil.PASSWORD, roleDTO.getRole());
        Assert.assertNotNull(userService.getUserByUsername(TestUtil.USERNAME));

        // Tests if proper exception is thrown if an existing user is passed
        thrown.expect(UserExistException.class);
        userService.createUser(TestUtil.USERNAME, TestUtil.PASSWORD, roleDTO.getRole());

        // Tests if proper exception is thrown if an invalid username, user id or role  is passed
        thrown.expect(NoSuchUserException.class);
        userService.getUserByUsername(TestUtil.INVALID_USERNAME);
        thrown.expect(NoSuchUserException.class);
        userService.getUserById(TestUtil.INVALID_ID);
        thrown.expect(NoSuchUserException.class);
        userService.getAllUsersByRole(TestUtil.INVALID_ROLE);
        userService.getAllUsersByRole(environment.getProperty("role_manager"));

        // Tests if proper exception is thrown if a null is passed.
        thrown.expect(NullOrEmptyArgumentsException.class);
        userService.createUser(null, null, null);
        thrown.expect(NullOrEmptyArgumentsException.class);
        userService.getUserByUsername(null);
        thrown.expect(NullOrEmptyArgumentsException.class);
        userService.getUserById(null);
        thrown.expect(NullOrEmptyArgumentsException.class);
        userService.getAllUsersByRole(null);

        // Test if correct quantity of users is returned
        Assert.assertEquals(userService.getAllUsersByRole(roleDTO.getRole()).size(), 2);

    }



}
