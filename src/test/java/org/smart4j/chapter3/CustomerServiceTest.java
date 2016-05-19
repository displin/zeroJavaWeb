package org.smart4j.chapter3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smart4j.chapter3.model.Customer;
import org.smart4j.chapter3.service.CustomerService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户服务的测试类
 *
 * Created by lin on 2016/4/21.
 */
public class CustomerServiceTest {
    private final CustomerService customerService;

    public CustomerServiceTest() {
        this.customerService = new CustomerService();
    }

    /**
     * 初始化数据库
     */
    @Before
    public void init() {

    }

    /**
     * 测试获取客户列表的函数
     * @throws IOException
     */
    @Test
    public void getCustomerListTest() throws IOException {
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2, customerList.size());
    }

    /**
     * 测试获取指定客户的函数
     * @throws IOException
     */
    @Test
    public void getCustomerTest() throws IOException {
        long id = 2;
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

    /**
     * 测试创建客户的函数
     * @throws IOException
     */
    @Test
    public void createCustomerTest() throws IOException {
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("name", "customer100");
        fieldMap.put("contact", "John");
        fieldMap.put("telephone", "13012345678");

        boolean result = customerService.createCustomer(fieldMap);
        Assert.assertTrue(result);
    }

    /**
     * 测试更新客户的函数
     * @throws IOException
     */
    @Test
    public void updateCustomerTest() throws IOException {
        long id = 2;
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("contact", "Tom");

        boolean result = customerService.updateCustomer(id, fieldMap);
        Assert.assertTrue(result);
    }

    /**
     * 测试删除客户的函数
     * @throws IOException
     */
    @Test
    public void deleteCustomerTest() throws IOException {
        long id = 1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }
}
