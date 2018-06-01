package oa.action;

import java.util.Date;

import oa.base.action.BaseAction;
import oa.domain.Reply;
import oa.domain.Topic;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
@Controller("replyAction")
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply>{
	private Long topicId;
	
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public String addUI(){
		Topic topic=topicService.getById(topicId);
	    ActionContext.getContext().put("topic", topic);
		return "addUI";
	}
	/**
	 * 发表回复
	 * @return
	 */
	public String add(){
		//已经封装title，content,faceIcon
		System.out.println(topicId);
	    model.setTopic(topicService.getById(topicId));
	    model.setAuthor(getCurrentUser());
	    model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
	    model.setPostTime(new Date());
	    replyService.save(model);
	    
		return "toTopicShow";//转发到所属主题显示页面
	}
}
