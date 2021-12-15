package edu.gmu.cs477.fall2020.finalproject_tnguye87;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Hashtable;
import java.util.List;

public class SingleTutorialView extends AppCompatActivity {

    private String tutorial; // used to get teh user selected tutorial from ViewAllTutorial.java
    private TextView diySavingNote, instruction;
    private Button next, previous;
    /* a hashtable for storing saving information */
    Hashtable<String, Integer> allMyDIYSaving =  new Hashtable<String, Integer>();

    int totalStep = 0;
    AllTutorialInfo info = new AllTutorialInfo();

    private ImageSwitcher tutorialImage;
    private TextSwitcher instructionDetail;

    public final static String SINGLE_TUTORIAL_VIEW = "edu.gmu.cs477.fall2020.finalprojecttnguye87.SINGLE_TUTORIAL_VIEW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_tutorial_view);

        Intent i = getIntent();

        // setup saving table
        allMyDIYSaving = setUpSaving();

        // retrieved the name of the tutorial selected by user from the ViewAllTutorial.java
        tutorial = i.getStringExtra(ViewAllTutorial.SINGLE_TUTORIAL_MESSAGE);
        diySavingNote = (TextView) findViewById(R.id.diySavingNote);
        String diySavingInfo = "By following this " + tutorial + " tutorial, you are saving $" + allMyDIYSaving.get(tutorial);
        diySavingNote.setText(diySavingInfo);

        instruction = (TextView) findViewById(R.id.instruction);
        instruction.setText(tutorial.toUpperCase());

        /* Disable the previous button */
        previous = (Button) findViewById(R.id.previousBtn);
        previous.setEnabled(false);

