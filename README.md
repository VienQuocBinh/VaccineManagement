# VaccineManagement
## Background 
A program to manage the vaccination process of high school students. The 
vaccination management program must allow administrators to manage student vaccination information. In 
addition, the program also provides a function for students to view information about the vaccine that they have 
been vaccinated. To simplify the application building, we give the following business roles:
1. Each student can only inject a maximum of **2 injections** of the vaccine.
2. The second dose of vaccine must be given **4 to 12 weeks** after the **first injection**
3. Two vaccines must be of the same type.
Injection information is stored in the injection.dat file
The student.dat file has stored student information including studentID and name fields.
The vaccine.dat file has stored vaccine information including vaccineID and name fields.

## Program Specifications
Build a management program. With the following basic functions
0. Build your data structure 
1. Show information all students have been injected
2. Add student's vaccine injection information
3. Updating information of students' vaccine injection
4. Delete student vaccine injection information
5. Search for injection information:
   5.1 Search by studentID
   5.2 Search by student name
7. Store data to file
8. Real time update processing
9. Information Encryption
Others- Quit

Each menu choice should invoke an appropriate function to perform the selected menu item. Your program must 
display the menu after each task and wait for the user to select another option until the user chooses to quit the program. 
Each injection information has the properties such that injection id, 1st injection place, 2nd injection place, 1st injection date, 2nd injection date, student ID and vaccine ID.

### Features:
This system contains the following functions:
Display a menu and ask doctor to select an option.
#### Function 0: Build the data structure
- Classes, abstract classes, Interfaces.
- The injectionID, studentID, and vaccineID fields cannot be changed after created.
- Must implement the polymorphism properties of object-oriented programming.
#### Function 1: Show information the injection information
- Show all data in the injection.dat file into the screen.
#### Function 2: Add new injection
- Create a submenu that allows the doctor add new injection information. 
- Remember that the constraints must be checked: the injectionID’s value cannot duplicate, 
student and vaccine must be existed and all business role above.
- Add the new injection to collection. If any information is left blank, it means that the 
information does not need to be input at the present time (2nd information).
- Ask to continuous create new injection or go back to the main menu.
#### Function 3: Update Injection information
- User is required enter the injection id
- If injection does not exist, the notification “Injection does not exist”. Otherwise, doctor can start update 2 nd information only (place, place). 
- Remember that new information must be validated.
- Then, system must print out the result of the updating. If the student has fully injected two 
injections, the system will display the message "Student has completed 2 injections!"
- After updating, the program returns to the main screen.
#### Function 4: Delete Injection
- Doctor can delete the Injection in the list.
- Before the delete system must show confirm message.
- Show the result of the delete: success or fail.
- After delete, the program returns to the main screen
#### Function 5: Search injection
- Doctor input student name want to search.
- The system will search in the list, and return all injection information that has student name 
contain the search string.
- Show result list: all information of injection.
#### Function 6: Store data to file
- Store data in collection to injection.dat file.
#### Function 7: Real-time update processing
- Modify the above functions so that when each function is completed, the system must 
immediately update to the files.
#### Function 8: Information Encryption
Injection information is confidential information, so the data needs to be encrypted before 
saving it to the file. You are required to encrypt injection information with the MD5 hashing 
method before saving it to the injection.dat file.
