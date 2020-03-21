import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.util.List;

public class AccountingForElectricPower {

    private Text textTitle1 = new Text(200, 50, "Главное меню");
    private Text textTitle2 = new Text(200, 80, "Точка учёта (ТУ)");
    private Text textTitle3 = new Text(200, 40, "Создание новой ТУ");
    private Text textTitle4 = new Text(200, 40, "Поиск ТУ");
    // создаём объект класса JDBCConnectForGetCity этот объект jdbcConnect выполнит подключение к БД
    private JDBCConnectToDB jdbcConnect = new JDBCConnectToDB();

    public BorderPane getPaneWindow1(Stage primaryStage, Stage window) {
        Button btTY = new Button("Точки учёта (ТУ)");
        Button btZamena = new Button("Замена/ установка приборов учёта");
        Button btNastroyka = new Button("Настройки сеансов связи");
        Button btRezultatoprosov = new Button("Результаты опросов ТУ");
        Button btSpravochniki = new Button("Справочники");
        Button btExit = new Button("Выход");

        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: #CCCCCC");

        Pane paneForText = new Pane();
        textTitle1.setFont(Font.font("Times New Roman", 25));
        paneForText.getChildren().add(textTitle1);
        VBox paneForButton = new VBox(5);
        paneForButton.getChildren().addAll(btTY, btZamena, btNastroyka, btRezultatoprosov, btSpravochniki, btExit);
        btTY.setOnAction(e -> getPaneWindow2(primaryStage));
        btExit.setOnAction(e -> window.close());

        pane.setTop(paneForText);
        pane.setCenter(paneForButton);
        paneForButton.setAlignment(Pos.CENTER);

        return pane;
    }

    private BorderPane getPaneWindow2(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: #CCCCCC");

        Button btSozdanie = new Button("Создание новой ТУ");
        Button btPoisk = new Button("Поиск ТУ");
        Button btExit2 = new Button("Выход");

        Pane paneForText = new Pane();
        textTitle2.setFont(Font.font("Times New Roman", 20));
        paneForText.getChildren().add(textTitle2);

        VBox paneForButton = new VBox(20);
        paneForButton.getChildren().addAll(btSozdanie);
        VBox paneForButton1 = new VBox(20);
        paneForButton.getChildren().addAll(btPoisk);
        VBox paneForButton2 = new VBox(20);
        paneForButton.getChildren().addAll(btExit2);
        pane.setTop(paneForButton);
        pane.setCenter(paneForButton1);
        pane.setCenter(paneForButton2);
        Scene secondScene = new Scene(pane, 550, 450);
        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Точки учёта (ТУ)");
        newWindow.setScene(secondScene);
        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);
        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(primaryStage);
        btSozdanie.setOnAction(e -> getPaneWindow3(newWindow));
        btPoisk.setOnAction(e -> getPaneWindow4(newWindow));
        btExit2.setOnAction(e -> newWindow.close());
        newWindow.show();

        pane.setTop(paneForText);
        pane.setCenter(paneForButton);
        paneForButton.setAlignment(Pos.CENTER);

