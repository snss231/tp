---
layout: page
title: Sean's Project Portfolio Page
---

### Project: NUS Classes

NUS Classes is an all-in-one task and contacts organizer for NUS Computing professors. It is written in Java.

Given below are my contributions to the project.

* **New feature**: Implement the model and storage parts to facilitate a `Task` entity
  * What it does: Allow users to add their tasks to NUS Classes.
  * Justification: Users can use the feature to be well organised with their tasks


* **New Feature**: Assign, unassign command 
  * What it does: Allow users to assign and unassign contacts from tasks 
  * Justification: Users can use the feature for bookkeeping purposes as well as to facilitate other contact-related commands.
  * Highlights: Users can use the `gen` command to easily generate and copy all emails related to a task for ease of communication.


* **New Feature**: Generate emails command
  * What it does: Users can use the `gen` command to easily generate and copy all emails related to a task, then paste into their preferred email client.
  * Justification: Users have an easy way to contact all contacts related to a task in case urgent dissemination of information is required (e.g. in the event of unforeseen circumstances)


* **New Feature**: Date and time range for events
  * What it does: Users can specify a stand and end date and time for tasks (as opposed to just one single deadline) to represent events that occur during a specific interval.
  * Justification: Users can easily keep track of start and end timings for events.
  

* **New Feature**: User-friendly date and time display
  * What it does: Instead of displaying all dates in full e.g.`13 May 2022, 3.00pm - 13 May 2022, 5.00pm`, dates are displayed relative to the current date
  * Examples:
    * `Today` for tasks that occur on the current calendar day, 
    * `Friday` for tasks that occur on the same week 
    * `13 Apr` (omitting the year for tasks that occur on the same calendar year)
    * `Today, 3.00 pm - 5.00 pm` (not repeating the end date when it is the same as the start date)
  * Justification: Dates are far more intuitive and easy to read for the user.


* **New Feature**: Import contacts from .csv
  * What it does: Import contacts from a .csv file to create contacts in NUS Classes.
  * Justification: It can be incredibly time-consuming and tedious to add all contacts manually.
  * Highlights: Users can easily export contacts from a spreadsheet containing contacts and relevant headers to import the contacts into NUS Classes.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=snss231&tabRepo=AY2122S2-CS2103T-T12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **Project management**:
  * Managed all releases `v1.1` - `v1.4` (4 releases) on GitHub


* **Enhancements to existing features**:
  * Updates and deletions to contacts are propagated to all tasks to ensure consistency in the model side of things.
  * Limit tag length to 50 characters to avoid UI bugging out (part of the tag getting obscured)
  * Polish GUI text feedback (e.g. message usages, fix typos and phrase messages better)
  * Adjust name constraints to allow symbols that are commonly present in names (e.g. `Martin Luther King, Jr.`, `Joseph Lewitt-Hewman`, `Raj s/o Rajesh`)
  * Change `clear` command to delete task data as well as contact data


* **Documentation**:
  * User Guide:
    * Added documentation for the features `clear`, `import` and `gen`
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `deletec` feature.
    * Added design considerations for how tasks should be updated when contacts are deleted or updated.
    * Updated text to reflect that the storage now handles both tasks and contacts
    * Updated all UML class architecture diagrams to reflect the current status
  * Readme.md, index.md
    * Update links to CI and Codecov that was previously linking to AB-3


* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#18](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/18), [\#59](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/59), [\#68](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/68), [\#83](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/83), [\#95](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/95), [\#98](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/98), [\#120](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/120), [\#121](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/121), [\#135](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/135), [\#170](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/170), [\#183](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/183), [\#249](https://github.com/AY2122S2-CS2103T-T12-4/tp/pull/249)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2122S2/forum/issues/241))
