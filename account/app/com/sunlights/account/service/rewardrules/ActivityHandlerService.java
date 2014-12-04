package com.sunlights.account.service.rewardrules;

import com.sunlights.account.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.account.service.rewardrules.vo.ActivityResponseVo;

import java.util.List;

/**
 * 活动处理服务
 * Created by tangweiqun on 2014/12/1.
 */
public class ActivityHandlerService {

    public void service(ActivityRequestVo requestVo, ActivityResponseVo responseVo) {
        ActivityRequestVo processeRequest = requestVo;
        HandlerExecutionChain mappedHandler = null;
        ActivityResponseVo vo = null;
        try {
            processeRequest = checkRequest(requestVo);
            mappedHandler = getHandler(requestVo);

            if(mappedHandler == null || mappedHandler.getHandler() == null) {
                //TODO 没有找到处理类

            }

            HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

            List<HandlerInterceptor> handlerInterceptors = mappedHandler.getInterceptorList();
            if(handlerInterceptors != null) {
                //TODO 活动处理前的过滤器
                for (HandlerInterceptor interceptor : handlerInterceptors) {
                    if(!interceptor.preHandler(processeRequest, mappedHandler.getHandler())) {
                        //TODO 过滤器不通过

                    }
                }
            }

            ha.handle(requestVo, responseVo, mappedHandler.getHandler());

            if(handlerInterceptors != null) {
                //TODO 活动处理后的过滤
                for (HandlerInterceptor interceptor : handlerInterceptors) {
                    interceptor.postHandler(requestVo, vo);
                }
            }

        } catch (Exception e) {

        } finally {
            clearupRequest(processeRequest);
        }

    }


    protected ActivityRequestVo checkRequest(ActivityRequestVo requestVo) {
        return requestVo;
    }

    protected HandlerExecutionChain getHandler(ActivityRequestVo requestVo) throws Exception {
        for(HandlerMapping hm : RewardRuleFactory.getHandlerMapping()) {
            HandlerExecutionChain handler = hm.getHandler(requestVo);
            if(handler != null) {
                return handler;
            }
        }
        return null;
    }

    protected  HandlerAdapter getHandlerAdapter(Object handler) {
        for(HandlerAdapter ha : RewardRuleFactory.getHandlerAdapters()) {
            if(ha.supports(handler)) {
                return ha;
            }
        }
        throw new RuntimeException("No Adapter for handler [" + handler + "] : Does you handler implement a supported interface like RuleHandler");
    }

    protected void clearupRequest(ActivityRequestVo requestVo) {

    }
}
