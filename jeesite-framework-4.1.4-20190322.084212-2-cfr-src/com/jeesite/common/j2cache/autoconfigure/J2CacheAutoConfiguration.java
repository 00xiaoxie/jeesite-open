/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.j2cache.autoconfigure;	
	
import com.jeesite.common.j2cache.cache.support.utils.J2CacheConfigUtils;	
import java.io.IOException;	
import net.oschina.j2cache.CacheChannel;	
import net.oschina.j2cache.J2Cache;	
import net.oschina.j2cache.J2CacheBuilder;	
import net.oschina.j2cache.J2CacheConfig;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
import org.springframework.context.annotation.DependsOn;	
import org.springframework.context.annotation.PropertySource;	
import org.springframework.core.env.StandardEnvironment;	
	
@Configuration	
@ConditionalOnClass(value={J2Cache.class})	
@PropertySource(value={"classpath:config/j2cache.properties"})	
public class J2CacheAutoConfiguration {	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 5 << 4 ^ 1 << 1;	
        (2 ^ 5) << 4 ^ (3 ^ 5) << 1;	
        int n4 = n2;	
        int n5 = 2 << 3 ^ 2;	
        while (n4 >= 0) {	
            int n6 = n2--;	
            arrc[n6] = (char)(s.charAt(n6) ^ n5);	
            if (n2 < 0) break;	
            int n7 = n2--;	
            arrc[n7] = (char)(s.charAt(n7) ^ n3);	
            n4 = n2;	
        }	
        return new String(arrc);	
    }	
	
    @Bean	
    @DependsOn(value={"springUtils", "j2CacheConfig"})	
    public CacheChannel cacheChannel(J2CacheConfig j2CacheConfig) throws IOException {	
        return J2CacheBuilder.init(j2CacheConfig).getChannel();	
    }	
	
    @Bean	
    public J2CacheConfig j2CacheConfig(StandardEnvironment environment) throws IOException {	
        return J2CacheConfigUtils.initFromConfig(environment);	
    }	
}	
	
