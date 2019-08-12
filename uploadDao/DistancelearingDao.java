package hyc.upload.dataupload.uploadDao;

import hyc.upload.dataupload.uploadEntity.DistanceLearning;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface DistancelearingDao {
    public List<DistanceLearning> getAll();//获取所有数据

    public List<DistanceLearning> getByTime( );//根据时间段获取数据
}
