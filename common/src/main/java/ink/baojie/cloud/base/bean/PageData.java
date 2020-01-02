package ink.baojie.cloud.base.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageData implements Serializable {
    public int total;
    public List list;
}
