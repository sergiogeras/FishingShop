package fishingshop.service;

import fishingshop.domain.goods.Groups;

import java.util.List;

/**
 * Created by Сергей on 04.08.2015.
 */
public interface GroupsService {
    void addGroups(Groups groups);
    void deleteGroups(Integer id);
    void editGroups(Groups groups);
    Groups getGroupsById(Integer id);
    List<Groups> getAllGroups();
}
