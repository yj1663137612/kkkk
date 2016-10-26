package enter;

import java.util.ArrayList;

/**
 * Created by ACER on 2016/10/24.
 */

public class Result {
    String  message;
    String statue;
    ArrayList<Person>data;
    public Result() {

    }
    public Result(String message, String statue) {
        this.message = message;
        this.statue = statue;

    }

    public Result(String message, String statue, ArrayList<Person> data) {
        this.message = message;
        this.statue = statue;
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public void setData(ArrayList<Person> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public String getStatue() {
        return statue;
    }

    public ArrayList<Person> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", statue='" + statue + '\'' +
                ", data=" + data +
                '}';
    }
}
