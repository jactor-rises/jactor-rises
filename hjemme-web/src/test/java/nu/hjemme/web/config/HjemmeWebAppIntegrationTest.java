package nu.hjemme.web.config;

import nu.hjemme.web.controller.AboutController;
import nu.hjemme.web.controller.HomeController;
import nu.hjemme.web.controller.UserController;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = HjemmeWebApp.class)
@Ignore("cannot run in ide on mac after upgrade to junit 5, ignored until resolved")
public class HjemmeWebAppIntegrationTest {

    @Resource private HomeController homeController;

    @Resource private AboutController aboutController;

    @Resource private UserController userController;

    @Test public void shouldGetControllers() {
        assertThat("HomeController", homeController, is(notNullValue()));
        assertThat("AboutController", aboutController, is(notNullValue()));
        assertThat("UserController", userController, is(notNullValue()));
    }
}