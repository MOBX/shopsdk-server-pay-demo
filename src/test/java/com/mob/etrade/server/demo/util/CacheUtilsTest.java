package com.mob.etrade.server.demo.util;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * describe:
 *
 * @author yunkai(xianyi)
 * <p>
 * date 2017/12/26
 */
public class CacheUtilsTest {

    @Test
    public void testPut() throws InterruptedException {
        Map<String, String> params = new HashMap<>();
        params.put("123", "456");
        CacheUtils.put(2000L,"1233", params);
        Assert.assertFalse(CollectionUtils.isEmpty(CacheUtils.get("1233")));
        Thread.sleep(4000L);
        CacheUtils.put(2000L, "2344", params);
        Assert.assertTrue(CollectionUtils.isEmpty(CacheUtils.get("1233")));
    }
}
