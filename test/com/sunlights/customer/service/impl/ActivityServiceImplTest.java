package com.sunlights.customer.service.impl;

import com.sunlights.customer.service.ActivityService;
import org.junit.Test;
import play.db.jpa.JPA;
import play.libs.F;
import play.test.WithApplication;

import java.util.List;

<<<<<<< HEAD
public class ActivityServiceImplTest extends play.test.WithApplication {

    @Test
    public void testGetActivityTitles() throws Exception {
        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                ActivityService activityService = new ActivityServiceImpl();
                List<String> titles = activityService.getActivityTitles("3333");
            }
        });
=======
import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

public class ActivityServiceImplTest extends WithApplication {

    @Test
    public void testGetActivityTitles() throws Exception {

                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        ActivityService activityService = new ActivityServiceImpl();
                        List<String> titles = activityService.getActivityTitles("3333");
                    }
                });
>>>>>>> master

    }
}