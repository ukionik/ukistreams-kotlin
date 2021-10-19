package net.ukisoft.ukistreams.model.playthrough.statistics;

import net.ukisoft.ukistreams.entity.Playthrough;
import net.ukisoft.ukistreams.model.repository.FetchField;
import net.ukisoft.ukistreams.model.repository.RepositoryFilter;
import net.ukisoft.ukistreams.model.v1.all.statistics.StatisticsGenreModelMapper;
import net.ukisoft.ukistreams.model.v1.all.statistics.StatisticsModel;
import net.ukisoft.ukistreams.model.v1.all.statistics.StatisticsPlatformModelMapper;
import net.ukisoft.ukistreams.repository.GenreRepository;
import net.ukisoft.ukistreams.repository.PlatformRepository;
import net.ukisoft.ukistreams.repository.PlaythroughRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.12.2020 01:14
 */
@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {
  private final PlaythroughRepository playthroughRepository;
  private final PlatformRepository platformRepository;
  private final GenreRepository genreRepository;

  @Autowired
  public StatisticsServiceImpl(PlaythroughRepository playthroughRepository
          , PlatformRepository platformRepository
          , GenreRepository genreRepository) {
    this.playthroughRepository = playthroughRepository;
    this.platformRepository = platformRepository;
    this.genreRepository = genreRepository;
  }

  @Override
  public StatisticsModel findStatistics() {
    var filter = new RepositoryFilter();
    filter.setFetchFields(FetchField.left("game")
    );

    var playthroughs = playthroughRepository.findByFilter(filter);
    var platforms = platformRepository.findAll(Sort.by(Sort.Direction.ASC, "ordinal"));
    var genres = genreRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

    var gameMap = playthroughs.parallelStream()
            .collect(Collectors.toMap(Playthrough::getGame, x -> x
                    , (x1, x2) -> x1.getEndDate().isAfter(x2.getEndDate()) ? x1 : x2));

    var platformMapper = new StatisticsPlatformModelMapper();
    var genreMapper = new StatisticsGenreModelMapper();

    var platformMap = gameMap.entrySet()
            .parallelStream()
            .collect(Collectors.groupingBy(x -> x.getKey().getPlatform().getId()));

    var genreMap = gameMap.entrySet()
            .parallelStream()
            .collect(Collectors.groupingBy(x -> x.getKey().getGenre().getId()));

    return new StatisticsModel(platformMapper.toModel(platforms, platformMap), genreMapper.toModel(genres, genreMap));
  }
}
