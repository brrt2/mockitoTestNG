package exceptions;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;
import static com.googlecode.catchexception.CatchException.*;

public class RequestHandlerTest {


    @Test
    public void shouldNotProcessInvalidRequests() {

        Request invalidRequest = new Request();
        RequestProcessor requestProcessor = mock(RequestProcessor.class);

        RequestHandler sut = new RequestHandler(requestProcessor);

        try {
            sut.handle(invalidRequest);
            fail("Should have thrown InvalidRequestException");
        } catch (InvalidRequestException e) {

            Mockito.verifyZeroInteractions(requestProcessor);
        }
    }

    @Test
    public void shouldThrowExceptions() throws InvalidRequestException {

        Request request = new Request();
        RequestProcessor requestProcessor = mock(RequestProcessor.class);
        RequestHandler sut = new RequestHandler(requestProcessor);

        catchException(sut).handle(request);

        assertTrue(caughtException() instanceof InvalidRequestException, "Should have thrown exception of InvalidRequestException class");

        Mockito.verifyZeroInteractions(requestProcessor);
    }


}