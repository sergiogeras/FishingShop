package fishingshop.controller;


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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
@Scope("session")
public class GoodsController implements Serializable{

    @Autowired
    GoodsService goodsService;

    @Autowired
    UploadImage uploadImage;

    private ResourceBundle bundle;
    private int id;
    private byte [] image;
    private Goods goods;
    private List<Goods> goodsList;
    private int amount;

    @PostConstruct
    public void init(){
        goodsList=goodsService.getAllGoods();
        goods=new Goods();
    }

    public void addGoods(){
        FacesContext context=FacesContext.getCurrentInstance();
        goods.setType("goods");
        goods.setGroups((Groups) context.getExternalContext().getSessionMap().get("group"));
        goods.setGoodsAmount(0);
        byte[] image=uploadImage.getImage();
        if(image!=null){
            goods.setImage(image);
        }
        goodsService.addGoods(goods);
        uploadImage.setImage(null);
        context.getExternalContext().getSessionMap().remove("group");
        RequestContext.getCurrentInstance().closeDialog(0);
        goods=null;
    }

    public void editGoodsDialog(Goods goods){
        this.goods=goods;
        Map<String, Object> props=new HashMap<>();
        props.put("resizable", false);
        props.put("contentWidth", 490);
        props.put("contentHeight", 295);
        RequestContext.getCurrentInstance().openDialog("editGoods", props, null);
    }

    public void editGoods(){
        byte[] image;
        image=uploadImage.getImage();
        if(image!=null){
            goods.setImage(image);
        }
        goodsService.editGoods(goods);
        uploadImage.setImage(null);
        RequestContext.getCurrentInstance().closeDialog(goods);
    }

    public void showGoodsDetails(int id){
        goods=goodsService.getGoodsById(id);
        RequestContext.getCurrentInstance().openDialog("goodsPage");
    }

    public void deleteGoods(int id){
        goodsService.deleteGoods(id);
        bundle= ResourceBundle.getBundle("locales.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("deleted_goods")));
    }

    public void changeAmount(){
        FacesContext context=FacesContext.getCurrentInstance();
        Goods goods=(Goods)context.getExternalContext().getSessionMap().get("goods");
        goods.setGoodsAmount(goods.getGoodsAmount()+amount);
        goodsService.changeGoodsAmount(goods);
        RequestContext.getCurrentInstance().closeDialog(goods);
        this.amount=0;
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
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
