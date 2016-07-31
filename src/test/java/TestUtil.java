import com.cafeManager.dto.OrderDTO;
import com.cafeManager.dto.RoleDTO;
import com.cafeManager.dto.UserDTO;

/**
 * Created by Martha on 7/30/2016.
 */
public class TestUtil {
    public static final String USERNAME = "test_user";
    public static final String USERNAME_2 = "test_user_2";
    public static final String PASSWORD = "test_password";
    public static final String INVALID_USERNAME = "invalid_test_user";
    public static final String INVALID_ID = "-1";
    public static final String INVALID_ROLE = "invalid_role";
    public static final String ROLE = "TEMP_ROLE";

    public static OrderDTO newActiveOrder(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setIsActive(true);
        return orderDTO;
    }

    public static OrderDTO newInactvieOrder(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setIsActive(false);
        return orderDTO;
    }

    public static UserDTO newUser(){
        return new UserDTO(USERNAME, PASSWORD, new RoleDTO(ROLE));
    }

}
