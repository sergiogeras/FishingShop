
import fishingshop.domain.goods.Goods;
import fishingshop.domain.goods.Groups;
import fishingshop.service.GoodsService;
import fishingshop.service.GroupsService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static junit.framework.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class GoodsServiceTest {


    private static ClassPathXmlApplicationContext context;
    private static GoodsService goodsService;
    private static GroupsService groupsService;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        context=new ClassPathXmlApplicationContext("/spring/application-context.xml");
        goodsService=(GoodsService)context.getBean("goodsService");
        groupsService=(GroupsService)context.getBean("groupsService");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{
        context.close();
    }

    @Ignore
    @Test
    public void addGoods(){
        Goods goods=new Goods();
        goods.setName("Spinning");
        goods.setDescription("Best of the best");
        goods.setPrice(4500);
        goods.setManufacturer("Daiwa");
        goods.setType("goods");
        List<Groups> allGroupses = groupsService.getAllGroups();
        int lastId= allGroupses.get(allGroupses.size()-1).getId();
        goods.setGroups(groupsService.getGroupsById(lastId));
        goodsService.addGoods(goods);
        assertNotNull(goods.getId());
    }

    @Ignore
    @Test
    public void uploadImage(){
        Goods goods;
        List<Goods> allGoods= goodsService.getAllGoods();
        int lastId=allGoods.get(allGoods.size()-1).getId();
        goods=goodsService.getGoodsById(lastId);
        File file=new File("C:\\same_iq.jpg");
        byte [] image=new byte[(int)file.length()];
        try{
            FileInputStream inputStream=new FileInputStream(file);
            inputStream.read(image);
            inputStream.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        goods.setImage(image);
        goodsService.editGoods(goods);
        assertNotNull(goods.getImage());

    }


    @Ignore
    @Test
    public void deleteGoods(){
        List<Goods> allGoods= goodsService.getAllGoods();
        int beforeValue=allGoods.size();
        goodsService.deleteGoods(allGoods.get(beforeValue-1).getId());
        int afterValue=allGoods.size();
        assertEquals(beforeValue, afterValue);
    }

    @Ignore
    @Test
    public void editGoods(){
        Goods goods;
        List<Goods> allGoods= goodsService.getAllGoods();
        int lastId=allGoods.get(allGoods.size()-1).getId();
        goods=goodsService.getGoodsById(lastId);
        goods.setName("Wobbler");
        goodsService.editGoods(goods);
        assertEquals("Wobbler", goods.getName());
    }

    //@Ignore
    @Test
    public void getGoodsById(){
        Goods goods;
        List<Goods> allGoods= goodsService.getAllGoods();
        int lastId=allGoods.get(allGoods.size()-1).getId();
        goods=goodsService.getGoodsById(lastId);
        assertNotNull(goods);
        assertEquals(lastId, goods.getId());
    }

    @Ignore
    @Test
    public void getAllGoods(){
        List<Goods> allGoods= goodsService.getAllGoods();
        assertNotNull(allGoods);
        assertTrue(allGoods.size()>0);
    }


}
