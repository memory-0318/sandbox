package tw.brian.jasypt.service;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/24
 */
@Service
@Slf4j
@ShellComponent
public class OrgAddressBookConnectionService {

    @ShellMethod("根據輸入的URL以及Token向機關通訊錄取得資料")
    public void pingOrgAddressBook(@ShellOption({"--url", "-u"}) String apiUrl, @ShellOption({"--token", "-t"}) String accessToken) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(0, TimeUnit.MINUTES);
        client.setReadTimeout(3, TimeUnit.MINUTES);
        Response response = null;
        log.info("API URL: {}, Token: {}", apiUrl, accessToken);
        try {
            Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + accessToken)
                .addHeader("Content-Type", "application/json")
                .url(apiUrl)
                .build();
            response = client.newCall(request)
                .execute();
        } catch (IOException e) {
            String errMsg = "向機關通訊錄取得access token時發生錯誤";
            log.error(errMsg, e);
            throw new RuntimeException(errMsg, e);
        }

        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            try {
                log.info(responseBody.string());
            } catch (IOException e) {
                String errMsg = "取得機關通訊錄access token回應內容時發生錯誤";
                log.error(errMsg, e);
                throw new RuntimeException(errMsg, e);
            }
        } else {
            String errMsg = String.format("機關通訊錄API沒有回應內容 (%s)", apiUrl);
            log.error(errMsg);
            throw new RuntimeException(errMsg);
        }
    }
}
