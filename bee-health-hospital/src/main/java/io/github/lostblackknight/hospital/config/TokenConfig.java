package io.github.lostblackknight.hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Configuration
public class TokenConfig {

    public static final String SECRET_KEY = "bee-health-signing-key";

    @Bean
    public TokenStore tokenStore() {
        final JwtTokenStore tokenStore = new JwtTokenStore(accessTokenConverter());
        tokenStore.setApprovalStore(approvalStore());
        return tokenStore;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final MacSigner signer = new MacSigner(SECRET_KEY);
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
            @Override
            protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                JsonParser objectMapper = JsonParserFactory.create();
                String content;
                try {
                    content = objectMapper.formatMap(getAccessTokenConverter().convertAccessToken(accessToken, authentication));
                } catch (Exception e) {
                    throw new IllegalStateException("Cannot convert access token to JSON", e);
                }
                return JwtHelper.encode(content, signer).getEncoded();
            }
        };
        converter.setSigner(signer);
        converter.setVerifier(signer);
        return converter;
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new InMemoryApprovalStore();
    }
}
