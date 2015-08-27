package fishingshop.domain.order;

import fishingshop.domain.goods.Goods;
import java.util.List;

/**
 * �������
 */


public class Cart {

    private List<OrderItem> orderItems;
    private int amount;
    private Goods goods;

    public Cart(List<OrderItem> orderItems,int amount, Goods goods) {
        this.orderItems=orderItems;
        this.amount = amount;
        this.goods = goods;
    }

    public void addItem(Goods goods, int amount){
        //TODO ���������� ������ � ��, ������� Cart ��� ������������ ����� � fishingshop.dao ����
    }

    public int getSumm(){
        int result=0;
        for(OrderItem order: orderItems){
            result+=order.getPrice();
        }
        return result;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
