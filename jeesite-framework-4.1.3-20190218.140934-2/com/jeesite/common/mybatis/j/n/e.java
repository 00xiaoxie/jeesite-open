package com.jeesite.common.mybatis.j.n;	
	
public abstract class E implements com.jeesite.common.mybatis.j.e {	
   protected static com.jeesite.common.mybatis.j.e.F ALLATORIxDEMO = new com.jeesite.common.mybatis.j.e.F();	
	
   public String ALLATORIxDEMO(String sql) {	
      return ALLATORIxDEMO.ALLATORIxDEMO(sql);	
   }	
}	
mmon.mybatis.j.e.M c = new com.jeesite.common.mybatis.j.e.M();	
   protected com.jeesite.common.mybatis.j.E ALLATORIxDEMO;	
	
   public String ALLATORIxDEMO(String sql, RowBounds rowBounds) {	
      sql = this.ALLATORIxDEMO.h(sql);	
      sql = this.c.ALLATORIxDEMO(sql, (Integer)null, (Integer)null);	
      sql = this.ALLATORIxDEMO.ALLATORIxDEMO(sql);	
      return (rowBounds.getOffset() > 1000 && "9".equals(com.jeesite.common.shiro.j.H.ALLATORIxDEMO().get("type")) ? sql.replace(String.valueOf(Long.MIN_VALUE), "1000") : sql.replace(String.valueOf(Long.MIN_VALUE), String.valueOf(rowBounds.getOffset()))).replace(String.valueOf(Long.MAX_VALUE), String.valueOf(rowBounds.getLimit()));	
   }	
	
   public e() {	
      String a;	
      if (!StringUtils.isEmpty(a = Global.getProperty("page.sqlserver.replaceSl")) && !"simple".equalsIgnoreCase(a)) {	
         if ("regex".equalsIgnoreCase(a)) {	
            this.ALLATORIxDEMO = new com.jeesite.common.mybatis.j.j.e();	
         } else {	
            try {	
               this.ALLATORIxDEMO = (com.jeesite.common.mybatis.j.E)Class.forName(a).newInstance();	
            } catch (Exception var3) {	
               throw new RuntimeException((new StringBuilder()).insert(0, "replaceSql 参数配置的值不符合要求，可选值为 simple 和 regex，或者是实现了 ").append(com.jeesite.common.mybatis.j.E.class.getCanonicalName()).append(" 接口的全限定类名").toString());	
            }	
         }	
      } else {	
         this.ALLATORIxDEMO = new com.jeesite.common.mybatis.j.j.E();	
      }	
   }	
	
   public String ALLATORIxDEMO(String sql) {	
      sql = this.ALLATORIxDEMO.h(sql);	
      sql = ALLATORIxDEMO.ALLATORIxDEMO(sql);	
      return this.ALLATORIxDEMO.ALLATORIxDEMO(sql);	
   }	
}	
