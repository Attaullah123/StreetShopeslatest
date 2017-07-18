package rest;


import java.util.Map;

import bean.SignupResponce;
import bean.StoreListResponce;
import pojo.LoginResponce;
import pojo.ProductDetails;
import pojo.ProductList;
import pojo.SellerCat;
import pojo.SellerCategorie;
import pojo.SellertsList;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiInterface {

    @FormUrlEncoded
    @POST(WebConstants.METHOD_SERVICES)
    Call<StoreListResponce> getStoreList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(WebConstants.METHOD_SERVICES)
    Call<SignupResponce> getSignupInfo(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST(WebConstants.METHOD_SERVICES)
    Call<LoginResponce> getLoginInfo(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(WebConstants.METHOD_GET_SELLER_CATEGORY_LIST)
    Call<SellerCat> getSellerCategoryList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(WebConstants.METHOD_GET_SELLER_PRODUCT_LIST)
    Call<ProductList> getSellerProductList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(WebConstants.METHOD_GET_SELLER_EARCH_PRODUCT_LIST)
    Call<ProductList> getSellerSearchProductList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(WebConstants.METHOD_GET_SELLER_LIST)
    Call<SellertsList> getSellerList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(WebConstants.METHOD_GET_PRODUCT_DETAILS)
    Call<ProductDetails> getProductDetails(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(WebConstants.METHOD_GET_RELATED_ITEM_LIST)
    Call<ProductList> getSellerRelatedProductList(@FieldMap Map<String, String> map);





}
