package userInreface;

import game.Board;
import game.Player;
import game.PlayerType;
import game.Token;
import gameControl.EndGameObject;
import gameControl.GameController;
import gameControl.GameMoveObject;
import gameControl.GameStartObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GUI implements UserInterface {

    private final int DEFAULT_WIDTH = 474;
    private final int DEFAULT_HEIGHT = 385;

    private JFrame mainFrame;
    private JPanel panelMain;
    private JPanel endGamePanel;
    private JPanel panelBoardNumbers;

    private JLayeredPane layeredGameBoard;

    private JPanel playersPanel;
    private String playerOneName;
    private String playerTwoName;


    private JLabel arrowLabel;

    private JLabel infoTextLabel;

    private JButton col1_button = new JButton();
    private JButton col2_button = new JButton();
    private JButton col3_button = new JButton();
    private JButton col4_button = new JButton();
    private JButton col5_button = new JButton();
    private JButton col6_button = new JButton();
    private JButton col7_button = new JButton();
    private JButton[] buttons = new JButton[]
            {col1_button, col2_button, col3_button, col4_button,
                    col5_button, col6_button, col7_button};


    private URL boardUrl = getClass().getResource("/images/Board.png");
    private ImageIcon boardImage = new ImageIcon(boardUrl);

    private URL leftYellowArrowURL = getClass().getResource("/images/LeftArrowYellow.png");
    private ImageIcon leftYellowArrowImage = new ImageIcon(leftYellowArrowURL);
    private URL rightRedArrowURL = getClass().getResource("/images/RightArrowRed.png");
    private ImageIcon rightRedArrowImage = new ImageIcon(rightRedArrowURL);

    private URL smallYellowTokenUrl = getClass().getResource("/images/YellowTokenSmall.png");
    private ImageIcon smallYellowTokenImage = new ImageIcon(smallYellowTokenUrl);
    private URL smallRedToken = getClass().getResource("/images/RedTokenSmall.png");
    private ImageIcon smallRedTokenImage = new ImageIcon(smallRedToken);

    private URL yellowTokenUrl = getClass().getResource("/images/YellowToken.png");
    private ImageIcon yellowTokenImage = new ImageIcon(yellowTokenUrl);
    private URL redToken = getClass().getResource("/images/RedToken.png");
    private ImageIcon redTokenImage = new ImageIcon(redToken);

    private URL arrowButtonImgUrl = getClass().getResource("/images/ArrowButton.png");
    private ImageIcon arrowButtonImage = new ImageIcon(arrowButtonImgUrl);
    private URL focusArrowButtonImgUrl = getClass().getResource("/images/FocusArrowButton.png");
    private ImageIcon focusArrowButtonImage = new ImageIcon(focusArrowButtonImgUrl);

    private GameController gameController;
    private JLabel playerOneNameLabel;
    private JLabel playerTwoNameLabel;

    public GUI() {
        //Creating the Frame
        mainFrame = new JFrame("Connect Four!");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        centerWindow(mainFrame, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        setButtons();
        addButtonListeners();
        Component compMainWindowContents = createContentComponents();
        mainFrame.getContentPane().add(compMainWindowContents, BorderLayout.CENTER);

        mainFrame.setFocusable(true);
        mainFrame.pack();
        //mainFrame.setVisible(true);
    }

    private void setButtons() {
        for (JButton button : buttons) {
            button.setIcon(arrowButtonImage);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);

            button.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    JButton btn = (JButton) e.getComponent();
                    btn.setIcon(focusArrowButtonImage);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    JButton btn = (JButton) e.getComponent();
                    btn.setIcon(arrowButtonImage);
                }
            });
        }
    }


    private void addButtonListeners() {
        col1_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.playNextMove(0);
            }
        });

        col2_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.playNextMove(1);
            }
        });

        col3_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.playNextMove(2);
            }
        });

        col4_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.playNextMove(3);
            }
        });

        col5_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.playNextMove(4);
            }
        });

        col6_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.playNextMove(5);
            }
        });

        col7_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.playNextMove(6);
            }
        });
    }

    private void centerWindow(JFrame frame, int width, int height) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (dimension.getWidth() - frame.getWidth() - width) / 2;
        int y = (int) (dimension.getHeight() - frame.getHeight() - height) / 2;
        frame.setLocation(x, y);
    }

    private Component createContentComponents() {
        createBoardNumbersPanel();
        createLayeredBoard();
        createPlayersPanel();
        createEndGamePanel();

        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        panelMain.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        panelMain.add(panelBoardNumbers, BorderLayout.NORTH);
        panelMain.add(layeredGameBoard, BorderLayout.CENTER);
        panelMain.add(playersPanel, BorderLayout.SOUTH);
        mainFrame.setResizable(false);
        return panelMain;
    }

    private void createEndGamePanel() {
        endGamePanel = new JPanel();
        endGamePanel.setLayout(new BorderLayout());
        endGamePanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, 70));
        endGamePanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 5, 30));

        infoTextLabel = new JLabel();
        infoTextLabel.setFont(new Font("Sans-Serif", Font.BOLD, 24));
        infoTextLabel.setForeground(new Color(10, 78, 169));
        endGamePanel.add(infoTextLabel, BorderLayout.CENTER);
    }

    private void createBoardNumbersPanel() {
        panelBoardNumbers = new JPanel();

        panelBoardNumbers.setLayout(new GridLayout(1, 7, 10, 0));
        panelBoardNumbers.setPreferredSize(new Dimension(DEFAULT_WIDTH, 70));
        panelBoardNumbers.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
        for (JButton button : buttons) {
            panelBoardNumbers.add(button);
        }
    }

    private void createLayeredBoard() {
        layeredGameBoard = new JLayeredPane();
        layeredGameBoard.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        JLabel imageBoardLabel = new JLabel(boardImage);
        imageBoardLabel.setBounds(20, 0, boardImage.getIconWidth(), boardImage.getIconHeight());
        layeredGameBoard.add(imageBoardLabel, 0, 1);
    }

    private void createPlayersPanel() {
        playersPanel = new JPanel();
        playersPanel.setLayout(new BorderLayout());

        JPanel yellowPLayerPanel = new JPanel();
        yellowPLayerPanel.setLayout(new BorderLayout());


        JLabel imageYellowTokenLabel = new JLabel(smallYellowTokenImage);
        playerOneNameLabel = new JLabel();
        playerOneNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        playerOneNameLabel.setFont(new Font("Sans-Serif", Font.BOLD, 18));
        playerOneNameLabel.setForeground(new Color(10, 78, 169));

        yellowPLayerPanel.add(imageYellowTokenLabel, BorderLayout.LINE_START);
        yellowPLayerPanel.add(playerOneNameLabel, BorderLayout.CENTER);

        JPanel redPLayerPanel = new JPanel();
        redPLayerPanel.setLayout(new BorderLayout());

        JLabel imageRedTokenLabel = new JLabel(smallRedTokenImage);

        playerTwoNameLabel = new JLabel();
        playerTwoNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        playerTwoNameLabel.setFont(new Font("Sans-Serif", Font.BOLD, 18));
        playerTwoNameLabel.setForeground(new Color(10, 78, 169));

        redPLayerPanel.add(imageRedTokenLabel, BorderLayout.LINE_END);
        redPLayerPanel.add(playerTwoNameLabel, BorderLayout.CENTER);

        arrowLabel = new JLabel(leftYellowArrowImage);

        playersPanel.add(yellowPLayerPanel, BorderLayout.LINE_START);
        playersPanel.add(redPLayerPanel, BorderLayout.LINE_END);
        playersPanel.add(arrowLabel, BorderLayout.CENTER);
        playersPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        playersPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, 50));
    }


    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void printStartGame(GameStartObject gameStartObject) {
        mainFrame.setVisible(true);
        Player playerOne = gameStartObject.getPlayer1();
        Player playerTwo = gameStartObject.getPlayer2();
        if (playerOne.getType() == PlayerType.AI) {
            playerOneName = "Player 1 (AI)";
        } else {
            playerOneName = "Player 1";
        }
        if (playerTwo.getType() == PlayerType.AI) {
            playerTwoName = "Player 2 (AI)";
        } else {
            playerOneName = "Player 2";
        }
        playerOneNameLabel.setText(playerOneName);
        playerTwoNameLabel.setText(playerTwoName);

        Player nextPlayer = gameStartObject.getNextPlayer();
        drawPlayerArrow(nextPlayer);

        if (nextPlayer.getType() == PlayerType.AI) {
            gameController.performAIMove();
        }
    }

    public void printGameAfterMove(GameMoveObject gameMoveObject, Player nextPlayer) {
        int lastMoveCol = gameMoveObject.getLastMoveCol();
        int lastMoveRow = gameMoveObject.getLatMoveRow();
        Token lastMoveToken = gameMoveObject.getLastMoveToken();
        placeChecker(lastMoveToken, lastMoveRow, lastMoveCol);

        drawPlayerArrow(nextPlayer);
        //mainFrame.validate();
        //mainFrame.repaint();
    }

    void drawPlayerArrow(Player nextPlayer) {
        if (nextPlayer.getPlayerToken() == Token.YELLOW) {
            arrowLabel.setIcon(leftYellowArrowImage);
        } else {
            arrowLabel.setIcon(rightRedArrowImage);
        }
    }

    private void placeChecker(Token token, int row, int col) {
        int xOffset = 60 * col;
        int yOffset = 60 * row;

        ImageIcon checkerIcon = token == Token.YELLOW ? yellowTokenImage : redTokenImage;
        JLabel checkerLabel = new JLabel(checkerIcon);
        checkerLabel.setBounds(30 + xOffset, 13 + yOffset, checkerIcon.getIconWidth(), checkerIcon.getIconHeight());
        layeredGameBoard.add(checkerLabel, 0, 0);
        mainFrame.paint(mainFrame.getGraphics());
    }

    public void printEndedGame(EndGameObject endGameObject) {
        GameMoveObject gameMoveObject = endGameObject.getGameMoveObject();
        int lastMoveCol = gameMoveObject.getLastMoveCol();
        int lastMoveRow = gameMoveObject.getLatMoveRow();
        Token lastMoveToken = gameMoveObject.getLastMoveToken();
        placeChecker(lastMoveToken, lastMoveRow, lastMoveCol);

        arrowLabel.setBounds(0, 0, leftYellowArrowImage.getIconWidth(), leftYellowArrowImage.getIconHeight());
        arrowLabel.setIcon(null);
        Player winner = endGameObject.getWinner();
        if (winner != null) {
            String winnerName = winner.getPlayerToken() == Token.YELLOW ? playerOneName : playerTwoName;

            infoTextLabel.setText("WINNER: " + winnerName);
        } else {
            infoTextLabel.setText("TIE");
        }
        panelMain.remove(panelBoardNumbers);
        panelMain.add(endGamePanel, BorderLayout.NORTH);
        panelMain.revalidate();
    }

    public void printWrongMove() {

    }
}
