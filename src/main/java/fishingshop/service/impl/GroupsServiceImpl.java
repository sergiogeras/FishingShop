package fishingshop.service.impl;

import fishingshop.dao.GroupsDao;
import fishingshop.domain.goods.Groups;
import fishingshop.service.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("groupsService")
@Transactional
public class GroupsServiceImpl implements GroupsService {

    @Autowired
    GroupsDao groupsDao;

    @Override
    public void addGroups(Groups groups) {
        groupsDao.addGroups(groups);
    }

    @Override
    public void deleteGroups(Integer id) {
        groupsDao.deleteGroups(id);
    }

    @Override
    public void editGroups(Groups groups) {
        groupsDao.editGroups(groups);
    }

    @Override
    public Groups getGroupsById(Integer id) {
        return groupsDao.getGroupsById(id);
    }

    @Override
    public List<Groups> getAllGroups() {
        return groupsDao.getAllGroups();
    }
}
