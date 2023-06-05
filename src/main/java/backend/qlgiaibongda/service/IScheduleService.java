package backend.qlgiaibongda.service;

import backend.qlgiaibongda.api.input.NewSchedule;
import backend.qlgiaibongda.dto.ResponeObject;
import org.springframework.http.ResponseEntity;

public interface IScheduleService {
    public ResponseEntity<ResponeObject> createNewSchedule(NewSchedule newSchedule);
}
