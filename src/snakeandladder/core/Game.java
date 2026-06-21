package snakeandladder.core;

import snakeandladder.model.Dice;
import snakeandladder.model.GameStatus;
import snakeandladder.model.Player;
import snakeandladder.model.jump.Jump;

import java.util.LinkedList;
import java.util.Queue;

public class Game {
    private final Board board;
    private final Dice dice;
    private final Queue<Player> players;

    private GameStatus status;
    private Player winner;

    public Game(Board board, Dice dice, Queue<Player> players) {
        this.board = board;
        this.dice = dice;
        this.players = new LinkedList<>(players);
        this.status = GameStatus.NOT_STARTED;
    }

    public boolean isGameOver() {
        return status == GameStatus.FINISHED;
    }

    public Player getWinner() {
        return winner;
    }

    public void start() {
        status = GameStatus.RUNNING;
        while (!isGameOver()) {
            playTurn();
        }
        System.out.println("Winner: " + winner.getName());
    }

    private boolean hasWon(Player player) {
        return player.getCurrentPosition() == board.getSize();
    }

    private void movePlayer(Player player, int diceValue) {
        int nextPosition = player.getCurrentPosition() + diceValue;
        if (nextPosition > board.getSize()) {
            return;
        }

        Jump jump = board.getJump(nextPosition);
        if (jump != null) {
            System.out.println("Jump encountered: " + nextPosition + " -> " + jump.getEnd());
            nextPosition = jump.getEnd();
        }
        player.setCurrentPosition(nextPosition);
        System.out.println(player.getName() + " moved to " + nextPosition);
    }

    private void playTurn() {
        Player currentPlayer = players.poll();
        boolean extraTurn = true;

        while (extraTurn && !isGameOver()) {
            int diceValue = dice.roll();
            System.out.println(currentPlayer.getName() + " rolled " + diceValue);
            if (diceValue == 6) {
                currentPlayer.incrementConsecutiveSixCount();
                if (currentPlayer.getConsecutiveSixCount() == 3) {
                    System.out.println(currentPlayer.getName() + " forfeits turn ");
                    currentPlayer.resetConsecutiveSixCount();
                    break;
                }
            } else {
                currentPlayer.resetConsecutiveSixCount();
            }

            movePlayer(currentPlayer, diceValue);
            if (hasWon(currentPlayer)) {
                winner = currentPlayer;
                status = GameStatus.FINISHED;
                return;
            }

            extraTurn = diceValue == 6;
        }
        players.offer(currentPlayer);
    }
}
