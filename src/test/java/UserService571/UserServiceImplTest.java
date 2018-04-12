package UserService571;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;


//Write a happy-path test for the class presented below. Verify that the user gets his new password, and
//        that the updateUser() method of userDAO is called.


public class UserServiceImplTest {


    private final String userPassword = "samplePassword";
    private final String userPasswordMD5 = "samplePasswordMD5";

    User user;
    SecurityService securityService;
    UserDAO userDAO;
    UserServiceImpl userService;


    @BeforeMethod
    public void setUp() {
        user = mock(User.class);
        securityService = mock(SecurityService.class);
        userDAO = mock(UserDAO.class);
        userService = new UserServiceImpl(userDAO,securityService);
    }


    @Test
    public void userShouldGetNewPassword() throws Exception{

        when(user.getPassword()).thenReturn(userPassword);
        when(securityService.md5(userPassword)).thenReturn(userPasswordMD5);
        userService.assignPassword(user);

        verify(user).setPassword(userPasswordMD5);

    }


    @Test
    public void userShouldGetPasswordUpdated() throws Exception{

        when(user.getPassword()).thenReturn(userPassword);
        when(securityService.md5(userPassword)).thenReturn(userPasswordMD5);
        userService.assignPassword(user);

        verify(userDAO).updateUser(user);

    }



}