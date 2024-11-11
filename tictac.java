import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class tictac extends JPanel implements ActionListener {
    // logic variables
    boolean playerX = true;
    boolean gameDone = false;
    int winner = -1;

    int player1wins = 0, player2wins = 0;

    int[][] board = new int[3][3];

    // paint variables
    int linewidth = 5;
    int linelength = 270;
    int x = 15, y = 100; // location of first line
    int offset = 95; // square width
    int selX = 0, selY = 0; // stores the location of mouse click

    // colors
    Color turtle = new Color(0xFFCCD4); // pink
    Color orange = new Color(0xD3B1C2); // purple
    Color offwhite = new Color(0xD59CAB); // lavender
    Color darkgray = new Color(0xFBFFCE); // yellow

    // components
    JButton jbutton;

    public tictac() {
        Dimension size = new Dimension(420, 300);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        jbutton = new JButton("Wanna Play Again?");
        jbutton.addActionListener(this);
        add(jbutton);
        jbutton.setVisible(false);

        XOListener listener = new XOListener();
        addMouseListener(listener);
        System.out.println("XOListener added: " + listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resetGame();
    }

    public void resetGame() {
        playerX = true;
        winner = -1;
        gameDone = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
        jbutton.setVisible(false);
        repaint();
    }

    @Override
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        System.out.println("paintComponent called.");
        drawBoard(page);
        drawUI(page);
        drawGame(page);
    }

    public void drawBoard(Graphics page) {
        setBackground(turtle);
        page.setColor(darkgray);
        page.fillRoundRect(x, y, linelength, linewidth, 5, 30); // horizontal lines
        page.fillRoundRect(x, y + offset, linelength, linewidth, 5, 30);
        page.fillRoundRect(y, x, linewidth, linelength, 30, 5); // vertical lines
        page.fillRoundRect(y + offset, x, linewidth, linelength, 30, 5);
    }

    public void drawUI(Graphics page) {
        // set colors and fonts
        page.setColor(darkgray);
        page.fillRect(300, 0, 120, 300);
        Font font = new Font("KG A Little Spark", Font.PLAIN, 20);
        page.setFont(font);

        // set win counter
        page.setColor(orange);
        page.drawString("WIN Count", 313, 30);
        page.drawString(": " + player1wins, 365, 70);
        page.drawString(": " + player2wins, 365, 105);

        // draw Score X
        ImageIcon xIcon = new ImageIcon("/Users/shahenazabushanab/NetBeansProjects/tictac/src/main/java/purpleheart.png");
        Image xImg = xIcon.getImage();
        Image newXImg = xImg.getScaledInstance(27, 27, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newXIcon = new ImageIcon(newXImg);
        page.drawImage(newXIcon.getImage(), 329, 47, null);

        // draw Score O
        ImageIcon OIcon = new ImageIcon("/Users/shahenazabushanab/NetBeansProjects/tictac/src/main/java/pinkheart.png");
        Image OImg = OIcon.getImage();
        Image newOImg = OImg.getScaledInstance(27, 27, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newOIcon = new ImageIcon(newOImg);
        page.drawImage(newOIcon.getImage(), 329, 87, null);

        // draw who's turn or winner
        page.setColor(offwhite);
        Font font1 = new Font("KG A Little Spark", Font.ITALIC, 18);
        page.setFont(font1);

        if (gameDone) {
            // show winner
            if (winner == 1) {
                page.drawString("The winner is:", 310, 150);
                page.drawImage(xImg, 335, 160, null);
            } else if (winner == 2) {
                page.drawString("The winner is", 310, 150);
                page.setColor(offwhite);
                page.fillOval(335, 160, 50, 50);
                page.setColor(darkgray);
                page.fillOval(345, 170, 30, 30);
            } else if (winner == 3) {
                // tie
                page.drawString("It's a tie!", 330, 178);
            }
        } else { // show who's turn
            Font font2 = new Font("KG A Little Spark", Font.ITALIC + Font.BOLD, 20);
            page.setFont(font2);
            page.drawString("It's", 350, 160);

            if (playerX) {
                page.drawString("Purple's Turn!", 325, 180);
            } else {
                page.drawString("Pink's Turn!", 325, 180); // issue with pink first showing up
            }
        }
        Image noza = Toolkit.getDefaultToolkit().getImage("/Users/shahenazabushanab/NetBeansProjects/tictac/src/main/java/purpleheart.png");
        page.drawImage(noza, 345, 235, 70, 70, this);
        Font n = new Font("KG A Little Spark", Font.ITALIC + Font.BOLD, 13);
        page.setFont(n);
        page.drawString("nozagames", 310, 280);
    }

    public void drawGame(Graphics page) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 1) {
                    ImageIcon xIcon = new ImageIcon("/Users/shahenazabushanab/NetBeansProjects/tictac/src/main/java/purpleheart.png");
                    Image xImg = xIcon.getImage();
                    Image newXImg = xImg.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon newXIcon = new ImageIcon(newXImg);
                    page.drawImage(newXIcon.getImage(), 15 + (i * (offset + linewidth)), 100 + (j * (offset + linewidth)), null);
                } else if (board[i][j] == 2) {
                    ImageIcon OIcon = new ImageIcon("/Users/shahenazabushanab/NetBeansProjects/tictac/src/main/java/pinkheart.png");
                    Image OImg = OIcon.getImage();
                    Image newOImg = OImg.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon newOIcon = new ImageIcon(newOImg);
                    page.drawImage(newOIcon.getImage(), 15 + (i * (offset + linewidth)), 100 + (j * (offset + linewidth)), null);
                }
            }
        }
    }

    public void checkWinner() {
        if (gameDone) {
            System.out.println("game over!");
            return;
        }

        int temp = -1;
        if ((board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] != 0)) {
            temp = board[0][0];
        } else if ((board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] != 0)) {
            temp = board[1][0];
        } else if ((board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] != 0)) {
            temp = board[2][0];
        } else if ((board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] != 0)) {
            temp = board[0][0];
        } else if ((board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[0][1] != 0)) {
            temp = board[0][1];
        } else if ((board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[0][2] != 0)) {
            temp = board[0][2];
        } else if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0)) {
            temp = board[0][0];
        } else if ((board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0)) {
            temp = board[0][2];
        } else {
            boolean notDone = false;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        notDone = true;
                        break;
                    }
                }
                if (notDone) break;
            }
            if (!notDone) {
                temp = 3;
            }
        }

        if (temp > 0) {
            winner = temp;
            if (winner == 1) {
                player1wins++;
                System.out.println("PURPLE WINS!");
            } else if (winner == 2) {
                player2wins++;
                System.out.println("PINK WINS!");
            } else if (winner == 3) {
                System.out.println("IT'S A TIE!");
            }
            gameDone = true;
            getJButton().setVisible(true);
            repaint();
        }
    }

    public JButton getJButton() {
        return jbutton;
    }

    public class XOListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent event) {
            selX = -1;
            selY = -1;
            if (!gameDone) {
                int a = event.getX();
                int b = event.getY();
                System.out.println("Clicker => x: " + a + ", y: " + b);
                if (a > 15 && a < 15 + offset) {
                    selX = 0;
                } else if (a > 15 + offset + linewidth && a < 15 + 2 * offset + linewidth) {
                    selX = 1;
                } else if (a > 15 + 2 * (offset + linewidth) && a < 15 + 3 * offset + 2 * linewidth) {
                    selX = 2;
                }

                if (b > 100 && b < 100 + offset) {
                    selY = 0;
                } else if (b > 100 + offset + linewidth && b < 100 + 2 * offset + linewidth) {
                    selY = 1;
                } else if (b > 100 + 2 * (offset + linewidth) && b < 100 + 3 * offset + 2 * linewidth) {
                    selY = 2;
                }

                System.out.println("Mapped Click => selX: " + selX + ", selY: " + selY);

                if (selX != -1 && selY != -1) {
                    // open spot to play
                    if (board[selX][selY] == 0) {
                        board[selX][selY] = playerX ? 1 : 2;
                        playerX = !playerX;
                        System.out.println("Clicker => x: " + a + ", y: " + b + " board: (" + selX + ", " + selY + " )");
                        repaint();
                        checkWinner();
                    }
                } else {
                    System.out.println("Invalid click");
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) { }

        @Override
        public void mouseReleased(MouseEvent e) { }

        @Override
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }
    }
}
    
    