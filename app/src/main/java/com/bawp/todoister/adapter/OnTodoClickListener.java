package com.bawp.todoister.adapter;

import com.bawp.todoister.model.Task;

public interface OnTodoClickListener {
    void onClick(Task task);
    void onRadioButtonClick(Task task);
}
