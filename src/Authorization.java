import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Authorization extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a scene and place it in the stage
        Scene scene = new Scene(getPaneForWindowAuthorization(primaryStage), 550, 450);
        primaryStage.setTitle("ShowCircleCentered"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private Pane getPaneForWindowAuthorization(Stage primaryStage) {
        // Основная панель
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: #CCCCCC");

        // Панель на которой размещаются поля для ввода логина, пароля и кнопка вход
        GridPane paneForLoginPasswordAndBtEnter = new GridPane();
        paneForLoginPasswordAndBtEnter.setAlignment(Pos.CENTER);
        paneForLoginPasswordAndBtEnter.setPadding(new Insets(11, 12, 40, 14));
        paneForLoginPasswordAndBtEnter.setHgap(5);
        paneForLoginPasswordAndBtEnter.setVgap(5);
        paneForLoginPasswordAndBtEnter.getColumnConstraints().add(new ColumnConstraints(80, 80, 80));
        paneForLoginPasswordAndBtEnter.getColumnConstraints().add(new ColumnConstraints(200, 200, 200));
        // Размещаем узлы на панели
        paneForLoginPasswordAndBtEnter.add(new Label("Логин:"), 0, 0);
        paneForLoginPasswordAndBtEnter.add(new TextField(), 1, 0);
        paneForLoginPasswordAndBtEnter.add(new Label("Пароль:"), 0, 1);
        paneForLoginPasswordAndBtEnter.add(new PasswordField(), 1, 1);

        // Панель для кнопок
        HBox hBoxForButton = new HBox(5);
        Button btAuthorization = new Button("   Вход    ");
        Button btRegistration = new Button("Регистрация");
        Button btExit = new Button("   Выход   ");
        hBoxForButton.getChildren().addAll(btAuthorization, btRegistration, btExit);
        hBoxForButton.setAlignment(Pos.CENTER);
        hBoxForButton.setPadding(new Insets(20, 20, 80, 20));

        // Панель для заголовка
        Pane paneForText = new Pane();
        Text textTitleAuthorization = new Text(220, 50, "Авторизация");
        textTitleAuthorization.setFont(Font.font("Times New Roman", 20));
        paneForText.getChildren().add(textTitleAuthorization);

        pane.setTop(paneForText);
        pane.setCenter(paneForLoginPasswordAndBtEnter);
        pane.setBottom(hBoxForButton);

        btExit.setOnAction(e -> primaryStage.close());

        btAuthorization.setOnAction(e -> {
            AccountingForElectricPower accountingForElectricPower = new AccountingForElectricPower();
            // New window (Stage)
            Stage newWindow = new Stage();
            Scene sceneWindow1 = new Scene(accountingForElectricPower.getPaneWindow1(primaryStage, newWindow), 550, 450);
            newWindow.setTitle("Точки учёта (ТУ)");
            newWindow.setScene(sceneWindow1);
            // Specifies the modality for new window.
            newWindow.initModality(Modality.WINDOW_MODAL);
            // Specifies the owner Window (parent) for new window
            newWindow.initOwner(primaryStage);
            newWindow.show();
        });

        return pane;
    }
}
