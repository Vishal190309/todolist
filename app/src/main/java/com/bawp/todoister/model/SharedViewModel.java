package com.bawp.todoister.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Task> selectedTask = new MutableLiveData<>();
    private boolean isEdit;

    public void setSelectedTask(Task task){
        selectedTask.setValue(task);
    }
    public LiveData<Task> getSelectedTask(){
        return selectedTask;
    }
    public void isEditable(boolean isEdit){
        this.isEdit = isEdit;
    }
    public boolean getEdit(){
        return isEdit;
    }
}
