package controller;
 
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LocalDateStringConverter;
import model.BetreuerDaoDatenListe;
import model.BetreuerKurz;
 
public class BetreuerViewKurzController implements Initializable {

    @FXML
    private TableView<BetreuerKurz> tableView;
    @FXML
    private TableColumn<BetreuerKurz, Integer> persnrColumn;
    @FXML
    private TableColumn<BetreuerKurz, String> vornameColumn;
    @FXML
    private TableColumn<BetreuerKurz, LocalDate> eintrittsdatumColumn;
    @FXML
    private TableColumn<BetreuerKurz, Double> gehaltColumn;

    @FXML
    private ProgressIndicator progressIndicator;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        createTable();
        createColumnsOfTable();

        createTask();
        //	formatColumns();

    }

    public void printBetreuer() {
        for (BetreuerKurz listePrint : tableView.getItems()) {
            String output = listePrint.getPersnr() + ", "
                    + listePrint.getVorname() + ", "
                    + listePrint.getGehalt() + ", "
                    + listePrint.getEintrittsdatum();
            System.out.println();

        }
    }

    @SuppressWarnings("rawtypes")
    @FXML
    public void changeBetreuerVornameCellEvent(CellEditEvent edittedCell) {
        BetreuerKurz betreuerSelected = tableView.getSelectionModel().getSelectedItem();

        betreuerSelected.setVorname(((edittedCell.getNewValue().toString())));

    }

    @SuppressWarnings("rawtypes")
    @FXML
    public void changeBetreuerPersNrCellEvent(CellEditEvent edittedCell) {
        BetreuerKurz betreuerSelected = tableView.getSelectionModel().getSelectedItem();

        betreuerSelected.setPersnr((Integer.parseInt(edittedCell.getNewValue().toString())));
        // Todo jetzt noch in die Datenbank aktualisieren

    }

    @SuppressWarnings("rawtypes")
    @FXML
    public void changeBetreuerEintrittsdatumCellEvent(CellEditEvent edittedCell) {

        BetreuerKurz betreuerSelected = tableView.getSelectionModel().getSelectedItem();
        LocalDate date = (LocalDate) edittedCell.getNewValue();
        betreuerSelected.setEintrittsdatum(date);

    }

    public void createTable() {
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                System.out.println(tableView.getSelectionModel().getSelectedItem());
            }
        });
    }

    public void createColumnsOfTable() {
        persnrColumn.setCellValueFactory(new PropertyValueFactory<BetreuerKurz, Integer>("persnr"));
        persnrColumn.setStyle("-fx-alignment: CENTER-RIGHT;");

        vornameColumn.setCellValueFactory(new PropertyValueFactory<BetreuerKurz, String>("vorname"));
        vornameColumn.setStyle("-fx-alignment: CENTER;");
        vornameColumn.setEditable(true); // Editierebar Aendern des Wertes
        vornameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Eintrittsdatum Editierbar 12.03.2016 Deutsches Format
        eintrittsdatumColumn.setCellValueFactory(new PropertyValueFactory<BetreuerKurz, LocalDate>("eintrittsdatum"));
        eintrittsdatumColumn.setStyle("-fx-alignment: CENTER;");
        eintrittsdatumColumn.setEditable(true);

        LocalDateStringConverter converter = new LocalDateStringConverter();
        eintrittsdatumColumn.setCellFactory(TextFieldTableCell.<BetreuerKurz, LocalDate>forTableColumn(converter));

        // Gehalt
        gehaltColumn.setCellValueFactory(new PropertyValueFactory<BetreuerKurz, Double>("gehalt"));
        gehaltColumn.setStyle("-fx-alignment: CENTER-RIGHT;");

    }

    /**
     * @deprecated
     */
    public void formatColumns() {
        eintrittsdatumColumn.setCellFactory(col -> {
            TableCell<BetreuerKurz, LocalDate> cell = new TableCell<BetreuerKurz, LocalDate>() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    // Cleanup the cell before populating it
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        // Format the birth date in mm/dd/yyyy format
                        String formattedDob = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(item);
                        this.setText(formattedDob);
                    }
                }
            };
            return cell;
        });

        gehaltColumn.setCellFactory(col -> {
            TableCell<BetreuerKurz, Double> cell = new TableCell<BetreuerKurz, Double>() {
                @Override
                public void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    // Cleanup the cell before populating it
                    this.setText(null);
                    this.setGraphic(null);
                    if (!empty) {
                        DecimalFormat df = new DecimalFormat("###,##0.00");
                        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.GERMAN));
                        String formattedDob = df.format(item);
                        this.setText(formattedDob);
                    }
                }
            };
            return cell;
        });
    }

    public void createTask() {

        Task<ObservableList<BetreuerKurz>> task1 = new GetAllBetreuerKurzDaten();
        tableView.itemsProperty().bind(task1.valueProperty());
        progressIndicator.progressProperty().bind(task1.progressProperty());
        progressIndicator.setVisible(true);

        task1.setOnSucceeded(e -> progressIndicator.setVisible(false));
        task1.setOnFailed(e -> progressIndicator.setVisible(false));

        new Thread(task1).start();

    }

}

// #################################
// Neue Klasse
@SuppressWarnings("rawtypes")
class GetAllBetreuerKurzDaten extends Task {

    @Override
    public ObservableList<BetreuerKurz> call() throws Exception {

        BetreuerDaoDatenListe dao = new BetreuerDaoDatenListe();
        return FXCollections.observableArrayList(dao.getAllDAten());

    }
}
