/*
 MIT License
Copyright (c) 2017 Devitgg
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package assignment12;

import java.util.Scanner;

/**
 *
 * @author Devitgg
 */
class ScheduleEntry {

    String  dateOfWork,
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
