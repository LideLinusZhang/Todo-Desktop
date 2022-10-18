module com.example.todo_desktop.ui {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires kotlin.stdlib;
    requires java.desktop;
    requires tornadofx;

    opens com.example.todo_desktop.ui;
    exports com.example.todo_desktop.ui;
    exports com.example.todo_desktop.controller;
    exports com.example.todo_desktop.common;
    exports com.example.todo_desktop.app;
    exports com.example.todo_desktop.data;
}