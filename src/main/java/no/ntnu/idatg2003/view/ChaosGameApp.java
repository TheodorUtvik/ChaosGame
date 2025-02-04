package no.ntnu.idatg2003.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import no.ntnu.idatg2003.controller.CreateCustomGameController;
import no.ntnu.idatg2003.controller.FractalDisplayController;
import no.ntnu.idatg2003.controller.FrontPageController;
import no.ntnu.idatg2003.controller.MandelbrotController;
import no.ntnu.idatg2003.controller.RunCustomGameMenuController;
import no.ntnu.idatg2003.controller.RunGameMenuController;
import no.ntnu.idatg2003.model.game.engine.ChaosGameDescription;
import no.ntnu.idatg2003.utility.enums.PresetTransforms;
import no.ntnu.idatg2003.utility.logging.LoggerUtil;

/**
 * The main application class for the Chaos Game application.
 */
public class ChaosGameApp extends Application {

  private Stage primaryStage;
  private Scene mainScene;
  private RunCustomGameMenuController runCustomGameMenuController;
  private CreateCustomGameController createCustomGameController;
  private FrontPageController frontPageController;
  private RunGameMenuController runGameMenuController;
  private FractalDisplayController fractalDisplayController;
  private MandelbrotController mandelbrotController;

  public static void appMain(String[] args) {
    launch(args);
  }

  /**
   * Starts the application by initializing the controllers and showing the front page scene.
   *
   * @param primaryStage the primary stage of the application
   * @throws Exception if the application fails to start
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    LoggerUtil.logInfo("Starting Chaos Game application");

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
    runGameMenuController = new RunGameMenuController(this);
    fractalDisplayController = new FractalDisplayController(this);
    createCustomGameController = new CreateCustomGameController(this);
    runCustomGameMenuController = new RunCustomGameMenuController(this);
    mandelbrotController = new MandelbrotController(this);
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
  public void showRunGameMenu() {
    this.mainScene = runGameMenuController.getScene();
    primaryStage.setScene(this.mainScene);
  }

  /**
   * Shows the preset game scene of the application, and creates its corresponding game.
   *
   * @param type the type of the game to be created
   */
  public void showPresetsGameScene(PresetTransforms type) {
    fractalDisplayController.createGame(type);
    this.mainScene = fractalDisplayController.getScene();
    primaryStage.setScene(this.mainScene);
  }

  /**
   * Shows the choice to create custom game scene of the application.
   */
  public void showCreateCustomGameScene() {
    this.mainScene = createCustomGameController.getScene();
    primaryStage.setScene(this.mainScene);
  }

  /**
   * Shows the run custom games scene of the application.
   */
  public void showRunCustomGamesScene() {
    LoggerUtil.logInfo("Showing custom games scene");
    this.mainScene = runCustomGameMenuController.getScene();
    primaryStage.setScene(this.mainScene);
  }

  /**
   * Runs a custom game with the given file.
   *
   * @param file the file to run
   */
  public void runCustomGame(String file) {
    LoggerUtil.logInfo("Running custom game: " + file);
    try {
      fractalDisplayController.createCustomGame(file);
      this.mainScene = fractalDisplayController.getScene();
      primaryStage.setScene(this.mainScene);
    } catch (Exception e) {
      LoggerUtil.logError("Failed to run custom game: " + e.getMessage());
      runCustomGameMenuController.showErrorMessage();
    }
  }

  /**
   * Runs a custom game with the given description.
   *
   * @param description the description of the game to run
   */
  public void runCustomGame(ChaosGameDescription description) {
    LoggerUtil.logInfo("Running custom game");
    fractalDisplayController.createCustomGame(description);
    this.mainScene = fractalDisplayController.getScene();
    primaryStage.setScene(this.mainScene);
  }

  /**
   * Exits the application.
   */
  public void exit() {
    primaryStage.close();
  }

  /**
   * Shows the Mandelbrot scene of the application.
   */
  public void showMandelbrotScene() {
    this.mainScene = mandelbrotController.getScene();
    mandelbrotController.createMandelbrotGame();
    primaryStage.setScene(this.mainScene);
  }
}
