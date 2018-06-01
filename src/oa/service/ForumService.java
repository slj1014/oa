package oa.service;

import oa.base.dao.BaseDao;
import oa.domain.Forum;

public interface ForumService extends BaseDao<Forum>{

	void moveUp(Long id);

	void moveDown(Long id);


}
