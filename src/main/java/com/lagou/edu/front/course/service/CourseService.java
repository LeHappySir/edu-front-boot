package com.lagou.edu.front.course.service;

import com.lagou.edu.front.course.model.response.CoursePurchasedRecordRespVo;

import java.util.List;

public interface CourseService {
    List<CoursePurchasedRecordRespVo> getAllCoursePurchasedRecord(Integer userId);
}
