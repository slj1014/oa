package oa.action;

import java.util.Date;

import oa.base.action.BaseAction;
import oa.domain.Forum;
import oa.domain.Reply;
import oa.domain.Topic;
import oa.utils.HqlHelper;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller("topicAction")
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {
	private Long forumId;

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	/**
	 * 显示当个主题(主贴+回帖的列表)
	 * 
	 * @return
	 */
	public String show() {
		//准备数据topic：
		Topic topic=topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		//根据数据：replyList
/*		List<Reply> replyList=replyService.findTopic(topic);
		ActionContext.getContext().put("replyList", replyList);*/
		//准备数据：回复列表的分页信息
	/*	PageBean pageBean=replyService.getPageBean(pageNum,topic);
		ActionContext.getContext().getValueStack().push(pageBean);*/
		new HqlHelper(Reply.class, "r")//
		.addCondition("r.topic=?", topic)//
		.addOrder("r.postTime", true)//
		.buildPageBeanForStruts2(pageNum, replyService);

		return "show";
	}

	/**
	 * 发表新主题的页面
	 * 
	 * @return
	 */
	public String addUI() {
		// 准备数据
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().getValueStack().push(forum);
		return "addUI";
	}

	/**
	 * 添加新主题
	 * 
	 * @return
	 */
	public String add() {
		// 封装
		// >>表单中的字段，已经封装了title,content,faceIcon
		model.setForum(forumService.getById(forumId));
		// >>当前可以直接获取的信息
		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		// 放在业务方法中的其他设置
		/*
		 * model.setType(type); model.setReplyCount(replyCount);
		 * model.setLastReply(lastReply);
		 * model.setLastUpdateTime(lastUpdateTime);
		 */
		topicService.save(model);
		return "toShow";// 转发到新主题显示的页面
	}
}
