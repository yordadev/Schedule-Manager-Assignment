package assignment12;

import java.util.Scanner;

/**
 *
 * @author Sean O'connell
 */
class ScheduleEntry {

    String dateOfWork,
            location,
            startTime,
            endTime,
            comment;

    public String[] load(Scanner schedFile) {
        //Check if theres another scheduled day in file
        if (schedFile.hasNext()) {
            String[] aSchedule = new String[]{
                dateOfWork = schedFile.nextLine(),
                location = schedFile.nextLine(),
                startTime = schedFile.nextLine(),
                endTime = schedFile.nextLine(),
                comment = schedFile.nextLine(),};
            return aSchedule;
        }
        String[] aSchedule = new String[]{
            "error"
        };
        return aSchedule;
    }

    public String getDateOfWork() {
        return dateOfWork;
    }
    public String getLocation(){
        return location;
    }
    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getComment() {
        return comment;
    }

    void Set(String setDate, String setLocation, String setStartTime, 
            String setEndTime, String setComments) {
                dateOfWork = setDate;
                location = setLocation;
                startTime = setStartTime;
                endTime = setEndTime;
                comment = setComments;
    }
}
