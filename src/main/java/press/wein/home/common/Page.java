package press.wein.home.common;

import java.util.Collections;
import java.util.List;

/**
 * 分页类
 *
 * @param <T>
 * @author oukailiang
 * @create 2017-02-16 下午5:03
 */

public class Page<T> {

    private List<T> resultList = Collections.emptyList();
    public static final String DIRECTION_DESC = "DESC";
    public static final String DIRECTION_ASC = "ASC";
    private boolean success = true;

    /**
     * 当前页，真实页数，取值：1、2、3....
     */
    private int curPage = 1;

    /**
     * 记录开始的rowNum，从零开始
     */
    private int start = 0;

    /**
     * 每页显示数量limit
     */
    private int pageSize = 20;

    /**
     * 排序asc,desc
     */
    private String sort;

    /**
     * 排序字段
     */
    private String sidx;

    private boolean needCount;

    private long totalCount;

    public Page() {
    }

    public Page(long totalCount, List<T> resultList) {
        this.resultList = resultList;
        this.totalCount = totalCount;
    }

    public final long getTotalCount() {
        return totalCount;
    }

    public final void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public final void calStart() {
        if (start >= totalCount) {
            start = ((int) ((totalCount - 1) / pageSize)) * pageSize;
        }
    }

    public final int getPgNumber() {
        return start / pageSize + 1;
    }

    public final int getEnd() {
        return this.start + this.pageSize;
    }

    public final int getStart() {
        return start;
    }

    public final void setStart(int start) {
        this.start = start;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1)
            throw new IllegalArgumentException("每页显示数量小于1");
        this.pageSize = pageSize;
        this.start = (curPage - 1) * pageSize;
    }

    public final boolean isNeedCount() {
        return needCount;
    }

    public final void setNeedCount(boolean needCount) {
        this.needCount = needCount;
    }

    public final String getSort() {
        return sort;
    }

    public final void setSort(String sort) {
        if (DIRECTION_ASC.equalsIgnoreCase(sort)) {
            this.sort = DIRECTION_ASC;
        } else if (DIRECTION_DESC.equalsIgnoreCase(sort)) {
            this.sort = DIRECTION_DESC;
        } else {
            this.sort = null;
        }
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        if (sidx != null && sidx.matches("^\\w+$")) {
            this.sidx = sidx;
        }
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        if (curPage < 1)
            throw new IllegalArgumentException("页数小于1");
        this.curPage = curPage;
        this.start = (curPage - 1) * pageSize;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    /**
     * 获取到总页数
     */
    public int getTotalPage() {
        if (totalCount <= pageSize) {
            return 1;
        } else {
            return ((int) (totalCount % pageSize)) != 0 ? ((int) (totalCount / pageSize)) + 1
                    : ((int) (totalCount / pageSize));
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
