package prj.margin.anywhere.http;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Component
public class HttpConnection {

    /**
     *
     * {@link java.net.HttpURLConnection} is used
     * @deprecated
     */
    public String sendRequest(String apiUrl, RequestMethod requestMethod, Map<String, String> headers, String queryStringForParams) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(requestMethod.toString());

        // set parameters
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(queryStringForParams);
        out.flush();
        out.close();

        // set header
        for (Map.Entry<String, String> header : headers.entrySet()) {
            con.setRequestProperty(header.getKey(), header.getValue());
        }

        int httpStatus = con.getResponseCode();

        if(httpStatus == HttpURLConnection.HTTP_OK)
            // todo: 정상호출
            System.out.println("200");
        else System.out.println("error"); // todo: 실패

        // todo: return result
        return null;
    }
}
