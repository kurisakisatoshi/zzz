package zzz;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ClassUtils;


public final class Entity2DtoConverter<ENTITY, DTO> {

    private final Class<DTO> dtoClass;

    private Entity2DtoConverter(Class<DTO> dtoClass) {
        this.dtoClass = dtoClass;
    }

    public DTO convert(ENTITY entity) {

        try {

            DTO dto = dtoClass.newInstance();

            Arrays.stream(dtoClass.getDeclaredFields())
                .filter(field -> !field.isEnumConstant() && !field.isSynthetic())
                .forEach(field -> convert(dto, field, entity));

            return dto;

        } catch (InstantiationException | IllegalAccessException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private void convert(DTO dto, Field field, ENTITY entity) {

        try {

            if (!field.isAccessible()) {
                field.setAccessible(true);
            }

            // 同じ名前のFieldがあるか
            Field entityField = entity.getClass().getDeclaredField(field.getName());
            if (!entityField.isAccessible()) {
                entityField.setAccessible(true);
            }

            Object value = entityField.get(entity);
            if (value == null) {
                // nullは無視
                return;
            }

            // プリミティブかそのWrapperクラス
            if (ClassUtils.isPrimitiveOrWrapper(value.getClass())) {
                field.set(dto, value);
                return;
            }

            if (field.getType().getName().equals(value.getClass().getName())) {
                field.set(dto, value);
                return;
            }

            Method m = value.getClass().getMethod("getValue");
            field.set(dto, m.invoke(value));

        } catch (NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();

        } catch (SecurityException | IllegalAccessException | IllegalArgumentException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static <ENTITY, DTO> Entity2DtoConverterBuilder<ENTITY, DTO> converter() {
        return new Entity2DtoConverterBuilder<ENTITY, DTO>();
    }

    public static <ENTITY, DTO> EntityList2DtoListConverterBuilder<ENTITY, DTO> listConverter() {
        return new EntityList2DtoListConverterBuilder<ENTITY, DTO>();
    }

    public static class Entity2DtoConverterBuilder<ENTITY, DTO> {

        private ENTITY entity;
        private Class<DTO> dtoClass;

        private Entity2DtoConverterBuilder() {
        }

        public Entity2DtoConverterBuilder<ENTITY, DTO> from(ENTITY enity) {
            this.entity = enity;
            return this;
        }

        public Entity2DtoConverterBuilder<ENTITY, DTO> to(Class<DTO> dtoClass) {
            this.dtoClass = dtoClass;
            return this;
        }

        public DTO convert() {
            return new Entity2DtoConverter<ENTITY, DTO>(dtoClass).convert(entity);
        }
    }

    public static class EntityList2DtoListConverterBuilder<ENTITY, DTO> {

        private List<ENTITY> enities;
        private Class<DTO> dtoClass;

        private EntityList2DtoListConverterBuilder() {
        }

        public EntityList2DtoListConverterBuilder<ENTITY, DTO> from(List<ENTITY> enities) {
            this.enities = enities;
            return this;
        }

        public EntityList2DtoListConverterBuilder<ENTITY, DTO> to(Class<DTO> dtoClass) {
            this.dtoClass = dtoClass;
            return this;
        }

        public List<DTO> convert() {
            Entity2DtoConverter<ENTITY, DTO> converter = new Entity2DtoConverter<>(dtoClass);
            return enities.stream()
                        .map(converter::convert)
                        .collect(Collectors.toList());
        }
    }
}
