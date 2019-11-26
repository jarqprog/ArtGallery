package com.jarqprog.commonapi.components;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapperProviderImpl implements MapperProvider {

    public static ModelMapper provide() {
        return new MapperProviderImpl().getModelMapper();
    }

    @Override
    public ModelMapper getModelMapper() {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
            return modelMapper;
    }

}
