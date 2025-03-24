package com.gayatech.shopping_service.modelmappers;


import com.gayatech.shopping_service.dtos.ShoppingDTO;
import com.gayatech.shopping_service.models.Shopping;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingMapper {

    private ModelMapper modelMapper;

    public ShoppingMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public List<ShoppingDTO> convertToDTOList(List<Shopping> productList) {
        return modelMapper.map(productList, new TypeToken<List<ShoppingDTO>>() {}.getType());
    }

    public List<Shopping> convertToModelList(List<ShoppingDTO> productDTOList) {
        return modelMapper.map(productDTOList, new TypeToken<List<Shopping>>() {}.getType());
    }

    public ShoppingDTO convertToDTO(Shopping product) {
        return modelMapper.map(product, ShoppingDTO.class);
    }

    public Shopping convertToModel(ShoppingDTO productDTO) {
        return modelMapper.map(productDTO, Shopping.class);
    }
}
