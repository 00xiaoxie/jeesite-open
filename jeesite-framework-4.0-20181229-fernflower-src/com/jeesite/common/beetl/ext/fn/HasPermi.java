package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.common.validator.ValidatorUtils;	
import com.jeesite.modules.sys.utils.UserUtils;	
import org.apache.commons.lang3.BooleanUtils;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
	
public class HasPermi implements Function {	
   public Object call(Object[] paras, Context ctx) {	
      String a;	
      if (StringUtils.isBlank(a = ObjectUtils.toString(paras[0]))) {	
         return false;	
      } else {	
         String a = "and";	
         if (paras.length >= 2) {	
            a = ObjectUtils.toString(paras[1]);	
         }	
	
         boolean[] a = UserUtils.getSubject().isPermitted(StringUtils.split(a, ","));	
         return "or".equalsIgnoreCase(a) ? BooleanUtils.or(a) : BooleanUtils.and(a);	
      }	
   }	
}	