        /* Setup the ImageSwitcher */
        tutorialImage = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        tutorialImage.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                return myView;
            }
        });
        int [] IMAGES = getIMAGES(info, tutorial);
        System.out.println("IMAGES: " + IMAGES[0]);
        if (IMAGES[0] != 0) tutorialImage.setImageResource(IMAGES[totalStep]);
        else Toast.makeText(getApplicationContext(), "No Image Information Available", Toast.LENGTH_SHORT).show();

        /* Setup the TextSwitcher */
        instructionDetail = (TextSwitcher) findViewById(R.id.textSwitcher);
        instructionDetail.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(SingleTutorialView.this);
                textView.setTextSize(17);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                return textView;
            }
        });
        String [] INSTRUCTION = getINSTRUCTION(info, tutorial);
        if (!INSTRUCTION[0].equalsIgnoreCase("empty")) instructionDetail.setText(INSTRUCTION[totalStep]);
        else Toast.makeText(getApplicationContext(), "No Instruction Information Available", Toast.LENGTH_SHORT).show();

    }

    /**
     * Private function to get the right array of IMAGES from the AllTutorialInfo.java
     * base on the tutorial string receive from ViewAllTutorial.java
     * @param info the AllTutorialInfo.java
     * @param tutorial the tutorial receive from ViewAllTutorial.java
     * @return corresponding array of int IMAGES
     */
    private int [] getIMAGES (AllTutorialInfo info, String tutorial) {
        int [] toReturn =  {0};
        if (tutorial.equalsIgnoreCase("Oil Change")) return info.getOilChangeImages();
        else if (tutorial.equalsIgnoreCase("Engine Air Filter Replacement")) return info.getENGINE_AIR_FILTER_IMAGES();
        else if (tutorial.equalsIgnoreCase("Cabin Air Filter")) return info.getCABIN_AIR_FILTER_REPLACEMENT_IMAGES();
        else if (tutorial.equalsIgnoreCase("Tire Rotation")) return info.getTIRE_ROTATION_IMAGES();
        else if (tutorial.equalsIgnoreCase("Windshield Fluid Top Up")) return info.getWINDSHIELD_FLUID_TOP_UP_IMAGES();
        else return toReturn;

    }

    /**
     * Private function to get the right array of INSTRUCTION from the AllTutorialInfo.java
     * base on the tutorial string receive from ViewAllTutorial.java
     * @param info the AllTutorialInfo.java
     * @param tutorial the tutorial receive from ViewAllTutorial.java
     * @return corresponding array of string INSTRUCTION
     */
    private String [] getINSTRUCTION (AllTutorialInfo info, String tutorial) {
        String [] toReturn = {"empty"};
        if (tutorial.equalsIgnoreCase("Oil Change")) return info.getOilChangeInstruction();
        else if (tutorial.equalsIgnoreCase("Engine Air Filter Replacement")) return info.getENGINE_AIR_FILTER_REPLACEMENT_INSTRUCTION();
        else if (tutorial.equalsIgnoreCase("Cabin Air Filter")) return info.getCABIN_AIR_FILTER_REPLACEMENT_INSTRUCTION();
        else if (tutorial.equalsIgnoreCase("Tire Rotation")) return info.getTIRE_ROTATION_INSTRUCTION();
        else if (tutorial.equalsIgnoreCase("Windshield Fluid Top Up")) return info.getWINDSHIELD_FLUID_TOP_UP_INSTRUCTION();
        else return toReturn;
    }

    /**
     * private function to setup all saving for the allMyDIYSaving hashtable
     * for retrieving the save info for each tutorial
     * @return the hashtable with all
     */
    private Hashtable<String, Integer> setUpSaving () {
        Hashtable<String, Integer> allMyDIYSaving = new Hashtable<String, Integer>();
        allMyDIYSaving.put("Oil Change", 30);
        allMyDIYSaving.put("Engine Air Filter Replacement", 25);
        allMyDIYSaving.put("Cabin Air Filter", 20);
        allMyDIYSaving.put("Tire Rotation", 40);
        allMyDIYSaving.put("Windshield Fluid Top Up", 10);
        return allMyDIYSaving;
    }

    /**
     * function to move to next steps
     * set the previous button to enable if totalStep > 0
     * set the next button to disable if the totalStep == the last element of the IMAGES or INSTRUCTION
     * @param v (view)
     */
    public void btnNext (View v) {
        previous = (Button) findViewById(R.id.previousBtn);
        next = (Button) findViewById(R.id.nextBtn);

        int [] IMAGES = getIMAGES(info, tutorial);
        String [] INSTRUCTION = getINSTRUCTION(info, tutorial);

        int length = IMAGES.length - 1;// the last element of the IMAGES/INSTRUCTION

        if (IMAGES != null) tutorialImage.setImageResource(IMAGES[++totalStep]);
        if (INSTRUCTION != null) instructionDetail.setText(INSTRUCTION[totalStep]);
        if (totalStep > 0) previous.setEnabled(true);
        if (totalStep == length) next.setEnabled(false);
    }

    /**
     * Function to move to previous steps
     * set the previous button to enable if totalStep == 0
     * set the next button to disable if the totalStep < the last element of the IMAGES or INSTRUCTION
     * @param v (view)
     */
    public void btnPrevious (View v) {
        next = (Button) findViewById(R.id.nextBtn);
        previous = (Button) findViewById(R.id.previousBtn);

        int [] IMAGES = getIMAGES(info, tutorial);
        String [] INSTRUCTION = getINSTRUCTION(info, tutorial);

        int length = IMAGES.length - 1; // the last element of the IMAGES/INSTRUCTION

        if (IMAGES != null) tutorialImage.setImageResource(IMAGES[--totalStep]);
        if (INSTRUCTION != null) instructionDetail.setText(INSTRUCTION[totalStep]);
        if (totalStep == 0) previous.setEnabled(false);
        if (totalStep < length) next.setEnabled(true);
    }

    /**
     * Function for display the list of all cars in car's database
     * using ViewMyGarage.java, so user can select a car and add note
     * It will send the request to the ViewMyGarage.java, so when user click on a car,
     * the AddNewService.java will kick-in
     * @param v
     */
    public void btnAddNote (View v) {

        Intent i = new Intent(SingleTutorialView.this, ViewMyGarage.class);
        i.putExtra(SINGLE_TUTORIAL_VIEW, tutorial);
        startActivity(i);
    }

    public void onBackPresses (View v) {
        Intent i = new Intent(SingleTutorialView.this, MainActivity.class);
        startActivity(i);
    }
}
