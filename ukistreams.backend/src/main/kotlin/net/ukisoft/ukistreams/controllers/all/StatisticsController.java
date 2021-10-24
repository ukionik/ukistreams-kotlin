package net.ukisoft.ukistreams.controllers.all;

import net.ukisoft.ukistreams.model.playthrough.statistics.StatisticsModel;
import net.ukisoft.ukistreams.model.playthrough.statistics.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 06.12.2020 02:15
 */
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
  private final StatisticsService statisticsService;

  public StatisticsController(StatisticsService statisticsService) {
    this.statisticsService = statisticsService;
  }

  @GetMapping("all")
  public ResponseEntity<StatisticsModel> all(){
    var model = statisticsService.findStatistics();
    return ResponseEntity.ok(model);
  }
}
