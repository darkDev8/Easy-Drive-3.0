# Easy Drive 3.5.32 ⚡
Easy drive is a simple online file manager, allow users to upload and modify files securely. <br>

The project is written in Intellij IDEA and the library via eclipse. <br>
Software needs "JDK11+" to run and it's runs on (Windows,Linux,Mac,Unix,...). <br>
This software uses MySQL for lolcal connection and Microsoft sql server for server connection. <br>
Software updated (3.4.19 -> 3.5.32).

You can upload up to 100MB for each user and it supports maximum 10 users -> 1GB total space. <br>
The software heavily uses sdk5 library, the link is **[here](https://github.com/mahdiDedsec/SDKLibrary-5.0).**

![ERROR](/shots/etitle.png)

## New Features

* New logo and prompt path color for Unix based operating systems.
* History command supports multi commands.
* Histories are in database instead of text file.
* Software support all operating systems(Win,Lin,Mac,Unix).
* New cls command for clearing screen.
* Disable all error colors.
* Invalid commands no longer save in history.
* New userconfig command for user configuration(username,password,hint,...)
* Lsu command also get title.
* Show upload status when starting upload file (add) command.
* Add changes command to show new features and updates.
* Disable login username case sensitive.
* New man page Styles.
* Faster animation speed for help command(animation is enabled).
* Change animation speed 1 -> 2.
* Disable case sensitive for commands(not arguments).
* Disabled case sensitive system in man and history commands.
* Cd command now supports ..\ command in backwards.
* New -s argument for lsc command.
* Add more man pages.

# Bug fixes
* Better animation performance (history,lsc,cat) commands.
* New -n argument in history command to show commands by number.
* Fix no -argument bug for show history command.
* Fix lsf command bug for showing all other user files.
* Fix download path bug.
* Fix memory heap space error for large files in add command.
* Fix bug for selecting file for commands.
* Fix bug NoSuchFileException error for creating multiple in directories.
* Fix man command nullPointerException for option argument.


## Commands

The commands are listed below.

![ERROR](/shots/help.jpg)

## Run

```
1.  Start xampp and run MySQL server.
2.  Go to localhost in browser.
3.  Create new database "esaydrive".
4.  Click import from above tabs.
5.  Select database/easydrive.sql.
6.  Go to out/artifacts/Easy_Drive_3_0_jar.
7.  Open cmd or powershell.
8.  Type "login.exe" or "login".
9.  Enter username and password(default -> username = mahdi, password = 23549918).
10. Type lsu to list registered users.
11. Fix some cd command bugs.
```
