package hyc.upload.dataupload.controller;

import hyc.upload.dataupload.service.DataReportService;
import hyc.upload.dataupload.service.UserService;
import hyc.upload.dataupload.uploadEntity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dataReport")
public class DataReportController {

    @Autowired
    private DataReportService dataReportService;
    @Autowired
    private UserService userService;
    @RequestMapping("/sendRomote")
    public void sendRomote(){
        try {
            dataReportService.ReportRemoteConsultationAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

    } @RequestMapping("/sendDinstance")
    public void sendDinstance(){
        try {
            dataReportService.ReportDistanceLearAll();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @GetMapping("/getAlluser")
    @ResponseBody
    public List<User> getAllUser(){

    return userService.getAllUser();
    }


}
