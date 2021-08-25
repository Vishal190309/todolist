package com.bawp.todoister.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_table")
public class Task {
    @ColumnInfo(name = "task_id")
    @PrimaryKey(autoGenerate = true)
    private int taskId;
    @ColumnInfo(name = "task_name")
    private String taskName;

    private Priority priority;
    @ColumnInfo(name = "due_date")
    private Date dueDate;
    @ColumnInfo(name = "today_date")
    private Date todayDate;
    @ColumnInfo(name = "is_done")
    private boolean isDone;

    public Task(String taskName, Priority priority, Date dueDate, Date todayDate, boolean isDone) {
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.todayDate = todayDate;
        this.isDone = isDone;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(Date todayDate) {
        this.todayDate = todayDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
