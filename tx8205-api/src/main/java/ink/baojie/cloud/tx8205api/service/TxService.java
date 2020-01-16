package ink.baojie.cloud.tx8205api.service;

import ink.baojie.cloud.tx8205api.bean.po.TxPo;

/**
 * @author renbaojie
 */
public interface TxService {

    String saveTx(String msg);

    TxPo selectById(Integer id);
}
