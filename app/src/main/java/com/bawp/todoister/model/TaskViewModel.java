package com.bawp.todoister.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bawp.todoister.data.Repository;
import com.bawp.todoister.util.TaskRoomDatabase;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    public final LiveData<List<Task>> allTasks;
    public static Repository repository;
    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allTasks = repository.getAllTasks();
    }
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
    public LiveData<Task> getTask(long id) {

        return repository.getTask(id);

    }

    public static void insert(Task task) {
        repository.insert(task);
    }

    public static void delete(Task task) {
        repository.delete(task);
    }

    public static void update(Task task) {
        repository.update(task);
    }
}
