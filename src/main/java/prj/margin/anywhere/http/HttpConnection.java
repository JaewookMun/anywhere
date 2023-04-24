package prj.margin.anywhere.http;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HttpConnection {

    /**
     *
     * {@link java.net.HttpURLConnection} is used
     * @deprecated
     */
    public void sendRequest(String apiUrl, RequestMethod requestMethod) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod.toString());

    }
}
