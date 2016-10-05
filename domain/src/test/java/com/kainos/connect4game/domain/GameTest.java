package com.kainos.connect4game.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class GameTest {

    @Test
    public void shouldThrowAnExceptionWhenSecondPlayerHasChosenTheSameColourAsFirstPlayer() throws Exception {
        Game game = new Game();
        game.addPlayer(new Player("John", Player.Colour.Red));

        assertThatThrownBy(() -> game.addPlayer(new Player("Carl", Player.Colour.Red)))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessage("Two players cannot choose the same colour");
    }

    @Test
    public void shouldThrowAnExceptionWhenThirdPlayerIsBeingAdded() throws Exception {
        Game game = new Game();
        game.addPlayer(new Player("John", Player.Colour.Red));
        game.addPlayer(new Player("Carl", Player.Colour.Yellow));

        assertThatThrownBy(() -> game.addPlayer(new Player("Stephanie", Player.Colour.Yellow)))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessage("Game cannot have more then 2 players");
    }

    @Test
    public void shouldThrowAnExceptionWhenTheSamePlayerIsMakingTwoConsecutiveDrops() throws Exception {
        Player player = new Player("John", Player.Colour.Red);
        Game game = new Game(player);
        game.dropDisc(player.getColour(), 0);

        assertThatThrownBy(() -> game.dropDisc(player.getColour(), 0))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessage("Single player cannot drop two discs in a row");
    }

}