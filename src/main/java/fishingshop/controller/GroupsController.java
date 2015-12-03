package fishingshop.controller;

import fishingshop.domain.goods.Groups;
import fishingshop.service.GoodsService;
import fishingshop.service.GroupsService;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;



@Controller
@Scope("session")
public class GroupsController implements Serializable {


    private int id;
    private ResourceBundle bundle;
    private List<Groups> groupsList;
    private Groups selectedGroups; //Выбранная группа
    private Groups groups;


    @Autowired
    GroupsService groupsService;

    @Autowired
    GoodsService goodsService;

    public GroupsController(){
        bundle=ResourceBundle.getBundle("locales.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

    }

    @PostConstruct
    public void init(){
        groupsList =groupsService.getAllGroups();
        groups=new Groups();
    }



    public void addGroup(){

        FacesContext context=FacesContext.getCurrentInstance();
        groups.setDescription("- "+bundle.getString("group")+" -");
        groups.setType("group");
        groups.setParentId((Groups) context.getExternalContext().getSessionMap().get("group"));
        groupsService.addGroups(groups);
        RequestContext.getCurrentInstance().closeDialog(0);
        context.getExternalContext().getSessionMap().remove("group");

    }

    public void editGroupDialog(Groups groups){
        this.groups=groups;
        Map<String, Object> props=new HashMap<>();
        props.put("resizable", false);
        props.put("contentWidth", 420);
        props.put("contentHeight", 90);
        RequestContext.getCurrentInstance().openDialog("editGroup",props,null);
    }

    public void editGroup(){
        groupsService.editGroups(groups);
        RequestContext.getCurrentInstance().closeDialog(groups);
        groups=new Groups();
    }


    public void deleteGroup(int id) {
        Groups groups =groupsService.getGroupsById(id);
        if(!groups.getChildrenList().isEmpty()){
            for(Groups groups1 : groups.getChildrenList()){
                groupsService.deleteGroups(groups1.getId());
            }
        }
        groupsService.deleteGroups(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("deleted_group")));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GroupsService getGroupsService() {
        return groupsService;
    }

    public void setGroupsService(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    public List<Groups> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
    }

    public Groups getSelectedGroups() {
        return selectedGroups;
    }

    public void setSelectedGroups(Groups selectedGroups) {
        this.selectedGroups = selectedGroups;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
}
