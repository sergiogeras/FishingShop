package fishingshop.controller;

import fishingshop.beans.TreeBuilder;
import fishingshop.domain.goods.Goods;
import fishingshop.domain.goods.Groups;
import fishingshop.service.GoodsService;
import fishingshop.service.GroupsService;
import org.primefaces.context.RequestContext;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;


@Controller
@Scope("session")
public class GroupsController implements Serializable {


    private int id;
    private ResourceBundle bundle;
    private List<Groups> groupsList;
    private Groups selectedGroups; //Выбранная группа
    private Groups groups;
    private Groups parentGroup;
    public TreeNode root;
    public TreeNode selectedNode;
    private List<Goods> goodsList;

    @Autowired
    GroupsService groupsService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    private TreeBuilder treeBuilder;

    @Autowired
    private GoodsController goodsController;

    public GroupsController(){
        bundle=ResourceBundle.getBundle("locales.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

    }

    @PostConstruct
    public void init(){
        root= treeBuilder.createTreeTable();
        groupsList =groupsService.getAllGroups();
        goodsList=new ArrayList<>();
        groups=new Groups();
    }

    /** Shows content of current group */
    public void chooseCategory(){
        groups=(Groups)selectedNode.getData();
        goodsList.clear();
        goodsController.setGoodsList(treeBuilder.getGoodsByCategory(groups, true));
        goodsController.setGroups(groups);
    }


    public void addGroupDialog(){

        Map<String, Object> props=new HashMap<>();
        props.put("resizable", false);
        props.put("contentWidth", 420);
        props.put("contentHeight", 100);

        groups =new Groups();
        if(selectedNode==null){
            RequestContext.getCurrentInstance().openDialog("addGroup", props,null);
        }
        if(selectedNode!=null){
            RequestContext.getCurrentInstance().openDialog("addGroup", props, null);
            parentGroup =(Groups)selectedNode.getData();
        }
    }

    public void addGroup(){
        groups.setParentId(parentGroup);
        groupsService.addGroups(groups);
        selectedNode=null;
        RequestContext.getCurrentInstance().closeDialog(0);
    }

    public void editGroupDialog(){
        groups=(Groups)selectedNode.getData();
        Map<String, Object> props=new HashMap<>();
        props.put("resizable", false);
        props.put("contentWidth", 420);
        props.put("contentHeight", 100);
        RequestContext.getCurrentInstance().openDialog("editGroup",props,null);
    }

    public void editGroup(){
        groupsService.editGroups(groups);
        RequestContext.getCurrentInstance().closeDialog(0);
        selectedNode=null;
        groups=new Groups();
    }


    public void deleteGroup() {
        if(selectedNode!=null){
            Groups groups=(Groups)selectedNode.getData();
            int id=groups.getId();
            groups =groupsService.getGroupsById(id);
            if(!groups.getChildrenList().isEmpty()){
                for(Groups groups1 : groups.getChildrenList()){
                    groupsService.deleteGroups(groups1.getId());
                }
            }
            groupsService.deleteGroups(id);
            selectedNode=null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("deleted_group")));
        }

    }

    public void closeGroupDialog(){
        RequestContext.getCurrentInstance().closeDialog(0);
    }

    public void updateTree(){
        root = treeBuilder.createTreeTable();
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
        if(groups==null){
            groups=new Groups();
        }
        return groups;
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
}
