package exceptions;

public class RequestHandler {

    private final RequestProcessor requestProcessor;

    public RequestHandler(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }


    public void handle( Request request) throws InvalidRequestException {

        if (invalidRequest(request)) {
            throw new InvalidRequestException();
        }
        requestProcessor.process(request);

    }

    private boolean invalidRequest(Request request) {

        return true;
    }


}
