package com.bawp.todoister.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bawp.todoister.model.Task;
import com.bawp.todoister.util.TaskRoomDatabase;

import java.util.List;

public class Repository {
    private final LiveData<List<Task>> allTasks;
    private final TaskDao taskDao;

    public Repository(Application application) {
        TaskRoomDatabase taskRoomDatabase = TaskRoomDatabase.getDatabase(application);
        taskDao = taskRoomDatabase.taskDao();
        allTasks = taskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public LiveData<Task> getTask(long id) {

        return taskDao.getTask(id);

    }

    public void insert(Task task) {
        TaskRoomDatabase.databaseExecutor.execute(() ->
                taskDao.insertTask(task)
        );
    }

    public void delete(Task task) {
        TaskRoomDatabase.databaseExecutor.execute(() ->
                taskDao.deleteTask(task)
        );
    }

    public void update(Task task) {
        TaskRoomDatabase.databaseExecutor.execute(() ->
                taskDao.updateTask(task)
        );
    }

}
