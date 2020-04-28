package userInreface;

import game.*;
import gameControl.EndGameObject;
import gameControl.GameController;
import gameControl.GameMoveObject;
import gameControl.GameStartObject;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GUI implements UserInterface {

    private final int DEFAULT_WIDTH = 474;
    private final int DEFAULT_THE_UPPEST_PANEL_HEIGHT = 70;
    private final int DEFAULT_UPPER_PANEL_HEIGHT = 70;
    private final int DEFAULT_BOARD_HEIGHT = 385;
    private final int DEFAULT_LOWER_PANEL_HEIGHT = 60;
    private final int DEFAULT_HEIGHT = DEFAULT_THE_UPPEST_PANEL_HEIGHT + DEFAULT_UPPER_PANEL_HEIGHT +
            DEFAULT_BOARD_HEIGHT + DEFAULT_LOWER_PANEL_HEIGHT;

    private final Color BACKGROUND_COLOR = new java.awt.Color(240, 234, 220);

    private JFrame mainFrame;
    private JPanel panelMain;
    private NewGamePanel newGamePanel;

    private JPanel panelBoardNumbers;
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

    private JLayeredPane layeredGameBoard;

    private JPanel playersPanel;
    private String playerOneName;
    private String playerTwoName;
    private JLabel playerOneNameLabel;
    private JLabel playerTwoNameLabel;
    private JLabel arrowLabel;


    private JPanel upperPanel;
    private JPanel infoGamePanel;
    private JLabel infoTextLabel;

    private JPanel buttonsPanel;
    private JButton newGameButton;
    private JButton exitButton;


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

    private URL newGameBtnImgUrl = getClass().getResource("/images/NewGameBtn.png");
    private ImageIcon newGameBtnImage = new ImageIcon(newGameBtnImgUrl);
    private URL focusNewGameBtnImgUrl = getClass().getResource("/images/FocusNewGameBtn.png");
    private ImageIcon focusNewGameBtnImage = new ImageIcon(focusNewGameBtnImgUrl);

    private URL exitBtnImgUrl = getClass().getResource("/images/ExitBtn.png");
    private ImageIcon exitBtnImage = new ImageIcon(exitBtnImgUrl);
    private URL focusExitBtnImgUrl = getClass().getResource("/images/FocusExitBtn.png");
    private ImageIcon focusExitBtnImage = new ImageIcon(focusExitBtnImgUrl);

    private GameController gameController;

    public GUI() {
        mainFrame = new JFrame("Connect Four!");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setBackground(BACKGROUND_COLOR);

        setButtons();
        addButtonListeners();
        createContentComponents();
        createNewGamePanel();

        mainFrame.getContentPane().add(panelMain, BorderLayout.CENTER);
        centerWindow(mainFrame, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        mainFrame.setFocusable(true);
        mainFrame.pack();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame.setVisible(true);
            }
        });
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
                    JButton btn = (JButton) e.getComponent();
                    btn.setIcon(arrowButtonImage);
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

    private void centerWindow(Window window, int width, int height) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) (dimension.getWidth() - width) / 2;
        int y = (int) (dimension.getHeight() - height) / 2;
        window.setLocation(x, y);
    }

    private void createNewGamePanel() {
        newGamePanel = new NewGamePanel(DEFAULT_WIDTH, DEFAULT_HEIGHT, this);
    }

    private Component createContentComponents() {

        createUpperPanel();
        createLayeredBoard();
        createPlayersPanel();

        panelMain = new JPanel();
        panelMain.setBackground(BACKGROUND_COLOR);
        panelMain.setLayout(new BorderLayout());
        panelMain.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        panelMain.add(upperPanel, BorderLayout.NORTH);
        panelMain.add(layeredGameBoard, BorderLayout.CENTER);
        panelMain.add(playersPanel, BorderLayout.SOUTH);
        mainFrame.setResizable(false);
        return panelMain;
    }

    private void createUpperPanel() {
        createBoardNumbersPanel();
        createButtonsPanel();
        createEndGamePanel();

        upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(buttonsPanel, BorderLayout.NORTH);
        upperPanel.add(panelBoardNumbers, BorderLayout.CENTER);
    }

    private void createButtonsPanel() {
        newGameButton = new JButton();
        newGameButton.setIcon(newGameBtnImage);
        newGameButton.setBorder(BorderFactory.createEmptyBorder());
        newGameButton.setContentAreaFilled(false);


        newGameButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton btn = (JButton) e.getComponent();
                btn.setIcon(newGameBtnImage);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton btn = (JButton) e.getComponent();
                btn.setIcon(focusNewGameBtnImage);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton btn = (JButton) e.getComponent();
                btn.setIcon(newGameBtnImage);
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        requestNewGame();
                    }
                }).start();
            }
        });

        exitButton = new JButton();
        exitButton.setIcon(exitBtnImage);
        exitButton.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        exitButton.setContentAreaFilled(false);

        exitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton btn = (JButton) e.getComponent();
                btn.setIcon(exitBtnImage);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton btn = (JButton) e.getComponent();
                btn.setIcon(focusExitBtnImage);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton btn = (JButton) e.getComponent();
                btn.setIcon(exitBtnImage);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
            }
        });

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(BACKGROUND_COLOR);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_THE_UPPEST_PANEL_HEIGHT));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonsPanel.add(newGameButton);
        buttonsPanel.add(exitButton);

        newGameButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        exitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void createEndGamePanel() {
        infoGamePanel = new JPanel();
        infoGamePanel.setBackground(BACKGROUND_COLOR);
        //infoGamePanel.setLayout(new BorderLayout());
        infoGamePanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_UPPER_PANEL_HEIGHT));
        infoGamePanel.setBorder(BorderFactory.createEmptyBorder(5, 25, 15, 25));

        infoTextLabel = new JLabel();
        infoTextLabel.setFont(new Font("Verdana", Font.PLAIN, 32));
        infoTextLabel.setForeground(new Color(10, 78, 169));

        infoGamePanel.add(infoTextLabel);
    }

    private void createBoardNumbersPanel() {
        panelBoardNumbers = new JPanel();
        panelBoardNumbers.setBackground(BACKGROUND_COLOR);

        panelBoardNumbers.setLayout(new GridLayout(1, 7, 10, 0));
        panelBoardNumbers.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_UPPER_PANEL_HEIGHT));
        panelBoardNumbers.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
        for (JButton button : buttons) {
            panelBoardNumbers.add(button);
        }
    }

    private void createLayeredBoard() {
        layeredGameBoard = new JLayeredPane();
        layeredGameBoard.setBackground(BACKGROUND_COLOR);
        layeredGameBoard.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_BOARD_HEIGHT));

        JLabel imageBoardLabel = new JLabel(boardImage);
        imageBoardLabel.setBounds(20, 0, boardImage.getIconWidth(), boardImage.getIconHeight());
        layeredGameBoard.add(imageBoardLabel, 0, 1);
    }

    private void createPlayersPanel() {
        playersPanel = new JPanel();
        playersPanel.setBackground(BACKGROUND_COLOR);
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.X_AXIS));

        JPanel yellowPLayerPanel = new JPanel();

        yellowPLayerPanel.setBackground(BACKGROUND_COLOR);
        playerOneNameLabel = new JLabel();
        playerOneNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        setPlayerLabelTextStyle(playerOneNameLabel);
        JLabel imageYellowTokenLabel = new JLabel(smallYellowTokenImage);
        yellowPLayerPanel.add(imageYellowTokenLabel);
        yellowPLayerPanel.add(playerOneNameLabel);
        yellowPLayerPanel.setPreferredSize(new Dimension(200, DEFAULT_LOWER_PANEL_HEIGHT));

        JPanel redPlayerPanel = new JPanel();
        redPlayerPanel.setBackground(BACKGROUND_COLOR);
        JLabel imageRedTokenLabel = new JLabel(smallRedTokenImage);
        playerTwoNameLabel = new JLabel();
        playerTwoNameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        setPlayerLabelTextStyle(playerTwoNameLabel);
        redPlayerPanel.add(playerTwoNameLabel);
        redPlayerPanel.add(imageRedTokenLabel);
        redPlayerPanel.setPreferredSize(new Dimension(200, DEFAULT_LOWER_PANEL_HEIGHT));

        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.PAGE_AXIS));
        arrowLabel = new JLabel(leftYellowArrowImage);
        centralPanel.add(Box.createVerticalGlue());
        centralPanel.add(arrowLabel);
        centralPanel.add(Box.createVerticalGlue());
        centralPanel.setBackground(BACKGROUND_COLOR);

        playersPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        playersPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_LOWER_PANEL_HEIGHT));

        yellowPLayerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        redPlayerPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        playersPanel.add(yellowPLayerPanel);
        playersPanel.add(Box.createHorizontalGlue());
        playersPanel.add(centralPanel);
        playersPanel.add(Box.createHorizontalGlue());
        playersPanel.add(redPlayerPanel);


    }

    private void setPlayerLabelTextStyle(JLabel playerLabel) {
        playerLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        playerLabel.setForeground(new Color(10, 78, 169));
    }


    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void printStartGame(GameStartObject gameStartObject) {
        panelMain.remove(layeredGameBoard);
        createLayeredBoard();
        panelMain.add(layeredGameBoard, BorderLayout.CENTER);
        upperPanel.remove(infoGamePanel);
        upperPanel.add(panelBoardNumbers, BorderLayout.CENTER);
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
            playerTwoName = "Player 2";
        }
        playerOneNameLabel.setText(playerOneName);
        playerTwoNameLabel.setText(playerTwoName);

        Player nextPlayer = gameStartObject.getNextPlayer();
        drawPlayerArrow(nextPlayer);
        activateButtons();

        if (!nextPlayer.isReal()) {
            deactivateButtons();
            final ComputerPlayer currentAIPlayer = (ComputerPlayer) nextPlayer;
            gameController.performAIMove(currentAIPlayer);
        } else {
            activateButtons();
        }
    }

    public void printGameAfterMove(GameMoveObject gameMoveObject, Player nextPlayer) {
        int lastMoveCol = gameMoveObject.getLastMoveCol();
        int lastMoveRow = gameMoveObject.getLatMoveRow();
        Token lastMoveToken = gameMoveObject.getLastMoveToken();
        placeToken(lastMoveToken, lastMoveRow, lastMoveCol);
        if (!nextPlayer.isReal()) {
            deactivateButtons();
        } else {
            activateButtons();
        }
        drawPlayerArrow(nextPlayer);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });

        if (nextPlayer.getType() == PlayerType.AI) {
            final ComputerPlayer currentAIPlayer = (ComputerPlayer) nextPlayer;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    gameController.performAIMove(currentAIPlayer);
                }
            }).start();
        }
    }

    private void activateButtons() {
        for (JButton button : buttons) {
            button.setEnabled(true);
        }
    }

    private void deactivateButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    void drawPlayerArrow(final Player nextPlayer) {
        if (nextPlayer.getPlayerToken() == Token.YELLOW) {
            arrowLabel.setIcon(leftYellowArrowImage);
        } else {
            arrowLabel.setIcon(rightRedArrowImage);
        }
    }

    private void placeToken(Token token, int row, int col) {
        int xOffset = 60 * col;
        int yOffset = 60 * row;

        ImageIcon checkerIcon = token == Token.YELLOW ? yellowTokenImage : redTokenImage;
        JLabel checkerLabel = new JLabel(checkerIcon);
        checkerLabel.setBounds(30 + xOffset, 13 + yOffset, checkerIcon.getIconWidth(), checkerIcon.getIconHeight());
        layeredGameBoard.add(checkerLabel, 0, 0);
    }

    public void printEndedGame(EndGameObject endGameObject) {
        GameMoveObject gameMoveObject = endGameObject.getGameMoveObject();
        int lastMoveCol = gameMoveObject.getLastMoveCol();
        int lastMoveRow = gameMoveObject.getLatMoveRow();
        Token lastMoveToken = gameMoveObject.getLastMoveToken();
        placeToken(lastMoveToken, lastMoveRow, lastMoveCol);

        arrowLabel.setBounds(0, 0, leftYellowArrowImage.getIconWidth(), leftYellowArrowImage.getIconHeight());
        arrowLabel.setIcon(null);
        Player winner = endGameObject.getWinner();
        if (winner != null) {
            String winnerName = winner.getPlayerToken() == Token.YELLOW ? playerOneName : playerTwoName;
            infoTextLabel.setText("WINNER: " + winnerName);
        } else {
            infoTextLabel.setText("TIE");
        }
        upperPanel.remove(panelBoardNumbers);
        upperPanel.add(infoGamePanel, BorderLayout.CENTER);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                upperPanel.revalidate();
                upperPanel.repaint();
            }
        });

    }

    public void printWrongMove() {

    }

    @Override
    public void requestNewGame() {
        mainFrame.getContentPane().remove(panelMain);
        mainFrame.getContentPane().add(newGamePanel);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });
    }

    public void setGame(ConnectFourGame game) {
        mainFrame.getContentPane().remove(newGamePanel);
        mainFrame.getContentPane().add(panelMain);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });
        gameController.setNewGame(game);
    }


}
