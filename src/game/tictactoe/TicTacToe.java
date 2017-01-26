package game.tictactoe;
/*
Игра Крестики - Нолики
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TicTacToe extends JFrame implements ActionListener {

  private int emptySquaresLeft;
  private int count;
  private int countcomp;

    //Создаем панели
    JPanel p = new JPanel(new GridLayout(3, 3, 3, 3));
    JPanel p1 = new JPanel(new GridLayout(1, 1));
    JPanel p2 = new JPanel(new GridLayout(1, 2));

    // Создаем кнопку
    JButton newGameButton = new JButton("Новая игра");

    // Текстовое поле
    JLabel score = new JLabel(" Поиграем?");

    // Текстовое поле счет
    JLabel counter = new JLabel();

    // Создаем массив кнопок
    JButton[][] squares = new JButton[3][3];


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

        //Вставляем созданную панель и устанавливаем шрифт для нее
        Font labelFont = new Font(Font.MONOSPACED, Font.BOLD, 25);
        score.setFont(labelFont);
        score.setForeground(Color.WHITE);
        p2.setBackground(Color.BLACK);
        p2.add(score);
        Font counterFont = new Font(Font.MONOSPACED, Font.BOLD, 25);
        counter.setFont(counterFont);
        counter.setForeground(Color.WHITE);
        p2.add(counter);

        // Устанавливаем цвет панели p
        p.setBackground(Color.BLACK);

        // Создаем кнопки, применяя цикл и массив и добавляем их на панель p
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                squares[i][j] = new JButton();
                squares[i][j].setBackground(Color.WHITE);
                Font buttonFont = new Font(Font.MONOSPACED, Font.BOLD, 70);
                squares[i][j].setFont(buttonFont);
                squares[i][j].addActionListener(this);
                p.add(squares[i][j]);
            }
        }

        // Добавляем кнопку Новая игра
        newGameButton.addActionListener(this);
        Font buttonFont = new Font(Font.MONOSPACED, Font.BOLD, 20);
        newGameButton.setFont(buttonFont);
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setForeground(Color.WHITE);
        p1.add(newGameButton);

        // Добавляем панели в окно
        this.add("North", p2);
        this.add("Center", p);
        this.add("South", p1);

        // Устанавливаем окно по центру экрана
        setLocationRelativeTo(null);

        // Делаем окно видимым
        setVisible(true);

    }

    /**
     * Этот метод будет обрабатывать все события
     *
     * @param ActionEvent объект
     */


    @Override
    public void actionPerformed(ActionEvent e) {

        Object theButton = e.getSource();
        // Это кнопка New Game ?
        if (theButton == newGameButton) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    squares[i][j].setEnabled(true);
                    squares[i][j].setText("");
                    squares[i][j].setBackground(Color.WHITE);
                }
            }
            emptySquaresLeft = 9;
            score.setText(" Поиграем?");
            return; // выходим из метода
        }
        String winner = "";
        // Это одна из клеток?
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (theButton == squares[i][j]) {
                    squares[i][j].setText("X");
                    squares[i][j].setEnabled(false);
                    winner = lookForWinner();
                    if (!"".equals(winner)) {
                        endTheGame();
                    } else {
                        computerMove();
                        winner = lookForWinner();
                        if (!"".equals(winner)) {
                            endTheGame();
                        }
                    }
                    break;
                }
            } // конец цикла for
        }
        if (winner.equals("X")) {
            score.setText(" Вы выграли!");
            while (true) {
                count++;
                break;
            }
            counter.setText(" счет: " + count + ":" + countcomp);
        } else if (winner.equals("O")) {
            score.setText(" Вы проиграли!");
            while (true) {
                countcomp++;
                break;
            }
            counter.setText(" счет: " + count + ":" + countcomp);
        } else if (winner.equals("T")) {
            score.setText(" Ничья!");
            counter.setText(" счет: " + count + ":" + countcomp);
        }
    } // конец метода actionPerformed

    /**
     * Этот метод вызывается после каждого хода, чтобы узнать,
     * есть ли победитель. Он проверяет каждый ряд, колонку
     * и диагональ, чтобы найти три клетки с одинаковыми надписями
     * (не пустыми)
     *
     * @return "X", "O", "T" – ничья, "" - еще нет победителя
     */
    String lookForWinner() {
        String theWinner = "";
        emptySquaresLeft--;
        if (emptySquaresLeft == 0) {
            return "T"; // это ничья. T от английского слова tie
        }
        // Проверяем ряд 1 – элементы массива 0,1,2
        if (!squares[0][0].getText().equals("")
                &&
                squares[0][0].getText().equals(squares[0][1].getText())
                &&
                squares[0][0].getText().equals(squares[0][2].getText())) {
            theWinner = squares[0][0].getText();
            //так как массив двумерный, поэтому заносим индексы
            //из 6 цифр. Метод выделения выигрышных клеток
            highlightWinner(0, 0, 0, 1, 0, 2);
            // Проверяем ряд 2 – элементы массива 3,4,5
        } else if (!squares[1][0].getText().equals("")
                &&
                squares[1][0].getText().equals(squares[1][1].getText())
                &&
                squares[1][0].getText().equals(squares[1][2].getText())) {

            theWinner = squares[1][0].getText();
            highlightWinner(1, 0, 1, 1, 1, 2);
            // Проверяем ряд 3 – элементы массива 6,7,8
        } else if (!squares[2][0].getText().equals("")
                &&
                squares[2][0].getText().equals(squares[2][1].getText())
                &&
                squares[2][0].getText().equals(squares[2][2].getText())) {

            theWinner = squares[2][0].getText();
            highlightWinner(2, 0, 2, 1, 2, 2);

            // Проверяем колонку 1 – элементы массива 0,3,6
        } else if (!squares[0][0].getText().equals("")
                &&
                squares[0][0].getText().equals(squares[1][0].getText())
                &&
                squares[0][0].getText().equals(squares[2][0].getText())) {

            theWinner = squares[0][0].getText();
            highlightWinner(0, 0, 1, 0, 2, 0);
            // Проверяем колонку 2 – элементы массива 1,4,7
        } else if (!squares[0][1].getText().equals("")
                &&
                squares[0][1].getText().equals(squares[1][1].getText())
                &&
                squares[0][1].getText().equals(squares[2][1].getText())) {
            theWinner = squares[0][1].getText();
            highlightWinner(0, 1, 1, 1, 2, 1);
            // Проверяем колонку 3 – элементы массива 2,5,8
        } else if (!squares[0][2].getText().equals("")
                &&
                squares[0][2].getText().equals(squares[1][2].getText())
                &&
                squares[0][2].getText().equals(squares[2][2].getText())) {

            theWinner = squares[0][2].getText();
            highlightWinner(0, 2, 1, 2, 2, 2);
            // Проверяем первую диагональ – элементы массива 0,4,8
        } else if (!squares[0][0].getText().equals("") &&
                squares[0][0].getText().equals(squares[1][1].getText()) &&
                squares[0][0].getText().equals(squares[2][2].getText())) {
            theWinner = squares[0][0].getText();
            highlightWinner(0, 0, 1, 1, 2, 2);
            // Проверяем вторую диагональ – элементы массива 2,4,6
        } else if (!squares[0][2].getText().equals("") &&
                squares[0][2].getText().equals(squares[1][1].getText()) &&
                squares[0][2].getText().equals(squares[2][0].getText())) {
            theWinner = squares[0][2].getText();
            highlightWinner(0, 2, 1, 1, 2, 0);
        }
        return theWinner;
    }

    void computerMove() {
        int selectedSquare;
// Сначала компьютер пытается найти пустую клетку
// рядом с двумя клетками с ноликами, чтобы выиграть
        selectedSquare = findEmptySquare("O");
// Если он не может найти два нолика, то хотя бы
// попытается не дать оппоненту сделать ряд из 3-х
// крестиков,поместив нолик рядом с двумя крестиками
        if (selectedSquare == -1) {
            selectedSquare = findEmptySquare("X");
        }
// если selectedSquare все еще равен -1, то
// попытается занять центральную клетку
        if ((selectedSquare == -1) && (squares[1][1].getText().equals(""))) {
            selectedSquare = 1;
            printMove(selectedSquare, selectedSquare);
        }
// не повезло с центральной клеткой...
// просто занимаем случайную клетку
        if (selectedSquare == -1) {
            selectedSquare = getRandomSquare();
        }
    }

    //печать ноликов компьютером, так как массив
