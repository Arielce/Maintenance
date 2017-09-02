package com.richsoft.maintenace.msg.view;


import com.richsoft.maintenace.bean.msg.SearchResult;

import java.util.List;

/**
 * 作者：周麟
 * 邮箱：chengzehao@163.com
 */
public interface SearchMsgsView {

    void onSearchMsgsSuccess(int pageIndex, List<SearchResult> data);

    void onSearchMsgsFail(Exception e);
}
