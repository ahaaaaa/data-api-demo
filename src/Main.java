import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Main {

    private static String url = "https://api.data.jpush.cn/v1/users?mac=84:38:38:4b:88:62&tag=all";
    private static final String DEFAULT_USER = "************************";//开发者自己的key
    private static final String DEFAULT_PASS = "************************";//开发者自己的secret

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClients.custom().build();
        HttpUriRequest request = RequestBuilder.get().setUri(url)
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json").setHeader(HttpHeaders.AUTHORIZATION, authorizationHeader(DEFAULT_USER, DEFAULT_PASS)).build();
        HttpResponse response = client.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }


    private static String authorizationHeader(final String username, final String password) {
        final String auth = username + ":" + password;
        final char[] encodedAuth = Base64.encode(auth.getBytes());
        return "Basic " + new String(encodedAuth);
    }
}
