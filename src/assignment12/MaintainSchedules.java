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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Devitgg
 */
public class MaintainSchedules {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        String      userChoice;
        userChoice  = "is to be amazing.";
        int         numEntries = 0;
        
        ScheduleEntry[] theSchedules = new ScheduleEntry[1000];

        while (!userChoice.equals("Exit Program")) {
            userChoice = getUserChoice();
            numEntries = performUserChoice(userChoice, theSchedules, numEntries);
        }
        System.exit(0); //Bye Felica
    }

    private static String getUserChoice() {
        String getUserChoice;

        String[] options = new String[]{
            "Load a List From a File",
            "Save a List to a File",
            "Add a Schedule Entry",
            "Delete a Schedule Entry",
            "Look up a Scheduled Date",
            "Display an Entire Schedule",
            "Exit Program"};

        getUserChoice = (String) JOptionPane.showInputDialog(
                null,
                "Greetings, Please select an option to perform",
                "Memers Inc.",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        return getUserChoice;
    }

    private static int performUserChoice(String userChoice,
            ScheduleEntry[] theSchedules, int numEntries)
            throws FileNotFoundException, IOException {
        switch (userChoice) {
            case "Load a List From a File":
                numEntries = load(theSchedules, numEntries);
                return numEntries;
                
            case "Save a List to a File":
                save(theSchedules, numEntries);
                break;
                
            case "Add a Schedule Entry":
                numEntries = add(theSchedules, numEntries);
                return numEntries;
                
            case "Delete a Schedule Entry":
                numEntries = delete(theSchedules, numEntries);
                break;
                
            case "Look up a Scheduled Date":
                lookup(theSchedules, numEntries);
                break;
                
            case "Display an Entire Schedule":
                ProduceReport(theSchedules, numEntries);
                break;
        }
        return numEntries;
    }

    private static int load(ScheduleEntry[] theSchedules, int numEntries)
            throws FileNotFoundException {
        int result;
        String fPath;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(
                new File(System.getProperty("user.home"))
        );

        result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            if (selectedFile.getAbsolutePath().contains("employe")) {
                Scanner schedFile = new Scanner(selectedFile);

                while (schedFile.hasNext()) {
                    theSchedules[numEntries] = new ScheduleEntry();
                    theSchedules[numEntries].load(schedFile);
                    numEntries++;
                }
                return numEntries;
            } else {
                //errrrrrrrr
                JOptionPane.showMessageDialog(null, "YOU BROKE IT!",
                        "ERRRRR ERORR!", 0);
            }
            return numEntries;
        }
        return numEntries;
    }

    private static void save(ScheduleEntry[] theSchedules, int numEntries)
            throws FileNotFoundException, IOException {
        {
            int result,
                    x;

            if (numEntries == 0) {
                //errrrrrrrr
                JOptionPane.showMessageDialog(null, "YOU BROKE IT!",
                        "ERRRRR ERORR!", 0);
            } else {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(
                        new File(System.getProperty("user.home"))
                );
                
                result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    if (selectedFile.getAbsolutePath().contains("employe")) {
                        ScheduleEntry thisSchedule;
                        thisSchedule = new ScheduleEntry();

                        try ( 
                                FileWriter foo = new FileWriter(selectedFile,
                                        false)) {
                            for (x = 0; x < numEntries; x++) {
                                foo.write(theSchedules[x].getDateOfWork()
                                        + "\n");
                                foo.write(theSchedules[x].getStartTime()
                                        + "\n");
                                foo.write(theSchedules[x].getEndTime()
                                        + "\n");
                                foo.write(theSchedules[x].getComment()
                                        + "\n");
                            }
                        }

                    } else {
                        //errrrrrrrr
                        JOptionPane.showMessageDialog(null, "YOU BROKE IT!",
                                "ERRRRR ERORR!", 0);

                    }
                }
            }
        }
    }

    private static int add(ScheduleEntry[] theSchedules, int numEntries) {
        int option;
        ScheduleEntry aScheduleEntry;

        JTextField getTheDate = new JTextField();
        JTextField getLocation = new JTextField();
        JTextField getStartTime = new JTextField();
        JTextField getEndTime = new JTextField();
        JTextField getComments = new JTextField();

        Object[] message = {
            "Date:", getTheDate,
            "Location:", getLocation,
            "Start Time:", getStartTime,
            "End Time:", getEndTime,
            "Comments:", getComments
        };

        option = JOptionPane.showConfirmDialog(null, message,
                "Memers Inc.", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            if (!getTheDate.getText().isEmpty()
                    && (!getLocation.getText().isEmpty())
                    && (!getStartTime.getText().isEmpty())
                    && (!getEndTime.getText().isEmpty())
                    && (!getComments.getText().isEmpty())) {

                aScheduleEntry = new ScheduleEntry();
                aScheduleEntry.Set(getTheDate.getText(),
                        getLocation.getText(),
                        getStartTime.getText(),
                        getEndTime.getText(),
                        getComments.getText());

                theSchedules[numEntries++] = aScheduleEntry;

                return numEntries;

            }
        } else {
            JOptionPane.showMessageDialog(null, "You failed to complete "
                    + "the given task", "Memers Inc.", 0);
        }
        return numEntries;
    }

    private static int delete(ScheduleEntry[] theSchedules, int numEntries) {
        String deleteThisDate;
        int foundIt;

        deleteThisDate = getUserEnteredDate();
        foundIt = SearchForDate(deleteThisDate, theSchedules, numEntries);
        numEntries = PerformDeletion(theSchedules, numEntries, foundIt);

        return numEntries;
    }

    private static String getUserEnteredDate() {
        String theDateToBeDeleted = JOptionPane.showInputDialog(
                "Whats the date to be deleted?");

        return theDateToBeDeleted;
    }

    private static int SearchForDate(String deleteThisDate,
            ScheduleEntry[] theSchedules, int numEntries) {

        int isItThisOne = 0;

        while (isItThisOne < numEntries
                && !deleteThisDate.equals(
                        theSchedules[isItThisOne].getDateOfWork())) {
            isItThisOne++;
        }
        return isItThisOne;
    }

    private static int PerformDeletion(ScheduleEntry[] theSchedules,
            int numEntries, int foundIt) {
        if (foundIt < numEntries) {
            numEntries--;
            theSchedules[foundIt] = theSchedules[numEntries];
            theSchedules[numEntries] = null;
            JOptionPane.showMessageDialog(null,
                    "Scheduled Date has Been Deleted.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Scheduled Date weas not Deleted.");
        }

        return numEntries;
    }

    private static void lookup(ScheduleEntry[] theSchedules, int numEntries) {
        String whatIsTheDate;
        int foundIt;

        whatIsTheDate = getUserEnteredDate();
        foundIt = SearchForDate(whatIsTheDate, theSchedules, numEntries);
        DisplayEntry(theSchedules, numEntries, foundIt);

    }

    private static void DisplayEntry(ScheduleEntry[] theSchedules,
            int numEntries, int foundIt) {
        JOptionPane.showMessageDialog(null, "Date: "
                + theSchedules[foundIt].getDateOfWork()
                + "\nLocation: " + theSchedules[foundIt].getLocation()
                + "\nStart Time: " + theSchedules[foundIt].getStartTime()
                + "\nEnd Time: " + theSchedules[foundIt].getEndTime()
                + "\nComments: " + theSchedules[foundIt].getComment());
    }

    private static void ProduceReport(ScheduleEntry[] theSchedules,
            int numEntries) {
        for (int x = 0; x < numEntries; x++) {
            System.out.println("\n\nDay " + (x + 1 + "\n"));
            System.out.println(theSchedules[x].getLocation());
            System.out.println(theSchedules[x].getDateOfWork());
            System.out.println(theSchedules[x].getStartTime());
            System.out.println(theSchedules[x].getStartTime());
            System.out.println(theSchedules[x].getEndTime());
            System.out.println(theSchedules[x].getComment());
        }
    }
}
