package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    Paises countriesManager;
    @FXML private ImageView userArrow;
    @FXML private ImageView locationArrow;
    @FXML private ImageView educationArrow;


    @FXML private AnchorPane userPanel;
    @FXML private AnchorPane locationPanel;
    @FXML private AnchorPane educationPanel;

    @FXML private ComboBox<String> comboboxID;
    @FXML private ComboBox<String> comboboxSeleccion;
    @FXML private ComboBox<String> comboboxEducation;

    @FXML private RadioButton masculino;
    @FXML private RadioButton femenino;

    @FXML private TextField textNombre;
    @FXML private TextField textApellido;
    @FXML private TextField textID;

    @FXML private ComboBox<String> comboboxCountry;
    @FXML private ComboBox<String> comboboxCity;
    @FXML private ComboBox<String> comboboxEstado;
    @FXML private TextField tituloAcademico;

    @FXML private TextField textCarrera;
    ObservableList<String> comboIDContent =
            FXCollections.observableArrayList(
                    "C.C.",
                    "C.E.",
                    "T.I.",
                    "R.C."
            );
    ObservableList<String> comboSeleccionContent =
            FXCollections.observableArrayList(
                    "Soltero",
                    "Casado",
                    "Union Libre"
            );
    ObservableList<String> comboAcademicContent =
            FXCollections.observableArrayList(
                    "Primaria",
                    "Secundaria",
                    "Tecnico",
                    "Tecnologo",
                    "Profesional",
                    "Maestria",
                    "Doctorado"
            );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countriesManager = new Paises();
        comboboxCountry.setItems(countriesManager.getCountries());
        textNombre.addEventFilter(KeyEvent.ANY,handlerLetters);
        textApellido.addEventFilter(KeyEvent.ANY,handlerLetters);
        textID.addEventFilter(KeyEvent.ANY,handlerNumbers);
        comboboxID.setItems(comboIDContent);
        comboboxSeleccion.setItems(comboSeleccionContent);
        comboboxEducation.setItems(comboAcademicContent);
        ToggleGroup group = new ToggleGroup();
        masculino.setToggleGroup(group);
        femenino.setToggleGroup(group);

    }



    public void onExitButtonClick(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    public void onUserButtonClick(MouseEvent event) {
        if (userPanel.isVisible()){
            userPanel.setVisible(false);
            userArrow.setVisible(false);
            return;
        }
        userPanel.setVisible(true);
        userArrow.setVisible(true);
        //los demas paneles y flechas se ponen invisibles
        locationPanel.setVisible(false);
        locationArrow.setVisible(false);
        educationPanel.setVisible(false);
        educationArrow.setVisible(false);


    }

    public void onLocationButtonClick(MouseEvent event) {
        if (locationPanel.isVisible()){
            locationPanel.setVisible(false);
            locationArrow.setVisible(false);
            return;
        }
       locationPanel.setVisible(true);
       locationArrow.setVisible(true);
        //los demas paneles y flechas se ponen invisibles
        userPanel.setVisible(false);
        userArrow.setVisible(false);
        educationPanel.setVisible(false);
        educationArrow.setVisible(false);


    }
    public void onEducationButtonClick(MouseEvent event) {
        if (educationPanel.isVisible()){
            educationPanel.setVisible(false);
            educationArrow.setVisible(false);
            return;
        }
        educationPanel.setVisible(true);
        educationArrow.setVisible(true);
        //los demas paneles y flechas se ponen invisibles
        userPanel.setVisible(false);
        userArrow.setVisible(false);
        locationPanel.setVisible(false);
        locationArrow.setVisible(false);

        comboboxEducation.requestFocus();

    }

    EventHandler<KeyEvent> handlerLetters = new EventHandler<KeyEvent>() {
        private boolean willConsume = false;
        @Override
        public void handle(KeyEvent event) {
            Object tempO = event.getSource();
            if (willConsume) {
                event.consume();
            }
            String temp = event.getCode().toString();
            if (!event.getCode().toString().matches("[a-zA-Z]")&& event.getCode()!= KeyCode.BACK_SPACE && event.getCode()!=KeyCode.SHIFT){
                if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                    willConsume = true;
                }else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                    willConsume = false;
                }

            }

        }
    };

    EventHandler<KeyEvent> handlerNumbers = new EventHandler<KeyEvent>() {
        private boolean willConsume = false;
        private int maxLength = 10;
        @Override
        public void handle(KeyEvent event) {
            TextField temp = (TextField) event.getSource();
            if (willConsume) {
                event.consume();
            }

            if (!event.getText().matches("[0-9]")&& event.getCode()!= KeyCode.BACK_SPACE){
                if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                    willConsume = true;
                }else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                    willConsume = false;
                }

            }
            if (temp.getText().length()>maxLength-1){
                if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                    willConsume = true;
                }else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                    willConsume = false;
                }
            }

        }
    };

    public void onComboCountryChange(ActionEvent event) {
        comboboxEstado.setDisable(false);
        for (int i = 0; i < countriesManager.getSize(); i++) {
            if (comboboxCountry.getValue().equals(countriesManager.getCountry(i))) {
                comboboxEstado.setItems(countriesManager.getStates(i));
            }
        }
    }

    public void onComboEstadoChange(ActionEvent event) {
        comboboxCity.setDisable(false);

    }
    
    public void onAcademicComboBoxChanged(ActionEvent event) {
        for (int i = 2; i <comboAcademicContent.size() ; i++) {
            if (comboboxEducation.getValue().equals(comboAcademicContent.get(i))){
                tituloAcademico.setDisable(false);
                return;
            }
        }
        tituloAcademico.setDisable(true);
    }
    public void onToggleButtonChanged(ActionEvent event) {
        if (textCarrera.isDisable()){
            textCarrera.setDisable(false);

        }else {
            textCarrera.setText("");
            textCarrera.setDisable(true);
        }
    }

    public void onSaveButtonClick(MouseEvent event) {
        if (textNombre.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guardar información");
            alert.setContentText("Existen campos vacíos");
            alert.showAndWait();
        }
    }
}

