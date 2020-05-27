package Presentation;

import Service.FanApplication;
import Service.UserApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;

public class RfaPageController extends HomePageController {


//    @FXML
//    Button viewSettings;
//    @FXML
//    Button registrationForGamesAlerts;
//    @FXML
//    Button myAlerts;

    @FXML
    public javafx.scene.control.Button definePolicyBtn;

    //delete later!!!!!!!!
    private FanApplication fanApplication=new FanApplication();
    private UserApplication userApplication= new UserApplication();
    private String userName = "nadav124";


    @FXML
    public void initUser (String userName) {
        this.userName=userName;
    }

    @FXML
    public void definePolicies(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("DefinePolicy.fxml"));
        Parent root=loader.load();

        Scene scene = new Scene(root, 900, 600);

        DefinePolicyController definePolicyController = loader.getController();
        definePolicyController.initUser(userName);

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.show();


//        FXMLLoader loader = new FXMLLoader();
//        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
//        Parent root = (Parent) loader.load(getClass().getResource("DefinePolicy.fxml").openStream());
//        stage.setScene(new Scene(root));
//        stage.show();

    }

    @FXML
    public void MyAlertsFunction(MouseEvent mouseEvent) throws IOException {

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("RfaNotification.fxml"));
        Scene scene = new Scene(root, 900, 600);
        //scene.getStylesheets().add(getClass().getResource("SignUp.css").toExternalForm());
        stageTheEventSourceNodeBelongs.setScene(scene);

    }

    @FXML
    public void registrationForGamesAlertsMouseClickHandling(ActionEvent event) throws IOException {

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("RegistrationGamesAlerts.fxml"));
        Parent root=loader.load();

        Scene scene = new Scene(root, 900, 600);

        RegistrationGamesAlertsController registrationGamesAlertsController=loader.getController();
        registrationGamesAlertsController.initUser(userName);

        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stageTheEventSourceNodeBelongs.setScene(scene);
        stageTheEventSourceNodeBelongs.show();
    }

    public void onLogOut(ActionEvent actionEvent) throws IOException {
        scheduler.cancel();
        String ans= userApplication.logout(userName);
        //String ans = ClientController.connectToServer("UserApplication", "logout", userName);
        if(ans.equals("success")){
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("Login.fxml"));
            Parent root=loader.load();

            Scene scene = new Scene(root, 700, 400);

            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.setScene(scene);
            stageTheEventSourceNodeBelongs.show();
        }

        else{
            Alert chooseFile = new Alert(Alert.AlertType.ERROR);
            chooseFile.setContentText("Logout was unsuccessful");
            chooseFile.show();
        }

    }

    @FXML
    public void initialize() {
        if (connectionOK && scheduler == null) {
            scheduler = new CheckNotificationsTask(userName, fanApplication);
            scheduler.setPeriod(Duration.seconds(10));
            scheduler.setOnSucceeded(
                    e -> {
                        System.out.println(scheduler.getValue());
                        if (scheduler.getValue().equals("ERROR")) {
                            scheduler.cancel();
                            connectionOK = false;
                        }

                    /*

                if(ans.equals("gotFanNotification")){//fan
                    Alert chooseFile = new Alert(Alert.AlertType.INFORMATION);
                    chooseFile.setContentText("You have a new Notification about a game you are following !");
                    chooseFile.show();
                }
                else if(ans.equals("gotRFAnotification")){//rfa
                    Alert chooseFile = new Alert(Alert.AlertType.INFORMATION);
                    chooseFile.setContentText("You have a new team to approve !");
                    chooseFile.show();
                }
                else if(ans.equals("gotRefereeNotification")){//referee
                    Alert chooseFile = new Alert(Alert.AlertType.INFORMATION);
                    chooseFile.setContentText("You have a new notification about your match !");
                    chooseFile.show();
                }
                else if(ans.equals("multipleNotifications")){//referee
                    Alert chooseFile = new Alert(Alert.AlertType.INFORMATION);
                    chooseFile.setContentText("You have multiple new notifications!");
                    chooseFile.show();
                }


            }
        */
                    });
            scheduler.setOnFailed(e -> System.out.println("failed to run"));
            scheduler.start();
        }
    }
}
