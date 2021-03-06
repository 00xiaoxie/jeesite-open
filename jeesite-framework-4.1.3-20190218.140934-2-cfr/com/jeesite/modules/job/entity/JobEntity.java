/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.fasterxml.jackson.annotation.JsonFormat	
 *  org.hibernate.validator.constraints.Length	
 *  org.quartz.JobDataMap	
 *  org.quartz.Scheduler	
 *  org.quartz.SchedulerException	
 *  org.quartz.Trigger	
 *  org.quartz.Trigger$TriggerState	
 *  org.quartz.TriggerKey	
 *  org.quartz.impl.triggers.CronTriggerImpl	
 */	
package com.jeesite.modules.job.entity;	
	
import com.fasterxml.jackson.annotation.JsonFormat;	
import com.jeesite.common.entity.DataEntity;	
import com.jeesite.common.j.e;	
import com.jeesite.common.mybatis.annotation.Column;	
import com.jeesite.common.mybatis.annotation.Table;	
import com.jeesite.common.mybatis.mapper.query.QueryType;	
import java.util.Date;	
import org.hibernate.validator.constraints.Length;	
import org.hyperic.sigar.ProcTime;	
import org.quartz.JobDataMap;	
import org.quartz.Scheduler;	
import org.quartz.SchedulerException;	
import org.quartz.Trigger;	
import org.quartz.TriggerKey;	
import org.quartz.impl.triggers.CronTriggerImpl;	
	
