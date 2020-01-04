package ink.baojie.cloud.appauth8102.shiro;

import ink.baojie.cloud.appauth8102.shiro.filter.CustomRolesAuthorizationFilter;
import ink.baojie.cloud.appauth8102.shiro.filter.TokenAuthenticatingFilter;
import ink.baojie.cloud.appauth8102.shiro.filter.TokenLogOutFilter;
import ink.baojie.cloud.appauth8102.shiro.realm.TokenRealm;
import ink.baojie.cloud.appauth8102.shiro.realm.UserRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author renbaojie
 */
@Configuration
@Slf4j
public class ShiroConfig extends ShiroWebAutoConfiguration {
    public static final String SALT = "sALT";

    /**
     * 设置密码加密方式和次数
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    /**
     * 会话管理器
     */
    @Bean
    @Override
    public DefaultSessionManager sessionManager() {
        DefaultSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
        return sessionManager;
    }

    /**
     * 安全管理器
     */
    @Bean
    @Override
    public DefaultWebSecurityManager securityManager(List<Realm> realms) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setRealms(realms);
        securityManager.setCacheManager(shiroCacheManager());
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    /**
     * 各种登录方式的过滤器配置
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager(realms()));

        // 设置登录地址
        filterFactoryBean.setLoginUrl("/login");

        Map<String, Filter> filters = new HashMap<>(3);
        filters.put("tokenAuth", tokenAuth());
        filters.put("customRoles", customRoles());
        filters.put("tokenLogout", tokenLogout());

        filterFactoryBean.setFilters(filters);
        filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());
        return filterFactoryBean;
    }

    /**
     * 资源权限配置
     */
    @Bean
    @Override
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/user/**", "tokenAuth,customRoles[admin]");
        chainDefinition.addPathDefinition("/logout", "tokenAuth,tokenLogout");
        // chainDefinition.addPathDefinition("/updatePass", "tokenAuth");
        // chainDefinition.addPathDefinition("/update", "tokenAuth");
        // chainDefinition.addPathDefinition("/finance/**", "tokenAuth");
        // chainDefinition.addPathDefinition("/quest/**", "tokenAuth");
        // chainDefinition.addPathDefinition("/user/**", "tokenAuth");
        // chainDefinition.addPathDefinition("/img/*", "tokenAuth");
        // chainDefinition.addPathDefinition("/mini/knowledge/*", "tokenAuth");
        // chainDefinition.addPathDefinition("/mini/team/*", "tokenAuth");
        // chainDefinition.addPathDefinition("/**", "noSessionCreation,anon");
        return chainDefinition;
    }

    @Bean
    public TokenAuthenticatingFilter tokenAuth() {
        return new TokenAuthenticatingFilter();
    }

    @Bean
    public TokenLogOutFilter tokenLogout() {
        return new TokenLogOutFilter("/logout");
    }

    @Bean
    public CustomRolesAuthorizationFilter customRoles() {
        return new CustomRolesAuthorizationFilter();
    }

    @Bean
    public List<Realm> realms() {
        List<Realm> list = new ArrayList<>();
        list.add(userRealm());
        list.add(tokenRealm());
        return list;
    }

    @Bean
    public Realm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    @Bean
    public Realm tokenRealm() {
        return new TokenRealm();
    }

    @Bean
    protected CacheManager shiroCacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    // @Bean
    // public static SimpleCredentialsMatcher simpleCredentialsMatcher() {
    //     return new SimpleCredentialsMatcher();
    // }
}
