package fishingshop.dao.impl;

import fishingshop.dao.GroupsDao;
import fishingshop.domain.goods.Groups;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class GroupsDaoImpl implements GroupsDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addGroups(Groups groups) {
        sessionFactory.getCurrentSession().save(groups);
    }

    @Override
    public void deleteGroups(Integer id) {
        sessionFactory.getCurrentSession().delete(getGroupsById(id));
    }

    @Override
    public void editGroups(Groups groups) {
        sessionFactory.getCurrentSession().update(groups);
    }

    @Override
    public Groups getGroupsById(Integer id) {
        return (Groups)sessionFactory.getCurrentSession().get(Groups.class, id);
    }

    @Override
    public List<Groups> getAllGroups() {
        return sessionFactory.getCurrentSession().createQuery("from Groups").list();
    }
}
