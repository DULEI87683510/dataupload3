package hyc.upload.dataupload.service.serviceImpl;

import hyc.upload.dataupload.service.DictService;
import hyc.upload.dataupload.uploadDao.DictDao;
import hyc.upload.dataupload.uploadEntity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DictServiceImpl implements DictService{
    @Autowired
    private DictDao dao;

    @Override
    public List<Dict> findList(Dict dict) {


            return dao.findList(dict);


    }
}
