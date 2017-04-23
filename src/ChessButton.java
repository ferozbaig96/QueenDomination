import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ChessButton extends JButton {

    private boolean markedAsQueen;
    private int reachableBy;
    private int rowIndex;
    private int columnIndex;

    ChessButton(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        reachableBy = 0;
        markedAsQueen = false;

        this.setPreferredSize(new Dimension(Constants.BUTTON_SIZE, Constants.BUTTON_SIZE));

        ButtonListener buttonListener = new ButtonListener();
        this.addActionListener(buttonListener);
    }

    int getReachableBy() {
        return reachableBy;
    }

    boolean isMarkedAsQueen() {
        return markedAsQueen;
    }

    void mark() {
        reachableBy++;
        setColor();
    }

    void unmark() {
        reachableBy--;
        setColor();
    }

    private void setColor() {
        if (reachableBy == 0)
            setBackground(Color.WHITE);
        else
            setBackground(Color.PINK);
    }

    void setQueenColor() {
        setBackground(Color.GRAY);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (!markedAsQueen) {       // if markedAsQueen == false
                mark();
                setQueenColor();

                markedAsQueen = true;
                Execute.setMarkings(rowIndex, columnIndex, true);

            } else {                    // if markedAsQueen == true
                unmark();

                markedAsQueen = false;
                Execute.setMarkings(rowIndex, columnIndex, false);
            }

        }
    }

}
