package de.europace.recruiting.devops;

import static de.europace.recruiting.devops.Constants.MAX_RANDOM_VALUE;
import static de.europace.recruiting.devops.Constants.MIN_RANDOM_VALUE;

import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class RandomValueProvider {

  private static final int RATE_3_SECONDS = 300;

  private final RandomCustomMetricService randomCustomMetricService;

  /*
   * The task suggested to add tests where it makes sense. While components like
   * this one tend to be very common test subjects, I would decide against for two
   * reasons. First, testing random generators would potentially create flaky
   * tests and builds, what makes it hard to reproduce. Reproducable tests is what
   * we want (the 'R' in F.I.R.S.T. principle for tests). Second, I can't see a
   * good reason why we would hard-limit a specific value that goes to a
   * metric. Doesn't make any sense.
   */

  @Scheduled(fixedRate = RATE_3_SECONDS)
  public void createRandomValue() {
    var rangedRandom = MIN_RANDOM_VALUE + (MAX_RANDOM_VALUE - MIN_RANDOM_VALUE) * new Random().nextDouble();
    randomCustomMetricService.recordRandomValueMetric(rangedRandom);
    log.info("created new random value: {}", rangedRandom);
  }
}
