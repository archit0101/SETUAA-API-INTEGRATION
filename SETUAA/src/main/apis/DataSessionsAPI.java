package apis;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.olingo.odata2.api.commons.HttpStatusCodes;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataSessionsAPI {

  private static final String clientID = "255d0b6c-492d-44cf-8581-e9494c7b0914";
  private static final String clientSecret = "9c2fc756-3d17-46d6-b28d-be4d71953e83";
  private static final String url = "https://fiu-uat.setu.co/sessions";

  private static final String sessionpath = "payloadsession.json";

  public String getSession(String id) {
    String responseStr = null;
    JsonParser parser = new JsonParser();
    try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
      HttpGet req = new HttpGet(url + "/"+ id);
      req.setHeader("Accept", "application/json");
      req.addHeader("x-client-id", clientID);
      req.addHeader("x-client-secret", clientSecret);
      try (CloseableHttpResponse httpResponse = httpClient.execute(req)) {
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatusCodes.OK.getStatusCode()) {
          HttpEntity responseEntity = httpResponse.getEntity();
          responseStr = EntityUtils.toString(responseEntity);
          if (responseEntity != null) {
            JsonElement jsonElement = parser.parse(responseStr);
            if (jsonElement != null) {
              JsonObject responseObj = jsonElement.getAsJsonObject();
              return responseObj.toString();
            }
          }
        }
        else{
          System.out.println("hello");
          System.out.println(httpResponse.getStatusLine());
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  public JsonObject createSession() {
    String responseStr = null;
    String payload = null;
    JsonParser parser = new JsonParser();
    try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
      payload = new String(
          Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(sessionpath).toURI())), StandardCharsets.UTF_8);
      HttpPost req = new HttpPost(url);
      req.setHeader("Accept", "application/json");
      req.setHeader("Content-type", "application/json");
      req.addHeader("x-client-id", clientID);
      req.addHeader("x-client-secret", clientSecret);
      req.setEntity(new StringEntity(payload.toString()));
      try (CloseableHttpResponse httpResponse = httpClient.execute(req)) {
        if (httpResponse.getStatusLine().getStatusCode() == 201) {
          HttpEntity responseEntity = httpResponse.getEntity();
          responseStr = EntityUtils.toString(responseEntity);
          if (responseEntity != null) {
            JsonElement jsonElement = parser.parse(responseStr);
            if (jsonElement != null) {
              JsonObject responseObj = jsonElement.getAsJsonObject();
              if(responseObj.has("id") && !responseObj.get("id").isJsonNull()){
                return responseObj;
              }
            }
          }
        }
        else{
          System.out.println("POST call to session API failed" + httpResponse.getStatusLine() + " with reason " + httpResponse.getEntity().toString());
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    return null;
  }



}
