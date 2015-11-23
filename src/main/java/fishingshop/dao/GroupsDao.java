package fishingshop.dao;

import fishingshop.domain.goods.Groups;

import java.util.List;


public interface GroupsDao {
    void addGroups(Groups groups);
    void deleteGroups(Integer id);
    void editGroups(Groups groups);
    Groups getGroupsById(Integer id);
    List<Groups> getAllGroups();

}
