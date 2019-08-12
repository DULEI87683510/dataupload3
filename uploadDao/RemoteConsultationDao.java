package hyc.upload.dataupload.uploadDao;

import hyc.upload.dataupload.uploadEntity.Consultant;
import hyc.upload.dataupload.uploadEntity.RemoteConsultationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface RemoteConsultationDao {

     List<RemoteConsultationEntity> getAll();//获取所有数据

     List<RemoteConsultationEntity> getByTime( );//根据时间段获取数据

    List<Consultant> getByApplyId(@Param("id") String id);//通过申请id获取结论及医生

    String  getDictName( @Param("hospName") String hospName );//根据医院名称获取地区名称


}
