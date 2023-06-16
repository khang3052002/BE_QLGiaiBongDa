package backend.qlgiaibongda.api.input;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateMatchInput {
    private Long idTranDau;
    private Timestamp time;

    private String timeString;

    public boolean checkValidInfo(){
        boolean check = true;
        if(idTranDau == null || timeString == null){
            check = false;
        }else{
            LocalDateTime localDateTime = LocalDateTime.parse(timeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(7));
            time = Timestamp.valueOf(offsetDateTime.toLocalDateTime());
        }
        return  check;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public Long getIdTranDau() {
        return idTranDau;
    }

    public void setIdTranDau(Long idTranDau) {
        this.idTranDau = idTranDau;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
