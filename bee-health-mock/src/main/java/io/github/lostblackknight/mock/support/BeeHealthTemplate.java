package io.github.lostblackknight.mock.support;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lostblackknight.mock.model.CommonResult;

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
    private String clientId = "";
    private String clientSecret = "";

    public static final String HOSPITAL_API = "http://192.168.86.131:7001/api/hospital";
    public static final String TOKEN_API = "http://192.168.86.131:7001/api/hospital/token";
    public static final String AUTH_PREFIX = "Bearer ";


    private final Map<String, String> cache = new ConcurrentHashMap<>(2);

    private final Lock lock = new ReentrantLock();

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public BeeHealthTemplate() {
    }

    public BeeHealthTemplate(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
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
                    throw new RuntimeException("无法获取 token。");
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
                throw new RuntimeException("无法获取 token。");
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
            cache.put("accessToken", accessToken);
        } else {
            throw new RuntimeException("token 请求失败");
        }
    }
}
