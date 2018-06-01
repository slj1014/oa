package oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import oa.base.dao.impl.BaseDaoImpl;
import oa.cfg.Configuration;
import oa.domain.Forum;
import oa.domain.PageBean;
import oa.domain.Topic;
import oa.service.TopicService;

@SuppressWarnings("unchecked")
@Service("topicService")
public class TopicServiceImpl extends BaseDaoImpl<Topic> implements
		TopicService {
	/**
	 * 查询指定板块的主题列表 。排序：所有置顶铁都在最前面，把新状态的放在前面
	 */
	@Override
	public List<Topic> findByForim(Forum forum) {
		// TODO Auto-generated method stub
		return getSession()
				.createQuery(
						"from Topic t where t.forum=? order by (case t.type when 2 then 2 else 0 end) desc,t.lastUpdateTime desc")
				.setParameter(0, forum).list();
	}

	@Override
	public void save(Topic topic) {
		// 1.设置属性保存
		// topic.setType(Topic.TYPE_NORAML);//默认是0，可以不写
		// topic.setReplyCount(0);//默认是0
		// topic.setLastReply(null);
		topic.setLastUpdateTime(topic.getPostTime());// 默认是主题发表的时间
		getSession().save(topic);
		// ===============
		// 2.维护相关的信息
		Forum forum = topic.getForum();
		forum.setTopicCount(forum.getTopicCount() + 1);// 主题数
		forum.setArticleCount(forum.getArticleCount() + 1);// 文章的数量
		forum.setLastTopic(topic);// 最后发表的主题
		getSession().update(forum);// 更新

	}

	@Override
	public PageBean getPageBean(int pageNum, Forum forum) {
		// TODO Auto-generated method stub
		int pageSize = Configuration.getPageSize();
		// 查询本页的数据列表
		/**
		 * 分页 frist max 第一页 0 10 第二页 10 10 第三页 20 10
		 */
		List list = getSession()
				.createQuery(
						"from Topic t where t.forum=? order by (case t.type when 2 then 2 else 0 end) desc,t.lastUpdateTime desc")
				.setParameter(0, forum)
				.setFirstResult((pageNum - 1) * pageSize)
				.setMaxResults(pageSize).list();
		// 默认返回的是Long类型的
		Long count = (Long) getSession()
				.createQuery(
						"select count(*) from Topic t where t.forum=?")
				.setParameter(0, forum).uniqueResult();
		return new PageBean(pageNum, pageSize, list, count.intValue());
	}

}
