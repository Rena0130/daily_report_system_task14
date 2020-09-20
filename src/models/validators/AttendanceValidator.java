package models.validators;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Attendance;

public class AttendanceValidator {
    public static List<String> validate(Attendance a,Boolean code_duplicate_check_flag, Boolean password_check_flag) {
        List<String> errors = new ArrayList<String>();

        String entry_error  = _validateEntry(a.getEntry());
        if(!entry_error.equals("")) {
            errors.add(entry_error);

        }

        String arrive_error = _validateArrive(a.getArrive());
        if(!arrive_error.equals("")){
            errors.add(arrive_error);
        }

       String finish_error = _validateFinish(a.getFinish());
        if(!finish_error.equals("")){
            errors.add(finish_error);
        }
        return errors;
    }

        private static String _validateEntry(Date entry){
            if(entry == null ){
                return"登録日時を入力してください。";
            }
            return "";
        }

        private static String _validateArrive(Date arrive){
            if(arrive == null){
                return "出勤時間を入力してください。";
            }
            return "";
        }

        private static String _validateFinish(Date finish){
            if(finish == null){
                return "退勤時間を入力してください。";
            }
            return "";
        }

}
