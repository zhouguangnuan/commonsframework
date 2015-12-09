package cn.singno.commonsframework.entity;

import java.util.Date;

public class Appdown {
    private Integer sysno;

    private String channel;

    private Integer downcnt;

    private String resource;

    private Date downdate;

    private Date inserttime;

    private Short devtype;

    public Integer getSysno() {
        return sysno;
    }

    public void setSysno(Integer sysno) {
        this.sysno = sysno;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public Integer getDowncnt() {
        return downcnt;
    }

    public void setDowncnt(Integer downcnt) {
        this.downcnt = downcnt;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource == null ? null : resource.trim();
    }

    public Date getDowndate() {
        return downdate;
    }

    public void setDowndate(Date downdate) {
        this.downdate = downdate;
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public Short getDevtype() {
        return devtype;
    }

    public void setDevtype(Short devtype) {
        this.devtype = devtype;
    }
}