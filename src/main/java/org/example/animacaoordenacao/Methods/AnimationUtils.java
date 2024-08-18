package org.example.animacaoordenacao.Methods;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class AnimationUtils {

    private static FadeTransition countFadeTransition, valueFadeTransition, finalFadeTransition, indexFadeTransition;
    private static FadeTransition bucketFadeTransition;


    public static void moveButtons(Button[] arrayValues) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                // permutação na tela
                for (int i = 0; i < 10; i++) {
                    Platform.runLater(() -> arrayValues[0].setLayoutY(arrayValues[0].getLayoutY() + 5));
                    Platform.runLater(() -> arrayValues[1].setLayoutY(arrayValues[1].getLayoutY() - 5));
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 16; i++) {
                    Platform.runLater(() -> arrayValues[0].setLayoutX(arrayValues[0].getLayoutX() + 5));

                    Platform.runLater(() -> arrayValues[1].setLayoutX(arrayValues[1].getLayoutX() - 5));
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 10; i++) {
                    Platform.runLater(() -> arrayValues[0].setLayoutY(arrayValues[0].getLayoutY() - 5));
                    Platform.runLater(() -> arrayValues[1].setLayoutY(arrayValues[1].getLayoutY() + 5));
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // permutação na memória
                Button aux = arrayValues[0];
                arrayValues[0] = arrayValues[1];
                arrayValues[1] = aux;
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    public static void sumCountingSortArray(Button[] countingSortArray, Button[] indexArray, CodeBlock codeBlock, Runnable callback) {
        Task<Void> task = new Task<Void>() {
            int sum = 0;

            @Override
            protected Void call() throws InterruptedException {
                System.out.println(countingSortArray.length);
                int i = 1;
                Platform.runLater(() -> {
                    codeBlock.highlightLine(13, Color.RED);
                    indexArray[1].setText(String.valueOf(countingSortArray.length));
                    changeIndexArrayColor(indexArray[1], Color.rgb(100, 200, 200));
                });

                Thread.sleep(1000);

                codeBlock.unhighlightLine(13);

                int value = Integer.parseInt(countingSortArray[i - 1].getText());
                sum = value;
                while (i < countingSortArray.length) {
                    codeBlock.highlightLine(15, Color.RED);
                    value = Integer.parseInt(countingSortArray[i].getText());
                    sum += value;
                    int finalSum = sum;
                    int finalI = i;

                    Platform.runLater(() -> {
                        indexArray[0].setText(String.valueOf(finalI));
                        changeIndexArrayColor(indexArray[0], Color.rgb(100, 200, 200));
                    });
                    Thread.sleep(1000);

                    Platform.runLater(() -> {
                        changeCountArrayColor(countingSortArray[finalI], Color.LIGHTPINK);
                    });
                    Thread.sleep(1000);

                    codeBlock.unhighlightLine(15);

                    Platform.runLater(() -> {
                        changeCountArrayColor(countingSortArray[finalI - 1], Color.LIGHTPINK);
                    });
                    Thread.sleep(1000);

                    Platform.runLater(() -> {
                        codeBlock.highlightLine(16, Color.RED);
                        countingSortArray[finalI].setText(String.valueOf(finalSum));
                    });
                    Thread.sleep(2000);

                    i++;
                    Thread.sleep(1000);

                    codeBlock.unhighlightLine(16);

                    Platform.runLater(() -> {
                        changeCountArrayColor(countingSortArray[finalI], Color.LIGHTGRAY);
                    });
                    Thread.sleep(1000);

                    Platform.runLater(() -> {
                        changeCountArrayColor(countingSortArray[finalI - 1], Color.LIGHTGRAY);
                    });

                    Thread.sleep(1000);
                }

                Platform.runLater(callback);
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    public static void countArray(Button[] values, Button[] count, Button[] indexArray, CodeBlock codeBlock, Runnable callback) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                Platform.runLater(() -> {
                    indexArray[1].setText(String.valueOf(values.length));
                    changeIndexArrayColor(indexArray[1], Color.rgb(100, 200, 200));
                });

                Thread.sleep(1000);

                for (int i = 0; i < values.length; i++) {
                    int value = Integer.parseInt(values[i].getText());
                    int index = value - 1;
                    int auxIndex = i;

                    codeBlock.highlightLine(8, Color.RED);
                    Platform.runLater(() -> {
                        indexArray[0].setText(String.valueOf(auxIndex));
                        changeIndexArrayColor(indexArray[0], Color.rgb(50, 200, 200));
                    });

                    Thread.sleep(2000);

                    Platform.runLater(() -> {
                        changeValuesArrayColor(values[auxIndex], Color.rgb(104, 255, 0));
                    });

                    Thread.sleep(1000);

                    Platform.runLater(() -> {
                        codeBlock.unhighlightLine(8);
                        codeBlock.highlightLine(9, Color.RED);
                        indexArray[3].setText(String.valueOf(Integer.valueOf(values[auxIndex].getText()) - 1));
                        changeIndexArrayColor(indexArray[3], Color.rgb(104, 255, 0));
                    });

                    Thread.sleep(2000);

                    Platform.runLater(() -> {
                        codeBlock.unhighlightLine(9);
                        codeBlock.highlightLine(10, Color.RED);
                        changeCountArrayColor(count[index], Color.rgb(104, 255, 0));
                    });

                    Thread.sleep(1500);


                    Platform.runLater(() -> {
                        codeBlock.unhighlightLine(9);
                        codeBlock.highlightLine(10, Color.RED);
                        count[index].setText(String.valueOf(Integer.parseInt(count[index].getText()) + 1));
                    });

                    Thread.sleep(3000);


                    Platform.runLater(() -> {
                        codeBlock.unhighlightLine(10);
                        changeCountArrayColor(count[index], Color.LIGHTGRAY);
                    });

                    Thread.sleep(1000);
                }

                Platform.runLater(callback);
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    public static void bucketArray(Button[] values, Button[][] buckets, Button[] indexArray, CodeBlock codeBlock, int maxValue, int minValue, Runnable callback) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {

                int index = values.length;
                int limit = (maxValue + minValue) / 2;
                codeBlock.highlightLine(3, Color.RED);
                Platform.runLater(() -> {
                    indexArray[1].setText(String.valueOf(index));
                    changeIndexArrayColor(indexArray[1], Color.rgb(104, 255, 0));
                });

                Thread.sleep(3000);

                codeBlock.unhighlightLine(3);

                codeBlock.highlightLine(6, Color.RED);
                Platform.runLater(() -> {
                    indexArray[5].setText(String.valueOf(limit));
                    changeIndexArrayColor(indexArray[5], Color.rgb(104, 255, 0));
                });

                Thread.sleep(3000);

                codeBlock.unhighlightLine(6);

                final int[] firstBucketIndex = {0};
                final int[] secondBucketIndex = {0};

                for (int i = 0; i < index; i++) {

                    codeBlock.highlightLine(8, Color.RED);
                    int finalI = i;
                    Platform.runLater(() -> {
                        indexArray[0].setText(String.valueOf(finalI));
                        changeIndexArrayColor(indexArray[0], Color.rgb(104, 255, 0));
                    });

                    Thread.sleep(1000);

                    int finalI3 = i;
                    Platform.runLater(() -> {
                        changeValuesArrayColor(values[finalI3], Color.rgb(104, 255, 0));
                    });

                    Thread.sleep(5000);

                    Platform.runLater(() -> {
                        codeBlock.unhighlightLine(8);
                        codeBlock.highlightLine(9, Color.RED);
                    });

                    Thread.sleep(2000);

                    if (Integer.valueOf(values[i].getText()) <= limit) {

                        codeBlock.highlightLine(10, Color.RED);
                        int finalI2 = i;
                        Platform.runLater(() -> {
                            buckets[0][firstBucketIndex[0]].setText(values[finalI2].getText());
                            changeBucketValuesArrayColor(buckets[0][firstBucketIndex[0]], Color.rgb(255, 120, 120));
                            firstBucketIndex[0]++;
                        });

                        Thread.sleep(2000);

                        Platform.runLater(() -> {
                            indexArray[3].setText(String.valueOf(firstBucketIndex[0]));
                            changeIndexArrayColor(indexArray[3], Color.rgb(255, 120, 120));
                        });

                        Thread.sleep(2000);

                        codeBlock.unhighlightLine(9);
                        codeBlock.unhighlightLine(10);

                    } else {

                        codeBlock.highlightLine(12, Color.RED);

                        int finalI1 = i;
                        Platform.runLater(() -> {
                            buckets[1][secondBucketIndex[0]].setText(values[finalI1].getText());
                            changeBucketValuesArrayColor(buckets[1][secondBucketIndex[0]], Color.rgb(255, 80, 80));
                            secondBucketIndex[0]++;
                        });

                        Thread.sleep(2000);

                        Platform.runLater(() -> {
                            indexArray[4].setText(String.valueOf(secondBucketIndex[0]));
                            changeIndexArrayColor(indexArray[4], Color.rgb(255, 80, 80));
                        });

                        Thread.sleep(2000);

                        codeBlock.unhighlightLine(9);
                        codeBlock.unhighlightLine(12);
                    }
                }

                Platform.runLater(() -> {
                    codeBlock.highlightLine(16, Color.RED);
                    codeBlock.highlightLine(17, Color.RED);
                });

                Thread.sleep(5000);

                Platform.runLater(callback);
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    public static void selectionSort(Button[][] buckets, Button[] indexArray, CodeBlock codeBlock, Runnable callback) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {

                int aux = 0;
                int firstBucketIndex = Integer.valueOf(indexArray[3].getText());
                int smallerPosition;
                Button auxValue;
                int secondBucketIndex = Integer.valueOf(indexArray[4].getText());

                for (int i = 0; i < firstBucketIndex - 1; i++) {
                    smallerPosition = i;

                    codeBlock.highlightLine(5, Color.RED);

                    Thread.sleep(3000);

                    int finalI1 = i;
                    Platform.runLater(() -> {
                        indexArray[0].setText(String.valueOf(finalI1));
                        changeIndexArrayColor(indexArray[0], Color.rgb(104, 255, 0));
                    });

                    Thread.sleep(2000);

                    Platform.runLater(() -> {
                        indexArray[1].setText(String.valueOf(firstBucketIndex));
                        changeIndexArrayColor(indexArray[1], Color.rgb(104, 255, 0));
                    });

                    Thread.sleep(2000);

                    Platform.runLater(() -> {
                        changeBucketValuesArrayColor(buckets[0][finalI1], Color.rgb(138, 253, 243));
                    });

                    Thread.sleep(3000);

                    int finalSmallerPosition = smallerPosition;
                    Platform.runLater(() -> {
                        codeBlock.unhighlightLine(5);
                        codeBlock.highlightLine(6, Color.RED);
                        indexArray[6].setText(String.valueOf(finalSmallerPosition));
                        changeIndexArrayColor(indexArray[6], Color.rgb(178, 111, 255));
                    });

                    Thread.sleep(3000);

                    for (int j = i + 1; j < firstBucketIndex; j++) {

                        codeBlock.unhighlightLine(6);
                        codeBlock.highlightLine(7, Color.RED);

                        int finalJ1 = j;
                        Platform.runLater(() -> {
                            indexArray[2].setText(String.valueOf(finalJ1));
                            changeIndexArrayColor(indexArray[2], Color.rgb(255, 120, 120));
                        });

                        Thread.sleep(3000);

                        codeBlock.unhighlightLine(7);
                        codeBlock.highlightLine(8, Color.RED);
                        codeBlock.highlightLine(9, Color.RED);

                        Thread.sleep(3000);

                        int finalJ = j;
                        Platform.runLater(() -> {
                            changeBucketValuesArrayColor(buckets[0][finalJ], Color.rgb(138, 253, 153));
                        });

                        Thread.sleep(3000);

                        if (Integer.valueOf(buckets[0][j].getText()) < Integer.valueOf(buckets[0][smallerPosition].getText())) {

                            if (aux == 0) {
                                Platform.runLater(() -> {
                                    changeBucketValuesArrayColor(buckets[0][finalI1], Color.rgb(255, 120, 120));
                                });

                                aux++;

                                Thread.sleep(3000);
                            }

                            int finalSmallerPosition3 = smallerPosition;
                            Platform.runLater(() -> {
                                changeBucketValuesArrayColor(buckets[0][finalSmallerPosition3], Color.rgb(255, 120, 120));
                            });

                            Thread.sleep(3000);

                            smallerPosition = j;

                            int finalSmallerPosition1 = smallerPosition;
                            Platform.runLater(() -> {
                                codeBlock.highlightLine(10, Color.RED);
                                indexArray[6].setText(String.valueOf(finalSmallerPosition1));
                                changeIndexArrayColor(indexArray[6], Color.rgb(178, 111, 255));
                            });

                            Thread.sleep(3000);
                        } else {
                            Platform.runLater(() -> {
                                changeBucketValuesArrayColor(buckets[0][finalJ], Color.rgb(255, 120, 120));
                            });

                            Thread.sleep(3000);
                        }

                        codeBlock.unhighlightLine(8);
                        codeBlock.unhighlightLine(9);
                        codeBlock.unhighlightLine(10);

                        Thread.sleep(3000);
                    }

                    if (smallerPosition != i) {

                        Platform.runLater(() -> {
                            changeBucketValuesArrayColor(buckets[0][finalI1], Color.rgb(138, 253, 243));
                        });

                        Thread.sleep(3000);

                        codeBlock.highlightLine(14, Color.RED);
                        codeBlock.highlightLine(15, Color.RED);
                        codeBlock.highlightLine(16, Color.RED);

                        for (int k = 0; k < 10; k++) {
                            int finalI = i;
                            Platform.runLater(() -> {
                                buckets[0][finalI].setLayoutY(buckets[0][finalI].getLayoutY() + 5);
                            });
                            int finalSmallerPosition2 = smallerPosition;
                            Platform.runLater(() -> {
                                buckets[0][finalSmallerPosition2].setLayoutY(buckets[0][finalSmallerPosition2].getLayoutY() - 5);
                            });

                            Thread.sleep(50);
                        }

                        int difference = smallerPosition - i;

                        for (int k = 0; k < 16 * difference; k++) {
                            int finalI = i;
                            Platform.runLater(() -> {
                                buckets[0][finalI].setLayoutX(buckets[0][finalI].getLayoutX() + 5);
                            });
                            int finalSmallerPosition2 = smallerPosition;
                            Platform.runLater(() -> {
                                buckets[0][finalSmallerPosition2].setLayoutX(buckets[0][finalSmallerPosition2].getLayoutX() - 5);
                            });

                            Thread.sleep(50);
                        }

                        for (int k = 0; k < 10; k++) {
                            int finalI = i;
                            Platform.runLater(() -> {
                                buckets[0][finalI].setLayoutY(buckets[0][finalI].getLayoutY() - 5);
                            });
                            int finalSmallerPosition2 = smallerPosition;
                            Platform.runLater(() -> {
                                buckets[0][finalSmallerPosition2].setLayoutY(buckets[0][finalSmallerPosition2].getLayoutY() + 5);
                            });

                            Thread.sleep(50);
                        }

                        auxValue = buckets[0][i];
                        buckets[0][i] = buckets[0][smallerPosition];
                        buckets[0][smallerPosition] = auxValue;

                        codeBlock.unhighlightLine(14);
                        codeBlock.unhighlightLine(15);
                        codeBlock.unhighlightLine(16);

                        Platform.runLater(() -> {
                            changeBucketValuesArrayColor(buckets[0][finalI1], Color.rgb(255, 120, 120));
                        });

                        Thread.sleep(1000);

                        int finalSmallerPosition4 = smallerPosition;
                        Platform.runLater(() -> {
                            changeBucketValuesArrayColor(buckets[0][finalSmallerPosition4], Color.rgb(255, 120, 120));
                        });

                        Thread.sleep(3000);
                    }

                    Platform.runLater(() -> {
                        changeBucketValuesArrayColor(buckets[0][finalI1], Color.rgb(104, 255, 0));
                    });

                    Thread.sleep(3000);
                }

                Platform.runLater(() -> {
                    changeBucketValuesArrayColor(buckets[0][firstBucketIndex - 1], Color.rgb(104, 255, 0));
                });

                Thread.sleep(3000);

                aux = 0;
                for (int i = 0; i < secondBucketIndex - 1; i++) {
                    smallerPosition = i;

                    codeBlock.highlightLine(5, Color.RED);

                    Thread.sleep(3000);

                    int finalI1 = i;
                    Platform.runLater(() -> {
                        indexArray[0].setText(String.valueOf(finalI1));
                        changeIndexArrayColor(indexArray[0], Color.rgb(104, 255, 0));
                    });

                    Thread.sleep(2000);

                    Platform.runLater(() -> {
                        indexArray[1].setText(String.valueOf(secondBucketIndex));
                        changeIndexArrayColor(indexArray[1], Color.rgb(104, 255, 0));
                    });

                    Thread.sleep(2000);

                    Platform.runLater(() -> {
                        changeBucketValuesArrayColor(buckets[1][finalI1], Color.rgb(138, 253, 243));
                    });

                    Thread.sleep(3000);

                    int finalSmallerPosition = smallerPosition;
                    Platform.runLater(() -> {
                        codeBlock.unhighlightLine(5);
                        codeBlock.highlightLine(6, Color.RED);
                        indexArray[6].setText(String.valueOf(finalSmallerPosition));
                        changeIndexArrayColor(indexArray[6], Color.rgb(178, 111, 255));
                    });

                    Thread.sleep(3000);

                    for (int j = i + 1; j < secondBucketIndex; j++) {

                        codeBlock.unhighlightLine(6);
                        codeBlock.highlightLine(7, Color.RED);

                        int finalJ1 = j;
                        Platform.runLater(() -> {
                            indexArray[2].setText(String.valueOf(finalJ1));
                            changeIndexArrayColor(indexArray[2], Color.rgb(178, 111, 255));
                        });

                        Thread.sleep(2000);

                        codeBlock.unhighlightLine(7);
                        codeBlock.highlightLine(8, Color.RED);
                        codeBlock.highlightLine(9, Color.RED);

                        Thread.sleep(3000);

                        int finalJ = j;
                        Platform.runLater(() -> {
                            changeBucketValuesArrayColor(buckets[1][finalJ], Color.rgb(138, 253, 153));
                        });

                        Thread.sleep(3000);

                        if (Integer.valueOf(buckets[1][j].getText()) < Integer.valueOf(buckets[1][smallerPosition].getText())) {

                            if (aux == 0) {
                                Platform.runLater(() -> {
                                    changeBucketValuesArrayColor(buckets[1][finalI1], Color.rgb(255, 80, 80));
                                });

                                aux++;

                                Thread.sleep(3000);
                            }

                            int finalSmallerPosition3 = smallerPosition;
                            Platform.runLater(() -> {
                                changeBucketValuesArrayColor(buckets[1][finalSmallerPosition3], Color.rgb(255, 80, 80));
                            });

                            Thread.sleep(3000);

                            smallerPosition = j;

                            int finalSmallerPosition1 = smallerPosition;
                            Platform.runLater(() -> {
                                codeBlock.highlightLine(10, Color.RED);
                                indexArray[6].setText(String.valueOf(finalSmallerPosition1));
                                changeIndexArrayColor(indexArray[6], Color.rgb(178, 111, 255));
                            });

                            Thread.sleep(3000);
                        } else {
                            Platform.runLater(() -> {
                                changeBucketValuesArrayColor(buckets[1][finalJ], Color.rgb(255, 80, 80));
                            });

                            Thread.sleep(3000);
                        }

                        codeBlock.unhighlightLine(8);
                        codeBlock.unhighlightLine(9);
                        codeBlock.unhighlightLine(10);

                        Thread.sleep(3000);
                    }

                    if (smallerPosition != i) {

                        Platform.runLater(() -> {
                            changeBucketValuesArrayColor(buckets[1][finalI1], Color.rgb(138, 253, 243));
                        });

                        Thread.sleep(3000);

                        codeBlock.highlightLine(14, Color.RED);
                        codeBlock.highlightLine(15, Color.RED);
                        codeBlock.highlightLine(16, Color.RED);

                        for (int k = 0; k < 10; k++) {
                            int finalI = i;
                            Platform.runLater(() -> {
                                buckets[1][finalI].setLayoutY(buckets[1][finalI].getLayoutY() + 5);
                            });
                            int finalSmallerPosition2 = smallerPosition;
                            Platform.runLater(() -> {
                                buckets[1][finalSmallerPosition2].setLayoutY(buckets[1][finalSmallerPosition2].getLayoutY() - 5);
                            });

                            Thread.sleep(50);
                        }

                        int difference = smallerPosition - i;

                        for (int k = 0; k < 16 * difference; k++) {
                            int finalI = i;
                            Platform.runLater(() -> {
                                buckets[1][finalI].setLayoutX(buckets[1][finalI].getLayoutX() + 5);
                            });
                            int finalSmallerPosition2 = smallerPosition;
                            Platform.runLater(() -> {
                                buckets[1][finalSmallerPosition2].setLayoutX(buckets[1][finalSmallerPosition2].getLayoutX() - 5);
                            });

                            Thread.sleep(50);
                        }

                        for (int k = 0; k < 10; k++) {
                            int finalI = i;
                            Platform.runLater(() -> {
                                buckets[1][finalI].setLayoutY(buckets[1][finalI].getLayoutY() - 5);
                            });
                            int finalSmallerPosition2 = smallerPosition;
                            Platform.runLater(() -> {
                                buckets[1][finalSmallerPosition2].setLayoutY(buckets[1][finalSmallerPosition2].getLayoutY() + 5);
                            });

                            Thread.sleep(50);
                        }

                        auxValue = buckets[1][i];
                        buckets[1][i] = buckets[1][smallerPosition];
                        buckets[1][smallerPosition] = auxValue;

                        codeBlock.unhighlightLine(14);
                        codeBlock.unhighlightLine(15);
                        codeBlock.unhighlightLine(16);

                        Platform.runLater(() -> {
                            changeBucketValuesArrayColor(buckets[1][finalI1], Color.rgb(255, 80, 80));
                        });

                        Thread.sleep(1000);

                        int finalSmallerPosition4 = smallerPosition;
                        Platform.runLater(() -> {
                            changeBucketValuesArrayColor(buckets[1][finalSmallerPosition4], Color.rgb(255, 80, 80));
                        });

                        Thread.sleep(3000);
                    }

                    Platform.runLater(() -> {
                        changeBucketValuesArrayColor(buckets[1][finalI1], Color.rgb(104, 255, 0));
                    });

                    Thread.sleep(3000);
                }

                Platform.runLater(() -> {
                    changeBucketValuesArrayColor(buckets[1][secondBucketIndex - 1], Color.rgb(104, 255, 0));
                });

                Thread.sleep(3000);

                Thread.sleep(5000);

                Platform.runLater(callback);
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    public static void mergeArray(Button[][] buckets, Button[] indexArray, Button[] values, CodeBlock codeBlock) {
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws InterruptedException {

                codeBlock.unhighlightLine(16);
                codeBlock.unhighlightLine(17);

                int firstBucketIndex = Integer.valueOf(indexArray[3].getText());
                int secondBucketIndex = Integer.valueOf(indexArray[4].getText());
                int j = 0;

                Platform.runLater(() -> {
                    codeBlock.highlightLine(19, Color.RED);
                    indexArray[2].setText("0");
                    changeIndexArrayColor(indexArray[2], Color.rgb(138, 253, 243));
                });

                Thread.sleep(3000);

                for (int i = 0; i < firstBucketIndex; i++) {

                    int finalI = i;
                    int finalJ = j;
                    Platform.runLater(() -> {
                        codeBlock.unhighlightLine(19);
                        codeBlock.highlightLine(20, Color.RED);
                        indexArray[0].setText(String.valueOf(finalI));
                        changeIndexArrayColor(indexArray[0], Color.rgb(178, 111, 255));
                    });

                    Thread.sleep(2000);

                    Platform.runLater(() -> {
                        codeBlock.unhighlightLine(20);
                        codeBlock.highlightLine(21, Color.RED);
                        changeBucketValuesArrayColor(buckets[0][finalI], Color.rgb(178, 111, 255));
                    });

                    Thread.sleep(2000);

                    Platform.runLater(() -> {
                        changeValuesArrayColor(values[finalJ], Color.rgb(138, 253, 243));
                    });

                    Thread.sleep(2000);

                    Platform.runLater(() -> {
                        values[finalJ].setText(buckets[0][finalI].getText());
                    });

                    Thread.sleep(2000);

                    Platform.runLater(() -> {
                        buckets[0][finalI].setDisable(true);
//                        changeOpacityValuesArrayColor(buckets[0][finalI], Color.rgb(178, 111, 255));
                    });

                    Thread.sleep(2000);

                    codeBlock.unhighlightLine(21);

                    j++;

                    Platform.runLater(() -> {
                        indexArray[2].setText(String.valueOf(finalJ));
                        changeIndexArrayColor(indexArray[2], Color.rgb(138, 253, 243));
                    });
                    Thread.sleep(2000);
                }

                for (int i = 0; i < secondBucketIndex; i++) {

                    int finalI = i;
                    int finalJ = j;
                    Platform.runLater(() -> {
                        codeBlock.highlightLine(23, Color.RED);
                        indexArray[0].setText(String.valueOf(finalI));
                        changeIndexArrayColor(indexArray[0], Color.rgb(178, 111, 255));
                    });

                    Thread.sleep(2000);

                    Platform.runLater(() -> {
                        codeBlock.unhighlightLine(23);
                        codeBlock.highlightLine(24, Color.RED);
                        changeBucketValuesArrayColor(buckets[1][finalI], Color.rgb(178, 111, 255));
                    });

                    Thread.sleep(1000);

                    Platform.runLater(() -> {
                        changeValuesArrayColor(values[finalJ], Color.rgb(138, 253, 243));
                    });

                    Thread.sleep(2000);

                    Platform.runLater(() -> {
                        values[finalJ].setText(buckets[1][finalI].getText());
                    });

                    Thread.sleep(2000);

                    Platform.runLater(() -> {
                        buckets[1][finalI].setDisable(true);
//                        changeOpacityValuesArrayColor(buckets[1][finalI], Color.rgb(178, 111, 255));
                    });

                    Thread.sleep(2000);

                    codeBlock.unhighlightLine(24);

                    j++;

                    Platform.runLater(() -> {
                        indexArray[2].setText(String.valueOf(finalJ));
                        changeIndexArrayColor(indexArray[2], Color.rgb(138, 253, 243));
                    });

                    Thread.sleep(2000);
                }

                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    public static void changeCountArrayColor(Button button, Color color) {
        if (countFadeTransition != null) {
            countFadeTransition.stop(); // Interrompe a animação anterior, se existir
        }

        countFadeTransition = new FadeTransition(Duration.seconds(0.4), button);
        countFadeTransition.setToValue(0.0); // Define a opacidade final
        countFadeTransition.setOnFinished(event -> {
            button.setStyle("-fx-background-color: " + toRGBCode(color)); // Altera a cor do botão
            countFadeTransition.setToValue(1.0); // Restaura a opacidade
            countFadeTransition.play(); // Inicia a animação novamente
        });

        countFadeTransition.play(); // Inicia a animação
    }

    public static void changeValuesArrayColor(Button button, Color color) {
        if (valueFadeTransition != null) {
            valueFadeTransition.stop(); // Interrompe a animação anterior, se existir
        }

        valueFadeTransition = new FadeTransition(Duration.seconds(0.4), button);
        valueFadeTransition.setToValue(0.0);
        valueFadeTransition.setOnFinished(event -> {
            button.setStyle("-fx-background-color: " + toRGBCode(color));
            valueFadeTransition.setToValue(1.0);
            valueFadeTransition.play();
        });

        valueFadeTransition.play(); // Inicia a animação
    }

    public static void changeOpacityValuesArrayColor(Button button, Color color) {
        if (valueFadeTransition != null) {
            valueFadeTransition.stop(); // Interrompe a animação anterior, se existir
        }

        valueFadeTransition = new FadeTransition(Duration.seconds(0.4), button);
        valueFadeTransition.setToValue(0.2);
        valueFadeTransition.setOnFinished(event -> {
            button.setStyle("-fx-background-color: " + toRGBCode(color));
            valueFadeTransition.play();
        });

        valueFadeTransition.play(); // Inicia a animação
    }

    public static void changeIndexArrayColor(Button button, Color color) {
        if (indexFadeTransition != null) {
            indexFadeTransition.stop();
        }

        indexFadeTransition = new FadeTransition(Duration.seconds(0.4), button);
        indexFadeTransition.setToValue(0.0);
        indexFadeTransition.setOnFinished(event -> {
            button.setStyle("-fx-background-color: " + toRGBCode(color));
            indexFadeTransition.setToValue(1.0);
            indexFadeTransition.play();
        });

        indexFadeTransition.play();
    }

    public static void changeFinalValuesArrayColor(Button button, Color color) {
        if (finalFadeTransition != null) {
            finalFadeTransition.stop();
        }

        finalFadeTransition = new FadeTransition(Duration.seconds(0.4), button);
        finalFadeTransition.setToValue(0.0);
        finalFadeTransition.setOnFinished(event -> {
            button.setStyle("-fx-background-color: " + toRGBCode(color));
            finalFadeTransition.setToValue(1.0);
            finalFadeTransition.play();
        });

        finalFadeTransition.play();
    }

    public static void buildFinalCountingSortArray(Button[] values, Button[] finalArray, Button[] countArray, Button[] indexArray, CodeBlock codeBlock) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {

                Platform.runLater(() -> {
                    indexArray[0].setText(String.valueOf(values.length));
                    changeIndexArrayColor(indexArray[0], Color.rgb(100, 200, 200));
                });

                Thread.sleep(2000);

                codeBlock.highlightLine(19, Color.RED);

                Platform.runLater(() -> {
                    indexArray[1].setText(String.valueOf(values.length));
                    changeIndexArrayColor(indexArray[1], Color.rgb(100, 200, 200));
                });

                Thread.sleep(2000);

                codeBlock.unhighlightLine(19);

                for (int i = values.length - 1; i >= 0; i--) {

                    int valueIndex = i;
                    Platform.runLater(() -> {
                        codeBlock.highlightLine(21, Color.RED);
                        indexArray[0].setText(String.valueOf(valueIndex));
                        changeIndexArrayColor(indexArray[0], Color.rgb(100, 200, 200));
                    });

                    Thread.sleep(3000);

                    Platform.runLater(() -> {
                        changeValuesArrayColor(values[valueIndex], Color.rgb(252, 106, 153));
                    });

                    Thread.sleep(4500);

                    int index = Integer.valueOf(countArray[Integer.valueOf(values[i].getText()) - 1].getText()) - 1;
                    int vetIndex = Integer.valueOf(values[i].getText()) - 1;

                    Platform.runLater(() -> {
                        codeBlock.unhighlightLine(21);
                        codeBlock.highlightLine(22, Color.RED);
                        indexArray[2].setText(String.valueOf(vetIndex));
                        changeIndexArrayColor(indexArray[2], Color.rgb(252, 106, 153));
                    });

                    Thread.sleep(4500);

                    Platform.runLater(() -> {
                        changeCountArrayColor(countArray[vetIndex], Color.rgb(232, 176, 255));
                    });

                    Thread.sleep(4500);

                    Platform.runLater(() -> {
                        codeBlock.unhighlightLine(22);
                        codeBlock.highlightLine(23, Color.RED);
                        indexArray[3].setText(String.valueOf(Integer.valueOf(countArray[vetIndex].getText()) - 1));
                        changeIndexArrayColor(indexArray[3], Color.rgb(232, 176, 255));
                    });

                    Thread.sleep(3000);

                    Platform.runLater(() -> {
                        codeBlock.unhighlightLine(23);
                        codeBlock.highlightLine(24, Color.RED);
                        changeFinalValuesArrayColor(finalArray[index], Color.rgb(0, 255, 127));
                        finalArray[index].setText(values[valueIndex].getText());
                    });

                    Thread.sleep(3000);

                    Platform.runLater(() -> {
                        codeBlock.unhighlightLine(24);
                        codeBlock.highlightLine(25, Color.RED);
                        countArray[vetIndex].setText(String.valueOf(index));
                        changeCountArrayColor(countArray[vetIndex], Color.LIGHTPINK);
                    });

                    Thread.sleep(6000);

                    Platform.runLater(() -> {
                        changeOpacityValuesArrayColor(values[valueIndex], Color.rgb(252, 106, 153));
                    });

                    Thread.sleep(3000);

                    Platform.runLater(() -> {
                        changeCountArrayColor(countArray[vetIndex], Color.LIGHTGRAY);
                    });

                    Thread.sleep(3000);

                    codeBlock.unhighlightLine(25);
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    public static void changeBucketValuesArrayColor(Button button, Color color) {
        if (bucketFadeTransition != null) {
            bucketFadeTransition.stop();
        }

        bucketFadeTransition = new FadeTransition(Duration.seconds(0.4), button);
        bucketFadeTransition.setToValue(0.0);
        bucketFadeTransition.setOnFinished(event -> {
            button.setStyle("-fx-background-color: " + toRGBCode(color));
            bucketFadeTransition.setToValue(1.0);
            bucketFadeTransition.play();
        });

        bucketFadeTransition.play();
    }

    private static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
