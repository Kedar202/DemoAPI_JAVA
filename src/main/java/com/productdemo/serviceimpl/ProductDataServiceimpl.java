package com.productdemo.serviceimpl;


import com.productdemo.dto.ProductDataDTO;
import com.productdemo.dto.ProductDataResponseDTO;
import com.productdemo.dto.ProductResponseDTO;
import com.productdemo.dto.Response;
import com.productdemo.entity.MemberData;
import com.productdemo.entity.ProductCategory;
import com.productdemo.entity.ProductData;
import com.productdemo.repository.MemberDataRepository;
import com.productdemo.repository.ProductCategoryRepository;
import com.productdemo.repository.ProductDataRepository;
import com.productdemo.service.ProductDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductDataServiceimpl implements ProductDataService {

    final ProductDataRepository productDataRepository;
    final ProductCategoryRepository productCategoryRepository;
    final MemberDataRepository memberDataRepository;

    @Override
    public Response addProductData(String userId, ProductDataDTO productDataDTO) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(productDataDTO.getProductCategoryId());
        Optional<MemberData> roleOfUserId = memberDataRepository.findByUserId(userId);
        if (roleOfUserId.get().getRole().equals("ADMIN")) {
            if (productCategory.isPresent()) {
                ProductData productData = new ProductData();
                BeanUtils.copyProperties(productDataDTO, productData);
                productDataRepository.save(productData);
                return new Response("200", "data saved successfully", new ArrayList<>());
            } else {
                return new Response("200", "product category is not present", new ArrayList<>());
            }
        } else {
            return new Response("500", "Employee can not has access to add product data", new ArrayList<>());
        }
    }

    @Override
    public Response productList(Integer pageSize, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageSize, pageNumber);
        Page<ProductData> abc = productDataRepository.findRecords(pageable);
        List<ProductData> productDataList = abc.getContent();
        List<ProductDataResponseDTO> productDataResponseDTOList = new ArrayList<>();
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        ProductDataResponseDTO productDataResponseDTO = new ProductDataResponseDTO();
        for (ProductData productData : productDataList) {
            BeanUtils.copyProperties(productData, productDataResponseDTO);
            productDataResponseDTO.setCategoryName(productCategoryRepository.findById(productData.getProductCategoryId()).get().getCategoryName());

            System.out.println(productDataResponseDTO.getCategoryName());
            productDataResponseDTOList.add(productDataResponseDTO);
        }
        responseDTO.setCount(productDataResponseDTOList.size());
        responseDTO.setProductList(productDataResponseDTOList);
        List list = new ArrayList();
        list.add(productDataList);
        list.add(0,abc.getTotalElements());
        return new Response("200", "data fetched successfully", list);
    }


}
