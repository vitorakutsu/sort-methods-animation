package org.example.animacaoordenacao.Methods;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.concurrent.CompletableFuture;

public class ArrayUtils {

    private static final String countingSortCode =
            "public void countingSort() {\n" +
                    "\n" +
                    "\tint[] aux = new int[vet.length]; \n" +
                    "\tint[] cont = new int[maxValue]; \n" +
                    "\tint index = vet.length; \n" +
                    "\tint contIndex;\n" +
                    "\tint vetIndex;\n" +
                    "\n" +
                    "\tfor(int i = 0; i < index; i++){\n" + // 8
                    "\t\tcontIndex = vet[i] - 1;\n" + // 9
                    "\t\tcont[contIndex]++;\n" + // 10
                    "\t}\n" +
                    "\n" +
                    "\tindex = cont.length\n" + // 13
                    "\n" +
                    "\tfor(int i = 1; i < index; i++){\n" + // 15
                    "\t\tcont[i] = cont[i - 1] + cont[i];\n" + // 16
                    "\t}\n" +
                    "\n" +
                    "\tindex = vet.length\n" + // 19
                    "\n" +
                    "\tfor(int i = index; i >= 0; i--) {\n" + // 21
                    "\t\tvetIndex = vet[i] - 1;\n" + // 22
                    "\t\tcontIndex = cont[vetIndex] - 1;\n" + // 23
                    "\t\taux[contIndex] = vet[i];\n" + // 24
                    "\t\tcont[contIndex]--;\n" + // 25
                    "\t}\n" +
                    "}";
    private static final String bucketSortCode =
            "public void bucketSort() {\n" +
                    "\n" +
                    "\tint numBuckets = 2;\n" +
                    "\tint index = vet.length;\n" +
                    "\tint[][] buckets = new int[numBuckets][index];\n" +
                    "\tint firstBucketIndex = 0, secondBucketIndex = 0;\n" +
                    "\tint limit = (maxValue + minValue) / numBuckets;\n" +
                    "\n" +
                    "\tfor (int i = 0; i < index; i++) {\n" +
                    "\t\tif (vet[i] <= limit) {\n" +
                    "\t\t\tbuckets[0][firstBucketIndex++] = vet[i];\n" +
                    "\t\t} else {\n" +
                    "\t\t\tbuckets[1][secondBucketIndex++] = vet[i];\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "\n" +
                    "\tselectionSort(buckets[0], firstBucketIndex);\n" +
                    "\tselectionSort(buckets[1], secondBucketIndex);\n" +
                    "\n" +
                    "\tint j = 0;\n" +
                    "\tfor (int i = 0; i < firstBucketIndex; i++) {\n" +
                    "\t\tvet[j++] = buckets[0][i];\n" +
                    "\t}\n" +
                    "\tfor (int i = 0; i < secondBucketIndex; i++) {\n" +
                    "\t\tvet[j++] = buckets[1][i];\n" +
                    "\t}\n" +
                    "}";

    private static final String selectionSortFunction =
            "private void selectionSort(Button[] bucket, int index) {\n" +
                    "\n" +
                    "\tint smallerPosition = 0;\n" +
                    "\tButton auxValue\n" +
                    "\n" +
                    "\tfor(int i = 0; i < index - 1; i++) {\n" +
                    "\t\tsmallerPosition = i;\n" +
                    "\t\tfor(int j = i + 1; j < index; j++) {\n" +
                    "\t\t\tif(Integer.valueOf(bucket[i].getText()) >\n" + // 8
                    "\t\t\tInteger.valueOf(bucket[j].getText())) {\n" + // 9
                    "\t\t\t\tsmallerPosition = j;\n" + // 10
                    "\t\t\t}\n" + // 11
                    "\t\t}\n" + // 12
                    "\n" +
                    "\t\tauxValue = bucket[i];\n" + // 14
                    "\t\tbucket[i] = bucket[smallerPosition];\n" + // 15
                    "\t\tbucket[smallerPosition] = auxValue;\n" + // 16
                    "\t}\n" +
                    "}";

    ;
    private static Button[] arrayValues;
    private static Button[] countingValues;
    private static Button[][] bucketsValues;

