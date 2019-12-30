package tictactoe.repositories;

import tictactoe.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestPlayerRepository extends JpaRepository<User, String> {
}
