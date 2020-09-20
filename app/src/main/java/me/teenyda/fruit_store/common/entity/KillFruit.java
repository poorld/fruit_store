package me.teenyda.fruit_store.common.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * author: teenyda
 * date: 2020/9/20
 * description: 限时秒杀
 */

@Data
@AllArgsConstructor
public class KillFruit {

    private String fruitName;

    private long endTime;
}
