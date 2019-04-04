package ccs.responses;

public class Response {
    private Boolean success;
    private Object object;
    private String message;

    private Response() {}

    public static Response ok() {
        Response response = new Response();
        response.setSuccess(true);

        return response;
    }

    public static Response ok(Object object) {
        Response response = new Response();
        response.setSuccess(true);
        response.setObject(object);

        return response;
    }

    public static Response error(String message) {
        Response response = new Response();
        response.setSuccess(false);
        response.setMessage(message);

        return response;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    private void setObject(Object object) {
        this.object = object;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Object getObject() {
        return object;
    }

    public String getMessage() {
        return message;
    }
}