//кнопок имеет разные типы
//нужен отдельный метод для вывода на экран ноликов
    int printMove(int x, int y) {
        squares[x][y].setText("O");
        squares[x][y].setEnabled(false);
        return 0;
    }

    /**
     * Этот метод проверяет каждый ряд, колонку и диагональ
     * чтобы узнать, есть ли в ней две клетки
     * с одинаковыми надписями и пустой клеткой.
     *
     * @param передается X – для пользователя и O – для компа
     * @return количество свободных клеток,
     * или -1, если не найдено две клетки
     * с одинаковыми надписями
     */
    int findEmptySquare(String player) {
        int weight[][] = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (squares[i][j].getText().equals("O"))
                    weight[i][j] = -1;
                else if (squares[i][j].getText().equals("X"))
                    weight[i][j] = 1;
                else
                    weight[i][j] = 0;
            }
        }
        int twoWeights = player.equals("") ? -2 : 2;

// Проверим, есть ли в ряду 1 две одинаковые клетки и
// одна пустая.
        if (weight[0][0] + weight[0][1] + weight[0][2] == twoWeights) {

            if (weight[0][0] == 0)
                //присваиваем методу индексы клетки
                return printMove(0, 0);
            else if (weight[0][1] == 0)
                return printMove(0, 1);
            else
                return printMove(0, 2);
        }
