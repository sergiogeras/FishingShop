package fishingshop.controller;


import fishingshop.beans.TreeBuilder;
import fishingshop.beans.UploadImage;
import fishingshop.domain.goods.Goods;
import fishingshop.domain.goods.Groups;
import fishingshop.service.GoodsService;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;

@Controller("goodsController")
@Scope("session")
public class GoodsController implements Serializable{

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UploadImage uploadImage;

    @Autowired
    private TreeBuilder treeBuilder;

    private ResourceBundle bundle;
    private int id;
    private byte [] image;
    private Goods goods;
    private List<Goods> goodsList;
    private int amount;
    private Groups groups;


    private String searchName, article;
    private int priceFrom, priceTo, amountFrom, amountTo;

    public GoodsController(){

    }

    @PostConstruct
    public void init(){
        goods = new Goods();
    }


    public void addGoodsDialog(){

        Map<String, Object> props = new HashMap<>();
        props.put("resizable", false);
        goods = new Goods();
        RequestContext.getCurrentInstance().openDialog("addGoods", props, null);

    }

    public void addGoods(){
        goods.setGroups(groups);
        goods.setGoodsAmount(0);
        byte [] image=uploadImage.getImage();
        if(image != null){
            goods.setImage(image);
        }
        goodsService.addGoods(goods);
        uploadImage.setImage(null);
        RequestContext.getCurrentInstance().closeDialog(0);

    }

    public void editGoodsDialog(Goods goods){
        this.goods = goods;
        Map<String, Object> props = new HashMap<>();
        props.put("resizable", false);
        RequestContext.getCurrentInstance().openDialog("editGoods", props, null);
    }

    public void editGoods(){
        byte [] image;
        image = uploadImage.getImage();
        if (image != null){
            goods.setImage(image);
        }
        goodsService.editGoods(goods);
        uploadImage.setImage(null);
        RequestContext.getCurrentInstance().closeDialog(0);
        goods = new Goods();
    }

    public void showGoodsDetails(int id){
        goods = goodsService.getGoodsById(id);
        RequestContext.getCurrentInstance().openDialog("goodsPage");
    }

    public void deleteGoods(int id){
        goodsService.deleteGoods(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Удалено"));
    }


    public void amountDialog(Goods goods){
        this.goods = goods;
        Map<String, Object> props=new HashMap<>();
        props.put("resizable", false);
        props.put("contentWidth", 420);
        props.put("contentHeight", 90);
        RequestContext.getCurrentInstance().openDialog("changeAmount", props,null);
    }

    public void changeAmount(){
        goods.setGoodsAmount(goods.getGoodsAmount()+amount);
        goodsService.changeGoodsAmount(goods);
        RequestContext.getCurrentInstance().closeDialog(0);
        this.amount = 0;
    }

    public void closeGoodsDialog(){
        RequestContext.getCurrentInstance().closeDialog(0);

    }


    public void launchMainSearch(){
        goodsList = goodsService.getGoodsByCriteria(searchName, article, priceFrom, priceTo, amountFrom, amountTo);
    }

    public void clearSearch(){
        searchName = null;
        article = null;
        priceFrom = 0;
        priceTo = 0;
        amountFrom = 0;
        amountTo = 0;
        goodsList = goodsService.getGoodsByCriteria(searchName, article, priceFrom, priceTo, amountFrom, amountTo);
    }

    public void clearGoods(){
        goods = new Goods();
    }

    public void updateTable(String op){
        goodsList = treeBuilder.getGoodsByCategory(groups, true);
        if(op.equals("add")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Добавлено"));
        }
        if(op.equals("change")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Изменено"));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GoodsService getGoodsService() {
        return goodsService;
    }

    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public Goods getGoods() {
        if(goods == null){
            goods=new Goods();
        }
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public int getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(int priceFrom) {
        this.priceFrom = priceFrom;
    }

    public int getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(int priceTo) {
        this.priceTo = priceTo;
    }

    public int getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(int amountFrom) {
        this.amountFrom = amountFrom;
    }

    public int getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(int amountTo) {
        this.amountTo = amountTo;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
