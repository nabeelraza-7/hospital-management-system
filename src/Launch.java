import utilities.Main;

public class Launch {
    public static void main(String[] args) {
        String[] arg = {"--module-path lib\\javafx-sdk-15.0.1\\lib", 
        "--add-modules javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web",
        "--add-opens javafx.base/com.sun.javafx.runtime=ALL-UNNAMED",
        "--add-opens javafx.controls/com.sun.javafx.scene.control.behavior=ALL-UNNAMED",
        "--add-opens javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED",
        "--add-opens javafx.base/com.sun.javafx.binding=ALL-UNNAMED",
        "--add-opens javafx.base/com.sun.javafx.event=ALL-UNNAMED",
        "--add-opens javafx.graphics/com.sun.javafx.stage=ALL-UNNAMED"};
        Main.main(arg);
    }
}