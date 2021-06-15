package vgame.xmppListener;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.concurrent.TimeUnit;

public class ApiCall {
    public static final int TIMEOUT = 30;
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .callTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build();

    public static String makeRequest(String url) throws Exception {
        XmppListenerApplication.logger.info("New Request URL = " + url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        String responseString = response.body().string();
        XmppListenerApplication.logger.info("Response : " + responseString);


        return responseString;
    }
}
