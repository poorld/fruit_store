package me.teenyda.mvp_template;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

import me.teenyda.mvp_template.common.entity.UserToken;

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
}