/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  javax.servlet.http.HttpServletRequest	
 *  javax.servlet.http.HttpSession	
 *  org.apache.commons.logging.Log	
 *  org.apache.commons.logging.LogFactory	
 *  org.apache.shiro.subject.PrincipalCollection	
 *  org.apache.shiro.subject.support.DefaultSubjectContext	
 *  org.jasig.cas.client.session.HashMapBackedSessionMappingStorage	
 *  org.jasig.cas.client.session.SessionMappingStorage	
 *  org.jasig.cas.client.util.CommonUtils	
 *  org.jasig.cas.client.util.XmlUtils	
 */	
package com.jeesite.common.shiro.cas;	
	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.shiro.realm.LoginInfo;	
import com.jeesite.common.web.returnjson.ReturnJsonSerializer;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpSession;	
import org.apache.commons.logging.Log;	
import org.apache.commons.logging.LogFactory;	
import org.apache.shiro.subject.PrincipalCollection;	
import org.apache.shiro.subject.support.DefaultSubjectContext;	
import org.jasig.cas.client.session.HashMapBackedSessionMappingStorage;	
import org.jasig.cas.client.session.SessionMappingStorage;	
import org.jasig.cas.client.util.CommonUtils;	
import org.jasig.cas.client.util.XmlUtils;	
	
public final class CasOutHandler {	
    private SessionMappingStorage sessionMappingStorage;	
    private final Log log;	
    private String artifactParameterName;	
    private String logoutParameterName;	
	
    public void setLogoutParameterName(String name) {	
        this.logoutParameterName = name;	
    }	
	
    public boolean isLogoutRequest(HttpServletRequest request) {	
        return "POST".equals(request.getMethod()) && !this.isMultipartRequest(request) && CommonUtils.isNotBlank((String)CommonUtils.safeGetParameter((HttpServletRequest)request, (String)this.logoutParameterName));	
    }	
	
    public static String ALLATORIxDEMO(String s) {	
        int n = s.length();	
        int n2 = n - 1;	
        char[] arrc = new char[n];	
        int n3 = 1 << 3 ^ (2 ^ 5);	
        int n4 = n2;	
        4 << 4 ^ (2 ^ 5);	
        int n5 = 4 << 4 ^ (2 << 2 ^ 1);	
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
	
    public SessionMappingStorage getSessionMappingStorage() {	
        return this.sessionMappingStorage;	
    }	
	
    public void init() {	
        CasOutHandler casOutHandler = this;	
        CommonUtils.assertNotNull((Object)casOutHandler.artifactParameterName, (String)"artifactParameterName cannot be null.");	
        CommonUtils.assertNotNull((Object)casOutHandler.logoutParameterName, (String)"logoutParameterName cannot be null.");	
        CommonUtils.assertNotNull((Object)casOutHandler.sessionMappingStorage, (String)"sessionMappingStorage cannote be null.");	
    }	
	
    /*	
     * Enabled aggressive block sorting	
     * Enabled unnecessary exception pruning	
     * Enabled aggressive exception aggregation	
     */	
    public LoginInfo destroySession(HttpServletRequest request) {	
        HttpSession a;	
        String a2 = CommonUtils.safeGetParameter((HttpServletRequest)request, (String)this.logoutParameterName);	
        if (this.log.isTraceEnabled()) {	
            this.log.trace((Object)new StringBuilder().insert(0, "Logout request:\n").append(a2).toString());	
        }	
        LoginInfo a3 = null;	
        String a4 = XmlUtils.getTextForElement((String)a2, (String)"SessionIndex");	
        if (CommonUtils.isNotBlank((String)a4) && (a = this.sessionMappingStorage.removeSessionByMappingId(a4)) != null) {	
            String a5 = a.getId();	
            if (this.log.isDebugEnabled()) {	
                this.log.debug((Object)new StringBuilder().insert(0, "Invalidating session [").append(a5).append("] for token [").append(a4).append("]").toString());	
            }	
            try {	
                PrincipalCollection a6 = (PrincipalCollection)a.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);	
                a3 = a6 != null ? (LoginInfo)a6.getPrimaryPrincipal() : null;	
                a.invalidate();	
                return a3;	
            }	
            catch (IllegalStateException a7) {	
                this.log.debug((Object)"Error invalidating session.", (Throwable)a7);	
            }	
        }	
        return a3;	
    }	
	
    public boolean isTokenRequest(HttpServletRequest request) {	
        return CommonUtils.isNotBlank((String)CommonUtils.safeGetParameter((HttpServletRequest)request, (String)this.artifactParameterName));	
    }	
	
    public void recordSession(HttpServletRequest request, String ticket) {	
        CasOutHandler casOutHandler;	
        CasOutHandler casOutHandler2;	
        String a;	
        HttpSession a2 = request.getSession();	
        if (ticket != null) {	
            String string = ticket;	
            casOutHandler2 = this;	
        } else {	
            a = CommonUtils.safeGetParameter((HttpServletRequest)request, (String)this.artifactParameterName);	
            casOutHandler2 = this;	
        }	
        if (casOutHandler2.log.isDebugEnabled()) {	
            this.log.debug((Object)new StringBuilder().insert(0, "Recording session for token ").append(a).toString());	
        }	
        try {	
            this.sessionMappingStorage.removeBySessionById(a2.getId());	
            casOutHandler = this;	
        }	
        catch (Exception exception) {	
            casOutHandler = this;	
        }	
        casOutHandler.sessionMappingStorage.addSessionById(a, a2);	
    }	
	
    public void recordSession(HttpServletRequest request) {	
        this.recordSession(request, null);	
    }	
	
    public void setSessionMappingStorage(SessionMappingStorage storage) {	
        this.sessionMappingStorage = storage;	
    }	
	
    public CasOutHandler() {	
        CasOutHandler casOutHandler = this;	
        CasOutHandler casOutHandler2 = this;	
        casOutHandler2.log = LogFactory.getLog(casOutHandler2.getClass());	
        CasOutHandler casOutHandler3 = this;	
        casOutHandler2.sessionMappingStorage = new HashMapBackedSessionMappingStorage();	
        casOutHandler.artifactParameterName = "ticket";	
        casOutHandler.logoutParameterName = "logoutRequest";	
    }	
	
    private /* synthetic */ boolean isMultipartRequest(HttpServletRequest request) {	
        return request.getContentType() != null && request.getContentType().toLowerCase().startsWith("multipart");	
    }	
	
    public void setArtifactParameterName(String name) {	
        this.artifactParameterName = name;	
    }	
}	
	