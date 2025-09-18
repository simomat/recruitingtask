package de.europace.recruiting.devops;

import static de.europace.recruiting.devops.Constants.MAX_RANDOM_VALUE;
import static de.europace.recruiting.devops.Constants.MIN_RANDOM_VALUE;
import static java.text.MessageFormat.format;

import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class RandomCustomMetricService {

  private final DistributionSummary radnomDistributionSummary;

  /*
   * Not sure if the reader would prefer to outsource the configuration of
   * DistributionSummary to a Spring @Configuration. I think the value would
   * depend on things... If there are several DistributionSummaries in this
   * application, then it would be necessary to use bean qualifiers. But it would
   * make this class a lot more testable for a bit more complex scenarios, tho.
   */

  public RandomCustomMetricService(MeterRegistry registry) {
    radnomDistributionSummary = DistributionSummary
        .builder("random.custom.metric")
        .description(format("a random value between {0} and {1}", MIN_RANDOM_VALUE, MAX_RANDOM_VALUE))
        .tags("application", "randommetric")
        .minimumExpectedValue(MIN_RANDOM_VALUE)
        .maximumExpectedValue(MAX_RANDOM_VALUE)
        .register(registry);
  }

  public void recordRandomValueMetric(double value) {
    radnomDistributionSummary.record(value);
  }

}