// Проверим, есть ли в ряду 2 две одинаковые клетки и
// одна пустая.
        if (weight[1][0] + weight[1][1] + weight[1][2] == twoWeights) {
            if (weight[1][0] == 0)
                return printMove(1, 0);
            else if (weight[1][1] == 0)
                return printMove(1, 1);
            else
                return printMove(1, 2);
        }
// Проверим, есть ли в ряду 3 две одинаковые клетки и
// одна пустая.
        if (weight[2][0] + weight[2][1] + weight[2][2] == twoWeights) {
            if (weight[2][0] == 0)
                return printMove(2, 0);
            else if (weight[2][1] == 0)
                return printMove(2, 1);
            else
                return printMove(2, 2);
        }
// Проверим, есть ли в колонке 1 две одинаковые клетки и
// одна пустая.
        if (weight[0][0] + weight[1][0] + weight[2][0] == twoWeights) {
            if (weight[0][0] == 0)
                return printMove(0, 0);
            else if (weight[1][0] == 0)
                return printMove(1, 0);
            else
                return printMove(2, 0);
        }
// Проверим, есть ли в колонке 2 две одинаковые клетки
// и одна пустая.
        if (weight[0][1] + weight[1][1] + weight[2][1] == twoWeights) {
            if (weight[0][1] == 0)
                return printMove(0, 1);
            else if (weight[1][1] == 0)
                return printMove(1, 1);
            else
                return printMove(2, 1);
        }
// Проверим, есть ли в колонке 3 две одинаковые клетки
// и одна пустая.
        if (weight[0][2] + weight[1][2] + weight[2][2] == twoWeights) {
            if (weight[0][2] == 0)
                return printMove(0, 2);
            else if (weight[1][2] == 0)
                return printMove(1, 2);
            else
                return printMove(2, 2);
        }
// Проверим, есть ли в диагонали 1 две одинаковые клетки
// и одна пустая.
        if (weight[0][0] + weight[1][1] + weight[2][2] == twoWeights) {
            if (weight[0][0] == 0)
                return printMove(0, 0);
            else if (weight[1][1] == 0)
                return printMove(1, 1);
            else
                return printMove(2, 2);
        }
        // Проверим, есть ли в диагонали 2 две одинаковые клетки
        // и одна пустая.
        if (weight[0][2] + weight[1][1] + weight[2][0] == twoWeights) {
            if (weight[0][2] == 0)
                return printMove(0, 2);
            else if (weight[1][1] == 0)
                return printMove(1, 1);
            else
                return printMove(2, 0);
        }

        // Не найдено двух одинаковых соседних клеток
        return -1;

    } // конец метода findEmptySquare()

    /**
     * Этот метод выбирает любую пустую клетку
     *
     * @return случайно выбранный номер клетки
     */
    int getRandomSquare() {
        boolean gotEmptySquare = false;

        int selectedSquare = -1;
        int selectedSquare1 = 0;
        do {
            //так как у двумерного массива два индекса
            //поэтому создаем два рандомных числа
            //с помощью которых будем искать пустую клетку
            selectedSquare = (int) (Math.random() * 3);
            selectedSquare1 = (int) (Math.random() * 3);
            if (squares[selectedSquare][selectedSquare1].getText().equals("")) {
                gotEmptySquare = true;
// чтобы закончить цикл
            }
        } while (!gotEmptySquare);
        //печатаем нолик
        return printMove(selectedSquare, selectedSquare1);
    } // конец метода getRandomSquare()

    /**
     * Этот метод выделяет выигравшую линию.
     *
     * @param первая, вторая и третья клетки для выделения
     */
    void highlightWinner(int win1, int win2, int win3, int win4, int win5, int win6) {
        //выделяем клетки которые победили!
        squares[win1][win2].setBackground(Color.BLACK);
        squares[win3][win4].setBackground(Color.BLACK);
        squares[win5][win6].setBackground(Color.BLACK);
    }

    // Делаем недоступными клетки и доступной кнопку ”New Game”
    void endTheGame() {
        newGameButton.setEnabled(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                squares[i][j].setEnabled(false);
            }
        }
    }
}





