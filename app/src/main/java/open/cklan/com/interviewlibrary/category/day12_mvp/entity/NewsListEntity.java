package open.cklan.com.interviewlibrary.category.day12_mvp.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * AUTHOR：lanchuanke on 17/5/7 18:03
 */
public class NewsListEntity<T> implements Serializable {
    @JSONField(name = "美女")
    public List<T> entities;

}
