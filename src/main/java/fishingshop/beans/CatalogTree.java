package fishingshop.beans;

import fishingshop.domain.goods.Groups;
import fishingshop.service.GroupsService;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Class for building tree of groups
 */

@Component
@Scope("session")
public class CatalogTree {

    @Autowired
    private GroupsService groupsService;


    private TreeNode root;

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

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
