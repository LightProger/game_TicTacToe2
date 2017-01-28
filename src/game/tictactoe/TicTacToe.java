package game.tictactoe;
/*
Игра Крестики - Нолики
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TicTacToe extends JFrame implements ActionListener {

    private int count = 0;
    private String letter = "";
    private boolean winner = false;
    private JPanel p, p2;
    private JButton button1, button2, button3, button4, button5, button6,
                    button7, button8, button9, newGameButton;
    private JLabel score;

    public static void main(String[] args) {

        // Создаем ссылку на конструктор TicTacToe
        new TicTacToe();
    }

    // Конструктор TicTacToe
    public TicTacToe() {
        super("Крестики - Нолики");

        //Задаем размер окна
        setSize(500, 500);

        // Реакция окна на закрытие
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Запрет на изменение размера окна
        setResizable(false);

        //Создаем панель и устанавливаем шрифт и цвет и шрифт для нее, добавляем текстовое  поле score
        p2 = new JPanel(new GridLayout(1, 2));
        score = new JLabel(" Поиграем?");
        Font labelFont = new Font(Font.MONOSPACED, Font.BOLD, 25);
        score.setFont(labelFont);
        score.setForeground(Color.WHITE);
        p2.setBackground(Color.BLACK);
        p2.add(score);

        // Создаем и устанавливаем цвет панели
        p = new JPanel(new GridLayout(3, 3, 3, 3));
        p.setBackground(Color.BLACK);

        // Создаем кнопки, задаем их цвет и шрифт, прикрепляем слушатель и добавляем их на панель p
        button1 = new JButton("");
        button1.setBackground(Color.WHITE);
        Font buttonFont = new Font(Font.MONOSPACED, Font.BOLD, 70);
        button1.setFont(buttonFont);
        button1.addActionListener(this);
        p.add(button1);

        button2 = new JButton("");
        button2.setBackground(Color.WHITE);
        Font buttonFont2 = new Font(Font.MONOSPACED, Font.BOLD, 70);
        button2.setFont(buttonFont2);
        button2.addActionListener(this);
        p.add(button2);

        button3 = new JButton("");
        button3.setBackground(Color.WHITE);
        Font buttonFont3 = new Font(Font.MONOSPACED, Font.BOLD, 70);
        button3.setFont(buttonFont3);
        button3.addActionListener(this);
        p.add(button3);

        button4 = new JButton("");
        button4.setBackground(Color.WHITE);
        Font buttonFont4 = new Font(Font.MONOSPACED, Font.BOLD, 70);
        button4.setFont(buttonFont4);
        button4.addActionListener(this);
        p.add(button4);

        button5 = new JButton("");
        button5.setBackground(Color.WHITE);
        Font buttonFont5 = new Font(Font.MONOSPACED, Font.BOLD, 70);
        button5.setFont(buttonFont5);
        button5.addActionListener(this);
        p.add(button5);

        button6 = new JButton("");
        button6.setBackground(Color.WHITE);
        Font buttonFont6 = new Font(Font.MONOSPACED, Font.BOLD, 70);
        button6.setFont(buttonFont6);
        button6.addActionListener(this);
        p.add(button6);

        button7 = new JButton("");
        button7.setBackground(Color.WHITE);
        Font buttonFont7 = new Font(Font.MONOSPACED, Font.BOLD, 70);
        button7.setFont(buttonFont7);
        button7.addActionListener(this);
        p.add(button7);

        button8 = new JButton("");
        button8.setBackground(Color.WHITE);
        Font buttonFont8 = new Font(Font.MONOSPACED, Font.BOLD, 70);
        button8.setFont(buttonFont8);
        button8.addActionListener(this);
        p.add(button8);

        button9 = new JButton("");
        button9.setBackground(Color.WHITE);
        Font buttonFont9 = new Font(Font.MONOSPACED, Font.BOLD, 70);
        button9.setFont(buttonFont9);
        button9.addActionListener(this);
        p.add(button9);

        // Добавляем панели в окно
        this.add("North", p2);
        this.add("Center", p);

        // Устанавливаем окно по центру экрана
        setLocationRelativeTo(null);

        // Делаем окно видимым
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;

        // Создание объекта кнопок
        Object theButton = e.getSource();

        // Логика игры
        if (count == 1 || count == 3 || count == 5 || count == 7 || count == 9) {
            letter = "X";
        } else if (count == 2 || count == 4 || count == 6 || count == 8 ) {
            letter = "O";
        }

        // Программирование кнопок
        if (theButton == button1) {
            button1.setText(letter);
            button1.setEnabled(false);
        } else if (theButton == button2) {
            button2.setText(letter);
            button2.setEnabled(false);
        } else if (theButton == button3) {
            button3.setText(letter);
            button3.setEnabled(false);
        } else if (theButton == button4) {
            button4.setText(letter);
            button4.setEnabled(false);
        } else if (theButton == button5) {
            button5.setText(letter);
            button5.setEnabled(false);
        } else if (theButton == button6) {
            button6.setText(letter);
            button6.setEnabled(false);
        } else if (theButton == button7) {
            button7.setText(letter);
            button7.setEnabled(false);
        } else if (theButton == button8) {
            button8.setText(letter);
            button8.setEnabled(false);
        } else if (theButton == button9) {
            button9.setText(letter);
            button9.setEnabled(false);
        }

        // Проверка выигрышных комбинаций по горизонтали
        // Строка 1
        if (button1.getText() == button2.getText()
                && button2.getText() == button3.getText()
                && button1.getText() != "") {
            winner = true;
        }
        // Строка 2
        else if (button4.getText() == button5.getText()
                && button5.getText() == button6.getText()
                && button4.getText() != "") {
            winner = true;
        }
        // Строка 3
        else if (button7.getText() == button8.getText()
                && button8.getText() == button9.getText()
                && button7.getText() != "") {
            winner = true;
        }

        // Проверка выигрышных комбинаций по вертикали
        // Колонка 1
        else if (button1.getText() == button4.getText()
                && button4.getText() == button7.getText()
                && button1.getText() != "") {
            winner = true;
        }
        // Колонка 2
        else if (button2.getText() == button5.getText()
                && button5.getText() == button8.getText()
                && button2.getText() != "") {
            winner = true;
        }
        // Колонка 3
        else if (button3.getText() == button6.getText()
                && button6.getText() == button9.getText()
                && button3.getText() != "") {
            winner = true;
        }
        // Проверка выигрышных комбинаций по диагонали
        // Диагональ слева на право
        else if (button1.getText() == button5.getText()
                && button5.getText() == button9.getText()
                && button1.getText() != "") {
            winner = true;
        }
        // Диагональ  справа на лево
        else if (button3.getText() == button5.getText()
                && button5.getText() == button7.getText()
                && button3.getText() != "") {
            winner = true;
        } else {
            winner = false;
        }

        // Определение победителя и вывод на экран счета игры
        if (winner == true) {
            score.setText(" Вы выиграли!");

        } else if(count == 9 && winner == false) {
            score.setText(" Ничья!");
        }
    }

}




