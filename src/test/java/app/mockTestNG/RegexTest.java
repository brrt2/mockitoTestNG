package app.mockTestNG;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class RegexTest {


    @Test
    public void givenAstringReturnAllNumbersOfAtLeastThreeDigits() {

        assertThat(new Regex().returnAllNumbersWithAtLeastThreeDigits("cdefg 345 12bbb33 678tt")).isEqualTo("345, 678");

    }




}