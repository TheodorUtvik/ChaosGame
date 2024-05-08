package no.ntnu.idatg2003.view;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import no.ntnu.idatg2003.controller.FrontPageController;
import no.ntnu.idatg2003.controller.PresetGameController;
import no.ntnu.idatg2003.controller.RunGameController;

/**
 * The main application class for the Chaos Game application.
 */
public class ChaosGameApp extends Application {


  private static final Logger logger = LogManager.getLogger(ChaosGameApp.class);
  private Stage primaryStage;
  private Scene mainScene;

  public static void appMain(String[] args) {
    launch(args);
  }

  private FrontPageController frontPageController;
  private RunGameController runGameController;
  private PresetGameController presetGameController;

  /**
   * Starts the application by initializing the controllers and showing the front page scene.
   *
   * @param primaryStage the primary stage of the application
   * @throws Exception if the application fails to start
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    logger.info("Starting Chaos Game application");

    this.primaryStage = primaryStage;

    initControllers();
    showMainScene();

    primaryStage.setTitle("Chaos Game");
    primaryStage.setMinHeight(600);
    primaryStage.setMinWidth(800);
    primaryStage.show();
  }

  /**
   * Initializes the controllers for the application.
   */
  private void initControllers() {
    frontPageController = new FrontPageController(this);
    runGameController = new RunGameController(this);
    presetGameController = new PresetGameController(this);
  }

  /**
   * Shows the main scene of the application.
   */
  public void showMainScene() {
    this.mainScene = frontPageController.getScene();
    primaryStage.setScene(this.mainScene);
  }

  /**
   * Shows the run game scene of the application.
   */
  public void showRunGameScene() {
    this.mainScene = runGameController.getScene();
    primaryStage.setScene(this.mainScene);
  }

  /**
   * Shows the preset game scene of the application, and creates its corresponding game.
   *
   * @param type the type of the game to be created
   */
  public void showPresetsGameScene(String type) {
    presetGameController.createGame(type);
    this.mainScene = presetGameController.getScene();
    primaryStage.setScene(this.mainScene);
  }

  /**
   * Exits the application.
   */
  public void exit() {
    primaryStage.close();
  }

}
