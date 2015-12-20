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

import java.util.ArrayList;
import java.util.List;

/**
 * Class for building tree of groups
 */

@Component
@Scope("session")
public class TreeBuilder {

    private List<Goods> goodsList = new ArrayList<>();

    @Autowired
    private GroupsService groupsService;

    @Autowired
    private GoodsService goodsService;

    public TreeNode createTreeTable(){
        TreeNode root=new DefaultTreeNode("root", null);
        for(Groups groups : groupsService.getAllGroups()){
            if(groups.getParentId()==null){
                TreeNode child=new DefaultTreeNode(groups,root);
                createChildrenNodes(child, groups);
            }
        }
        return root;
    }

    public TreeNode createChildrenNodes(TreeNode root, Groups groups){
        for(Groups gr: groups.getChildrenList()){
            TreeNode child=new DefaultTreeNode(gr, root);
            createChildrenNodes(child, gr);
        }
        return root;
    }

    public List<Goods> getGoodsByCategory(Groups groups, boolean notEmptyList){
        if(notEmptyList){
            goodsList.clear();
            notEmptyList=false;
        }
        goodsList.addAll(goodsService.getGoodsByGroup(groups.getId()));
        for (Groups gr: groups.getChildrenList()) {
            getGoodsByCategory(gr, notEmptyList);
        }
        return goodsList;
    }
}
