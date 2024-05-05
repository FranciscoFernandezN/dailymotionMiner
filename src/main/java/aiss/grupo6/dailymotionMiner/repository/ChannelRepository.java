package aiss.grupo6.dailymotionMiner.repository;

import aiss.grupo6.dailymotionMiner.database.VMChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<VMChannel, String> {
}