    private static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public static void startButton(Stage stage, AnchorPane pane) {
        final boolean[] oneTime = {true};
        stage.setTitle("Pesquisa e Ordenacao");
    }

    public static void initializeArray(AnchorPane pane) {
        arrayValues = new Button[10];
        int maxValue = 0, minValue = 9, value;
        Label[] label = new Label[10];

        for (int i = 0; i < 10; i++) {
            value = (int) (Math.random() * 9) + 1;
            arrayValues[i] = new Button(String.valueOf(value));
            arrayValues[i].setLayoutX(10 + (80 * (i + 1)));
            arrayValues[i].setLayoutY(200);
            arrayValues[i].setMinHeight(40);
            arrayValues[i].setMinWidth(40);
            arrayValues[i].setFont(new Font(16));
            label[i] = new Label(String.valueOf(i));
            label[i].setLayoutX(25 + (80 * (i + 1)));
            label[i].setLayoutY(250);

            if (value > maxValue) {
                maxValue = value;
            }

            if (value < minValue) {
                minValue = value;
            }

            pane.getChildren().add(label[i]);
            pane.getChildren().add(arrayValues[i]);
        }

        methodsButton(pane, maxValue, minValue);
    }

    public static void countingSortArray(AnchorPane pane, int maxValue) {

        countingValues = new Button[maxValue];
        Label[] label = new Label[maxValue];

        Button[] indexValues = new Button[4];

        countingSortIndexes(pane, indexValues);

        int[] countingSortArray = new int[maxValue];

        for (int i = 0; i < maxValue; i++) {
            countingSortArray[i] = 0;
        }

        for (int i = 0; i < countingSortArray.length; i++) {
            countingValues[i] = new Button("0");
            countingValues[i].setLayoutX(10 + (80 * (i + 1)));
            countingValues[i].setLayoutY(300);
            countingValues[i].setMinHeight(40);
            countingValues[i].setMinWidth(40);
            countingValues[i].setFont(new Font(16));
            countingValues[i].setStyle("-fx-background-color: " + toRGBCode(Color.LIGHTGRAY));
            label[i] = new Label(String.valueOf(i));
            label[i].setLayoutX(25 + (80 * (i + 1)));
            label[i].setLayoutY(350);

            pane.getChildren().add(label[i]);
            pane.getChildren().add(countingValues[i]);
        }

        CodeBlock codeBlock = new CodeBlock(countingSortCode);

        buildCodeBlock(pane, codeBlock);

        AnimationUtils.countArray(arrayValues, countingValues, indexValues, codeBlock, () -> {
            AnimationUtils.sumCountingSortArray(countingValues, indexValues, codeBlock, () -> {
                startCountingSort(pane, indexValues, codeBlock);
            });
        });
    }

