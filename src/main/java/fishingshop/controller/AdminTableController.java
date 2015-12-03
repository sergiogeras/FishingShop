package fishingshop.controller;


import fishingshop.beans.AdminTreeTable;
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
public class AdminTableController implements Serializable {

    private ResourceBundle bundle;

    @Autowired
    private AdminTreeTable adminTreeTable;

    @Autowired
    private GoodsController goodsController;

    @Autowired
    private GroupsController groupsController;

    public TreeNode root;
    public TreeNode selectedNode;

    @PostConstruct
    public void init(){
        root= adminTreeTable.createTreeTable();
        bundle= ResourceBundle.getBundle("locales.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

    }

    public void updateTable(String item){
        TreeNode node=new DefaultTreeNode("root", null);
        root= adminTreeTable.createTreeTable();
        if(item.equals("goods")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("added_goods")));
            goodsController.setGoods(null);
        }
        if(item.equals("group")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("added_group")));
            groupsController.setGroups(null);
        }
        if(item.equals("update")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("successfully_updated")));
        }
    }



    public void addGoodsDialog(){

        Map<String, Object> props=new HashMap<>();
//        props.put("resizable", false);
//        props.put("contentWidth", 490);
//        props.put("contentHeight", 295);

        if(selectedNode==null){
            RequestContext.getCurrentInstance().openDialog("addGoods", props, null);
        }
        if(selectedNode!=null){
            Groups groups =null;
            RequestContext.getCurrentInstance().openDialog("addGoods",props,null);
            if(selectedNode.getData().getClass()==Goods.class){
                if(selectedNode.getParent().getData().equals("root")){
                    RequestContext.getCurrentInstance().openDialog("addGoods",props,null);
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




    public void addGroupDialog(){

        Map<String, Object> props=new HashMap<>();
        props.put("resizable", false);
        props.put("contentWidth", 420);
        props.put("contentHeight", 90);

        Groups groups =null;
        if(selectedNode==null){
            RequestContext.getCurrentInstance().openDialog("addGroup", props,null);
        }
        if(selectedNode!=null){
            RequestContext.getCurrentInstance().openDialog("addGroup", props, null);
            if(selectedNode.getData().getClass()==Goods.class){
                if(selectedNode.getParent().getData().equals("root")){
                    RequestContext.getCurrentInstance().openDialog("addGroup", props,null);
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
            selectedNode=null;
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


    public void amountDialog(){
        if(selectedNode!=null){
            if(selectedNode.getData().getClass()==Goods.class){
                Map<String, Object> props=new HashMap<>();
                props.put("resizable", false);
                props.put("contentWidth", 420);
                props.put("contentHeight", 90);
                RequestContext.getCurrentInstance().openDialog("changeAmount", props,null);
                Goods goods=(Goods) selectedNode.getData();
                FacesContext context=FacesContext.getCurrentInstance();
                context.getExternalContext().getSessionMap().put("goods", goods);
                selectedNode=null;
            }

        }

    }




    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }


}
