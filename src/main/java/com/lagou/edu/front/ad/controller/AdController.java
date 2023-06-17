package com.lagou.edu.front.ad.controller;

import com.lagou.edu.ad.client.dto.PromotionAdDTO;
import com.lagou.edu.ad.client.dto.PromotionSpaceDTO;
import com.lagou.edu.ad.client.remote.AdRemoteService;
import com.lagou.edu.common.response.ResponseDTO;
import com.lagou.edu.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AdController
 *
 * @author xianhongle
 * @data 2022/1/11 10:45 下午
 **/
@RestController
@RequestMapping("/ad")
@Api(description = "广告接口", tags = "广告接口")
public class AdController {

    @Autowired
    private AdRemoteService adRemoteService;

    @GetMapping("/space/getAllSpaces")
    @ApiOperation("获取所有广告位")
    public ResponseDTO<List<PromotionSpaceDTO>> getAllSpaces() {
        return ResponseDTO.success(adRemoteService.getAllSpaces());
    }

    @GetMapping("/getAdBySpaceKey")
    @ApiOperation("根据广告位ID获取广告列表信息")
    public ResponseDTO<List<PromotionSpaceDTO>> getAdBySpaceKey(@RequestParam("spaceKey") String[] spaceKey) {
        return ResponseDTO.success(adRemoteService.getAdBySpaceKey(spaceKey));
    }



    @ApiOperation(value = "获取所有的广告位及其对应的广告")
    @GetMapping("/getAllAds")
    public ResponseDTO<List<PromotionSpaceDTO>> getAllAds(@RequestParam("spaceKeys") String[] spaceKeys){
        List<PromotionSpaceDTO> allAds = adRemoteService.getAdBySpaceKey(spaceKeys);
        if(allAds.size() == 0){
            return ResponseDTO.response(ResultCode.SUCCESS, null);
        }
        return ResponseDTO.response(ResultCode.SUCCESS, allAds);
    }

    @ApiOperation(value = "获取所有的广告列表")
    @GetMapping("/getAdList")
    public ResponseDTO<List<PromotionAdDTO>> getAdList(){
        List<PromotionAdDTO> allAds = adRemoteService.getAllAds();
        if(allAds.size() == 0){
            return ResponseDTO.response(ResultCode.SUCCESS, null);
        }
        return ResponseDTO.response(ResultCode.SUCCESS, allAds);
    }


    @ApiOperation(value = "新增或者修改广告信息")
    @PostMapping("/saveOrUpdate")
    public ResponseDTO saveOrUpdateAd(@RequestBody PromotionAdDTO promotionAdDTO){
        return adRemoteService.saveOrUpdateAd(promotionAdDTO);
    }

    @ApiOperation(value = "根据Id获取广告信息")
    @GetMapping("/getAdById")
    public ResponseDTO<PromotionAdDTO> getAdById(@RequestParam("id") Integer id){
        return adRemoteService.getAdById(id);
    }


    @ApiOperation(value = "更新广告的状态")
    @GetMapping("/updateStatus")
    public ResponseDTO updateStatus(
            @ApiParam(name = "id",value = "广告Id") Integer id,
            @ApiParam(name = "status",value = "状态: 0-下架,1-上架") Integer status
    ){
        return adRemoteService.updateStatus(id,status);
    }

    @ApiOperation(value = "新增或者修改广告位")
    @PostMapping("/space/saveOrUpdate")
    public ResponseDTO saveOrUpdateSpace(@RequestBody PromotionSpaceDTO promotionSpaceDTO){
        return adRemoteService.saveOrUpdateSpace(promotionSpaceDTO);
    }


    @ApiOperation(value = "根据Id获取广告位")
    @GetMapping("/space/getSpaceById")
    public ResponseDTO<PromotionSpaceDTO> getSpaceById(@RequestParam("id") Integer id){
        PromotionSpaceDTO promotionSpaceDTO = adRemoteService.getSpaceById(id);
        if(promotionSpaceDTO == null){
            return ResponseDTO.response(ResultCode.SUCCESS,null);
        }
        return ResponseDTO.response(ResultCode.SUCCESS,promotionSpaceDTO);
    }
}
