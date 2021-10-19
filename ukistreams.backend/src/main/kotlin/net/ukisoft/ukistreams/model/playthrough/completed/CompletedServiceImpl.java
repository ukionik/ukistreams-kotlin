package net.ukisoft.ukistreams.model.playthrough.completed;

import net.ukisoft.ukistreams.entity.Game;
import net.ukisoft.ukistreams.entity.Playthrough;
import net.ukisoft.ukistreams.entity.VodPart;
import net.ukisoft.ukistreams.model.repository.FetchField;
import net.ukisoft.ukistreams.model.repository.RepositoryFilter;
import net.ukisoft.ukistreams.model.v1.all.completed.GameCompletedByPlatformItemModelMapper;
import net.ukisoft.ukistreams.model.v1.all.completed.GameCompletedItemModel;
import net.ukisoft.ukistreams.model.v1.all.completed.GameCompletedItemModelMapper;
import net.ukisoft.ukistreams.model.v1.all.completed.GameCompletedPlatformModel;
import net.ukisoft.ukistreams.repository.PlatformRepository;
import net.ukisoft.ukistreams.repository.PlaythroughRepository;
import net.ukisoft.ukistreams.repository.VodPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 25.10.2020 02:53
 */
@Service
@Transactional
public class CompletedServiceImpl implements CompletedService {
  private final PlaythroughRepository playthroughRepository;
  private final VodPartRepository vodPartRepository;
  private final PlatformRepository platformRepository;
  private final GameCompletedItemModelMapper gameCompletedItemModelMapper;

  @Autowired
  public CompletedServiceImpl(
          PlaythroughRepository playthroughRepository,
          VodPartRepository vodPartRepository,
          PlatformRepository platformRepository
  ) {
    this.playthroughRepository = playthroughRepository;
    this.vodPartRepository = vodPartRepository;
    this.platformRepository = platformRepository;
    this.gameCompletedItemModelMapper = new GameCompletedItemModelMapper();
  }

  @Override
  public List<GameCompletedItemModel> findAll() {
    var vodPartRepositoryFilter = new RepositoryFilter();
    vodPartRepositoryFilter.setFetchFields(FetchField.left("vod"));

    var vodGroup = vodPartRepository.findByFilter(vodPartRepositoryFilter)
            .parallelStream()
            .collect(Collectors.groupingBy(VodPart::getVod));

    var filter = new RepositoryFilter();
    filter.setFetchFields(FetchField.left("game")
            , FetchField.left("game.platform")
            , FetchField.left("game.genre")
            , FetchField.left("game.review")
            , FetchField.left("project")
    );

    var playthroughs = playthroughRepository.findByFilter(filter);

    var playthroughVodGroup = vodGroup.entrySet()
            .parallelStream()
            .collect(Collectors.groupingBy(x -> x.getKey().getPlaythrough().getId()));

    var gameMap = playthroughs.parallelStream()
            .collect(Collectors.toMap(Playthrough::getGame, x -> x
                    , (x1, x2) -> x1.getEndDate().isAfter(x2.getEndDate()) ? x1 : x2));

    var items = gameMap.entrySet().parallelStream()
            .sorted(Comparator.comparing((Map.Entry<Game, Playthrough> x) -> x.getValue().getEndDate(), Comparator.reverseOrder())
                    .thenComparing(x -> x.getValue().getId(), Comparator.reverseOrder()))
            .map(x -> gameCompletedItemModelMapper.toModel(x.getKey(), x.getValue(), playthroughVodGroup.getOrDefault(x.getValue().getId(), new ArrayList<>())))
            .collect(Collectors.toList());

    for (var i = 0; i < items.size(); i++) {
      var item = items.get(i);
      item.setIndex(items.size() - i);
    }

    return items;
  }

  @Override
  public List<GameCompletedPlatformModel> findByPlatform() {
    var vodPartRepositoryFilter = new RepositoryFilter();
    vodPartRepositoryFilter.setFetchFields(FetchField.left("vod"));

    var vodGroup = vodPartRepository.findByFilter(vodPartRepositoryFilter)
            .parallelStream()
            .collect(Collectors.groupingBy(VodPart::getVod));

    var filter = new RepositoryFilter();
    filter.setFetchFields(FetchField.left("game")
            , FetchField.left("game.genre")
            , FetchField.left("game.review")
    );

    var playthroughs = playthroughRepository.findByFilter(filter);

    var platforms = platformRepository.findAll(Sort.by(Sort.Direction.ASC, "ordinal"));

    var playthroughVodGroup = vodGroup.entrySet()
            .parallelStream()
            .collect(Collectors.groupingBy(x -> x.getKey().getPlaythrough().getId()));

    var gameMap = playthroughs.parallelStream()
            .collect(Collectors.toMap(Playthrough::getGame, x -> x
                    , (x1, x2) -> x1.getEndDate().isAfter(x2.getEndDate()) ? x1 : x2));

    var gameByPlatformGroup = gameMap.entrySet().parallelStream()
            .filter(x -> x.getKey().getPlatform() != null)
            .collect(Collectors.groupingBy(x -> x.getKey().getPlatform().getId()));

    var gamePlatforms = new ArrayList<GameCompletedPlatformModel>();
    var mapper = new GameCompletedByPlatformItemModelMapper();

    platforms.forEach(platform -> {
      var list = gameByPlatformGroup.getOrDefault(platform.getId(), new ArrayList<>());

      var games = list.stream()
              .sorted(Comparator.comparing(x -> x.getKey().getName()))
              .map(x -> mapper.toModel(x.getKey(), x.getValue(), playthroughVodGroup.getOrDefault(x.getValue().getId(), new ArrayList<>())))
              .collect(Collectors.toList());

      var model = new GameCompletedPlatformModel(platform, games);
      gamePlatforms.add(model);
    });

    return gamePlatforms;
  }
}