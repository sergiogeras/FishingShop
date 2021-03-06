import fishingshop.domain.goods.Groups;
import fishingshop.service.GoodsService;
import fishingshop.service.GroupsService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static junit.framework.Assert.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class GroupsServiceTest {

    private static ClassPathXmlApplicationContext context;
    private static GroupsService groupsService;
    private static GoodsService goodsService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        context=new ClassPathXmlApplicationContext("/spring/application-context.xml");
        groupsService=(GroupsService)context.getBean("groupsService");
        goodsService=(GoodsService)context.getBean("goodsService");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{
        context.close();
    }

    @Test
    public void addGroups(){
        Groups groups =new Groups();
        groups.setName("Item");
        groups.setGoodsList(goodsService.getAllGoods());
        groupsService.addGroups(groups);
        assertNotNull(groups.getId());
    }

    @Test
    public void deleteGroups(){
        List<Groups> allGroupses = groupsService.getAllGroups();
        int beforeValue= allGroupses.size();
        groupsService.deleteGroups(allGroupses.get(beforeValue - 1).getId());
        int afterValue= allGroupses.size();
        assertEquals(beforeValue, afterValue);
    }


    @Test
    public void editGroups(){
        Groups groups;
        List<Groups> allGroupses = groupsService.getAllGroups();
        int lastId= allGroupses.get(allGroupses.size()-1).getId();
        groups =groupsService.getGroupsById(lastId);
        groups.setName("Hooks");
        groupsService.editGroups(groups);
        assertEquals("Hooks", groups.getName());
    }


    @Test
    public void getGoodsById(){
        Groups groups;
        List<Groups> allGroupses = groupsService.getAllGroups();
        int lastId= allGroupses.get(allGroupses.size()-1).getId();
        groups =groupsService.getGroupsById(lastId);
        assertNotNull(groups);
        assertEquals(lastId, groups.getId());
    }


    @Test
    public void getAllGoods(){
        List<Groups> allGroupses = groupsService.getAllGroups();
        assertNotNull(allGroupses);
        assertTrue(allGroupses.size() > 0);
    }

}
