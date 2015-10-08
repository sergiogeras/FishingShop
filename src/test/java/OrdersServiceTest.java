import fishingshop.domain.goods.Goods;
import fishingshop.domain.order.OrderItem;
import fishingshop.service.GoodsService;
import fishingshop.service.OrderService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class OrdersServiceTest {

    private static ClassPathXmlApplicationContext context;
    private static OrderService orderService;
    private static GoodsService goodsService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = new ClassPathXmlApplicationContext("/spring/application-context.xml");
        orderService=(OrderService)context.getBean("orderService");
        goodsService=(GoodsService) context.getBean("goodsService");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{
        context.close();
    }

    @Ignore
    @Test
    public void addOrder(){

        List<OrderItem> items=new ArrayList<>();

        OrderItem item=new OrderItem();
        item.setGoods(goodsService.getGoodsById(264));
        item.setAmount(2);
        item.setOrderId(1);
        items.add(item);

        OrderItem item2=new OrderItem();
        item2.setGoods(goodsService.getGoodsById(263));
        item2.setAmount(3);
        item2.setOrderId(1);
        items.add(item2);

        orderService.addOrder(items);
        assertNotNull(item.getOrderId());
        assertEquals(1,item.getOrderId());
    }

//    @Ignore
    @Test
    public void deleteOrder(){
//        int beforeValue=orderService.getAllOrders().size();
        orderService.deleteOrder(2);
//        int afterValue=orderService.getAllOrders().size()-1;
//        assertEquals(beforeValue, afterValue);
    }

    @Ignore
    @Test
    public void deleteOrderPositions(){
        int [] positions={7};
        orderService.deleteOrderPositions(positions);
    }

    @Ignore
    @Test
    public void getAllOrders(){
        List<OrderItem> orderItems=orderService.getAllOrders();
        assertNotNull(orderItems);
        assertTrue(orderItems.size()>0);

    }

    @Ignore
    @Test
    public void getOrdersByOrderId(){
        List<OrderItem> orderItems=orderService.getOrdersByOrderId(2);
        assertNotNull(orderItems);
        assertTrue(orderItems.size()>0);
        assertEquals(3, orderItems.size());
    }
}
