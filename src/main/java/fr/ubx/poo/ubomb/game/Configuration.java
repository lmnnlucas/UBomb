package fr.ubx.poo.ubomb.game;

public record Configuration(Position playerPosition, int bombBagCapacity, int playerLives, long playerInvincibilityTime,
                            int monsterVelocity, long monsterInvincibilityTime) {
}
