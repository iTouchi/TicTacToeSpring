package tictactoe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tictactoe.entities.GameStateModel;

@Repository
public interface GameStateRepository extends MongoRepository<GameStateModel, String> {

    public Optional<GameStateModel> findById(String id);
    public List<GameStateModel> findByStartedAndDisconnect(boolean started, boolean disconnect);
    public GameStateModel findTopByPlayerOneIdOrPlayerTwoIdOrderByCreatedDesc(String playerOneId, String playerTwoId);

}
