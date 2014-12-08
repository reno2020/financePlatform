package com.sunlights.customer.service.rewardrules.exchange;

import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import play.Logger;

/**
 * 红包取现处理链上的第一个处理类
 *      负责组装红包取现的逻辑处理功能链
 * Created by tangweiqun on 2014/12/3.
 */
public class RedPacketExchangeRuleHandler extends AbstractExchangeRuleHandler {

    @Override
    public void exchange(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        Logger.debug("红包取现开始requestVo ：" + requestVo);

        setNextHandler(new ExchangeRuleGainHandler(
                            new ExchangeValidHandler(
                                new ExchangeFlowHandler(
                                        new ExchangeResultHandler()))));
        getNextHandler().exchange(requestVo, responseVo);

        Logger.debug("红包取现结束responseVo ：" + responseVo);
    }

    @Override
    public void exchangeInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        //donothing
    }

    @Override
    public String toString() {
        return "RedPacketExchangeRuleHandler";
    }
}