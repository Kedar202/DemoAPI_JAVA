package com.productdemo.serviceimpl;

import com.productdemo.dto.ProductCategoryDTO;
import com.productdemo.dto.Response;
import com.productdemo.entity.MemberData;
import com.productdemo.entity.ProductCategory;
import com.productdemo.repository.MemberDataRepository;
import com.productdemo.repository.ProductCategoryRepository;
import com.productdemo.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    final ProductCategoryRepository productCategoryRepository;

    final MemberDataRepository memberDataRepository;

    @Override
    public Response addCategory(String userId, ProductCategoryDTO productCategoryDTO) {
        Optional<MemberData> userIdRole = memberDataRepository.findByUserId(userId);
        if (userIdRole.get().getRole().equals("ADMIN")) {
            Optional<String> categoryName = productCategoryRepository.findByCategoryName(productCategoryDTO.getCategoryName());
            if (!categoryName.isPresent()) {
                ProductCategory productCategory = new ProductCategory();
                BeanUtils.copyProperties(productCategoryDTO, productCategory);
                productCategoryRepository.save(productCategory);
                return new Response("200", "data saved Successfully", new ArrayList<>());
            } else {
                return new Response("200", "category is already created", new ArrayList<>());
            }
        } else {
            return new Response("200", "data not saved successfully", new ArrayList<>());
        }
    }

    @Override
    public Response getCategoryList() {

        return new Response("200","data fetched successfully",productCategoryRepository.findAll());
    }
}
