package com.hippalus.orderingservice.domain.converter;

import com.hippalus.orderingservice.domain.models.ProductId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class ProductIdConverter implements AttributeConverter<ProductId, String> {
    @Override
    public String convertToDatabaseColumn(ProductId attribute) {
        return Objects.isNull(attribute)  ? null : attribute.toUUID();
    }

    @Override
    public ProductId convertToEntityAttribute(String dbData) {
        return Objects.isNull(dbData)? null : new ProductId(dbData);
    }
}
