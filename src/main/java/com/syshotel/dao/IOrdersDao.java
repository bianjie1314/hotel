package com.syshotel.dao;

import com.syshotel.pojo.OrdersPojo;

import java.util.List;
import java.util.Map;

public interface IOrdersDao {

    /**
     * 添加信息
     * @param ordersPojo
     * @return
     */
    public int addBean(OrdersPojo ordersPojo);

    /**
     * 统计数量
     * @param id
     * @return
     */
    public Map<String,Integer> countOrderStatus(int id);

    /**
     * 通过id删除
     * @param id
     */
    public void deleteById(int id);

    /**
     * 更新
     * @param ordersPojo
     */
    public void updateBean(OrdersPojo ordersPojo);

    /**
     * 查询满足条件的信息
     * @param paramMap
     * @return
     */
    public List<OrdersPojo> selectOrdersList(Map<String, Object> paramMap);

    /**
     * 查询满足条件的信息
     * @param paramMap
     * @return
     */
    public List<OrdersPojo> selectOrdersDetailList(Map<String, Object> paramMap);

    /**
     * 通过id获取
     * @param id
     * @return
     */
    public OrdersPojo getById(int id);

    /**
     * 更新
     * @param map
     */
    public void updateByMap(Map<String, Object> map);

    /**
     * 统计满足条件的数目
     * @param queryMap
     * @return
     */
    public int countOrders(Map<String, Object> queryMap);

    /**
     * 统计需要支付的费用
     * @param map
     * @return
     */
    public double countNeedPay(Map<String,Object> map);
}
