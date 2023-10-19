package edu.baylor.cs.junit.params;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Pass the method parameters provided by the @ValueSource annotation") //This creates a custom name for the test class
public class Ex2ValueSourceExampleTest {
 
    @DisplayName("Should pass a non-null message to our test method") //Custom name for the method
    @ParameterizedTest //Declares it as a parameterized test so that paramaters can be passed
    @ValueSource(strings = {"Hello", "World"}) //Specifies the parameters
    void shouldPassNonNullMessageAsMethodParameter(String message) { //Makes sure the parameter is not null
        assertNotNull(message); //Asserts that the message is not null
    }
}