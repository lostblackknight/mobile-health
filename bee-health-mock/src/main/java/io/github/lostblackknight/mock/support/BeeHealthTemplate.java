package io.github.lostblackknight.mock.support;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@SuppressWarnings("all")
public class BeeHealthTemplate {
    private final String clientId;
    private final String clientSecret;
    public static final String TOKEN_API = "http://localhost:7001/api/admin/token/hospital";
    public static final String REFRESH_TOKEN_API = "http://localhost:7001/api/admin/token/hospital/refresh";
    private final Map<String, String> cache = new ConcurrentHashMap<>(2);
    private final Lock lock = new ReentrantLock();
    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public BeeHealthTemplate(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getRefreshToken() {
        return cache.get("refreshToken");
    }

    public String getToken() {
        if (StrUtil.isNotEmpty(cache.get("accessToken"))) {
            return cache.get("accessToken");
        } else {
            lock.lock();
            try {
                getToken0();
                return cache.get("accessToken");
            } catch (Exception e) {
                if (atomicInteger.get() == 5) {
                    throw new RuntimeException("无法获取token。");
                }
                final int count = atomicInteger.incrementAndGet();
                // 再次获取
                try {
                    System.out.println("第" + count + "尝试获取 token。");
                    TimeUnit.SECONDS.sleep(2);
                    getToken();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                throw new RuntimeException("无法获取token。");
            } finally {
                lock.unlock();
            }
        }
    }

    private void getToken0() throws JsonProcessingException {
        final HashMap<String, Object> body = new HashMap<>();
        body.put("clientId", clientId);
        body.put("clientSecret", clientSecret);
        final ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(body);

        // 发送请求
        final String result = HttpUtil.createPost(TOKEN_API)
                .contentType("application/json")
                .body(str).execute().body();

        CommonResult<Map<String, Object>> commonResult = mapper.readValue(result, CommonResult.class);
        if (commonResult.getCode() == 1) {
            // 获取成功
            final Map<String, Object> data = commonResult.getData();
            String accessToken = (String) data.get("access_token");
            String refreshToken = (String) data.get("refresh_token");
            cache.put("accessToken", accessToken);
            cache.put("refreshToken", refreshToken);
        } else {
            throw new RuntimeException("token请求失败");
        }
    }
}
