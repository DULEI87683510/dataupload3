package hyc.upload.dataupload.uploadDao;

import hyc.upload.dataupload.uploadEntity.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DictDao {
     List<Dict> findList(Dict dict);
}
