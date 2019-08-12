package hyc.upload.dataupload.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

import java.util.concurrent.atomic.AtomicInteger;

public class RoutingDataSource extends AbstractRoutingDataSource {
    private AtomicInteger count = new AtomicInteger(0);

    private int readsize;

    public RoutingDataSource(int readsize) {
        this.readsize = readsize;
    }
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getJdbcType();
        if (typeKey == null) {
            logger.error("无法确定数据源");
            return DataSourceType.READ.getType();
        }

        if (typeKey.equals(DataSourceType.WRITE.getType())) {
            return DataSourceType.WRITE.getType();
        }
        //读库进行负载均衡
        int a = count.getAndAdd(1);
        int lookupkey = a % readsize;
        return lookupkey;


    }
}
