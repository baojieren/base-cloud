package ink.baojie.cloud.tx8205api.bean.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "t_tx")
public class TxPo implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String msg;

    private String createTime;
}
