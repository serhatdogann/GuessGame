package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnGuess;

    @FXML
    private Pane pane;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtYear;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtOrigin;

    @FXML
    private TextField txtDirector;

    @FXML
    private TextField txtStar;

    @FXML
    private Pane pane1;

    @FXML
    private TextField txtName1;

    @FXML
    private TextField txtYear1;

    @FXML
    private TextField txtGenre1;

    @FXML
    private TextField txtOrigin1;

    @FXML
    private TextField txtDirector1;

    @FXML
    private TextField txtStar1;

    @FXML
    private Pane pane2;

    @FXML
    private TextField txtName2;

    @FXML
    private TextField txtYear2;

    @FXML
    private TextField txtGenre2;

    @FXML
    private TextField txtOrigin2;

    @FXML
    private TextField txtDirector2;

    @FXML
    private TextField txtStar2;

    @FXML
    private Button btnRestart;

    private MovieList movieList;
    private int guessCount = 0;

    @FXML
    void btnGuessClick(ActionEvent event) {
        String movieName = txtSearch.getText();
        if (movieName.isEmpty()) {
            // Eğer film adı girilmediyse uyarı mesajı göster ve işlemi durdur.
            showErrorMessage("FILM ADI GIRMEDINIZ!");
            return;
        }

        Movie movie = movieList.findMovieByName(movieName);
        if (movie != null) {
            guessCount++;
            if (guessCount == 1) {
                setMovieFields(movie, txtName, txtYear, txtGenre, txtOrigin, txtDirector, txtStar);
                pane.setVisible(true);
                pane1.setVisible(false);
                pane2.setVisible(false);
                setTextFieldColor(txtName, "The Dark Knight");
                setTextFieldColor(txtYear, "2008");
                setTextFieldColor(txtGenre, "Drama");
                setTextFieldColor(txtOrigin, "USA");
                setTextFieldColor(txtDirector, "Christopher Nolan");
                setTextFieldColor(txtStar, "Christian Bale");

                if (movieName.equalsIgnoreCase("The Dark Knight")) {
                    showCongratulationsDialog();
                    btnGuess.setDisable(true);
                    btnRestart.setVisible(true);
                   
                    
                }else {
    				btnRestart.setVisible(false);
    			}
               
            } else if (guessCount == 2) {
                setMovieFields(movie, txtName1, txtYear1, txtGenre1, txtOrigin1, txtDirector1, txtStar1);
                pane1.setVisible(true);
                pane2.setVisible(false);
                setTextFieldColor(txtName1, "The Dark Knight");
                setTextFieldColor(txtYear1, "2008");
                setTextFieldColor(txtGenre1, "Drama");
                setTextFieldColor(txtOrigin1, "USA");
                setTextFieldColor(txtDirector1, "Christopher Nolan");
                setTextFieldColor(txtStar1, "Christian Bale");

                if (movieName.equalsIgnoreCase("The Dark Knight")) {
                    showCongratulationsDialog();
                    btnRestart.setVisible(true);
                }else {
    				btnRestart.setVisible(false);
    			}
            } else {
                setMovieFields(movie, txtName2, txtYear2, txtGenre2, txtOrigin2, txtDirector2, txtStar2);
                pane2.setVisible(true);
                setTextFieldColor(txtName2, "The Dark Knight");
                setTextFieldColor(txtYear2, "2008");
                setTextFieldColor(txtGenre2, "Drama");
                setTextFieldColor(txtOrigin2, "USA");
                setTextFieldColor(txtDirector2, "Christopher Nolan");
                setTextFieldColor(txtStar2, "Christian Bale");
            }
            
            if (movieName.equalsIgnoreCase("The Dark Knight")) {
                showCongratulationsDialog();
                btnRestart.setVisible(true);
            }else {
				btnRestart.setVisible(false);
			}
        } else {
            showErrorMessage("YANLIS FILM ADI GIRDINIZ!");
            resetMovieFields();
            pane.setVisible(false);
            pane1.setVisible(false);
            pane2.setVisible(false);
            btnRestart.setVisible(false);
        }
    }


    @FXML
    void btnRestartClick(ActionEvent event) {
    	
    	guessCount = 0;
    	txtSearch.clear();
        resetMovieFields();
        pane.setVisible(false);
        pane1.setVisible(false);
        pane2.setVisible(false);
        btnRestart.setVisible(false);
        btnGuess.setDisable(false);
    
    }


    private void showErrorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Hata");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        String filePath = "imdb_top_2501.csv";
        File file = new File(filePath);
        if (file.exists()) {
            movieList = new MovieList(filePath);
        } else {
            System.out.println("CSV dosyası bulunamadı: " + filePath);
        }

        pane.setVisible(false);
        pane1.setVisible(false);
        pane2.setVisible(false);
        btnRestart.setVisible(false);
    }

    private void setMovieFields(Movie movie, TextField nameField, TextField yearField, TextField genreField,
            TextField originField, TextField directorField, TextField starField) {
        nameField.setText(movie.getName());
        yearField.setText(movie.getYear());
        genreField.setText(movie.getGenre());
        originField.setText(movie.getOrigin());
        directorField.setText(movie.getDirector());
        starField.setText(movie.getStar());
    }

    private void resetMovieFields() {
        txtName.setText("");
        txtYear.setText("");
        txtGenre.setText("");
        txtOrigin.setText("");
        txtDirector.setText("");
        txtStar.setText("");

        txtName1.setText("");
        txtYear1.setText("");
        txtGenre1.setText("");
        txtOrigin1.setText("");
        txtDirector1.setText("");
        txtStar1.setText("");

        txtName2.setText("");
        txtYear2.setText("");
        txtGenre2.setText("");
        txtOrigin2.setText("");
        txtDirector2.setText("");
        txtStar2.setText("");
    }




    private void setTextFieldColor(TextField textField, String targetValue) {
        String fieldValue = textField.getText();
        if (fieldValue.equals(targetValue)) {
            textField.setStyle("-fx-background-color: green;");
        } else {
            textField.setStyle("-fx-background-color: red;");
        }
    }

    private void showCongratulationsDialog() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Tebrikler!");
        alert.setHeaderText(null);
        alert.setContentText("Doğru Filmi Tahmin Ettiniz");
        alert.showAndWait();
    }
}
