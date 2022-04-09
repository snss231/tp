---
layout: page

title: Ong Jun Jie Project Portfolio Page
---

### Project: NUSClasses

NUS Classes is a desktop app for NUS Computing professors to manage their tasks and contacts. It includes task management features such as
creating tasks, tagging tasks, assigning contacts to tasks, and marking tasks as complete or incomplete. It also includes contact management features such as finding contacts, assigning contacts to specific tasks and tagging contacts.

Given below are my contributions to the project.

* **New Feature**: Delete Task feature
  * What it does: Delete the task from the task list based on index
  * Justification: Important for user to remove the task that is not needed
  * Credits: *{code ideas from AB3 delete contacts feature}*

* **New Feature**: Edit Task feature
  * What it does: Edit the selected task based on the field(s) user want to edit
  * Justification: Important for user to edit the task to change or update the information
  * Highlights: 
    * This enhancement requires in-depth analysis on the class `EditPersonDescriptor` and modify it to suit for `Task`. 
    * Able to construct `EditTaskDescriptor` and provide flexibility to modify it if there is any changes to the fields in class`Task`
  * Credits: *{code ideas from AB3 edit contacts feature}*

* **New Feature**: Mark and Unmark Task feature
  * What it does: 
    * Mark feature allows the user to set the task as done
    * Unmark feature allows the user to set the task as not done
  * Justification: User may want to know which task has completed
  * Highlights: This enhancement requires to understand the structure of `UI` and have to modify the `taskcard` UI and FMXL to hold image that represent the checkbox.

* **New Feature**: List Task feature
  * What it does: List the task based on the prefix(all/, c/, nc/) input by user.
  * Justification: User may prefer to see all the available task, or see list of completed/uncompleted task.
  * Credits: *{code ideas from AB3 list feature with modification}*

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=junjunjieOng&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18)

* **Project management**:
  * Managed releases `v1.2` - `v1.4rc` (3 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `deletet` [\#6](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/6)
    * Added documentation for the features `editt` [\#86](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/86)
    * Added documentation for the features `mark` and `unmark` [\#150](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/150)
    * Added documentation for the features `listt` [\#154](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/154)
    * Annotated half of the document images for clearer explanation [\#276](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/276)
  * Developer Guide:
    * Updated `user profile` and `value proposition` [\#7](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/7/files)
    * Updated `User Stories` and `Non-functional requirement` [\#7](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/7/files)
    * Added implementation detail of `deletet` and `editt` feature [\#107](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/107)
    * Created activity diagram and Sequence diagram for `editt` and `deletet` feature [\#107](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/107)

* **Community**:
  * Total PRs reviewed (with non-trivial review comments): **14**
    * [\#106](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/106), [\#114](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/114), 
    [\#145](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/145), [\#149](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/149),
    [\#158](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/158), [\#175](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/175), 
    [\#183](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/183), [\#251](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/251),
    [\#255](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/255), [\#260](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/260),
    [\#263](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/263), [\#276](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/276),
    [\#278](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/278), [\#281](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/281)
  * Reported bugs and suggestions for other team in [PE-D](https://github.com/junjunjieOng/ped/issues)


