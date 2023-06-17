package com.lagou.edu.front.course.model.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("课程播放历史")
public class CoursePlayHistoryReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("课程id")
    private Integer courseId;

    @ApiModelProperty("章节id")
    private Integer sectionId;

    @ApiModelProperty("课时id")
    private Integer lessonId;

    @ApiModelProperty("历史播放节点(s)")
    private Integer historyNode;

    @ApiModelProperty("最高历史播放节点")
    private Integer historyHighestNode;


}