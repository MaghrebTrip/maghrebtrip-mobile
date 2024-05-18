package com.maghrebtrip.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.maghrebtrip.R;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SelectionActivity extends AppCompatActivity {

    // For number picker
    private EditText adultsAndChildrenEditText;
    private EditText RoomsEditText;
    private int numberOfAdults = 0;
    private int numberOfChildren = 0;
    private int numberOfRooms=0;

    // For date picker
    private EditText checkInDateEditText;
    private EditText checkOutDateEditText;
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener checkInDateSetListener;
    private DatePickerDialog.OnDateSetListener checkOutDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection); // Replace "your_layout" with your layout file name

        // Initialize number picker EditText
        adultsAndChildrenEditText = findViewById(R.id.numberOfGuestsEditText);
        adultsAndChildrenEditText.setOnClickListener(v -> showNumberPickerDialog());
        adultsAndChildrenEditText.setInputType(InputType.TYPE_NULL);
        // Initialize date picker EditTexts and Calendar
        checkInDateEditText = findViewById(R.id.checkInDateEditText);
        checkOutDateEditText = findViewById(R.id.checkOutDateEditText);
        checkInDateEditText.setInputType(InputType.TYPE_NULL);
        checkOutDateEditText.setInputType(InputType.TYPE_NULL);
        calendar = Calendar.getInstance();
        //for Room selection
        RoomsEditText=findViewById(R.id.numberOfRoomsEditText);
        RoomsEditText.setInputType(InputType.TYPE_NULL);
        RoomsEditText.setOnClickListener(v -> showNumberPickerDialogRoom());



        // Set up the listeners for check-in and check-out date pickers
        checkInDateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);
            updateDateInView(checkInDateEditText);
        };

        checkOutDateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);
            updateDateInView(checkOutDateEditText);
        };
        // Continue button
        findViewById(R.id.btnContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionActivity.this, PlansActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to show the number picker dialog
    private void showNumberPickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.guest_picker_dialog, null);
        builder.setView(dialogView);

        TextView numberOfAdultsTextView = dialogView.findViewById(R.id.numberOfAdults);
        TextView numberOfChildrenTextView = dialogView.findViewById(R.id.numberOfChildren);
        View adultMinusButton = dialogView.findViewById(R.id.adultMinusButton);
        View adultPlusButton = dialogView.findViewById(R.id.adultPlusButton);
        View childMinusButton = dialogView.findViewById(R.id.childMinusButton);
        View childPlusButton = dialogView.findViewById(R.id.childPlusButton);

        numberOfAdultsTextView.setText(String.valueOf(numberOfAdults));
        numberOfChildrenTextView.setText(String.valueOf(numberOfChildren));

        adultMinusButton.setOnClickListener(v -> {
            if (numberOfAdults > 0) {
                numberOfAdults--;
                numberOfAdultsTextView.setText(String.valueOf(numberOfAdults));
            }
        });

        adultPlusButton.setOnClickListener(v -> {
            numberOfAdults++;
            numberOfAdultsTextView.setText(String.valueOf(numberOfAdults));
        });

        childMinusButton.setOnClickListener(v -> {
            if (numberOfChildren > 0) {
                numberOfChildren--;
                numberOfChildrenTextView.setText(String.valueOf(numberOfChildren));
            }
        });

        childPlusButton.setOnClickListener(v -> {
            numberOfChildren++;
            numberOfChildrenTextView.setText(String.valueOf(numberOfChildren));
        });


        builder.setPositiveButton("Done", (dialog, which) -> {
            String text = "Adults: " + numberOfAdults + ", Children: " + numberOfChildren;
            adultsAndChildrenEditText.setText(text);
            dialog.dismiss();
        });


        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Method for room selection
    private void showNumberPickerDialogRoom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.room_picker_dialog, null);
        builder.setView(dialogView);


        TextView numberOfRoomsTextView = dialogView.findViewById(R.id.numberOfRooms);
        View roomMinusButton = dialogView.findViewById(R.id.roomMinusButton);
        View roomPlusButton = dialogView.findViewById(R.id.roomPlusButton);


        numberOfRoomsTextView.setText(String.valueOf(numberOfRooms));


        roomMinusButton.setOnClickListener(v -> {
            if (numberOfRooms > 0) {
                numberOfRooms--;
                numberOfRoomsTextView.setText(String.valueOf(numberOfRooms));
            }
        });

        roomPlusButton.setOnClickListener(v -> {
            numberOfRooms++;
            numberOfRoomsTextView.setText(String.valueOf(numberOfRooms));
        });




        builder.setPositiveButton("Done", (dialog, which) -> {
            String text = "Room(s): " + numberOfRooms;
            RoomsEditText.setText(text);
            dialog.dismiss();
        });


        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Method to show the Date Picker for check-in date
    public void showCheckInDatePicker(View v) {
        new DatePickerDialog(this, checkInDateSetListener, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    // Method to show the Date Picker for check-out date
    public void showCheckOutDatePicker(View v) {
        new DatePickerDialog(this, checkOutDateSetListener, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    // Method to update the selected date in the EditText field
    private void updateDateInView(EditText editText) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        editText.setText(dateFormat.format(calendar.getTime()));
    }

    // Method to handle onClick for Number of Guests EditText
    public void showGuestPickerDialog(View v) {
        showNumberPickerDialog();
        // Implement your logic for showing guest picker dialog
    }

    // Method to handle onClick for Number of Rooms EditText
    public void showRoomPickerDialog(View v) {
        showNumberPickerDialogRoom();
        // Implement your logic for showing room picker dialog
    }

}
