package se.magnus.microservices.core.review.controllers;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import se.magnus.api.core.review.Review;
import se.magnus.api.core.review.ReviewController;
import se.magnus.api.exceptions.InvalidInputException;
import se.magnus.util.http.ServiceUtil;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewController {

  private final ServiceUtil serviceUtil;


  @Override
  public List<Review> getReviews(int productId) {

    if (productId < 1) {
      throw new InvalidInputException("Invalid productId: " + productId);
    }

    if (productId == 213) {
      log.debug("No reviews found for productId: {}", productId);
      return new ArrayList<>();
    }

    List<Review> list = new ArrayList<>();
    list.add(new Review(productId, 1, "Author 1", "Subject 1", "Content 1", serviceUtil.getServiceAddress()));
    list.add(new Review(productId, 2, "Author 2", "Subject 2", "Content 2", serviceUtil.getServiceAddress()));
    list.add(new Review(productId, 3, "Author 3", "Subject 3", "Content 3", serviceUtil.getServiceAddress()));

    log.debug("/reviews response size: {}", list.size());

    return list;
  }
}
