/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: UniqueFieldValidator
 *	@CreatedOn	: 12-09-2025
 *
 *	@Type		: Class
 *	@Layer		: Validator
 *	@Package	: Common / Validator
 *
 **/

package dz.mdn.iaas.common.validator;

import dz.mdn.iaas.exception.BusinessValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Centralized validator for unique field constraints
 * Reduces code duplication in service layers
 */
@Component
@Slf4j
public class UniqueFieldValidator {

    /**
     * Validate unique field for create operation
     * 
     * @param fieldName Human-readable field name for error message
     * @param fieldValue The value to check
     * @param existsFunction Function to check if value exists (returns boolean)
     */
    public <T> void validateUniqueForCreate(String fieldName, T fieldValue, 
                                             Function<T, Boolean> existsFunction) {
        if (fieldValue == null) {
            return; // Skip validation for null values
        }

        if (existsFunction.apply(fieldValue)) {
            String message = String.format("%s '%s' already exists", fieldName, fieldValue);
            log.warn("Unique constraint violation: {}", message);
            throw new BusinessValidationException(message);
        }
    }

    /**
     * Validate unique field for update operation (excluding current entity)
     * 
     * @param fieldName Human-readable field name for error message
     * @param fieldValue The value to check
     * @param excludeId ID of current entity to exclude from check
     * @param existsExcludingFunction Function to check if value exists excluding the given ID
     */
    public <T, ID> void validateUniqueForUpdate(String fieldName, T fieldValue, ID excludeId,
                                                 BiFunction<T, ID, Boolean> existsExcludingFunction) {
        if (fieldValue == null) {
            return; // Skip validation for null values
        }

        if (existsExcludingFunction.apply(fieldValue, excludeId)) {
            String message = String.format("Another record with %s '%s' already exists", 
                                          fieldName, fieldValue);
            log.warn("Unique constraint violation on update: {}", message);
            throw new BusinessValidationException(message);
        }
    }

    /**
     * Validate multiple unique fields for create operation
     * 
     * @param validations Array of UniqueFieldValidation objects
     */
    public void validateMultipleForCreate(UniqueFieldValidation<?>... validations) {
        for (UniqueFieldValidation<?> validation : validations) {
            validateUniqueForCreateInternal(validation);
        }
    }

    /**
     * Validate multiple unique fields for update operation
     * 
     * @param validations Array of UniqueFieldUpdateValidation objects
     */
    public <ID> void validateMultipleForUpdate(UniqueFieldUpdateValidation<?, ID>... validations) {
        for (UniqueFieldUpdateValidation<?, ID> validation : validations) {
            validateUniqueForUpdateInternal(validation);
        }
    }

    private <T> void validateUniqueForCreateInternal(UniqueFieldValidation<T> validation) {
        validateUniqueForCreate(
            validation.fieldName, 
            validation.fieldValue, 
            validation.existsFunction
        );
    }

    private <T, ID> void validateUniqueForUpdateInternal(UniqueFieldUpdateValidation<T, ID> validation) {
        validateUniqueForUpdate(
            validation.fieldName, 
            validation.fieldValue, 
            validation.excludeId,
            validation.existsExcludingFunction
        );
    }

    /**
     * Helper class to bundle validation parameters for create operations
     */
    public static class UniqueFieldValidation<T> {
        private final String fieldName;
        private final T fieldValue;
        private final Function<T, Boolean> existsFunction;

        public UniqueFieldValidation(String fieldName, T fieldValue, 
                                    Function<T, Boolean> existsFunction) {
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;
            this.existsFunction = existsFunction;
        }

        public static <T> UniqueFieldValidation<T> of(String fieldName, T fieldValue,
                                                       Function<T, Boolean> existsFunction) {
            return new UniqueFieldValidation<>(fieldName, fieldValue, existsFunction);
        }
    }

    /**
     * Helper class to bundle validation parameters for update operations
     */
    public static class UniqueFieldUpdateValidation<T, ID> {
        private final String fieldName;
        private final T fieldValue;
        private final ID excludeId;
        private final BiFunction<T, ID, Boolean> existsExcludingFunction;

        public UniqueFieldUpdateValidation(String fieldName, T fieldValue, ID excludeId,
                                          BiFunction<T, ID, Boolean> existsExcludingFunction) {
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;
            this.excludeId = excludeId;
            this.existsExcludingFunction = existsExcludingFunction;
        }

        public static <T, ID> UniqueFieldUpdateValidation<T, ID> of(String fieldName, T fieldValue, 
                                                                     ID excludeId,
                                                                     BiFunction<T, ID, Boolean> existsExcludingFunction) {
            return new UniqueFieldUpdateValidation<>(fieldName, fieldValue, excludeId, existsExcludingFunction);
        }
    }
}
