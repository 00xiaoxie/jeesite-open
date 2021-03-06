package com.jeesite.common.shiro.authc;	
	
import java.util.Map;	
import org.apache.shiro.authc.UsernamePasswordToken;	
	
public class FormToken extends UsernamePasswordToken {	
   private Map params;	
   private String captcha;	
   private static final long serialVersionUID = 1L;	
   private String ssoToken;	
	
   public void setSsoToken(String ssoToken) {	
      this.ssoToken = ssoToken;	
   }	
	
   public Map getParams() {	
      return this.params;	
   }	
	
   public String getSsoToken() {	
      return this.ssoToken;	
   }	
	
   public String getCaptcha() {	
      return this.captcha;	
   }	
	
   public FormToken(String username, String password, boolean rememberMe, String host, String captcha, Map var6) {	
      super(username, password, rememberMe, host);	
      this.captcha = captcha;	
      this.params = var6;	
   }	
	
   public FormToken() {	
   }	
	
   public void setPassword(String password) {	
      this.setPassword(password != null ? password.toCharArray() : null);	
   }	
	
   public void setCaptcha(String captcha) {	
      this.captcha = captcha;	
   }	
	
   public void setParams(Map params) {	
      this.params = params;	
   }	
	
   public FormToken(String username, char[] password, boolean rememberMe, String host, String captcha, Map var6) {	
      super(username, password, rememberMe, host);	
      this.captcha = captcha;	
      this.params = var6;	
   }	
}	
