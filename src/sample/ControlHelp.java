package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlHelp implements Initializable {
    @FXML private TextArea area;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String text = "Приветствую вас в программе TabWorkPlace -  программе работы с табулированными функциями.\n" +
                "В основном меню вы можете выбрать способ задания базовой функции, на которой будет строиться\n" +
                "табулированная функция. Это либо загрузка .class функции либо выбор известной функции\n" +
                "Выбрав способ задания базовой функции вы попадаете в меню создания функции\n" +
                "Задав необходимые параметры вы можете отобразить функцию в виде графика или таблицы или же\n" +
                "сохранить функцию\n" +
                "в новом файле или перезаписать старый. Все файлы табулированных функций используют \n" +
                "расширение .tabf или .txt.\n" +
                "При работе с таблицей вы можете изменять функцию прописывая координаты новых точек и добавляя\n" +
                "их к таблице\n" +
                "или же перезаписывать координаты точек согласно правилам табулирования.\n" +
                "Для этого достаточно нажать на нужную ячейку\n" +
                "и выбрать необходимое действие.\n" +
                "При работе с графиком вы не можете изменять функцию. Рекомендуется быть острожным\n" +
                "с большим количеством точек и\n" +
                "правильным заданием границ функции. Ухнать координаты точки можно наведя на нее курсор\n" +
                "В главном меню вы также можете загрузить выбранную функцию из файла tabf или txt и работать с ней также.";
        area.setText(text);
    }

    @FXML
    public void back() throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        Scene newScene = new Scene(loader);
        Main.getCurStage().setScene(newScene);
    }
}
