package io.yang.shop.utils.id;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zhangyang
 * @date 2022/07/21 0:17
 **/
public class SnowFlakeFactory {
    /**
     * 默认数据中心id
     */
    private static final long DEFAULT_DATACENTER_ID = 1;
    private static final long DEFAULT_MACHINE_ID = 1;
    private static final String DEFAULT_SNOW_FLAKE = "snow_flake";
    /**
     * 缓存SnowFlake对象
     */
    private static final ConcurrentMap<String, SnowFlake> SNOW_FLAKE_CACHE = new ConcurrentHashMap<>(2);

    public static SnowFlake getSnowFlake(long datacenterId, long machineId) {
        return new SnowFlake(datacenterId, machineId);
    }
    public static SnowFlake getSnowFlake() {
        return new SnowFlake(DEFAULT_DATACENTER_ID, DEFAULT_MACHINE_ID);
    }

    public static SnowFlake getSnowFlakeFromCache() {
        // 懒汉单例模式
        SnowFlake snowFlake = SNOW_FLAKE_CACHE.get(DEFAULT_SNOW_FLAKE);
        if (snowFlake == null) {
            snowFlake = new SnowFlake(DEFAULT_DATACENTER_ID, DEFAULT_MACHINE_ID);
            SNOW_FLAKE_CACHE.put(DEFAULT_SNOW_FLAKE, snowFlake);
        }
        return snowFlake;
    }

    public static SnowFlake getSnowFlakeByDataCenterIdAndMachineIdFromCache(Long dataCenterId, Long machineId) {
        if (dataCenterId > SnowFlake.getMaxDataCeneterNum() || dataCenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > SnowFlake.getMaxMachineNum() || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        String key = DEFAULT_SNOW_FLAKE.concat("_").concat(String.valueOf(dataCenterId)).concat("_").concat(String.valueOf(machineId));

        SnowFlake snowFlake = SNOW_FLAKE_CACHE.get(key);
        if (snowFlake == null) {
            snowFlake = new SnowFlake(dataCenterId, machineId);
            SNOW_FLAKE_CACHE.put(key, snowFlake);
        }
        return snowFlake;

    }

}
