package DTO;

public class ProductInCategoryCounterDTO {
    private Long categoryId;
    private Long productInCategoryCounter;

    public ProductInCategoryCounterDTO(Long categoryId, Long productInCategoryCounter) {
        this.categoryId = categoryId;
        this.productInCategoryCounter = productInCategoryCounter;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getProductInCategoryCounter() {
        return productInCategoryCounter;
    }
}
