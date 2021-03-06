/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.modules.msg.entity;	
	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.modules.msg.entity.MsgPush;	
import org.springframework.beans.BeanUtils;	
	
@Table(name="${_prefix}sys_msg_pushed", alias="a", columns={@Column(includeEntity=MsgPush.class)}, orderBy="a.id DESC")	
public class MsgPushed	
extends MsgPush {	
    private static final long serialVersionUID = 1L;	
	
    public MsgPushed() {	
        super(null);	
    }	
	
    public MsgPushed(MsgPush msgPush) {	
        MsgPushed msgPushed = this;	
        BeanUtils.copyProperties(msgPush, msgPushed);	
    }	
	
    public MsgPushed(String id) {	
        super(id);	
    }	
}	
	
