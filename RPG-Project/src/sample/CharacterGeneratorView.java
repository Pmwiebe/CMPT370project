package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CharacterGeneratorView extends Pane implements CharacterSubscribers{
    Button generateRandom, save, play;
    ComboBox<String> classList, backgroundList, raceList, goalsList, traitsList, genderList, extrasList,
            heightList, strengthList, dexterityList, constitutionList, wisdomList, intelligenceList, charismaList;
    ChoiceBox<String> hairColour, armour, weapon, eyeColour, hairType, bodyType;
    TextField name;
    Label charName,Class, background, race, goals, traits, gender, extras, height, strength, dexterity, constitution,
            wisdom, intelligence, charisma, hairC, armourChoice, weaponChoice, eyeColourChoice, hairT, body;
    Image character;
    HBox bottom,above,mid, textField;
    VBox top,combo,vboxChoice,labels,choiceLabels;
    CharacterGenerator model;
    ObservableList<String> stats;


    public CharacterGeneratorView() throws FileNotFoundException {
        // Stats to add to some choices
        stats = FXCollections.observableArrayList();
        stats.addAll("3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18");

        // All Boxes for structure
        top = new VBox();
        above = new HBox();
        mid = new HBox();
        textField = new HBox();
        bottom = new HBox();
        combo = new VBox();
        vboxChoice = new VBox();
        labels = new VBox();
        choiceLabels = new VBox();

        // Name input/random name
        name = new TextField();
        charName = new Label("Name: ");
        textField.getChildren().addAll(charName, name);
        textField.setAlignment(Pos.TOP_CENTER);

        // Generate random button. Located on top of everything
        generateRandom = new Button("Generate Random");
        top.getChildren().addAll(generateRandom, textField);
        top.setAlignment(Pos.CENTER);
        top.setSpacing(15);
        above.getChildren().addAll(top);
        above.setAlignment(Pos.CENTER);

        // Save and play buttons. Located in bottom right corner
        save = new Button("Save");
        play = new Button("Play");
        bottom.getChildren().addAll(save,play);
        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        bottom.setSpacing(10);

        // All ComboBox Labels (far left)
        Class = new Label ("Class: ");
        background = new Label("Background: ");
        race = new Label("Race: ");
        goals = new Label("Goals: ");
        traits = new Label("Traits: ");
        gender = new Label("Gender: ");
        extras = new Label("Extras: ");
        height = new Label("Height: ");
        strength = new Label("Strength: ");
        dexterity = new Label("Dexterity: ");
        constitution = new Label("Constitution: ");
        wisdom = new Label("Wisdom: ");
        intelligence = new Label("Intelligence: ");
        charisma = new Label("Charisma: ");
        labels.getChildren().addAll(Class, background, race, goals, traits, gender, extras, height, strength,
                dexterity, constitution, wisdom, intelligence, charisma);
        labels.setSpacing(20);

        // All comboboxes (far left)
        classList = new ComboBox<>();
        backgroundList = new ComboBox<>();
        raceList = new ComboBox<>();
        goalsList = new ComboBox<>();
        traitsList = new ComboBox<>();
        genderList = new ComboBox<>();
        extrasList = new ComboBox<>();
        heightList = new ComboBox<>();
        strengthList = new ComboBox<>(stats);
        dexterityList = new ComboBox<>(stats);
        constitutionList = new ComboBox<>(stats);
        wisdomList = new ComboBox<>(stats);
        intelligenceList = new ComboBox<>(stats);
        charismaList = new ComboBox<>(stats);
        combo.getChildren().addAll(classList, backgroundList, raceList, goalsList, traitsList, genderList, extrasList,
                heightList, strengthList, dexterityList, constitutionList, wisdomList, intelligenceList, charismaList);
        combo.setSpacing(12);


        // Random stickman just for fun (in middle)
        FileInputStream inputStream = new FileInputStream("stickfigure.jpg");
        character = new Image(inputStream);
        ImageView imageView = new ImageView();
        imageView.setImage(character);
        imageView.setFitHeight(600);
        imageView.setFitWidth(350);

        // Labels for choice boxes (far right)
        hairC = new Label("Hair Colour: ");
        armourChoice = new Label("Armour: ");
        weaponChoice = new Label("Weapon: ");
        eyeColourChoice = new Label("Eye Colour: ");
        hairT = new Label("Hair Type: ");
        body = new Label("Body Type: ");
        choiceLabels.getChildren().addAll(hairC, armourChoice, weaponChoice, eyeColourChoice, hairT, body);
        choiceLabels.setSpacing(20);

        // Choice boxes (far right)
        hairColour = new ChoiceBox<>();
        armour = new ChoiceBox<>();
        weapon = new ChoiceBox<>();
        eyeColour = new ChoiceBox<>();
        hairType = new ChoiceBox<>();
        bodyType = new ChoiceBox<>();
        vboxChoice.getChildren().addAll(hairColour, armour, weapon, eyeColour, hairType, bodyType);
        vboxChoice.setSpacing(12);

        // Mid section (label -> combobox -> stickman -> label -> choicebox)
        mid.getChildren().addAll(labels,combo,imageView, choiceLabels, vboxChoice);
        mid.setSpacing(40);

        // All put together
        VBox main = new VBox();
        main.getChildren().addAll(above, mid, bottom);
        main.setSpacing(40);
        this.getChildren().addAll(main);
    }

    /**
     * Deals with what to send to controller when buttons in view are pushed
     */
    public void setController(Controller controller){
        generateRandom.setOnAction(e -> controller.handleGenerateRandom());
    }

    public void setModel(CharacterGenerator mod){
        model = mod;
    }

    @Override
    public void modelChanged() {
        strengthList.setValue(Integer.toString(model.character.characterStats.getStr()));
        dexterityList.setValue(Integer.toString(model.character.characterStats.getDex()));
        constitutionList.setValue(Integer.toString(model.character.characterStats.getCon()));
        wisdomList.setValue(Integer.toString(model.character.characterStats.getWis()));
        intelligenceList.setValue(Integer.toString(model.character.characterStats.getInt()));
        charismaList.setValue(Integer.toString(model.character.characterStats.getCha()));
    }
}
