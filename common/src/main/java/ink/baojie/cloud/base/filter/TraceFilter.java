package ink.baojie.cloud.base.filter;

import ink.baojie.cloud.util.TraceIdUtil;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.springframework.util.StringUtils;

/**
 * 不论是接收还是发起一次rpc请求, 都会经过该过滤器, 并携带traceId 用于调用链追踪
 *
 * @author renbaojie
 */
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER})
public class TraceFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext rpcContext = RpcContext.getContext();

        // 消费者侧: 如果业务层没有手动生成traceId,这里会默认生成一个
        if (rpcContext.isConsumerSide()) {
            String traceId;
            if (StringUtils.isEmpty(TraceIdUtil.getTraceId())) {
                traceId = TraceIdUtil.createTraceId();
            } else {
                traceId = TraceIdUtil.getTraceId();
            }
            rpcContext.setAttachment(TraceIdUtil.TRACE_ID, traceId);
        }

        // 提供侧: RpcContext肯定会有traceId, 取出traceId并设置到当前线程上下文, 用于后续该线程发起的rpc请求时使用
        if (rpcContext.isProviderSide()) {
            String rpcContextTraceId = rpcContext.getAttachment(TraceIdUtil.TRACE_ID);
            TraceIdUtil.setTraceId(rpcContextTraceId);
        }
        return invoker.invoke(invocation);
    }
}
