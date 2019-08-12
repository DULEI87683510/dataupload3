package hyc.upload.dataupload.service;

import hyc.upload.dataupload.uploadEntity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();
    public User getUserById(String id);
    public void addUser(User user);
}
