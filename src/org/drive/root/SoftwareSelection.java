package org.drive.root;

import org.drive.softwares.*;

public class SoftwareSelection {

    public SoftwareSelection(String command) {
        Shell.optimizeCommand(command);
    }

    public void exit() {
        new Exit().exitSoftware();
    }

    public void envs() {
        new Envs().printVariables();
    }

    public void echo() {
        new Echo().printText();
    }

    public void pwd() {
        new Pwd().printPath();
    }

    public void lsc() {
        new Lsc().listFiles();
    }

    public void cd() {
        new Cd().changeDirectory();
    }

    public void clear() {
        new Clear().clearScreen();
    }

    public void wc() {
        new Wc().wordCount();
    }

    public void count() {
        new Count().countTable();
    }

    public void id() {
        new Id().showFileId();
    }

    public void add() {
        new Add().addNewFile();
    }

    public void cat() {
        new Cat().openFile();
    }

    public void rma() {
        new Rma().removeAll();
    }

    public void sfi() {
        new Sfi().showFileInformation();
    }

    public void rm() {
        new Rm().removeFile();
    }

    public void get() {
        new Get().downloadFile();
    }

    public void rn() {
        new Rn().renameFile();
    }

    public void lsf() {
        new Lsf().listDatabaseFiles();
    }

    public void history() {
        new History().manageHistory();
    }

    public void info() {
        new Info().showFileInformation();
    }

    public void lsu() {
        new Lsu().listUsers();
    }

    public void update() {
        new Update().updateContent();
    }

    public void mkdir() {
        new Mkdir().makeDirectories();
    }

    public void help() {
        new Help().showHelp();
    }

    public void man() {
        new Man().showManPage();
    }

    public void userconfig() {
        new UserConfig().configUser();
    }

    public void changes() {
        new Changes().showChanges();
    }

    public void find() {
        new Find().findItems();
    }
}
