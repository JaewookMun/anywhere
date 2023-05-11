package prj.margin.anywhere.http;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import prj.margin.anywhere.service.dto.NaverResponseDto;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
public class HttpConnection<T> {

    private static final Logger logger = LoggerFactory.getLogger(HttpConnection.class);

    /**
     *
     * {@link java.net.HttpURLConnection} is used
     */
    public T sendRequest(String apiUrl, RequestMethod requestMethod, Map<String, String> headers, String queryString, Class<T> type) throws IOException {
        apiUrl = apiUrl + queryString;
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(requestMethod.toString());

        logger.info("[connection]: " + con);
        logger.info("[method]: " + con.getRequestMethod());

        // set header
        for (Map.Entry<String, String> header : headers.entrySet()) {
            con.setRequestProperty(header.getKey(), header.getValue());
            System.out.println(header.getKey() + ", " + header.getValue());
        }

        // set parameters
//        con.setDoOutput(true);
//        DataOutputStream out = new DataOutputStream(con.getOutputStream());
//        out.writeBytes(queryString);
//        out.flush();
//        out.close();

//        con.setConnectTimeout(5000);
//        con.setReadTimeout(5000);

        int httpStatus = con.getResponseCode();
        logger.info("[HTTP_STATUS]: " + httpStatus);

        NaverResponseDto naverResponseDto = null;

        if(httpStatus == HttpURLConnection.HTTP_OK) {

            String response = readBody(con.getInputStream());
            logger.info("[RESPONSE]: " + response);

            // RFC_1123_DATE_TIME -> "Mon, 08 May 2023 01:59:19 +0900"
            DateTimeFormatter timeFormatter = DateTimeFormatter.RFC_1123_DATE_TIME;

            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.registerModule(getTimeModule(timeFormatter));

            naverResponseDto = jsonMapper.readValue(response, NaverResponseDto.class);

        }
        else System.out.println("error: " + httpStatus); // todo: 실패

        return type.cast(naverResponseDto);
    }

    /**
     * when deserializing JSON data on DateTime, this method supply the implementation of {@link com.fasterxml.jackson.databind.Module}
     * for parse the date string formatted by the given {@link DateTimeFormatter} to {@link LocalDateTime}
     * @return
     */
    private JavaTimeModule getTimeModule(DateTimeFormatter dateTimeFormatter) {

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
                String dateString = parser.getValueAsString();
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, dateTimeFormatter);

                return zonedDateTime.toLocalDateTime();
            }
        });

        return javaTimeModule;
    }

    private String readBody(InputStream body) throws UnsupportedEncodingException {
        InputStreamReader streamReader = new InputStreamReader(body, "UTF-8");

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder response = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}