    public static void bucketSortArray(AnchorPane pane, int maxValue, int minValue) {
        int index = arrayValues.length;
        bucketsValues = new Button[2][index];
        Label[][] label = new Label[2][index];
        Button[] indexValues = new Button[8];

        bucketSortIndexes(pane, indexValues);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < index; j++) {
                bucketsValues[i][j] = new Button("X");
                bucketsValues[i][j].setLayoutX(10 + (80 * (j + 1)));
                bucketsValues[i][j].setLayoutY(300 + (i * 100));
                bucketsValues[i][j].setMinHeight(40);
                bucketsValues[i][j].setMinWidth(40);
                bucketsValues[i][j].setFont(new Font(16));
                bucketsValues[i][j].setStyle("-fx-background-color: " + toRGBCode(Color.LIGHTGRAY));
                label[i][j] = new Label(String.valueOf(j));
                label[i][j].setLayoutX(25 + (80 * (j + 1)));
                label[i][j].setLayoutY(350 + (i * 100));

                pane.getChildren().add(label[i][j]);
                pane.getChildren().add(bucketsValues[i][j]);
            }
        }

        CodeBlock bucketSortCodeBlock = new CodeBlock(bucketSortCode);
        buildCodeBlock(pane, bucketSortCodeBlock);

        CodeBlock selectionSortCodeBlock = new CodeBlock(selectionSortFunction);
        buildCodeBlock(pane, selectionSortCodeBlock);

        pane.getChildren().remove(selectionSortCodeBlock);
        AnimationUtils.bucketArray(arrayValues, bucketsValues, indexValues, bucketSortCodeBlock, maxValue, minValue, () -> {
            pane.getChildren().remove(bucketSortCodeBlock);
            pane.getChildren().add(selectionSortCodeBlock);
            AnimationUtils.selectionSort(bucketsValues, indexValues, selectionSortCodeBlock, () -> {
                pane.getChildren().remove(selectionSortCodeBlock);
                pane.getChildren().add(bucketSortCodeBlock);
                AnimationUtils.mergeArray(bucketsValues, indexValues, arrayValues, bucketSortCodeBlock);
            });
        });
    }

    public static void buildCodeBlock(AnchorPane pane, CodeBlock codeBlock) {

        codeBlock.setLayoutX(1000);
        codeBlock.setLayoutY(200);

        pane.getChildren().add(codeBlock);
    }

    public static void countingSortIndexes(AnchorPane pane, Button[] indexValues) {
        Label labelI = new Label("i");
        labelI.setLayoutX(920);
        labelI.setLayoutY(220);
        pane.getChildren().add(labelI);
        indexValues[0] = new Button("0");
        indexValues[0].setLayoutX(900);
        indexValues[0].setLayoutY(240);
        indexValues[0].setMinHeight(40);
        indexValues[0].setMinWidth(40);
        indexValues[0].setFont(new Font(16));
        pane.getChildren().add(indexValues[0]);

        Label labelIndex = new Label("index");
        labelIndex.setLayoutX(905);
        labelIndex.setLayoutY(300);
        pane.getChildren().add(labelIndex);
        indexValues[1] = new Button("0");
        indexValues[1].setLayoutX(900);
        indexValues[1].setLayoutY(320);
        indexValues[1].setMinHeight(40);
        indexValues[1].setMinWidth(40);
        indexValues[1].setFont(new Font(16));
        pane.getChildren().add(indexValues[1]);

        Label labelVetIndex = new Label("vetIndex");
        labelVetIndex.setLayoutX(895);
        labelVetIndex.setLayoutY(380);
        pane.getChildren().add(labelVetIndex);
        indexValues[2] = new Button("0");
        indexValues[2].setLayoutX(900);
        indexValues[2].setLayoutY(400);
        indexValues[2].setMinHeight(40);
        indexValues[2].setMinWidth(40);
        indexValues[2].setFont(new Font(16));
        pane.getChildren().add(indexValues[2]);

        Label labelContIndex = new Label("contIndex");
        labelContIndex.setLayoutX(895);
        labelContIndex.setLayoutY(460);
        pane.getChildren().add(labelContIndex);
        indexValues[3] = new Button("0");
        indexValues[3].setLayoutX(900);
        indexValues[3].setLayoutY(480);
        indexValues[3].setMinHeight(40);
        indexValues[3].setMinWidth(40);
        indexValues[3].setFont(new Font(16));
        pane.getChildren().add(indexValues[3]);
    }

    public static void bucketSortIndexes(AnchorPane pane, Button[] indexValues) {
        Label labelI = new Label("i");
        labelI.setLayoutX(920);
        labelI.setLayoutY(220);
        pane.getChildren().add(labelI);
        indexValues[0] = new Button("0");
        indexValues[0].setLayoutX(900);
        indexValues[0].setLayoutY(240);
        indexValues[0].setMinHeight(40);
        indexValues[0].setMinWidth(40);
        indexValues[0].setFont(new Font(16));
        pane.getChildren().add(indexValues[0]);

        Label labelIndex = new Label("index");
        labelIndex.setLayoutX(905);
        labelIndex.setLayoutY(300);
        pane.getChildren().add(labelIndex);
        indexValues[1] = new Button("0");
        indexValues[1].setLayoutX(900);
        indexValues[1].setLayoutY(320);
        indexValues[1].setMinHeight(40);
        indexValues[1].setMinWidth(40);
        indexValues[1].setFont(new Font(16));
        pane.getChildren().add(indexValues[1]);

        Label labelJ = new Label("j");
        labelJ.setLayoutX(920);
        labelJ.setLayoutY(380);
        pane.getChildren().add(labelJ);
        indexValues[2] = new Button("0");
        indexValues[2].setLayoutX(900);
        indexValues[2].setLayoutY(400);
        indexValues[2].setMinHeight(40);
        indexValues[2].setMinWidth(40);
        indexValues[2].setFont(new Font(16));
        pane.getChildren().add(indexValues[2]);

        Label labelFirstBucketIndex = new Label("firstBucketIndex");
        labelFirstBucketIndex.setLayoutX(860);
        labelFirstBucketIndex.setLayoutY(460);
        pane.getChildren().add(labelFirstBucketIndex);
        indexValues[3] = new Button("0");
        indexValues[3].setLayoutX(900);
        indexValues[3].setLayoutY(480);
        indexValues[3].setMinHeight(40);
        indexValues[3].setMinWidth(40);
        indexValues[3].setFont(new Font(16));
        pane.getChildren().add(indexValues[3]);

        Label labelSecondBucketIndex = new Label("secondBucketIndex");
        labelSecondBucketIndex.setLayoutX(860);
        labelSecondBucketIndex.setLayoutY(540);
        pane.getChildren().add(labelSecondBucketIndex);
        indexValues[4] = new Button("0");
        indexValues[4].setLayoutX(900);
        indexValues[4].setLayoutY(560);
        indexValues[4].setMinHeight(40);
        indexValues[4].setMinWidth(40);
        indexValues[4].setFont(new Font(16));
        pane.getChildren().add(indexValues[4]);

        Label labelLimit = new Label("limit");
        labelLimit.setLayoutX(905);
        labelLimit.setLayoutY(620);
        pane.getChildren().add(labelLimit);
        indexValues[5] = new Button("0");
        indexValues[5].setLayoutX(900);
        indexValues[5].setLayoutY(640);
        indexValues[5].setMinHeight(40);
        indexValues[5].setMinWidth(40);
        indexValues[5].setFont(new Font(16));
        pane.getChildren().add(indexValues[5]);

        Label smaller = new Label("smallerPosition");
        smaller.setLayoutX(880);
        smaller.setLayoutY(700);
        pane.getChildren().add(smaller);
        indexValues[6] = new Button("0");
        indexValues[6].setLayoutX(900);
        indexValues[6].setLayoutY(720);
        indexValues[6].setMinHeight(40);
        indexValues[6].setMinWidth(40);
        indexValues[6].setFont(new Font(16));
        pane.getChildren().add(indexValues[6]);
    }

    public static void methodsButton(AnchorPane pane, int maxValue, int minValue) {
        Button countingSort = new Button(), bucketSort = new Button();

        countingSort.setLayoutX(90);
        countingSort.setLayoutY(40);
        countingSort.setText("Counting Sort");
        countingSort.setOnAction(e -> {
            countingSort.setDisable(true);
            bucketSort.setDisable(true);

            countingSortArray(pane, maxValue);
        });

        pane.getChildren().add(countingSort);

        bucketSort.setLayoutX(240);
        bucketSort.setLayoutY(40);
        bucketSort.setText("Bucket Sort");
        bucketSort.setOnAction(e -> {
            countingSort.setDisable(true);
            bucketSort.setDisable(true);

            bucketSortArray(pane, maxValue, minValue);
        });

        pane.getChildren().add(bucketSort);
    }

    public static void startCountingSort(AnchorPane pane, Button[] indexValues, CodeBlock codeBlock) {
        Button[] countingSort = new Button[arrayValues.length];
        Label[] label = new Label[arrayValues.length];

        for (int i = 0; i < countingSort.length; i++) {
            countingSort[i] = new Button("");
            countingSort[i].setLayoutX(10 + (80 * (i + 1)));
            countingSort[i].setLayoutY(400);
            countingSort[i].setMinHeight(40);
            countingSort[i].setMinWidth(40);
            countingSort[i].setFont(new Font(16));
            label[i] = new Label(String.valueOf(i));
            label[i].setLayoutX(25 + (80 * (i + 1)));
            label[i].setLayoutY(450);

            pane.getChildren().add(label[i]);
            pane.getChildren().add(countingSort[i]);
        }

        AnimationUtils.buildFinalCountingSortArray(arrayValues, countingSort, countingValues, indexValues, codeBlock);
    }
}

