package org.example.animacaoordenacao.Methods;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CodeBlock extends VBox {

    private String code;

    public CodeBlock(String code) {
        this.code = code;
        setupCodeBlock();
    }

    private void setupCodeBlock() {
        String[] lines = code.split("\n");
        for (String line : lines) {
            Label label = new Label(line);
            label.setFont(new Font("Courier New", 12));
            getChildren().add(label);
        }
    }

    public void highlightLine(int lineNumber, Color color) {
        if (lineNumber >= 0 && lineNumber < getChildren().size()) {
            Label lineLabel = (Label) getChildren().get(lineNumber);
            lineLabel.setTextFill(color);
        }
    }

    public void unhighlightLine(int lineNumber) {
        highlightLine(lineNumber, Color.BLACK);
    }
}

