package fishingshop.dao.impl;

import fishingshop.dao.GoodsDao;
import fishingshop.domain.goods.Goods;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class GoodsDaoImpl implements GoodsDao{

    @Autowired
    SessionFactory sessionFactory;
    @Override
    public void addGoods(Goods goods) {
        sessionFactory.getCurrentSession().save(goods);
    }

    @Override
    public void deleteGoods(Integer id) {
        sessionFactory.getCurrentSession().delete(getGoodsById(id));
    }

    @Override
    public void editGoods(Goods goods) {
        sessionFactory.getCurrentSession().update(goods);
    }

    @Override
    public Goods getGoodsById(Integer id) {
        return (Goods)sessionFactory.getCurrentSession().get(Goods.class, id);
    }

    @Override
    public List<Goods> getAllGoods() {
        return sessionFactory.getCurrentSession().createQuery("from Goods").list();
    }

    @Override
    public List<Goods> getGoodsByGroup(Integer id) {
       //return (List<Goods>)sessionFactory.getCurrentSession().createSQLQuery("SELECT FROM goods WHERE GROUP_ID=:id").setParameter("id", id);
        return sessionFactory.getCurrentSession().createQuery("from Goods where id=:id").setParameter("id", id).list();
    }
}