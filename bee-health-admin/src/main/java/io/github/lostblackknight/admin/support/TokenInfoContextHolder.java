package io.github.lostblackknight.admin.support;

import io.github.lostblackknight.model.dto.TokenInfoDTO;

/**
 * token 信息上下文持有者
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
public class TokenInfoContextHolder {

    private static final ThreadLocal<TokenInfoDTO> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(TokenInfoDTO tokenInfoDTO) {
        THREAD_LOCAL.set(tokenInfoDTO);
    }

    public static TokenInfoDTO get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
