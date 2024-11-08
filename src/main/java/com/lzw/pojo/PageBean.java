
package com.lzw.pojo;

import java.util.List;

public class PageBean<T> {
    private int totalCount;
    private List<T> rows;

    public PageBean() {
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
