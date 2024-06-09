package se.magnus.microservices.core.product.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import se.magnus.api.core.product.Product;
import se.magnus.api.core.product.ProductController;
import se.magnus.api.exceptions.InvalidInputException;
import se.magnus.api.exceptions.NotFoundException;
import se.magnus.util.http.ServiceUtil;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductControllerImpl implements ProductController {

  private final ServiceUtil serviceUtil;

  @Override
  public Product getProduct(int productId) {
    log.debug("/product return the found product for productId={}", productId);

    if (productId < 1) {
      throw new InvalidInputException("Invalid productId: " + productId);
    }

    if (productId == 13) {
      throw new NotFoundException("No product found for productId: " + productId);
    }

    return new Product(productId, "name-" + productId, 123, serviceUtil.getServiceAddress());
  }
}
