package me.teenyda.fruit;

import com.alibaba.fastjson.JSON;


import org.junit.Test;

import me.teenyda.fruit.common.entity.UserToken;
import me.teenyda.fruit.common.utils.ToolUtils;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void test1() {
        UserToken userToken = new UserToken();
        userToken.setUserName("teenyda");
        userToken.setToken("fjdaskfjadks;fjadklsfjadisfweqwf");
        String json = JSON.toJSONString(userToken);
        System.out.println(json);
    }

    @Test
    public void test2() {
        double mul = ToolUtils.mul(2, 25.0d);
        System.out.println(mul);

        double sub = ToolUtils.sub(mul, 8.0f);
        System.out.println(sub);
    }
}