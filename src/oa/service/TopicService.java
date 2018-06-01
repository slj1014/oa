package oa.service;

import java.util.List;

import oa.base.dao.BaseDao;
import oa.domain.Forum;
import oa.domain.PageBean;
import oa.domain.Topic;

public interface TopicService extends BaseDao<Topic> {

	List<Topic> findByForim(Forum forum);

	PageBean getPageBean(int pageNum, Forum forum);

}