        return pane;
    }

    private BorderPane getPaneWindow3(Stage newWindow) {
        BorderPane pane = new BorderPane();
        // вызываем у объекта jdbcConnect метод getCityTitles() - этот метод вернет лист с названиями городов
        List<String> list = jdbcConnect.getCityTitles();
        List<String> list1 = jdbcConnect.getStreetTitles();
        List<String> list2 = jdbcConnect.getHouseTitles();
        List<String> list3 = jdbcConnect.getFlatTitles();
        // преобразуем лист с названиями в массив
        String[] cityTitles = list.toArray(new String[list.size()]);

        String[] streetTitles = list1.toArray(new String[list1.size()]);
        String[] houseTitles = list2.toArray(new String[list2.size()]);
        String[] flatTitles = list3.toArray(new String[list3.size()]);
        pane.setStyle("-fx-background-color: #CCCCCC;");
        VBox paneForData = new VBox(5);
        ComboBox<String> cbo1 = new ComboBox<>();
        BorderPane paneForCity = new BorderPane();
        paneForCity.setLeft(new Label("Город: "));
        paneForCity.setBottom(cbo1);
        paneForCity.setBottom(cbo1);
        cbo1.setValue(" ");
        ObservableList<String> items = FXCollections.observableArrayList(cityTitles);
        cbo1.getItems().addAll(items);
        ComboBox<String> cbo2 = new ComboBox<>();
        BorderPane paneForStreet = new BorderPane();
        paneForStreet.setLeft(new Label("Улица "));
        paneForStreet.setBottom(cbo2);
        paneForStreet.setBottom(cbo2);
        cbo2.setValue(" ");
        ObservableList<String> items1 = FXCollections.observableArrayList(streetTitles);
        cbo2.getItems().addAll(items1);
        ComboBox<String> cbo3 = new ComboBox<>();
        BorderPane paneForHous = new BorderPane();
        paneForHous.setLeft(new Label("Дом "));
        paneForHous.setBottom(cbo3);
        paneForHous.setBottom(cbo3);
        cbo3.setValue(" ");
        ObservableList<String> items2 = FXCollections.observableArrayList(houseTitles);
        cbo3.getItems().addAll(items2);
        ComboBox<String> cbo5 = new ComboBox<>();
        BorderPane paneForApartmen = new BorderPane();
        paneForApartmen.setLeft(new Label("Квартира"));
        paneForApartmen.setBottom(cbo5);
        paneForApartmen.setBottom(cbo5);
        cbo5.setValue(" ");
        ObservableList<String> items4 = FXCollections.observableArrayList(flatTitles);
        cbo5.getItems().addAll(items4);
        TextField cbo7 = new TextField();
        BorderPane paneForFi = new BorderPane();
        paneForFi.setLeft(new Label("ФИО"));
        paneForFi.setBottom(cbo7);
        paneForFi.setBottom(cbo7);
        TextField cbo8 = new TextField();
        BorderPane paneForLicevo = new BorderPane();
        paneForLicevo.setLeft(new Label("Лицевой счёт"));
        paneForLicevo.setBottom(cbo8);
        paneForLicevo.setBottom(cbo8);
        Button btExit3 = new Button("Выход");
        Button btSozdanie = new Button("Создать");

        HBox paneForButton = new HBox(5);
        paneForButton.setAlignment(Pos.CENTER);
        paneForButton.setPadding(new Insets(20, 20, 20, 20));
        paneForButton.getChildren().addAll(btSozdanie, btExit3);
        Pane paneForText = new Pane();
        textTitle3.setFont(Font.font("Times New Roman", 20));
        paneForText.getChildren().add(textTitle3);
        HBox hBoxForCityStreetHouse = new HBox(paneForCity, paneForStreet, paneForHous);
        hBoxForCityStreetHouse.setSpacing(20);
        hBoxForCityStreetHouse.setAlignment(Pos.CENTER);
        HBox hBoxForKorpuApartmen = new HBox(paneForApartmen);
        hBoxForKorpuApartmen.setSpacing(40);
        hBoxForKorpuApartmen.setAlignment(Pos.CENTER);
        paneForData.setPadding(new Insets(20, 20, 20, 20));
        paneForData.getChildren().addAll(hBoxForCityStreetHouse, hBoxForKorpuApartmen, paneForFi, paneForLicevo);
        pane.setTop(paneForText);
        pane.setCenter(paneForData);
        pane.setBottom(paneForButton);
        Scene secondScene = new Scene(pane, 550, 450); // New window (Stage)
        Stage newWindow3 = new Stage();
        newWindow3.setTitle("Создание новой ТУ");
        newWindow3.setScene(secondScene); // Specifies the modality for new window.
        newWindow3.initModality(Modality.WINDOW_MODAL); // Specifies the owner Window (parent) for new window
        newWindow3.initOwner(newWindow);
        btExit3.setOnAction(e -> newWindow3.close());
        newWindow3.show();
        return pane;
    }

    private BorderPane getPaneWindow4(Stage newWindow) {
        BorderPane pane = new BorderPane();
        // вызываем у объекта jdbcConnect метод getCityTitles() - этот метод вернет лист с названиями городов
        List<String> list = jdbcConnect.getCityTitles();
        List<String> list1 = jdbcConnect.getStreetTitles();
        List<String> list2 = jdbcConnect.getHouseTitles();
        List<String> list3 = jdbcConnect.getFlatTitles();
        // преобразуем лист с названиями в массив
        String[] cityTitles1 = list.toArray(new String[list.size()]);
        String[] streetTitles = list1.toArray(new String[list1.size()]);
        String[] houseTitles = list2.toArray(new String[list2.size()]);
        String[] flatTitles = list3.toArray(new String[list3.size()]);
        pane.setStyle("-fx-background-color: #CCCCCC;");
        VBox paneForData = new VBox(5);
        ComboBox<String> cbo1 = new ComboBox<>();

        BorderPane paneForCity = new BorderPane();
        paneForCity.setLeft(new Label("Город: "));
        paneForCity.setBottom(cbo1);
        paneForCity.setBottom(cbo1);
        cbo1.setValue(" ");
        ObservableList<String> items = FXCollections.observableArrayList(cityTitles1);
        cbo1.getItems().addAll(items);
        ComboBox<String> cbo2 = new ComboBox<>();
        BorderPane paneForStreet = new BorderPane();
        paneForStreet.setLeft(new Label("Улица "));
        paneForStreet.setBottom(cbo2);
        paneForStreet.setBottom(cbo2);
        cbo2.setValue(" ");
        ObservableList<String> items1 = FXCollections.observableArrayList(streetTitles);
        cbo2.getItems().addAll(items1);
        ComboBox<String> cbo3 = new ComboBox<>();
        BorderPane paneForHous = new BorderPane();
        paneForHous.setLeft(new Label("Дом "));
        paneForHous.setBottom(cbo3);
        paneForHous.setBottom(cbo3);
        cbo3.setValue(" ");
        ObservableList<String> items2 = FXCollections.observableArrayList(houseTitles);
        cbo3.getItems().addAll(items2);
        ComboBox<String> cbo5 = new ComboBox<>();
        BorderPane paneForApartmen = new BorderPane();
        paneForApartmen.setLeft(new Label("Квартира"));
        paneForApartmen.setBottom(cbo5);
        paneForApartmen.setBottom(cbo5);
        cbo5.setValue(" ");
        ObservableList<String> items4 = FXCollections.observableArrayList(flatTitles);
        cbo5.getItems().addAll(items4);
        TextField cbo7 = new TextField();
        BorderPane paneForFi = new BorderPane();
        paneForFi.setLeft(new Label("ФИО"));
        paneForFi.setBottom(cbo7);
        paneForFi.setBottom(cbo7);
        TextField cbo8 = new TextField();
        BorderPane paneForLicevo = new BorderPane();
        paneForLicevo.setLeft(new Label("Лицевой счёт"));
        paneForLicevo.setBottom(cbo8);
        paneForLicevo.setBottom(cbo8);
        Button btExit4 = new Button("Выход");
        Button btDelete = new Button("Удалить");
        Button btRedak = new Button("Редактировать");
        HBox paneForButton = new HBox(5);
        paneForButton.setAlignment(Pos.CENTER);
        paneForButton.setPadding(new Insets(20, 20, 20, 20));
        paneForButton.getChildren().addAll(btRedak, btDelete, btExit4);
        Pane paneForText = new Pane();

        textTitle4.setFont(Font.font("Times New Roman", 20));
        paneForText.getChildren().add(textTitle4);
        HBox hBoxForCityStreetHouse = new HBox(paneForCity, paneForStreet, paneForHous);
        hBoxForCityStreetHouse.setSpacing(20);
        hBoxForCityStreetHouse.setAlignment(Pos.CENTER);
        HBox hBoxForKorpuApartmen = new HBox(paneForApartmen);
        hBoxForKorpuApartmen.setSpacing(40);
        hBoxForKorpuApartmen.setAlignment(Pos.CENTER);
        paneForData.setPadding(new Insets(20, 20, 20, 20));
        paneForData.getChildren().addAll(hBoxForCityStreetHouse, hBoxForKorpuApartmen, paneForFi, paneForLicevo);
        pane.setTop(paneForText);
        pane.setCenter(paneForData);
        pane.setBottom(paneForButton);
        Scene secondScene = new Scene(pane, 550, 450);
        // New window (Stage)
        Stage newWindow4 = new Stage();
        newWindow4.setTitle("Поиск ТУ");
        newWindow4.setScene(secondScene);
        // Specifies the modality for new window.
        newWindow4.initModality(Modality.WINDOW_MODAL);
        // Specifies the owner Window (parent) for new window
        newWindow4.initOwner(newWindow);
        //close this window
        btExit4.setOnAction(e -> newWindow4.close());
        newWindow4.show();
        return pane;
    }
}

