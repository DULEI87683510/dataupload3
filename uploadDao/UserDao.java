package hyc.upload.dataupload.uploadDao;

import hyc.upload.dataupload.uploadEntity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
/**
 * user查询类
 * @author DL
 * @date
 */
public interface UserDao {
    /**
     *大苏打
     * @param id
     * @return
     */
    User getById(@Param("id") String id);

    /**
     *大苏打
     * @return
     */
    List<User> getAllUser();

    /**
     *大苏打
     * @param user
     */
    void  addUser(User user);
}