@Table(name="${_prefix}sys_job", alias="a", columns={@Column(includeEntity=DataEntity.class), @Column(name="job_name", attrName="jobName", label="\u4efb\u52a1\u540d\u79f0", isPK=true), @Column(name="job_group", attrName="jobGroup", label="\u4efb\u52a1\u5206\u7ec4", isPK=true), @Column(name="description", attrName="description", label="\u4efb\u52a1\u63cf\u8ff0"), @Column(name="invoke_target", attrName="invokeTarget", label="\u8c03\u7528\u76ee\u6807\u5b57\u7b26\u4e32", queryType=QueryType.LIKE), @Column(name="cron_expression", attrName="cronExpression", label="Cron\u6267\u884c\u8868\u8fbe\u5f0f"), @Column(name="misfire_instruction", attrName="misfireInstruction", label="\u8ba1\u5212\u6267\u884c\u9519\u8bef\u7b56\u7565"), @Column(name="concurrent", attrName="concurrent", label="\u662f\u5426\u5e76\u53d1\u6267\u884c")}, orderBy="a.job_group, a.job_name")	
public class JobEntity	
extends DataEntity<JobEntity> {	
    private static final long serialVersionUID = 1L;	
    public static final String STATUS_DELETE = "1";	
    private String invokeTarget;	
    private String description;	
    public static final String STATUS_COMPLETE = "3";	
    public static final String STATUS_PAUSED = "2";	
    public static final String STATUS_NORMAL = "0";	
    private String cronExpression;	
    private String concurrent;	
    public static final String STATUS_ERROR = "4";	
    private Date prevFireTime;	
    private Integer misfireInstruction;	
    private String jobGroup;	
    public static final String STATUS_BLOCKED = "5";	
    private String jobName;	
    private Date nextFireTime;	
	
    public void setMisfireInstruction(Integer misfireInstruction) {	
        this.misfireInstruction = misfireInstruction;	
    }	
	
    public void setInvokeTarget(String invokeTarget) {	
        this.invokeTarget = invokeTarget;	
    }	
	
    public void setCronExpression(String cronExpression) {	
        this.cronExpression = cronExpression;	
    }	
	
    public String getJobName() {	
        return this.jobName;	
    }	
	
    public JobEntity convert(Scheduler scheduler, CronTriggerImpl trigger) {	
        Trigger.TriggerState a2;	
        JobEntity a3;	
        block12 : {	
            block11 : {	
                JobEntity jobEntity = a3 = this;	
                CronTriggerImpl cronTriggerImpl = trigger;	
                a3.setJobName(trigger.getName());	
                a3.setJobGroup(cronTriggerImpl.getGroup());	
                jobEntity.setDescription(cronTriggerImpl.getDescription());	
                jobEntity.setInvokeTarget((String)trigger.getJobDataMap().get((Object)"invokeTarget"));	
                JobEntity jobEntity2 = a3;	
                CronTriggerImpl cronTriggerImpl2 = trigger;	
                a3.setCronExpression(cronTriggerImpl2.getCronExpression());	
                jobEntity2.setMisfireInstruction(cronTriggerImpl2.getMisfireInstruction());	
                jobEntity2.setConcurrent((String)trigger.getJobDataMap().get((Object)"concurrent"));	
                a3.setRemarks((String)trigger.getJobDataMap().get((Object)"remarks"));	
                a2 = scheduler.getTriggerState(trigger.getKey());	
                if (!Trigger.TriggerState.NORMAL.equals((Object)a2)) break block11;	
                a3.setStatus(STATUS_NORMAL);	
            }	
            if (!Trigger.TriggerState.NONE.equals((Object)a2)) break block12;	
            a3.setStatus(STATUS_DELETE);	
        }	
        try {	
            if (Trigger.TriggerState.PAUSED.equals((Object)a2)) {	
                a3.setStatus(STATUS_PAUSED);	
            } else if (Trigger.TriggerState.COMPLETE.equals((Object)a2)) {	
                a3.setStatus(STATUS_COMPLETE);	
            } else if (Trigger.TriggerState.ERROR.equals((Object)a2)) {	
                a3.setStatus(STATUS_ERROR);	
            } else if (Trigger.TriggerState.BLOCKED.equals((Object)a2)) {	
                a3.setStatus(STATUS_BLOCKED);	
            }	
        }	
        catch (SchedulerException a4) {	
            a3.setStatus(STATUS_DELETE);	
        }	
        JobEntity jobEntity = a3;	
        jobEntity.setPrevFireTime(trigger.getPreviousFireTime());	
        jobEntity.setNextFireTime(trigger.getNextFireTime());	
        return a3;	
    }	
	
    public JobEntity(String jobName, String jobGroup) {	
        JobEntity jobEntity = this;	
        jobEntity.jobName = jobName;	
        jobEntity.jobGroup = jobGroup;	
    }	
	
    @Length(min=0, max=100, message="\u4efb\u52a1\u63cf\u8ff0\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 100 \u4e2a\u5b57\u7b26")	
    public String getDescription() {	
        return this.description;	
    }	
	
    public Integer getMisfireInstruction() {	
        return this.misfireInstruction;	
    }	
	
    @Length(min=0, max=1, message="\u662f\u5426\u5e76\u53d1\u6267\u884c\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1 \u4e2a\u5b57\u7b26")	
    public String getConcurrent() {	
        return this.concurrent;	
    }	
	
    public void setJobName(String jobName) {	
        this.jobName = jobName;	
    }	
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")	
    public Date getPrevFireTime() {	
        return this.prevFireTime;	
    }	
	
    public void setDescription(String description) {	
        this.description = description;	
    }	
	
    public void setNextFireTime(Date nextFireTime) {	
        this.nextFireTime = nextFireTime;	
    }	
	
    public void setPrevFireTime(Date prevFireTime) {	
        this.prevFireTime = prevFireTime;	
    }	
	
    public void setJobGroup(String jobGroup) {	
        this.jobGroup = jobGroup;	
    }	
	
    public JobEntity() {	
    }	
	
    public String getJobGroup() {	
        return this.jobGroup;	
    }	
	
    @Length(min=0, max=1000, message="\u8c03\u7528\u76ee\u6807\u5b57\u7b26\u4e32\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 1000 \u4e2a\u5b57\u7b26")	
    public String getInvokeTarget() {	
        return this.invokeTarget;	
    }	
	
    public void setConcurrent(String concurrent) {	
        this.concurrent = concurrent;	
    }	
	
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")	
    public Date getNextFireTime() {	
        return this.nextFireTime;	
    }	
	
    @Length(min=0, max=255, message="Cron\u6267\u884c\u8868\u8fbe\u5f0f\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc7 255 \u4e2a\u5b57\u7b26")	
    public String getCronExpression() {	
        return this.cronExpression;	
    }	
}	
	
