package prj.margin.anywhere.http;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Component
public class HttpConnection {

    /**
     *
     * {@link java.net.HttpURLConnection} is used
     */
    public String sendRequest(String apiUrl, RequestMethod requestMethod, Map<String, String> headers, String queryStringForParams) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(requestMethod.toString());

        // set header
        for (Map.Entry<String, String> header : headers.entrySet()) {
            con.setRequestProperty(header.getKey(), header.getValue());
        }

        // set parameters
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(queryStringForParams);
        out.flush();
        out.close();

        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        int httpStatus = con.getResponseCode();

        if(httpStatus == HttpURLConnection.HTTP_OK) {
            // todo: 정상호출
            System.out.println("200");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine = "";
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            System.out.println("content = " + content);
        }
        else System.out.println("error: " + httpStatus); // todo: 실패

        // todo: return result
        return null;
    }
}
