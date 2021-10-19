package net.ukisoft.ukistreams.model.playthrough.completed;

import net.ukisoft.ukistreams.model.v1.all.completed.GameCompletedItemModel;
import net.ukisoft.ukistreams.model.v1.all.completed.GameCompletedPlatformModel;

import java.util.List;

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 25.10.2020 02:53
 */
public interface CompletedService {
  List<GameCompletedItemModel> findAll();
  List<GameCompletedPlatformModel> findByPlatform();
}
