#include <iostream>

#include <conio.h>
#include <string.h>
#include <windows.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <thread>

#define ENTER 13
#define ENTER2 10
#define TAB 9
#define BKSP 8
#define BKSP2 127
#define ESC 033

using std::cout, std::cin, std::endl, std::string;

void print_rectangle(int n, int m, int termWidth, int termHeight);

string getInput(bool star, int length);

void print(string input, bool nextLine);

int getTerminalWidth();

int getTerminalHeight();

void gotoxy(int x, int y);

void clrscr();

void textcolor(int color);

void textbackground(int);

enum {
    BLACK,
    RED,
    GREEN,
    BROWN,
    BLUE,
    MAGENTA,
    CYAN,
    LIGHTGRAY,
    DARKGRAY,
    LIGHTRED,
    LIGHTGREEN,
    YELLOW,
    LIGHTBLUE,
    LIGHTMAGENTA,
    LIGHTCYAN,
    WHITE,
};


int main() {
    int width = getTerminalWidth(), height = getTerminalHeight();
    clrscr();

    print_rectangle(height -1 , width, width, height - 1);
    print_rectangle(4, 35, width, height);

    int x = (width - 30) / 2, y = height / 2;
    gotoxy(x, y);

    string username = getInput(false, 31);

    y = (height / 2) + 1;
    gotoxy(x, y);

    string password = getInput(true, 31);

    size_t found = username.find(' ');
    if (found != string::npos) {
        username = "\"" + username + "\"";
    }

    found = password.find(' ');
    if (found != string::npos) {
        password = "\"" + password + "\"";
    }

    string key = "4H/eZQjV2cn66nbOU32pKg==";
    string command = "java -jar drive.jar " + username + " " + password + " " + key;

    clrscr();
    printf("\n");

    system(command.c_str());
    return 0;
}

void print(string input, bool nextLine) {
    char array [input.length() + 1];
    strcpy(array, input.c_str());

    for (int i = 0; i < input.length(); i++) {
        std::this_thread::sleep_for(std::chrono::microseconds(500));
        fputc(array[i], stdout);
    }

    if (nextLine) {
        cout << endl;
    }
}


void print_rectangle(int n, int m, int termWidth, int termHeight) {
    int i, j, widthSize = (termWidth - m) / 2, heightSize = (termHeight - n) / 2;

    gotoxy(widthSize, heightSize);

    for (i = 1; i <= n; i++) {
        for (j = 1; j <= m; j++) {
            gotoxy(j + widthSize, i + heightSize);
            if (i == 1 || i == n) {
                print("-", false);
            } else if (j == 1 || j == m) {
                print(".", false);
            } else {
                print(" ", false);
            }
        }
        print("\n", false);
    }
}

string getInput(bool star, int length) {
    char pwd[length + 1], ch;
    int i = 0;

    while (true) {
        ch = getch();

        if (i == length) {
            break;
        }

        if (ch == ENTER || ch == TAB || ch == ENTER2) {
            pwd[i] = '\0';
            break;
        } else if (ch == BKSP || ch == BKSP2) {
            if (i > 0) {
                i--;
                printf("\b \b");
            }
        } else {
            pwd[i++] = ch;

            if (star) {
                printf("* \b");
            } else {
                fputc(ch, stdout);
            }
        }
    }

    string input(pwd);
    return input;
}

int getTerminalWidth() {
    CONSOLE_SCREEN_BUFFER_INFO csbi;

    GetConsoleScreenBufferInfo(GetStdHandle(STD_OUTPUT_HANDLE), &csbi);
    return csbi.srWindow.Right - csbi.srWindow.Left + 1;
}

int getTerminalHeight() {
    CONSOLE_SCREEN_BUFFER_INFO csbi;

    GetConsoleScreenBufferInfo(GetStdHandle(STD_OUTPUT_HANDLE), &csbi);
    return csbi.srWindow.Bottom - csbi.srWindow.Top + 1;
}

void gotoxy(int x, int y){
  COORD coord;
  coord.X = x;
  coord.Y = y;

  SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

void clrscr() {
    system("cls");
}

void textcolor(int color) {
    if (color >= BLACK && color <= LIGHTGRAY) /* dark color */
        printf("%c[0;%dm", ESC, 30 + color);
    else
        printf("%c[1;%dm", ESC, 30 + (color - 8));
}

void textbackground(int color) {
    printf("%c[%dm", ESC, 40 + color);
}
