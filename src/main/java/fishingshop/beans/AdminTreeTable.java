package fishingshop.beans;

import fishingshop.domain.goods.Goods;
import fishingshop.domain.goods.Groups;
import fishingshop.service.GoodsService;
import fishingshop.service.GroupsService;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class AdminTreeTable {

    @Autowired
    private GroupsService groupsService;

    @Autowired
    private GoodsService goodsService;

    private TreeNode root;

    public TreeNode createTreeTable(){
        TreeNode root=new DefaultTreeNode("root", null);
        for(Groups groups : groupsService.getAllGroups()){
            if(groups.getParentId()==null){
                TreeNode child=new DefaultTreeNode(groups,root);
                createChildrenNodes(child, groups);
                for(Goods goods: groups.getGoodsList()){
                    TreeNode childGoods= new DefaultTreeNode(goods, child);
                }
            }
        }
        for(Goods goods: goodsService.getAllGoods()){
            if(goods.getGroups()==null){
                TreeNode child=new DefaultTreeNode(goods,root);
            }
        }
        return root;
    }

    public TreeNode createChildrenNodes(TreeNode root, Groups groups){
        for(Groups gr: groups.getChildrenList()){
            TreeNode child=new DefaultTreeNode(gr, root);
            for(Goods goods: gr.getGoodsList()){
                TreeNode childGoods= new DefaultTreeNode(goods, child);
            }
            createChildrenNodes(child, gr);
        }
       return root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}