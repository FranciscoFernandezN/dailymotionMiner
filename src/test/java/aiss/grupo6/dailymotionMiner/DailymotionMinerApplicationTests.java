package aiss.grupo6.dailymotionMiner;

import aiss.grupo6.dailymotionMiner.service.CaptionServiceTest;
import aiss.grupo6.dailymotionMiner.service.ChannelServiceTest;
import aiss.grupo6.dailymotionMiner.service.VideoServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
		ChannelServiceTest.class,
		VideoServiceTest.class,
		CaptionServiceTest.class
})
public class DailymotionMinerApplicationTests {

	@Test
    public void contextLoads() {
	}

}
