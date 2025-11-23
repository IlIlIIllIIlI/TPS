import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.PomodoroPhase;
import model.TimerState;


public class PomodoroApp extends Application {
    private DoubleProperty secondsRemaining = new SimpleDoubleProperty(15);
    private TimerState currentState = TimerState.IDLE;
    private PomodoroPhase currentPhase = PomodoroPhase.WORK;
    private DoubleProperty totalDuration = new SimpleDoubleProperty(15);
    private IntegerProperty cycleCount = new SimpleIntegerProperty(1);
    @Override
    public void start(Stage primaryStage){
        Label timerLabel = new Label();
        timerLabel.setStyle("-fx-font-size: 72px; -fx-font-weight: bold;");
        Label phaseLabel = new Label();
        phaseLabel.setStyle("-fx-font-size: 72px; -fx-font-weight: bold;");
        Label cycleLabel = new Label();
        cycleLabel.setStyle("-fx-font-size: 72px; -fx-font-weight: bold;");
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root,400,300);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Pomodoro Timer");
        primaryStage.show();

        timerLabel.textProperty().bind(
                Bindings.createStringBinding(
                        () -> formatTime(this.secondsRemaining.get()),
                        this.secondsRemaining
                )
        );
        cycleLabel.textProperty().bind(
                this.cycleCount.asString()
        );


        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event ->{
                    if (this.secondsRemaining.get() >0) {
                        this.secondsRemaining.set(this.secondsRemaining.get() -1);
                    } else {
                        this.switchPhase(phaseLabel,timerLabel);
                    }
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.pause();

        Button startButton = new Button("Start");
        Button pauseButton = new Button("Pause");
        Button resetButton = new Button("Reset");

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(startButton,pauseButton,resetButton);

        updateButtonStates(startButton,pauseButton);


        startButton.setOnAction(event -> {
            if (this.currentState != TimerState.RUNNING) {
             timeline.play();
             this.currentState = TimerState.RUNNING;
             updateButtonStates(startButton,pauseButton);

            }
        });
        pauseButton.setOnAction(event ->{
            if (this.currentState == TimerState.RUNNING) {
                timeline.pause();
                this.currentState = TimerState.PAUSED;
                updateButtonStates(startButton,pauseButton);

            }
        });
        resetButton.setOnAction(event -> {
            timeline.stop();
            this.currentState = TimerState.IDLE;
            this.currentPhase = PomodoroPhase.WORK;
            this.secondsRemaining.set(15);
            updateButtonStates(startButton,pauseButton);
        });

        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefWidth(300);

        progressBar.progressProperty().bind(
                this.secondsRemaining.divide(this.totalDuration)
        );


        updatePhaseDisplay(phaseLabel,timerLabel);
        root.getChildren().addAll(timerLabel,progressBar, buttonBox,phaseLabel,cycleLabel);

    }

    public static void main(String[] args) {
        launch(args);
    }

    private String formatTime(double totalSeconds){
        long minutes = Math.round(totalSeconds) / 60;
        long seconds = Math.round(totalSeconds) % 60;
        return String.format("%02d:%02d",minutes,seconds);
    }

    private void switchPhase(Label phaseLabel, Label timerLabel) {

        if (this.currentPhase == PomodoroPhase.WORK) {
            this.currentPhase = PomodoroPhase.BREAK;
            this.secondsRemaining.set(3);
        } else {
            this.currentPhase = PomodoroPhase.WORK;
            this.secondsRemaining.set(15);
            this.cycleCount.set(this.cycleCount.get() +1);

        }

        this.totalDuration.set(this.currentPhase == PomodoroPhase.WORK ? 15 : 3);
        updatePhaseDisplay(phaseLabel, timerLabel);
    }

    private void updateButtonStates(Button startButton, Button pauseButton){
        startButton.setDisable(this.currentState == TimerState.RUNNING);
        pauseButton.setDisable(this.currentState != TimerState.RUNNING);
    }

    private void updatePhaseDisplay(Label phaseLabel, Label timerLabel) {
        if (this.currentPhase == PomodoroPhase.WORK) {
            phaseLabel.setText("WORK");
            phaseLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #2E7D32;");
            timerLabel.setStyle("-fx-font-size: 72px; -fx-font-weight: bold; -fx-text-fill: #2E7D32;");
        } else {
            phaseLabel.setText("PAUSE");
            phaseLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #1976D2;");
            timerLabel.setStyle("-fx-font-size: 72px; -fx-font-weight: bold; -fx-text-fill: #1976D2;");
        }
    }
}
