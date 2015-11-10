package fishingshop.controller;

import fishingshop.beans.CatalogTree;
import fishingshop.domain.goods.Goods;
import fishingshop.domain.goods.Groups;
import fishingshop.service.GoodsService;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Сергей on 02.11.2015.
 */

@Controller
@Scope("session")
public class CatalogController implements Serializable {

    private Groups groups;
    private TreeNode rootNode;
    private List<Goods> goodsList;
    public TreeNode root;
    public TreeNode selectedNode;
    private String searchStr;
    private List<Goods> searchResult;
    private boolean searchMode=false;

    @Autowired
    private CatalogTree catalogTree;

    @Autowired
    private GoodsService goodsService;

    @PostConstruct
    public void init(){
        root=catalogTree.createTreeTable();
        goodsList=new ArrayList<>();

        //Create goods list on the start page (random, without duplicates)
        //TODO: List depends on goods rating
        List<Goods> list=goodsService.getAllGoods();
        for(int i=0; i<9;i++ ){
            Goods goods=list.get((int)Math.round(Math.random() * goodsList.size()));
            if(goodsList.contains(goods)){
                i--;
                continue;
            }
            goodsList.add(goods);
        }
    }

    /**
     * Shows content of current group
     */
    public void chooseCategory(){
        searchMode=false;
        searchStr="";
        groups=(Groups)selectedNode.getData();
        goodsList.clear();
        goodsList=getGoodsByCategory(groups);

    }

    /**
     * Build group`s tree with subgroups
     */
    private List<Goods> getGoodsByCategory(Groups groups){
        goodsList.addAll(groups.getGoodsList());
        for (Groups gr: groups.getChildrenList()) {
            getGoodsByCategory(gr);
        }
        return goodsList;
    }


    /**
     * Search %searchStr% by name, manufacturer and description
     */
    public void mainSearch(){
        if(!searchStr.equals("")){
            searchResult=goodsService.getGoodsViaMainSearch(searchStr);
            Set<Goods> set=new TreeSet<>(searchResult); //Sort and delete duplicates
            searchResult.clear();
            searchResult.addAll(set);
            searchMode=true;
        }
    }


    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public List<Goods> getGoodsList() {
        return searchMode ? searchResult : goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public boolean isSearchMode() {
        return searchMode;
    }

    public void setSearchMode(boolean searchMode) {
        this.searchMode = searchMode;
    }


}
