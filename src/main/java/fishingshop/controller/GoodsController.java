package fishingshop.controller;


import fishingshop.beans.UploadImage;
import fishingshop.domain.goods.Goods;
import fishingshop.domain.goods.Groups;
import fishingshop.service.GoodsService;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
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
    private String name;
    private int price;
    private String manufacturer;
    private String description;
    private byte [] image;
    private String type;
    private Goods goods;

    public String addGoods(){
        FacesContext context=FacesContext.getCurrentInstance();
        goods=new Goods(name, price, manufacturer, description, "goods");
        goods.setGroups((Groups) context.getExternalContext().getSessionMap().get("group"));
        byte[] image;
        image=uploadImage.getImage();
        if(image!=null){
            goods.setImage(image);
        }
        goodsService.addGoods(goods);
        context.getExternalContext().getSessionMap().remove("group");
        RequestContext.getCurrentInstance().closeDialog(0);
        return "";
    }

    public String showGoodsDetails(int id){
        goods=goodsService.getGoodsById(id);
        RequestContext.getCurrentInstance().openDialog("goodsPage");
        return "";
    }

    public String deleteGoods(int id){
        goodsService.deleteGoods(id);
        bundle= ResourceBundle.getBundle("locales.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(bundle.getString("deleted_goods")));
        return "";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}