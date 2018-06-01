package oa.service.impl;

import java.util.List;

import oa.base.dao.impl.BaseDaoImpl;
import oa.cfg.Configuration;
import oa.domain.Forum;
import oa.domain.PageBean;
import oa.domain.Reply;
import oa.domain.Topic;
import oa.service.ReplyService;

import org.springframework.stereotype.Service;

@Service("replyService")
public class ReplyServiceImpl extends BaseDaoImpl<Reply> implements
		ReplyService {
	@Override
	public void save(Reply entity) {
		// 保存到数据库
		getSession().save(entity);
		// 维护信息
		Topic topic = entity.getTopic();
		Forum forum = topic.getForum();
		forum.setArticleCount(forum.getArticleCount() + 1);// 板块的文章数
		topic.setReplyCount(topic.getReplyCount() + 1);// 主题的文章数
		topic.setLastReply(entity);// 最后的回复数
		topic.setLastUpdateTime(entity.getPostTime());// 最后的回复数
		getSession().update(forum);
		getSession().update(topic);
	}

	/**
	 * 查询指主题中所有的回复列表，排序:最早的在前面
	 */
	/*
	 * @Override public List<Reply> findTopic(Topic topic) { // TODO
	 * Auto-generated method stub return getSession().createQuery(
	 * "from Reply r where r.topic=? order by r.postTime asc").setParameter(0,
	 * topic).list(); }
	 */
	/*@Override*/
/*	public PageBean getPageBean(int pageNum, Topic topic) {
		// TODO Auto-generated method stub
		int pageSize = Configuration.getPageSize();
		// 查询本页的数据列表
		*//**
		 * 分页 frist max 第一页 0 10 第二页 10 10 第三页 20 10
		 *//*
		List list = getSession()
				.createQuery(
						"from Reply r where r.topic=? order by r.postTime asc")
				.setParameter(0, topic)
				.setFirstResult((pageNum - 1) * pageSize)
				.setMaxResults(pageSize).list();
		// 默认返回的是Long类型的
		Long count = (Long) getSession()
				.createQuery(
						"select count(*) from Reply r where r.topic=?")
				.setParameter(0, topic).uniqueResult();
		return new PageBean(pageNum, pageSize, list, count.intValue());
	}
*/
}
