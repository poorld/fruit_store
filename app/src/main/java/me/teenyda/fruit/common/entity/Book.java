package me.teenyda.fruit.common.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * author: teenyda
 * date: 2020/9/5
 * description:
 */

@Data
public class Book {

    @Getter
    @Setter
    String bookName;

    /*public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }*/
}
