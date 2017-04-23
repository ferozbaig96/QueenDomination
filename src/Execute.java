import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Execute extends JFrame {

    public static void main(String[] args) {
        new Execute();
    }

    private static ChessButton chessButtons[][];
    private static int N = 8;

    private JPanel panel;
    private JPanel chessPanel;
    private JPanel menuPanel;

    private Execute() {

        int dimension = Constants.BUTTON_SIZE * N;
        this.setSize(dimension, dimension);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.black);

        setupChessPanel();
        setupMenuPanel();

        panel.add(menuPanel, BorderLayout.NORTH);
        panel.add(chessPanel, BorderLayout.CENTER);
        this.add(panel);
        this.setVisible(true);

    }

    private void setupChessPanel() {
        chessPanel = new JPanel(new GridLayout(N, N, Constants.GAP, Constants.GAP));

        chessButtons = new ChessButton[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                chessButtons[i][j] = new ChessButton(i, j);
                chessPanel.add(chessButtons[i][j]);
            }
        }
    }

    private void setupMenuPanel() {

        menuPanel = new JPanel(new GridLayout(1, 3, Constants.GAP, Constants.GAP));

        JTextArea textareaCount = new JTextArea();
        JButton buttonRestart = new JButton("Restart");
        JButton buttonPlay = new JButton("Play");

        buttonRestart.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(chessPanel);

                setupChessPanel();
                panel.add(chessPanel, BorderLayout.CENTER);

                panel.revalidate();
                panel.repaint();
            }
        });

        buttonPlay.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String val = textareaCount.getText();

                if (val == null || val.length() == 0)
                    return;

                N = Integer.valueOf(val);

                buttonRestart.doClick();
            }
        });

        menuPanel.add(textareaCount);
        menuPanel.add(buttonPlay);
        menuPanel.add(buttonRestart);
    }

    static void setMarkings(int r, int c, boolean haveToMark) {

        //Horizontal + Vertical

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (i == r && j == c)
                    continue;

                if (i == r)
                    doMarking(i, j, haveToMark);

                if (j == c)
                    doMarking(i, j, haveToMark);
            }
        }


        // Diagonals

        int j = 1;

        for (int i = 1; i < N; i++) {

            if (r + i < N) {

                if (c + j < N)
                    doMarking(r + i, c + j, haveToMark);

                if (c - j >= 0)
                    doMarking(r + i, c - j, haveToMark);
            }

            if (r - i >= 0) {

                if (c + j < N)
                    doMarking(r - i, c + j, haveToMark);

                if (c - j >= 0)
                    doMarking(r - i, c - j, haveToMark);
            }

            j++;
        }

       /* System.out.println();
        debug();
        System.out.println();*/

    }

    private static void doMarking(int i, int j, boolean haveToMark) {

        if (haveToMark) {
            chessButtons[i][j].mark();
            checkIfQueen(i, j);

        } else {
            chessButtons[i][j].unmark();
            checkIfQueen(i, j);
        }
    }

    private static void checkIfQueen(int i, int j) {
        if (chessButtons[i][j].isMarkedAsQueen()) {
            chessButtons[i][j].setQueenColor();
        }
    }

    @SuppressWarnings("all")
    private static void debug() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //System.out.print(chessButtons[i][j].getReachableBy() + " ");
                System.out.print((chessButtons[i][j].isMarkedAsQueen() ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }
}
