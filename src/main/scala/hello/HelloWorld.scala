package hello

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage

class ScalaFXHelloWorld extends Application {
  override def start(stage: Stage): Unit = {
    stage.setTitle("Does it work?")
    stage.setScene(new Scene(
      new Label("It works!")
    ))
    stage.show()
  }
}

object ScalaFXHelloWorld {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[ScalaFXHelloWorld], args: _*)
  }
}