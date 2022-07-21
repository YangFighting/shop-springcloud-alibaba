package io.yang.shop.utils.id;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangyang
 * @date 2022/07/21 23:45
 **/
public class SnowFlakeTest {
    private final Logger logger = LoggerFactory.getLogger(SnowFlakeTest.class);

    @Test
    public void nextDefaultId() {

        for (int i = 0; i < 100; i++) {
            logger.warn(String.valueOf(SnowFlakeFactory.getSnowFlakeFromCache().nextId()));
        }
    }

    @Test
    public void nextId() {
        for (int i = 0; i < 100; i++) {
            logger.warn(String.valueOf(SnowFlakeFactory.getSnowFlakeByDataCenterIdAndMachineIdFromCache(SnowFlakeLoader.getDataCenterId(), SnowFlakeLoader.getDataCenterId()).nextId()));
        }
    }


}