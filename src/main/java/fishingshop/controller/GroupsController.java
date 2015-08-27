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
import java.util.List;
import java.util.ResourceBundle;



@Controller
@Scope("request")
public class GroupsController {


    private int id;
    private ResourceBundle bundle;

    private List<Groups> groupsList;
    private Groups selectedGroup; //Выбранная группа
    private String name;
    private  String type;
    private Groups group;


    @Autowired
    GroupsService groupsService;

    @Autowired
    GoodsService goodsService;

    public GroupsController(){
        bundle=ResourceBundle.getBundle("locales.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    @PostConstruct
    public void init(){
        groupsList=groupsService.getAllGroups();
    }



    public String addGroup(){
        FacesContext context=FacesContext.getCurrentInstance();
        group=new Groups();
        group.setName(name);
        group.setDescription("- "+bundle.getString("group")+" -");
        group.setType("group");
        group.setParentId((Groups) context.getExternalContext().getSessionMap().get("group"));
        groupsService.addGroups(group);
        RequestContext.getCurrentInstance().closeDialog(group);
        context.getExternalContext().getSessionMap().remove("group");
        group=null;
        return "";
    }

    public String showGroupDetails(int id){

        return "";
    }

    public String deleteGroup(int id) {
        Groups groups=groupsService.getGroupsById(id);
        if(!groups.getChildrenList().isEmpty()){
            for(Groups groups1:groups.getChildrenList()){
                groupsService.deleteGroups(groups1.getId());
            }
        }
        groupsService.deleteGroups(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("deleted_group")));
        return "";
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

    public Groups getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(Groups selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
