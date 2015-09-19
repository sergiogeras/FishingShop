package fishingshop.controller;


import fishingshop.beans.AdminTable;
import fishingshop.domain.goods.Goods;
import fishingshop.domain.goods.Groups;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


@Controller
@Scope("session")
public class TableController implements Serializable {

    private ResourceBundle bundle;
    private TreeNode rootNode;

    @Autowired
    private AdminTable adminTable;

    @Autowired
    private GoodsController goodsController;

    @Autowired
    private GroupsController groupsController;

    public TreeNode root;
    public TreeNode selectedNode;

    @PostConstruct
    public void init(){
        root=adminTable.createTreeTable();
        bundle= ResourceBundle.getBundle("locales.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

    }

    private Map<String, Object> goodsDialogProps(){
        Map<String, Object> props=new HashMap<>();
        props.put("resizable", false);
        props.put("contentWidth", 490);
        props.put("contentHeight", 260);
        return props;
    }

    private Map<String, Object> groupDialogProps(){
        Map<String, Object> props=new HashMap<>();
        props.put("resizable", false);
        props.put("contentWidth", 400);
        props.put("contentHeight", 80);
        return props;
    }

    public void updateTable(String item){
        TreeNode node=new DefaultTreeNode("root", null);
        root=adminTable.createTreeTable();
        if(item.equals("goods")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("added_goods")));
        }
        if(item.equals("group")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("added_group")));
        }
        if(item.equals("update")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("successfully_updated")));
        }
    }



    public void addGoodsDialog(){

        if(selectedNode==null){
            RequestContext.getCurrentInstance().openDialog("addGoods", goodsDialogProps(), null);
        }
        if(selectedNode!=null){
            Groups groups =null;
            RequestContext.getCurrentInstance().openDialog("addGoods",goodsDialogProps(),null);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("goodsController", null);
            if(selectedNode.getData().getClass()==Goods.class){
                if(selectedNode.getParent().getData().equals("root")){
                    RequestContext.getCurrentInstance().openDialog("addGoods",goodsDialogProps(),null);
                } else{
                    groups =(Groups)selectedNode.getParent().getData();
                }
            }
            if(selectedNode.getData().getClass()==Groups.class){
                groups =(Groups)selectedNode.getData();
            }
            FacesContext context=FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("group", groups);
        }
    }




    public void addGroupDialog(){

        Groups groups =null;
        if(selectedNode==null){
            RequestContext.getCurrentInstance().openDialog("addGroup", groupDialogProps(),null);
        }
        if(selectedNode!=null){
            RequestContext.getCurrentInstance().openDialog("addGroup",groupDialogProps(), null);
            if(selectedNode.getData().getClass()==Goods.class){
                if(selectedNode.getParent().getData().equals("root")){
                    RequestContext.getCurrentInstance().openDialog("addGroup",groupDialogProps(),null);
                } else{
                    groups =(Groups)selectedNode.getParent().getData();
                }
            }
            if(selectedNode.getData().getClass()==Groups.class){
                groups =(Groups)selectedNode.getData();
            }
            FacesContext context=FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("group", groups);
            selectedNode=null;
        }
    }

    public void editDialog(){
        if(selectedNode!=null){
            if(selectedNode.getData().getClass()==Goods.class){
                goodsController.editGoodsDialog((Goods)selectedNode.getData());
            }
            if(selectedNode.getData().getClass()==Groups.class){
                groupsController.editGroupDialog((Groups)selectedNode.getData());
            }
        }
    }


    public void deleteItem(){
        if(selectedNode!=null){
            if(selectedNode.getData().getClass()==Goods.class){
                Goods goods;
                goods=(Goods)selectedNode.getData();
                goodsController.deleteGoods(goods.getId());
            }
            if(selectedNode.getData().getClass()==Groups.class){
                Groups groups;
                groups =(Groups)selectedNode.getData();
                groupsController.deleteGroup(groups.getId());

            }
            selectedNode=null;
        }
    }



    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public AdminTable getAdminTable() {
        return adminTable;
    }

    public void setAdminTable(AdminTable adminTable) {
        this.adminTable = adminTable;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }


}
