# User Guide

NUS Classes is a desktop app for NUS Computing professors to manage their tasks and contacts. It includes task management features such as 
creating tasks, tagging tasks, assigning contacts to tasks, and marking tasks as complete or incomplete. It also includes contact management features such as finding contacts, assigning contacts to specific tasks and tagging contacts. 

NUS Classes also provides a simple alert feature for tasks by displaying tasks in different color based on the urgency of the task. Tasks that are overdue are marked as red, whereas, tasks that are nearing deadline are marked as yellow.

NUS Classes is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 
Using NUS Classes can get your contact management tasks done faster than traditional GUI apps, saving time on otherwise tedious administrative tasks.

* Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
    * [Help](#viewing-help--help)
    * [Contact Features](#contact-features)
        * [Adding a person](#adding-a-person-addc)
        * [Listing all persons](#listing-all-persons--listc)
        * [Editing a person](#editing-a-person--editc)
        * [Locating persons by name](#locating-persons-by-name-findc)
        * [Deleting a person](#deleting-a-person--deletec)
    * [Task Features](#task-features)
        * [Adding a task](#adding-a-task-addt)
        * [Listing tasks](#listing-tasks-listt)
        * [Editing a task](#editing-a-task-editt)
        * [Finding tasks by name](#finding-tasks-by-name-findt)
        * [Assigning a contact to a task](#assigning-a-contact-to-a-task-assign)
        * [Unassigning a contact from a task](#unassigning-a-contact-from-a-task-unassign)
        * [Viewing contacts assigned to a task](#viewing-contacts-assigned-to-a-task-view)
        * [Mark a task as done](#mark-a-task-as-done-mark)
        * [Unmark a task as not done](#unmark-a-task-as-not-done-unmark)
        * [Deleting tasks](#deleting-tasks-deletet)
    * [Other Features](#other-features)
        * [Clearing all contacts](#clearing-all-contacts--clear)
        * [Exiting the program](#exiting-the-program--exit)
        * [Saving the data](#saving-the-data)
        * [Editing the data file](#editing-the-data-file)
* [FAQ](#faq)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `nusclasses.jar` from [here](https://github.com/AY2122S2-CS2103T-T12-4/tp/releases).

1. Copy the file to the folder you want to use as the _main folder_ for your NUS Classes manager.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press `Enter` to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`listc`** : Lists all contacts.

    * **`addc`**`n/John Doe p/98765432 e/johnd@u.nus.edu u/john123 t/Schoolmate` : Adds a contact named `John Doe` to NUS Classes,
   with email `johnd@u.nus.edu`, Github Username `john123` and tag `Schoolmate`

    * **`deletec`**`3` : Deletes the 3rd contact shown in the current contact list.

   * `assign 1 p/ 2` : Assigns the contact at index 2 to the task at index 1.

   * **`clear`** : Clears all contacts from NUS Classes

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

### Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addc n/CONTACTNAME`, `CONTACTNAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `addc n/CONTACTNAME p/PHONENUMBER e/EMAIL u/GIT_USERNAME [t/TAGS]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

# Contact Features

### Adding a person: `addc`

Adds a contact.

Format: `addc n/CONTACTNAME p/PHONENUMBER e/EMAIL u/GIT_USERNAME [t/TAGS]`

Examples:

* `addc n/john p/12345678 e/john@nus.edu.sg u/john123 t/Schoolmate`
* `addc n/mary p/87654321 e/mary@nus.edu.sg u/maryCS t/Teammate t/Classmate`

<div markdown="span" class="alert alert-warning">:bulb: **Tip**
You can add multiple tags to a contact. Just put t/ before every tag!

</div>

### Listing all persons : `listc`

Shows a list of all persons in NUS Classes.

Format: `listc`

### Editing a person : `editc`

Edits an existing person in NUS Classes.

Format: `editc INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG] [u/GITUSERNAME]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
* `editc 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
* `editc 1 p/82223333 e/Joseph@comp.nus.edu.sg` Edits the phone number and email address of the 1st person to be `82223333` and `Joseph@comp.nus.edu.sg` respectively.
    <br><br>
    ![Result for 'editc'](images/editcCommandShowcase.png)
    
    


### Locating persons by name: `findc`

Finds persons whose names contain any of the given keywords.

Format: `findc KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `findc John` returns `john` and `John Doe`
* `findc brian sean` returns `Brian Chow`, `Sean Ng`<br>
    <br>
    ![result for 'find brian sean'](images/findcCommandShowcase.png)

### Deleting a person : `deletec`

Deletes the specified person from NUS Classes.

Format: `deletec INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `listc` followed by `deletec 2` deletes the 2nd person in NUS Classes.
* `findc Joseph` followed by `deletec 1` deletes the 1st person in the results of the `findc` command.<br>
    <br>
    ![result for `deletec 1` part 1](images/deletecCommandShowcase1.png) <br><br>
    ![result for `deletec 1` part 2](images/deletecCommandShowcase2.png)



# Task Features

### Adding a task: `addt`

Adds a task for a datetime with a tag.

Format: `addt tn/TASKNAME dt/DATETIME [ENDDATETIME] [t/TAG]…​ [z/LINK] [r/INTERVAL RECURRENCE]`

Note: `INTERVAL` refers to the number of days for another recurrence of the task to occur: </br>e.g. `INTERVAL` of 5 for a task on 01-01-2022 would next occur on 05-01-2022.

Note: `RECURRENCE` refers to how many times the task is repeated: </br> e.g. `RECURRENCE` of 5 with an `INTERVAL` of 7 means that the task repeats weekly for 5 weeks.

<div markdown="span" class="alert alert-warning">:bulb: **Tip** For `INTERVAL`, the values `daily`,`weekly`,`monthly`, `quarterly` and `annually` are accepted.
</div>

<div markdown="span" class="alert alert-warning">:bulb: **Tip** The format for TIME is in dd-mm-yyyy hhmm.
</div>

Examples:
* `addt tn/Meeting dt/17-03-2022 1800 t/School` Adds a task called Meeting for 17th March 2022, 6pm at School
* `addt tn/Consultation dt/19-03-2022 1500, 19-03-2022 1600` Adds a task called Consultation taking place from `19th March 2022 3-4pm`
* `addt tn/CS2103 Lecture dt/19-03-2022 1500, 19-03-2022 1600 z/https://nus-sg.zoom.us…​ r/weekly 12`
Adds a task called CS2103 Lecture taking place from `19th March 2022 3-4pm` that occurs `weekly` for the next `12 weeks` with the `meeting link`.


<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
There cannot be an already existing tag with the same name; tags must be unique.

</div>

### Listing tasks : `listt`

Shows a list of all the tasks in the task list as per the specified filtering options. `listt` has the three folowing formats:

Format: 

Shows a list of all tasks in the task list.

`listt all/`

Shows a list of tasks that is marked as completed in the task list.

`listt c/`

Shows a list of tasks that is not mark as completed in the task list.

`listt nc/`

<div markdown="span" class="alert alert-warning">:bulb: **Tip**
Good usage of `listt` will help in keeping track of tasks by status!

</div>

### Editing a task: `editt`
Edits an existing task in the task list.

Format: `editt INDEX [tn/TASKNAME] [dt/DATETIME, ENDDATETIME*] [t/TAG]`

* Updates the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
* `editt 1 tn/Meeting with TAs` Updates the name of the 1st displayed task to be `Meeting with TAs`
* `editt 2 tn/Meeting with Prof Tan dt/01-12-2022 1200, 01-12-2022 1300` Updates the name of the 2nd displayed task to be `Meeting with Profs Tan` and the date to be 1st Dec 2022, 12pm-1pm. <br>
    <br>
    ![`editt 2 tn/Meeting with Prof Tan dt/01-12-2022 1200, 01-12-2022 1300`](images/edittCommandShowcase1.png)


* `editt 1 dt/12-12-2022 1200, 12-12-2022 1400` Updates the datetime of the 1st displayed task to be on `12th Dec 2022, 12-2pm.` <br>
    <br>
    ![`editt 1 dt/12-12-2022 1200, 12-12-2022 1400`](images/edittCommandShowcase2.png)
    

<div markdown="span" class="alert alert-warning">:bulb: **Tip**
If there's no need to change a certain field you can leave it out!<br>
:bulb: **Tip** The `ENDDATETIME` field is optional
</div>


### Finding tasks by name: `findt`

Finds tasks whose task names contain any of the given keywords.

Format: `findt KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `eat` will match `Eat`
* The order of the keywords does not matter. e.g. `Eat Apple` will match `Apple Eat`
* Only the name is searched.
* Only full words will be matched e.g. `Apples` will not match `Apple`
* Tasks matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Apple Pear` will return `Eat apple`, `Buy pear`

Examples:
* `findt with` returns `Consultation with students` and `Meeting with invigilators`
* `findt TAs lecture` returns `Meeting with TAs` and all recurrences of `CS2103T lecture` <br>
    <br>
    ![`findt TAs lecture`](images/findtCommandShowcase.png)

### Assigning a contact to a task: `assign`
Assigns a person in the contact list to a task.

Format: `assign INDEX p/PERSONINDEX`

* Assigns the person at the specified `PERSONINDEX` to the task at `INDEX`. The indices refer to the index numbers shown in the corresponding displayed task/person list. The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `assign 1 p/2` Assigns the 2nd person in the person list to the 1st task in the task list. <br>
    <br>
    ![`assign 1 p/2](images/assignCommandShowcase.png)

### Unassigning a contact from a task: `unassign`
Unassigns a person in the contact list from a task.

Format: `unassign INDEX p/PERSONINDEX`

* Assigns the person at the specified `PERSONINDEX` to the task at `INDEX`. The indices refer to the index numbers shown in the corresponding displayed task/person list. The index **must be a positive integer** 1, 2, 3, …​
* If the person is not already assigned to the task, the operation will fail.
* The `view` command can help you quickly identify which contacts are already assigned to a task.

Examples:
* `unassign 1 p/2` Unassigns the 2nd person in the person list from the 1st task in the task list.
<br>
<br>
    ![`unassign 1 p/2](images/unassignCommandShowcase.png)


### Viewing contacts assigned to a task: `view`

Display all contacts assigned to a given task.

Format: `view INDEX`

* View all the contact assigned to the task located the specified `INDEX`
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `view 2` will display all contacts assigned to the 1st task in the task list. <br>
    <br>
    ![`view 2`](images/viewtCommandShowcase.png)



### Mark a task as done: `mark`

Marks the specified task from the task list as done.

Format: `mark INDEX`

* Marks the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​
* Icon will display a green tick to show the task is marked.

Examples:
* `mark 2` marks the task at index 2 as done <br>
    <br>
    ![`mark 2`](images/marktCommandShowcase.png)

### Unmark a task as not done: `unmark`

Unmarks the specified task from the task list as not done.

Format: `unmark INDEX`

* Unmarks the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​
* Icon will display a empty white box to show the task is unmarked.

Examples:
* `unmark 2` unmarks the task at index 2 as not done. <br>
    <br>
    ![`unmark 2`](images/unmarkCommandShowcase.png)

### Deleting a task: `deletet`

Deletes the specified task from the task list.

Format: `deletet 1`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `listt` followed by `deletet 1` lists out all the tasks in NUS Classes, then deletes the task at index 1.
* `findt lecture` followed by `deletet 2` lists out all tasks with the keyword `lecture`, then deletes the task at index 2. <br>
    <br> Finding the tasks by keyword `lecture`:
    ![`findt lecture` followed by `deletet 2`](images/deletettCommandShowcase1.png)
    <br>
    <br> Deleting the lecture at index 2 `deletet 2`:
    ![`deletet 2`](images/deletetCommandShowcase2.png)


# Other Features

### Clearing all contacts : `clear`

Clears all contacts from NUS Classes.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

NUS Classes data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

NUS Classes data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, NUS Classes will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous NUS Classes home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                                | Format, Examples                                                                                                                                           |
|---------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Contact**                       | `addc n/NAME p/PHONE_NUMBER e/EMAIL u/GIT_USERNAME [t/TAG]…​` <br> e.g., `add n/James Ho p/91234567 e/jamesho@example.com u/James123 t/friend t/colleague` |
| **List Contacts**                     | `listc`                                                                                                                                                    |
| **Edit Contact**                      | `editc INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [u/GITHUBUSERID] [t/TAG]…​`<br>                                                                           |
 | **Find Contact**                      | `findc KEYWORD [MORE_KEYWORDS]...`                                                                                                                         |
| **Delete**                            | `deletec INDEX`<br> e.g., `delete 3`                                                                                                                       |
| **Add Task**                          | `addt tn/TASKNAME dt/DATETIME[, ENDDATETIME] [t/TAG]…​ [z/LINK] [r/INTERVAL RECURRENCE]`                                                                   |
| **List Tasks**                        | `listt`                                                                                                                                                    |
| **Edit Task**                         | `editt INDEX [tn/TASKNAME] [dt/DATETIME, ENDDATETIME*] [t/TAG]`                                                                                            |
| **Find Task**                         | `findt KEYWORD [MORE_KEYWORDS]`                                                                                                                            |
| **Assign contact <br> To Task**       | `assign INDEX p/PERSONINDEX`                                                                                                                               |
| **Unassign contact <br> From Task**   | `unassign INDEX p/PERSONINDEX`                                                                                                                             |
| **View contacts<br>Assigned to Task** | `view INDEX`                                                                                                                                               |
| **Mark Task**                         | `mark INDEX`                                                                                                                                               |
| **Unmark Task**                       | `unmark INDEX`                                                                                                                                             |
| **Delete Task**                       | `deletet INDEX`                                                                                                                                            |
| **Clear all contacts**                | `clear`                                                                                                                                                    |
| **Exit**                              | `exit`                                                                                                                                                     |
