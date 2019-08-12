package hyc.upload.dataupload.service.serviceImpl;


import annotation.MyException;
import hyc.upload.dataupload.service.UserService;
import hyc.upload.dataupload.uploadDao.UserDao;
import hyc.upload.dataupload.uploadEntity.User;
import hyc.upload.dataupload.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public UserServiceImpl() {
        super();
    }

    @Override
    public List<User> getAllUser() {
    return userDao.getAllUser();
    }

    @Override
    public User getUserById(String id) {
        boolean flag=true;
        if(flag){
            throw new MyException(ErrorCode.ERROR_CODE.code(),ErrorCode.ERROR_CODE.message());
        }

        return userDao.getById(id);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
        System.out.println("=======插入user成功");
    }
}
