package com.bawp.todoister;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.ViewModelProvider;

import com.bawp.todoister.model.Priority;
import com.bawp.todoister.model.SharedViewModel;
import com.bawp.todoister.model.Task;
import com.bawp.todoister.model.TaskViewModel;
import com.bawp.todoister.util.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;

public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    private EditText enterTodo;
    private ImageButton calendarButton;
    private ImageButton priorityButton;
    private ImageButton saveButton;
    private CalendarView calendarView;
    private RadioGroup radioGroup;
    private int selectedButtonId;
    private Date dueDate;
    private Group calendarGroup;
    private SharedViewModel sharedViewModel;
    private Priority priority;
    Calendar calendar = Calendar.getInstance();
    public BottomSheetFragment(){

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        setStyle(BottomSheetFragment.STYLE_NORMAL,R.style.DialogStyle);
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        calendarButton = view.findViewById(R.id.today_calendar_button);
        priorityButton = view.findViewById(R.id.priority_todo_button);
        saveButton = view.findViewById(R.id.save_todo_button);
        enterTodo = view.findViewById(R.id.enter_todo_et);
        calendarView = view.findViewById(R.id.calendar_view);
        calendarGroup = view.findViewById(R.id.calendar_group);
        radioGroup = view.findViewById(R.id.radioGroup_priority);



        Chip todayChip = view.findViewById(R.id.today_chip);
        todayChip.setOnClickListener(this);
        Chip tomorrowChip = view.findViewById(R.id.tomorrow_chip);
        tomorrowChip.setOnClickListener(this);
        Chip nextWeekChip = view.findViewById(R.id.next_week_chip);
        nextWeekChip.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(sharedViewModel.getSelectedTask().getValue()!=null){
            Task task = sharedViewModel.getSelectedTask().getValue();
            enterTodo.setText(task.getTaskName());

        }
    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        enterTodo.requestFocus();
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        calendarButton.setOnClickListener(view12 -> {
            calendarGroup.setVisibility(
                    calendarGroup.getVisibility() ==View.VISIBLE ? View.GONE:View.VISIBLE
            );
            Utils.hideKeyboard(view12);
        });
        priorityButton.setOnClickListener(view13 -> {
            Utils.hideKeyboard(view13);
            radioGroup.setVisibility(radioGroup.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);

            radioGroup.setOnCheckedChangeListener((radioGroup, checkedButton) -> {
                if (radioGroup.getVisibility() == View.VISIBLE) {
                    selectedButtonId = checkedButton;

                    if (selectedButtonId == R.id.radioButton_high) {
                        priority = Priority.HIGH;
                    } else if (selectedButtonId == R.id.radioButton_med) {
                        priority = Priority.MEDIUM;
                    } else if (selectedButtonId == R.id.radioButton_low) {
                        priority = Priority.lOW;
                    } else {
                        priority = Priority.lOW;
                    }
                }
            });


        });
        calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
            calendar.clear();
            calendar.set(year,month,dayOfMonth);
            dueDate = calendar.getTime();
        });
        saveButton.setOnClickListener(view1 -> {
            String task = enterTodo.getText().toString().trim();

            if (!TextUtils.isEmpty(task) && dueDate !=null && priority!=null){
                Task todoTask = new Task(task, priority,dueDate,Calendar.getInstance().getTime(),false);
                if (sharedViewModel.getEdit()){
                    Task updateTask = sharedViewModel.getSelectedTask().getValue();
                    updateTask.setTaskName(task);
                    updateTask.setDueDate(dueDate);
                    updateTask.setTodayDate(Calendar.getInstance().getTime());
                    updateTask.setPriority(priority);
                    TaskViewModel.update(updateTask);
                    sharedViewModel.isEditable(false);
                }else {
                    TaskViewModel.insert(todoTask);
                }
                enterTodo.setText("");
                if (this.isVisible()){
                    this.dismiss();
                }
            }else {
                Snackbar.make(saveButton,R.string.empty_field, BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        calendar = Calendar.getInstance();
        if (id==R.id.today_chip){
            calendar.add(Calendar.DAY_OF_YEAR,0);
            dueDate=calendar.getTime();

            Log.d("TAG", "onClick: " +dueDate);
        }else if (id==R.id.tomorrow_chip){
            calendar.add(Calendar.DAY_OF_YEAR,1);
            dueDate=calendar.getTime();

            Log.d("TAG", "onClick: " +dueDate);
        }else if (id==R.id.next_week_chip){
            calendar.add(Calendar.DAY_OF_YEAR,7);
            dueDate=calendar.getTime();

            Log.d("TAG", "onClick: " +dueDate);
        }
    }
}