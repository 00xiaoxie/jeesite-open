/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.shiro.d;	
	
import com.jeesite.common.shiro.d.c;	
import java.util.Set;	
import org.apache.shiro.cache.Cache;	
import org.apache.shiro.cache.CacheException;	
import org.apache.shiro.cache.CacheManager;	
	
public interface i	
extends CacheManager {	
    public Set<String> ALLATORIxDEMO();	
	
    public void ALLATORIxDEMO(String var1);	
	
    public <K, V> c<K, V> ALLATORIxDEMO(String var1) throws CacheException;	
}	
	
