package backend.qlgiaibongda.api.lichthidau;

import backend.qlgiaibongda.api.input.NewSchedule;
import backend.qlgiaibongda.dto.ResponeObject;
import backend.qlgiaibongda.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lichthidau")
@EnableTransactionManagement
public class LichThiDauAPI {

    @Autowired
    private IScheduleService scheduleService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponeObject> getSchedule(@PathVariable("id") Long id){
        return scheduleService.getSchedule(id);
    }

    @PostMapping
    public ResponseEntity<ResponeObject> createSchedule(@RequestBody NewSchedule newSchedule){
        if(!newSchedule.checkValidInfo()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("FAIL", "Invalid Info", ""));
        }
        return scheduleService.createNewSchedule(newSchedule);
    }
}
