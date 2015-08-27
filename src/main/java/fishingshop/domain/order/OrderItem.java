package fishingshop.domain.order;

import fishingshop.domain.goods.Goods;

/**
 * Товар и количество
 */
public class OrderItem {

    private Goods goods;
    private int amount;

    public OrderItem(Goods goods, int amount) {
        this.goods = goods;
        this.amount = amount;
    }

    public int getPrice(){
        return goods.getPrice()*amount;
    }

    public Goods getGoods() {
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
}
