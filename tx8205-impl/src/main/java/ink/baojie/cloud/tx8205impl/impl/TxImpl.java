package ink.baojie.cloud.tx8205impl.impl;

import ink.baojie.cloud.tx8205api.bean.po.TxPo;
import ink.baojie.cloud.tx8205api.service.TxService;
import ink.baojie.cloud.tx8205impl.dao.TxDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class TxImpl implements TxService {

    @Resource
    TxDao txDao;

    @Override
    public String saveTx(String msg) {
        TxPo txPo = new TxPo();
        txPo.setMsg(msg);
        txDao.insertSelective(txPo);
        return "处理成功:" + msg;
    }

    @Override
    public TxPo selectById(Integer id) {
        return txDao.selectByPrimaryKey(id);
    }
}
