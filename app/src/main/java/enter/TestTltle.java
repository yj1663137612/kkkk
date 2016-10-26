package enter;

import java.util.ArrayList;

/**
 * Created by ACER on 2016/10/24.
 */

public class TestTltle {
    String message;
    String status;
    ArrayList<Content> data;
    String string;

    public void setString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
    public TestTltle(String string) {
        this.string=string;

    }
    public TestTltle() {

    }

    public TestTltle(String message, String status, ArrayList<Content> data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Content> getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(ArrayList<Content> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TestTltle{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
