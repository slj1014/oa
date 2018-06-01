package oa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Topic extends Article implements Serializable{
public static final int TYPE_NORAML=0;//普通贴
public static final int TYPE_BEST=1;//精华帖
public static final int TYPE_TOP=2;//置顶帖
private Forum forum;//所属的板块
private Set<Reply> replies=new HashSet<Reply>();//所有的回复
private int type;//帖子的类型，比如是精华帖
private int replyCount;//回复的数量，特殊属性，这样设计可减少查询带来的效率低的问题
private Reply lastReply;//最后回复
private Date lastUpdateTime;//最后回复的时间（主题发表时间或者回复的时间）
public Forum getForum() {
	return forum;
}
public void setForum(Forum forum) {
	this.forum = forum;
}
public Set<Reply> getReplies() {
	return replies;
}
public void setReplies(Set<Reply> replies) {
	this.replies = replies;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public int getReplyCount() {
	return replyCount;
}
public void setReplyCount(int replyCount) {
	this.replyCount = replyCount;
}
public Reply getLastReply() {
	return lastReply;
}
public void setLastReply(Reply lastReply) {
	this.lastReply = lastReply;
}
public Date getLastUpdateTime() {
	return lastUpdateTime;
}
public void setLastUpdateTime(Date lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
}


}
