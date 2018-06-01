package oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import oa.base.dao.impl.BaseDaoImpl;
import oa.domain.Forum;
import oa.service.ForumService;

@SuppressWarnings("unchecked")
@Service("forumService")
public class ForumServiceImpl extends BaseDaoImpl<Forum> implements
		ForumService {

	@Override
	public void save(Forum entity) {
		// TODO Auto-generated method stub
		getSession().save(entity);
		// 因为save后，entity就是持久化对象了
		entity.setPosition(entity.getId().intValue());

	}

	@Override
	public List<Forum> findAll() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Forum f order by f.position asc")
				.list();
	}

	@Override
	public void moveUp(Long id) {
		// TODO Auto-generated method stub
		Forum currForum = getById(id);// 要上移的对象
		Forum privfourm = (Forum) getSession()
				.createQuery(
						"from Forum f where f.position<? order by f.position desc")
				.setFirstResult(0).setMaxResults(1).setParameter(0, currForum.getPosition()).uniqueResult();
		

		if(privfourm==null){
			return;
		}
		int temp = currForum.getPosition();
		currForum.setPosition(privfourm.getPosition());
		privfourm.setPosition(temp);
		// 因为是持久化对象，所以不需要update了
	}

	@Override
	public void moveDown(Long id) {
		// TODO Auto-generated method stub
		Forum currForum = getById(id);// 要下移的对象
		Forum nextFourm = (Forum) getSession()
				.createQuery(
						"from Forum f where f.position>? order by f.position asc")
				.setFirstResult(0).setMaxResults(1).setParameter(0,currForum.getPosition()).uniqueResult();
		if(nextFourm==null){
			return;
		}
		int temp = currForum.getPosition();
		currForum.setPosition(nextFourm.getPosition());
		nextFourm.setPosition(temp);
	}

}
