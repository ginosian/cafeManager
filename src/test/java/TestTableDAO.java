import com.cafeManager.configuration.Configurations;
import com.cafeManager.configuration.Initializer;
import com.cafeManager.dto.OrderDTO;
import com.cafeManager.dto.TableDTO;
import com.cafeManager.dto.UserDTO;
import com.cafeManager.exception.*;
import com.cafeManager.service.OrderService;
import com.cafeManager.service.TableService;
import com.cafeManager.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Martha on 7/31/2016.
 */
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = Configurations.class),
        @ContextConfiguration(classes = Initializer.class)
})
// TODO Extend Configurations class, override database URL from original to test one and pass as ContextConfiguration class for tests
public class TestTableDAO {

    OrderDTO orderDTO;
    UserDTO userDTO;

    @Autowired
    Environment environment;

    @Autowired
    TableService tableService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Before
    public void initItems()throws CustomException{
        orderDTO = orderService.createOrder();
        userDTO = userService.createUser(TestUtil.USERNAME, TestUtil.PASSWORD, TestUtil.ROLE);

    }

    @Test
    public void TestTable()throws CustomException{
            // Tests if table creates.
            TableDTO tableDTO = tableService.createTable();
            Assert.assertNotNull(tableService.getAllTables().size());

            // Tests if proper exception is thrown if an invalid table id is passed
            thrown.expect(NoSuchTableException.class);
            tableService.getTableById(TestUtil.INVALID_ID);

            // Tests if it is prevented to add order to a table if table already has an active order
            thrown.expect(TableHasActiveOrderException.class);
            tableService.updateTableWIthOrder("1", "1");
            tableService.updateTableWIthOrder("1", "1");
    }

}
