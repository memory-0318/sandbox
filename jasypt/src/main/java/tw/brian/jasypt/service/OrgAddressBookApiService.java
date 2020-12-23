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

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description:
 * @date: 2020/12/23
 */
@Service
@ShellComponent
@Slf4j
public class OrgAddressBookApiService {
    @ShellMethod(key = { "fetch-org-address-book" }, value = "從機關通訊錄API取得資料")
    public void retrieveOrgAddressBook(
        @ShellOption(value = { "--url", "-u" }) String url,
        @ShellOption(value = { "--token", "-t" }) String token) {
        OkHttpClient httpClient = new OkHttpClient();
        Response response;
        try {
            Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("Content-Type", "application/json")
                .url(url)
                .build();
            response = httpClient.newCall(request)
                .execute();
        } catch (IOException e) {
            String errMsg = "向機關通訊錄取得機關資料時發生錯誤";
            log.error(errMsg);
            throw new RuntimeException(errMsg, e);
        }

        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            try {
                log.info(responseBody.string());
            } catch (IOException e) {
                String errMsg = "取得機關通訊錄資料回應內容時發生錯誤";
                log.error(errMsg);
                throw new RuntimeException(errMsg, e);
            }
        } else {
            throw new RuntimeException("機關通訊錄資料沒有回應內容");
        }
    }
}
