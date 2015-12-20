package fishingshop.dao.impl;

import fishingshop.dao.GoodsDao;
import fishingshop.domain.goods.Goods;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
        return sessionFactory.getCurrentSession().createQuery("from Goods where groups.id=:id").setParameter("id", id).list();
    }

    @Override
    public void changeGoodsAmount(Goods goods) {
        sessionFactory.getCurrentSession().update(goods);
    }

    @Override
    public List<Goods> getGoodsViaMainSearch(String searchStr) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Goods where name like :searchStr or manufacturer like :searchStr " +
                        "or description like :searchStr").setParameter("searchStr", "%"+searchStr+"%").list();
    }

    @Override
    public List<Goods> getGoodsByCriteria(String name, String article,  int priceFrom, int priceTo, int amountFrom, int amountTo) {
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Goods.class);
        if(name!=null){
            criteria.add(Restrictions.like("name", "%"+name+"%"));
        }
        if(article!=null){
            criteria.add(Restrictions.like("article", "%"+article+"%"));
        }
        if(priceFrom !=0 ){
            criteria.add((Restrictions.ge("price", priceFrom)));
        }
        if(priceTo !=0 ){
            criteria.add((Restrictions.le("price", priceTo)));
        }
        if(amountFrom !=0 ){
            criteria.add((Restrictions.ge("goodsAmount", amountFrom)));
        }
        if(amountTo !=0 ){
            criteria.add((Restrictions.le("goodsAmount", amountTo)));
        }
        return criteria.list();
    }
}
