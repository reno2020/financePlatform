package com.sunlights.account.web;

import com.sunlights.BaseTest;
import com.sunlights.account.AccountConstant;
import com.sunlights.account.service.RewardFlowService;
import com.sunlights.account.service.impl.RewardFlowServiceImpl;
import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.MessageVo;
import models.RewardFlow;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.mvc.Http;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class RewardControllerTest extends BaseTest {
    private static Http.Cookie cookie;

    @Before
    public void getCookie(){
        final String mobilePhoneNo = "13811599307";
        final String password = "1";
        running(fakeApplication(), new Runnable() {
            public void run() {
                cookie = getCookieAfterLogin(mobilePhoneNo, password);
            }
        });
    }

    @Test
    public void testGetSingInCanObtainRewards() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        RewardFlowService rewardFlowService = new RewardFlowServiceImpl();
                        RewardFlow rewardFlows = rewardFlowService.findTodayFlowByCustIdAndScene("20141119102210010000000029", AccountConstant.ACTIVITY_SIGNIN_SCENE_CODE);

                        Logger.info("============testGetSingInCanObtainRewards start====");
                        Map<String, String> formParams = new HashMap<String, String>();
                        play.mvc.Result result = getResult("/account/reward/get_signin", formParams, cookie);
                        Logger.info("============testGetSingInCanObtainRewards result====\n" + contentAsString(result));
                        assertThat(status(result)).isEqualTo(OK);
                        final MessageVo message = toMessageVo(result);

                        if (rewardFlows != null) {
                            assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.ALREADY_SIGN.getCode());
                            assertThat("20141119102210010000000029").isEqualTo(rewardFlows.getCustId());
                        } else {
                            assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.ACTIVITY_QUERY_SUCC.getCode());
                        }
                    }
                });

            }
        });
    }

    @Test
    public void testGetMyRewardDetail() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Logger.info("============testGetMyRewardDetail start====");
                Map<String, String> formParams = new HashMap<String, String>();
                play.mvc.Result result = getResult("/account/reward/get_golden", formParams, cookie);
                Logger.info("============testGetMyRewardDetail result====\n" + contentAsString(result));
                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.ACTIVITY_QUERY_SUCC.getCode());

            }
        });
    }
}