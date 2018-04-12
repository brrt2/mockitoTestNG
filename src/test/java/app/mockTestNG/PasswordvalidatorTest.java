package app.mockTestNG;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

@Test
public class PasswordvalidatorTest {

    private Passwordvalidator passwordvalidator;

    @BeforeMethod
    public void setUp() {
        passwordvalidator = new Passwordvalidator();
    }


    @DataProvider
    public Object[][] supplyCorrectPasswords() {

        return new Object[][] {{"Abc123@ffdsdf"},{"Bartek123!fsfs@65"},{"abc123@@@fdsfsdfdsfAbA"}};
    }

    @DataProvider
    public Object[][] supplyFalsePasswords() {

        return new Object[][] {{"gfgd"},{""},{"ab"}};

    }

    @Test(dataProvider = "supplyCorrectPasswords")
    public void givenACorrectPasswordThenReturnTrue(String input)  {
    assertThat(passwordvalidator.validatePassword(input)).isTrue();
    }

    @Test(dataProvider = "supplyFalsePasswords")
    public void givenAFalsePasswordThenReturnFalse(String input) {
        assertThat(passwordvalidator.validatePassword(input)).isFalse();
    }




}