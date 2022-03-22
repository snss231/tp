---
layout: page
title: User Guide
---

NUS Classes is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, NUS Classes can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `nusclasses.jar` from [here](https://github.com/AY2122S2-CS2103T-T12-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your NUS Classes manager.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * **`list`** : Lists all contacts.

    * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

    * **`delete`**`3` : Deletes the 3rd contact shown in the current list.


   * `remove` `tn/TA meeting cn/john`: Removes the TA meeting task from contact name john.

   * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

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


### Adding a person: `addc`

Adds a contact.

Format: `addc n/CONTACTNAME p/PHONENUMBER e/EMAIL u/USERNAME t/TAGS`

Examples:

* `addc n/john p/12345678 e/john@gmail.com t/Schoolmate`
* `addc n/mary p/87654321 e/mary@gmail.com t/Teammate Classmate`

<div markdown="span" class="alert alert-warning">:bulb: **Tip**
You can add multiple tags to a contact. Just put t/ before every tag!

</div>

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Filtering tasks by name: `filter`

Filters out tasks whose task names contain any of the given keywords.

Format: `filter KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `eat` will match `Eat`
* The order of the keywords does not matter. e.g. `Eat Apple` will match `Apple Eat`
* Only the name is searched.
* Only full words will be matched e.g. `Apples` will not match `Apple`
* Tasks matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Apple Pear` will return `Eat apple`, `Buy pear`

Examples:
* `filter apple` returns `Buy apple` and `Make apple juice`
* `filter orange pear` returns `Buy orange`, `Buy pear`<br>

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Adding a task: `addt`

Adds a task for a datetime with a tag.

Format: `addt tn/TASKNAME dt/DATETIME[, ENDDATETIME] t/TAG`

Examples:
* `addt tn/Meeting dt/17-03-2022 1800 t/School` Adds a task called Meeting for 17th March 2022, 6pm at School
* `addt tn/Consultation dt/19-03-2022 1500, 19-03-2022 1600` Adds a task called Consultation taking place from `19th March 2022 3-4pm`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
The format for TIME is in dd-mm-yyyy hhmm.
</div>

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
There cannot be an already existing tag with the same name; tags must be unique.

</div>

### Delete a task: `deletet` 

Deletes the specified task from the task list.

Format: `deletet 1`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `deletet 1` Deletes the task at index 1.
* `deletet 2` Deletes the task at index 2.


### Editing a task: `updatet`
Edit and updates an existing task in the task list.

* Updates the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
* `updatet 1 n/Meeting with TAs` Updates the name of the 1st displayed task to be `Meeting with TAs`
* `updatet 2 n/Meeting with Prof Tan dt/2022-12-01 1200` Updates the name of the 2nd displayed task to be `Meeting with Profs Tan` and the date to be 1st Dec 2022, 12pm.
* `updatet 1 dt/2022-12-12 1200, 2022-12-12 1400` Updates the datetime of the 1st displayed task to be on `12th Dec 2022, 12-2pm.`

Format: `updatet INDEX [tn/TASKNAME] [dt/DATETIME, ENDDATETIME*] [t/TAG]`



<div markdown="span" class="alert alert-warning">:bulb: **Tip**
If there's no need to change a certain field you can leave it out!<br>
:bulb: **Tip** *[, ENDDATETIME] is optional.
</div>

### Assigning a contact to a task: `assign`
Assigns a person in the contact list to a task.

* Assigns the person at the specified `PERSONINDEX` to the task at `INDEX`. The indices refer to the index numbers shown in the corresponding displayed task/person list. The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `assign 1 p/ 2` Assigns the 2nd person in the person list to the 1st task in the task list.

### Unassigning a contact from a task: `unassign`
Unassigns a person in the contact list from a task.

* Assigns the person at the specified `PERSONINDEX` to the task at `INDEX`. The indices refer to the index numbers shown in the corresponding displayed task/person list. The index **must be a positive integer** 1, 2, 3, …​
* If the person is not already assigned to the task, the operation will fail.
* The `view` command can help you quickly identify which contacts are already assigned to a task.

Examples:
* `unassign 1 p/ 2` Unassigns the 2nd person in the person list from the 1st task in the task list.

### Viewing contacts assigned to a task: `view`

Display all contacts assigned to a given task.

Format: `view INDEX`

* View all the contact assigned to the task located the specified `INDEX`
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `view 1` will display all contacts assigned to the 1st task in the task list.


### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action       | Format, Examples                                                                                                                                                      |
|--------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**      | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**    | `clear`                                                                                                                                                               |
| **Delete**   | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Remove**   | `remove tn/TASKNAME cn/CONTACTNAME`<br> e.g., `remove tn/taskname cn/john`                                                                                            |
| **Edit**     | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**     | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List**     | `list`                                                                                                                                                                |
| **Tag Task** | `tag [tn/TASKNAME] [tg/INDEX]` e.g., `tag tn/attend meeting @utown tg/1`                                                                                              |
| **Help**     | `help`                                                                                                                                                                |
