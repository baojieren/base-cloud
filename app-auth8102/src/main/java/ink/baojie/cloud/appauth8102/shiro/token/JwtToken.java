package ink.baojie.cloud.appauth8102.shiro.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author renbaojie
 */
@Data
@AllArgsConstructor
public class JwtToken implements AuthenticationToken {
    private String userId;
    private String token;

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
